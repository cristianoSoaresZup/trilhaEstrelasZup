package br.com.zup.estrelas.trilhas.nivel2.desafio1.controller;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.estrelas.trilhas.nivel2.desafio1.repository.MarvelComicRepository;
import br.com.zup.estrelas.trilhas.nivel2.desafio1.serviceImpl.MarvelComicService;

@RunWith(MockitoJUnitRunner.class)
public class MarvelComicControllerTest {
	
	@Mock
	MarvelComicRepository comicRepository;
	
	@InjectMocks
	MarvelComicService comicService;

}
