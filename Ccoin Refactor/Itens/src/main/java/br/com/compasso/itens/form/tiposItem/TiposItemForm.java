package br.com.compasso.itens.form.tiposItem;

import java.util.Optional;

import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.model.TiposItem;
import br.com.compasso.itens.repository.TiposItemRepository;

public class TiposItemForm {

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public TiposItem atualizar(Long idTipoItem, TiposItemRepository tiposItemRepository)
			throws CustomNotFoundException {

		Optional<TiposItem> tipoItemFind = tiposItemRepository.findById(idTipoItem);

		if (tipoItemFind.isPresent()) {

			TiposItem tipoItem = tipoItemFind.get();
			tipoItem.setDescricao(this.descricao);

			return tipoItem;
		}

		throw new CustomNotFoundException("Tipo item '" + idTipoItem + "' não encontrado");
	}
}
