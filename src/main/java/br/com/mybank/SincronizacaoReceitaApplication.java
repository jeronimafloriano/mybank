package br.com.mybank;

import br.com.mybank.service.ReceitaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(){
		return args -> {
			ReceitaService.lerContas();
		};
	}

}
