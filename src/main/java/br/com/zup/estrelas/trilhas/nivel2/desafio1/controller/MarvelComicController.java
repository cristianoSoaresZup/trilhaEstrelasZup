package br.com.zup.estrelas.trilhas.nivel2.desafio1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.trilhas.nivel2.desafio1.entity.MarvelComic;
import br.com.zup.estrelas.trilhas.nivel2.desafio1.repository.MarvelComicRepository;
import br.com.zup.estrelas.trilhas.nivel2.desafio1.serviceImpl.MarvelComicService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/marvel")
@RestController
public class MarvelComicController {
	
	private MarvelComicService service;
	
	@Autowired
	private MarvelComicRepository marvelComicRepository;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping( value = "comics")
	public MarvelComic findAll () {
		
	MarvelComic comic = new MarvelComic();
		comic = service.findAll();
		marvelComicRepository.save(comic);
		return comic;
	}
	


}
