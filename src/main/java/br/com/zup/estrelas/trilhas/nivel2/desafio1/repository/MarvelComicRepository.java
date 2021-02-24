package br.com.zup.estrelas.trilhas.nivel2.desafio1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.trilhas.nivel2.desafio1.entity.MarvelComic;

@Repository
public interface MarvelComicRepository extends CrudRepository<MarvelComic, Long> {

}
