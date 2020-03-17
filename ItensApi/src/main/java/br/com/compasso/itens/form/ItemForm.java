package br.com.compasso.itens.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Item;
import br.com.compasso.itens.repository.ItemRepository;

public class ItemForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Item atualizar(Item itens, ItemRepository itemRepository) {

		itens.setDescricao(this.descricao);

		return itens;
	}
}
