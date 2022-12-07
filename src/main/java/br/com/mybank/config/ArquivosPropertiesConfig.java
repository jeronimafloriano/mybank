package br.com.mybank.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ArquivosPropertiesConfig {

    private final String arquivoEntrada =  "src/main/resources/documents/contas.csv";
    private final String arquivoSaida =  "src/main/resources/documents/saida.csv";

    public String getArquivoEntrada() {
        return arquivoEntrada;
    }

    public String getArquivoSaida() {
        return arquivoSaida;
    }
}
