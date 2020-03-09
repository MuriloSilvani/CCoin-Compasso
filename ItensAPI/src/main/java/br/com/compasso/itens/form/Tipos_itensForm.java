package br.com.compasso.itens.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Tipos_itens;
import br.com.compasso.itens.repository.Tipos_itensRepository;

public class Tipos_itensForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Tipos_itens atualizar(Tipos_itens tipo_item, Tipos_itensRepository tipos_itensRepository) {
		
		tipo_item.setDescricao(this.descricao);
		
		return tipo_item;
	}

}
