package br.com.mybank.service;

import br.com.mybank.model.Conta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReceitaServiceTest {

    @Mock
    ReceitaService service;

    @Test
    void atualizarContaComAgenciaInvalidaDeveRetornarFalse() {
        Conta conta = new Conta("11112222", "123456", 123.45, "A");
        boolean resultado = service.atualizarConta(conta);

        assertThat(resultado).isFalse();
    }

    @Test
    void atualizarContaComNumeroInvalidoDeveRetornarFalse() {
        Conta conta = new Conta("1111", "123", 123.45, "A");
        boolean resultado = service.atualizarConta(conta);

        assertThat(resultado).isFalse();
    }

    @Test
    void atualizarContaComStatusInvalidoDeveRetornarFalse() {
        Conta conta = new Conta("1111", "123456", 123.45, "Y");
        boolean resultado = service.atualizarConta(conta);

        assertThat(resultado).isFalse();
    }

    @Test
    void atualizarContaComDadosValidosDeveRetornarTrue() {
        Conta conta = new Conta("1111", "123456", 123.45, "I");
        boolean resultado = service.atualizarConta(conta);

        assertThat(resultado).isTrue();
    }
}