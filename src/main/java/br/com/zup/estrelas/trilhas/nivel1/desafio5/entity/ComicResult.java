package br.com.zup.estrelas.trilhas.nivel1.desafio5.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class ComicResult {

	@Id
	private Long id;
	private String title;
	private String isbn;
	private String upc;
	
	@OneToOne(mappedBy = "comicResult", cascade = CascadeType.ALL)
	private ComicSerie series;
	
	@OneToOne(mappedBy = "comicResult", cascade = CascadeType.ALL)
	private ComicThumbs thumbnail;
	
	@ManyToOne
	@JoinColumn(name = "data_comic", foreignKey = @ForeignKey(name = "comic_result_fk"))
	private DataComic dataComic;
	
}
