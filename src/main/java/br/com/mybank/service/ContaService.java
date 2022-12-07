package br.com.mybank.service;

import br.com.mybank.config.ArquivosPropertiesConfig;
import br.com.mybank.exception.FileException;
import br.com.mybank.model.Conta;
import br.com.mybank.repository.ContaRepository;
import com.mongodb.client.result.UpdateResult;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ContaRepository repository;


    @Autowired
    private ArquivosPropertiesConfig arquivos;

    public void processarContas() {
        try {
            List<Conta> contas = new CsvToBeanBuilder(new FileReader(arquivos.getArquivoEntrada()))
                    .withSeparator(';')
                    .withType(Conta.class)
                    .build()
                    .parse();
            enviarContasReceita(contas);
            atualizarContasBanco(contas);
            retornarArquivoProcessado(contas);

        } catch (FileNotFoundException ex) {
            throw new FileException("Erro ao processar arquivo de entrada: " + ex);
        }



    }


    private void enviarContasReceita(List<Conta> contas) {
        contas.forEach(conta -> {
            boolean resultado = receitaService.atualizarConta(conta);
            conta.setProcessado(resultado);
        });
    }


    public void retornarArquivoProcessado(List<Conta> contas)  {
        try {
            Writer writer = new FileWriter(arquivos.getArquivoSaida());

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withApplyQuotesToAll(false)
                    .withOrderedResults(true)
                    .build();

            beanToCsv.write(contas);
            writer.close();

        } catch (Exception ex) {
            throw new FileException("Erro ao processar arquivo de retorno: " + ex);
        }

    }

    public void atualizarContasBanco(List<Conta> contas) {
        var contasEncontradas = repository.findAll();

        contas.stream()
                .filter(contasEncontradas::contains)
                .forEach(c -> {
                    Query query = new Query(Criteria.where("agencia").is(c.getAgencia())
                            .and("numero").is(c.getNumero()));
                    Update update = new Update();
                    update.set("satus", c.getStatus());
                    update.set("saldo", c.getSaldo());
                    update.set("processado", c.isProcessado());

                    UpdateResult result = mongoTemplate.updateFirst(query, update, Conta.class);
                });

        var contasASalvar = contas.stream().filter(conta -> !contasEncontradas.contains(conta)).collect(Collectors.toList());
        repository.saveAll(contasASalvar);

    }

    public List<Conta> buscarContasProcessadas() {
        return repository.processado(true);
    }

    public List<Conta> buscarContasPorSaldo(Double de, Double ate){
        return repository.obterContasPorSaldo(de, ate);
    }


}

