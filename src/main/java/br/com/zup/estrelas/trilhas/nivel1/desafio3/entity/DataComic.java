package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataComic {

	private Integer total;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 

	@OneToOne
	@JoinColumn(name = "marvel_comic", foreignKey = @ForeignKey(name = "data_marvel_comic_fk"))
	private MarvelComic marvelComic;

//	private List<ComicResult> results;
}
