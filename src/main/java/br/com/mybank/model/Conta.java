package br.com.mybank.model;

import br.com.mybank.config.utils.FormatarConta;
import br.com.mybank.config.utils.FormatarSaldo;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;


public class Conta {

    @CsvBindByName(column = "agencia")
    private String agencia;
    @CsvCustomBindByName(column = "conta", converter = FormatarConta.class)
    private String numeroConta;

    @CsvBindByName(column = "status")
    private String status;
    @CsvBindByName(column = "resultado")
    private boolean processado;

    @CsvCustomBindByName(column = "saldo", converter = FormatarSaldo.class)
    private double saldo;

    public Conta(String agencia, String numeroConta, double saldo, String status){
        this.agencia = agencia;
        this.numeroConta = numeroConta;
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

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
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
                + " Número: " + this.numeroConta
                + " Saldo: " + this.saldo
                + " Status: " + this.status;
    }
}
