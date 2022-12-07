package br.com.mybank.config;

import com.opencsv.bean.AbstractBeanField;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class FormatarSaldo<T, I>  extends AbstractBeanField<T, I> {
    @Override
    protected Object convert(String s){
        double saldo = 0;
        try {
            var valor = NumberFormat.getInstance((new Locale("pt", "BR")))
                    .parse(s);
            saldo = valor.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao formatar saldo: " + e);
        }
        return saldo;

        }
}
