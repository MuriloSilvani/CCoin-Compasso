package br.com.compasso.itens.form.estoque;

import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.model.TiposItem;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.TiposItemRepository;

public class EstoqueForm {

	@NotNull
	private Long idItem;
	@NotNull
	private Long idTipoItem;
	@NotNull
	@Min(1)
	private int quantidadeDisponivel;
	@NotNull
	@Min(1)
	private int quantidadeReservado;
	@NotNull
	@Min(1)
	private int valor;

	public Long getIdItem() {
		return idItem;
	}

	public Long getIdTipoItem() {
		return idTipoItem;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public int getQuantidadeReservado() {
		return quantidadeReservado;
	}

	public int getValor() {
		return valor;
	}

	public Estoque atualizar(Long idEstoque, EstoqueRepository estoqueRepository, ItensRepository itensRepository, TiposItemRepository tiposItemRepository) throws CustomNotFoundException {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(idEstoque);

		if (estoqueFind.isPresent()) {
			Estoque estoque = estoqueFind.get();

			Optional<Itens> item = itensRepository.findById(this.idItem);
			if (item.isPresent()) {
				estoque.setItem(item.get());
			} else {
				throw new CustomNotFoundException("Item '" + this.idItem + "' não encontrado");
			}
			Optional<TiposItem> tipoItem = tiposItemRepository.findById(this.idTipoItem);
			if (tipoItem.isPresent()) {
				estoque.setTipoItem(tipoItem.get());
			} else {
				throw new CustomNotFoundException("TipoItem '" + this.idTipoItem + "' não encontrado");
			}

			estoque.setQuantidadeReservado(this.quantidadeReservado);
			estoque.setQuantidadeDisponivel(this.quantidadeDisponivel);
			estoque.setValor(this.valor);

			return estoque;
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}

}
