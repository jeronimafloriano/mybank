package br.com.mybank.service;

import br.com.mybank.model.Conta;
import br.com.mybank.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository repository;

    @InjectMocks
    private ContaService contaService;


    @Test
    void buscarContasProcessadas() {
        //given
        Conta conta = new Conta("1111", "123456", 123.45, "I");
        Conta conta2 = new Conta("2222", "654321", 654.32, "A");
        conta.setProcessado(true);
        conta2.setProcessado(true);

        List<Conta> contas = new ArrayList<>();
        contas.add(conta);
        contas.add(conta2);

        given(repository.processado(true)).willReturn(contas);

        //when
        var result = contaService.buscarContasProcessadas();

        //then
        then(repository).should().processado(true);
        assertThat(result).isEqualTo(contas);
        assertThat(result).hasSize(2);
    }

    @Test
    void buscarContasPorSaldo() {
        //given
        Conta conta = new Conta("1111", "123456", 123.45, "I");
        Conta conta2 = new Conta("2222", "654321", 654.32, "A");
        List<Conta> contas = new ArrayList<>();
        contas.add(conta);
        contas.add(conta2);

        given(repository.obterContasPorSaldo(100.45, 700.00)).willReturn(contas);

        //when
        var result = contaService.buscarContasPorSaldo(100.45, 700.00);

        //then
        then(repository).should().obterContasPorSaldo(100.45, 700.00);
        assertThat(result).isEqualTo(contas);
        assertThat(result).hasSize(2);
    }
}