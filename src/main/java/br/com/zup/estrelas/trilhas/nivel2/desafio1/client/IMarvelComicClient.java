package br.com.zup.estrelas.trilhas.nivel2.desafio1.client;

import br.com.zup.estrelas.trilhas.nivel2.desafio1.entity.MarvelComic;
import feign.Param;
import feign.RequestLine;

public interface IMarvelComicClient {
	
	@RequestLine("GET /comics?ts={ts}&apikey={apikey}&hash={hash}")
	public MarvelComic getAll (@Param( "ts") Integer timeStamp,
			@Param("apikey") String publicKey,
			@Param("hash") String hashMD5);
	
}
