package br.com.zup.estrelas.trilhas.nivel1.desafio3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.MarvelComic;
import br.com.zup.estrelas.trilhas.nivel1.desafio3.serviceImpl.MarvelComicService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/marvel")
@RestController
public class MarvelComicController {
	
	private MarvelComicService service;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping( value = "comics")
	public MarvelComic findAll () {
		return service.findAll();
	}

}
