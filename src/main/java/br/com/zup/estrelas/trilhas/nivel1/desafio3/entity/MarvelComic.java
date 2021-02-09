package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class MarvelComic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column(nullable = false)
	private String copyright;
	
	@Column(name = "attribution_text", nullable = false)
	private String attributionText;
	
	@Column(name = "attribution_html", nullable = false)
	private String attributionHTML;
	
	@JoinColumn(name = "data_comic")
	@OneToOne(mappedBy = "marvelComic", cascade = CascadeType.ALL )
	private DataComic data;
	
}
