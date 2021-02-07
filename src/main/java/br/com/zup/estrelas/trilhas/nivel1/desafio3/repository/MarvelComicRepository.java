package br.com.zup.estrelas.trilhas.nivel1.desafio3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.trilhas.nivel1.desafio3.entity.MarvelComic;

@Repository
public interface MarvelComicRepository extends CrudRepository<MarvelComic, Long> {

}
