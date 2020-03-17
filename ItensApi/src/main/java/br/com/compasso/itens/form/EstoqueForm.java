package br.com.compasso.itens.form;

import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Item;
import br.com.compasso.itens.model.TipoItem;
import br.com.compasso.itens.repository.ItemRepository;
import br.com.compasso.itens.repository.TipoItemRepository;

public class EstoqueForm {

	@NotNull
	private Long idItem;
	@NotNull
	private Long idTipoItem;
	@NotNull
	@Min(0)
	private int quantidadeReservado;
	@NotNull
	@Min(0)
	private int quantidadeDisponivel;
	@NotNull
	@Min(0)
	private float valor;

	public Long getIdItem() {
		return idItem;
	}

	public Long getIdTipoItem() {
		return idTipoItem;
	}

	public int getQuantidadeReservado() {
		return quantidadeReservado;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public float getValor() {
		return valor;
	}
	
	public Estoque atualizar(Estoque estoque, ItemRepository itemRepository, TipoItemRepository tipoItemRepository) {

		// TODO: warning aqui por usar .get() mas não .isPresent()

		Optional<Item> item = itemRepository.findById(this.idItem);
		Optional<TipoItem> tipoItem = tipoItemRepository.findById(this.idTipoItem);
		
		estoque.setItem(item.get());
		estoque.setTipoItem(tipoItem.get());
		estoque.setQuantidadeReservado(this.quantidadeReservado);
		estoque.setQuantidadeDisponivel(this.quantidadeDisponivel);
		estoque.setValor(this.valor);
		
		return estoque;
	}

}
