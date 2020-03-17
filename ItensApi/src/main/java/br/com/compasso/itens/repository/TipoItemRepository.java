package br.com.compasso.itens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.form.TipoItemForm;
import br.com.compasso.itens.model.TipoItem;

public interface TipoItemRepository extends JpaRepository<TipoItem, Long> {

	Optional<TipoItem> findByDescricao(String descricao);

	TipoItem save(TipoItemForm form);
}
