package br.com.zup.estrelas.trilhas.nivel1.desafio5.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.trilhas.nivel1.desafio5.entity.MarvelComic;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.repository.MarvelComicRepository;
import br.com.zup.estrelas.trilhas.nivel1.desafio5.serviceImpl.MarvelComicService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/marvel")
@RestController
@CrossOrigin
public class MarvelComicController {

	private MarvelComicService service;

	@Autowired
	private MarvelComicRepository marvelComicRepository;

	static final Logger LOGGER = Logger.getLogger(MarvelComicController.class);

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "comics")
	public MarvelComic findAll() {

		LOGGER.info("iniciando busca de dados na API da MARVEL");
		MarvelComic comic = new MarvelComic();
		comic = service.findAll();
		marvelComicRepository.save(comic);
		LOGGER.info("busca na API da MARVEL concluída.");
		return comic;

	}

}
