package br.com.zup.estrelas.trilhas.nivel1.desafio2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.trilhas.nivel1.desafio2.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

}
