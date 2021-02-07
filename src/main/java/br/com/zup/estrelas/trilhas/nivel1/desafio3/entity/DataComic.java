package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataComic {
	
	private Integer total;

	private List<ComicResult> results;
}
