package br.com.mybank.service;

import br.com.mybank.model.Conta;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceitaService {


    private final static String ARQUIVO_ENTRADA =  "src/main/resources/documents/contas.csv";
    private final static String ARQUIVO_SAIDA =  "src/main/resources/documents/saida.csv";

    public static void lerContas() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        List<Conta> contas = new CsvToBeanBuilder(new FileReader(ARQUIVO_ENTRADA))
                .withSeparator(';')
                .withType(Conta.class)
                .build()
                .parse();


        processarContas(contas);
        retornarArquivoProcessado(contas);

    }


    private static void processarContas(List<Conta> contas){
        contas.forEach(conta -> {
            boolean resultado = atualizarConta(conta);
            conta.setProcessado(resultado);
        });
    }

    private static boolean atualizarConta(Conta conta){
        // Formato agencia: 0000
        if (conta.getAgencia() == null || conta.getAgencia().length() != 4) {
            return false;
        }

        // Formato conta: 000000
        if (conta.getNumeroConta()== null || conta.getNumeroConta().length() != 6) {
            return false;
        }

        // Tipos de status validos:
        List tipos = new ArrayList();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");

        if (conta.getStatus() == null || !tipos.contains(conta.getStatus())) {
            return false;
        }

        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 4000) + 1000;
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) {
            throw new RuntimeException("Error");
        }

        return true;
    }


    public static void retornarArquivoProcessado(List<Conta> contas) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Writer writer = new FileWriter(ARQUIVO_SAIDA);

        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(';')
                .withApplyQuotesToAll(false)
                .withOrderedResults(true)
                .build();

        beanToCsv.write(contas);
        writer.close();

    }
}
