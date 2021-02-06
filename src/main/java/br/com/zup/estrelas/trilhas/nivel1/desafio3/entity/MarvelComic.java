package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarvelComic {
	
	private String copyright;
	
	@Column(name = "attribution_text")
	private String attributionText;
	
	@Column(name = "attribution_html")
	private String attributionHTML;
	
	private DataComic data;
	
}
