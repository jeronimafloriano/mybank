package br.com.mybank.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class ContaTest {

    @Test
    void duasContasComMesmaAgenciaEMesmoNumeroDevemSerIguais() {
        Conta conta = new Conta("1111", "123456", 123.45, "A");
        Conta conta2 = new Conta("1111", "123456", 456.78, "B");

        assertThat(conta).isEqualTo(conta2);
    }

    @Test
    void duasContasComMesmaAgenciaENumerosDiferentesDevemSerDiferentes() {
        Conta conta = new Conta("1111", "123456", 123.45, "A");
        Conta conta2 = new Conta("1111", "654321", 456.78, "B");

        assertThat(conta).isNotEqualTo(conta2);
    }

    @Test
    void duasContasComAgenciaDiferentesENumerosIguaisDevemSerDiferentes() {
        Conta conta = new Conta("1111", "123456", 123.45, "A");
        Conta conta2 = new Conta("2222", "123456", 456.78, "B");

        assertThat(conta).isNotEqualTo(conta2);
    }

    @Test
    void duasContasComAgenciaENumerosDiferentesDevemSerDiferentes() {
        Conta conta = new Conta("1111", "123456", 123.45, "A");
        Conta conta2 = new Conta("2222", "654321", 456.78, "B");

        assertThat(conta).isNotEqualTo(conta2);
    }
}