package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@Entity
public class MarvelComic {
	
	@Column(nullable = false, unique = true)
	@Id
	private Long id;
	private String title;
	
	@Column(name = "variant_description")
	private String variantDescription;
	private String upc;
	private String ean;
	private String format;
	
	@Column(name = "page_count")
	private String pageCount;
	
	@Column(name = "resource_uri")
	private String resourceURI;
	
	@OneToMany(mappedBy = "marvelComic")
	private List<Serie> series;
	
}
