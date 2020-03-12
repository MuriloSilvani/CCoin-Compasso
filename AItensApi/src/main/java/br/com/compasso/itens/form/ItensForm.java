package br.com.compasso.itens.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.repository.ItensRepository;

public class ItensForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Itens atualizar(Itens itens, ItensRepository itensRepository) {

		itens.setDescricao(this.descricao);

		return itens;
	}
}
