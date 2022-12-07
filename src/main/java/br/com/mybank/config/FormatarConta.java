package br.com.mybank.config;

import com.opencsv.bean.AbstractBeanField;

public class FormatarConta<T, I>  extends AbstractBeanField<T, I> {
    @Override
    protected Object convert(String s) {
        String formatacao = s.replaceAll("-", "");
        return formatacao;
    }
}
