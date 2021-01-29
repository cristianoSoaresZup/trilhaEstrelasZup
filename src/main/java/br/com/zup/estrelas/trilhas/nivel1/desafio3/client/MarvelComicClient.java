package br.com.zup.estrelas.trilhas.nivel1.desafio3.client;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.resource.MarvelComicResource;
import feign.Feign;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public interface MarvelComicClient {
	
	@RequestLine("GET")
	MarvelComicResource findAll();
	
	MarvelComicResource marvelComic = Feign.builder()
			.client(new OkHttpClient())
			.encoder(new GsonEncoder())
			 .decoder(new GsonDecoder())
			 .logger(new Slf4jLogger(MarvelComicResource.class))
			 .target(MarvelComicResource.class, "http://gateway.marvel.com/v1/public/comics?"
			 		+ "ts=1611754724&apikey=4b34fbdb553d7334263478cff7ab94d1&hash="
			 		+ "f637faa1d70510bc43dbae409b76fe9b");

}
