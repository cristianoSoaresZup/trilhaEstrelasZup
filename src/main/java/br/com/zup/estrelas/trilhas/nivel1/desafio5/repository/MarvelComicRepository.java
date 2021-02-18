package br.com.zup.estrelas.trilhas.nivel1.desafio5.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.trilhas.nivel1.desafio5.entity.MarvelComic;

@Repository
public interface MarvelComicRepository extends CrudRepository<MarvelComic, Long> {

}
