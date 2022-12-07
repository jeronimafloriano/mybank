package br.com.mybank.model;

import br.com.mybank.config.FormatarConta;
import br.com.mybank.config.FormatarSaldo;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "contas")
public class Conta {

    @CsvBindByName(column = "agencia")
    private String agencia;
    @CsvCustomBindByName(column = "conta", converter = FormatarConta.class)
    private String numero;

    @CsvBindByName(column = "status")
    private String status;
    @CsvBindByName(column = "resultado")
    private boolean processado;

    @CsvCustomBindByName(column = "saldo", converter = FormatarSaldo.class)
    private double saldo;

    public Conta(String agencia, String numeroConta, double saldo, String status){
        this.agencia = agencia;
        this.numero = numeroConta;
        this.saldo = saldo;
        this.status = status;
    }

    public Conta(){}

    public boolean isProcessado() {
        return processado;
    }

    public void setProcessado(boolean processado) {
        this.processado = processado;
    }


    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agencia: " + this.agencia
                + " Número: " + this.numero
                + " Saldo: " + this.saldo
                + " Status: " + this.status;
    }

    @Override
    public boolean equals(Object obj) {
        Conta outraConta = (Conta) obj;
        if (agencia.contains(outraConta.agencia) && numero.contains(outraConta.numero)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, numero);
    }
}
