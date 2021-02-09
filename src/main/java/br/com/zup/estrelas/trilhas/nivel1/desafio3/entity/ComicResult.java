package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComicResult {

	private Integer id;
	private String title;
	private String isbn;
	private String upc;
	private ComicSerie series;
	private ComicThumbs thumbnail;
	
}
