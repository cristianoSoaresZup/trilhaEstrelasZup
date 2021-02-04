package br.com.zup.estrelas.trilhas.nivel1.desafio3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Desafio1Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio1Application.class, args);
	}

}
