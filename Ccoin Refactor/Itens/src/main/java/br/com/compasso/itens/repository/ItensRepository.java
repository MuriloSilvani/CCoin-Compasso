package br.com.compasso.itens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.model.Itens;

public interface ItensRepository extends JpaRepository<Itens, Long> {

	Optional<Itens> findByDescricao(String descricao);
}
