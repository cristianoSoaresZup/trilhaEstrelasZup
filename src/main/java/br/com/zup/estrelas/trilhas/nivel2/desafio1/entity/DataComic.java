package br.com.zup.estrelas.trilhas.nivel2.desafio1.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "dataComic", cascade = CascadeType.ALL )
	private List<ComicResult> results;
}
