package br.com.zup.estrelas.trilhas.nivel1.desafio5.entity;

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
public class ComicThumbs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String path;
	private String extension;
	
	@OneToOne
	@JoinColumn(name = "comic_result", foreignKey = @ForeignKey(name = "result_thumbnail_fk"))
	private ComicResult comicResult;

}
