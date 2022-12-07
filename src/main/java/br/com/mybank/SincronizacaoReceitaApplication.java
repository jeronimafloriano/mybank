package br.com.mybank;

import br.com.mybank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	@Autowired
	private ContaService contaService;

	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(){
		return args -> {
			contaService.processarContas();
		};
	}

}
