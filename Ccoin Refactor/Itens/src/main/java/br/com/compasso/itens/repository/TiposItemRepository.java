package br.com.compasso.itens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.model.TiposItem;

public interface TiposItemRepository extends JpaRepository<TiposItem, Long> {

	Optional<TiposItem> findByDescricao(String descricao);
}
