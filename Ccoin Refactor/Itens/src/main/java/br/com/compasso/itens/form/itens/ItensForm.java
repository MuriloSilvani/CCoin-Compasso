package br.com.compasso.itens.form.itens;

import java.util.Optional;

import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.repository.ItensRepository;

public class ItensForm {

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Itens atualizar(Long idItem, ItensRepository itensRepository) throws CustomNotFoundException {
		
		Optional<Itens> itemFind = itensRepository.findById(idItem);

		if (itemFind.isPresent()) {

			Itens item = itemFind.get();
			item.setDescricao(this.descricao);

			return item;
		}

		throw new CustomNotFoundException("Item '" + idItem + "' não encontrado");
	}
}
