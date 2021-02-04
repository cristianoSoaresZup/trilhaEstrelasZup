package br.com.zup.estrelas.trilhas.nivel1.desafio3.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.MarvelComic;

@FeignClient(name = "MarvelComic", url = "http://gateway.marvel.com/v1/public")
public interface IMarvelComicClient {
	
	@GetMapping("/comics")
	public MarvelComic getAll (@RequestParam(value = "ts") Integer timeStamp,
			@RequestParam(value = "apikey") String publicKey,
			@RequestParam(value = "hash") String hashMD5);
	
}
