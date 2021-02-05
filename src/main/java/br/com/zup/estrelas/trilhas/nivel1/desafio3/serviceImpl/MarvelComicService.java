package br.com.zup.estrelas.trilhas.nivel1.desafio3.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.client.IMarvelComicClient;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.MarvelComic;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MarvelComicService {
	
	private static final String PUBLIC_KEY = "4b34fbdb553d7334263478cff7ab94d1";
	private static final String HASH = "f637faa1d70510bc43dbae409b76fe9b";
	private static final Integer TIMESTAMP = 1611754724;
	
	@Autowired
	IMarvelComicClient comicMarvel;
	
	public MarvelComic findAll() {
		
		return comicMarvel.getAll(TIMESTAMP, PUBLIC_KEY, HASH);
	}
	
}
