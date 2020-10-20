package io.github.pessoas.nossobancodigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NossoBancoDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoBancoDigitalApplication.class, args);
	}

}
