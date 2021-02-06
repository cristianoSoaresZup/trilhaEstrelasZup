package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataComic {
	
	private Integer total;

	private List<ComicResult> results;
}
