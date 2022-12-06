package br.com.mybank.config.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class FormatarSaldo<T, I>  extends AbstractBeanField<T, I> {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        double saldo = 0;
        try {
            var valor = NumberFormat.getInstance((new Locale("pt", "BR")))
                    .parse(s);
            saldo = valor.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return saldo;

        }
}
