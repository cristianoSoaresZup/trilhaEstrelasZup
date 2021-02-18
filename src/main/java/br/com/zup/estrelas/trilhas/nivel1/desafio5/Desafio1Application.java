package br.com.zup.estrelas.trilhas.nivel1.desafio5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.zup.estrelas.trilhas.nivel1.desafio5.client.IMarvelComicClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@SpringBootApplication
public class Desafio1Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio1Application.class, args);
	}
	@Bean
	IMarvelComicClient client () {
		return  Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .logger(new Slf4jLogger(IMarvelComicClient.class))
				  .logLevel(Logger.Level.FULL)
				  .target(IMarvelComicClient.class, "http://gateway.marvel.com/v1/public");
	}
}
