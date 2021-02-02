package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Serie {

	@Id
	@Column(name = "id_serie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSerie;
	
	@ManyToOne
	@JoinColumn(name = "marvel_comic", foreignKey = @ForeignKey(name = "series_marvel_comic_fk"))
	private MarvelComic marvelComic;
	
	@Column(name = "resource_uri")
	private String resourceURI;
	private String name;

}
