package br.com.compasso.itens.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.TipoItem;
import br.com.compasso.itens.repository.TipoItemRepository;

public class TipoItemForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public TipoItem atualizar(TipoItem tipo_item, TipoItemRepository tipoItemRepository) {
		
		tipo_item.setDescricao(this.descricao);
		
		return tipo_item;
	}

}
