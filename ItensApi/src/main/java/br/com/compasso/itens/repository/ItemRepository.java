package br.com.compasso.itens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.form.ItemForm;
import br.com.compasso.itens.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findByDescricao(String descricao);

	Item save(ItemForm form);
}
