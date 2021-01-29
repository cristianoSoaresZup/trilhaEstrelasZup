package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import javax.persistence.Entity;

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

	private String resourceURI;
	private String name;

}
