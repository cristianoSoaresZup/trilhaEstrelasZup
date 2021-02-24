package br.com.zup.estrelas.trilhas.nivel2.desafio1;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.zup.estrelas.trilhas.nivel2.desafio1.client.IMarvelComicClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@SpringBootApplication
public class Desafio1Application {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Desafio1Application.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		LOGGER.debug("Log4j appender configuration is successful !!");
		
		SpringApplication.run(Desafio1Application.class, args);
		LOGGER.info("Api iniciada e pronta para receber novos cadastros e consultas.");
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
