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
	private Long id_item;
	@NotNull
	private Long id_tipo_item;
	@NotNull
	@Min(0)
	private int qtde_reservado;
	@NotNull
	@Min(0)
	private int qtde_disponivel;
	@NotNull
	@Min(0)
	private float valor;

	public Long getId_item() {
		return id_item;
	}

	public Long getId_tipo_item() {
		return id_tipo_item;
	}

	public int getQtde_reservado() {
		return qtde_reservado;
	}

	public int getQtde_disponivel() {
		return qtde_disponivel;
	}

	public float getValor() {
		return valor;
	}
	
	public Estoque atualizar(Estoque estoque, ItemRepository itemRepository, TipoItemRepository tipoItemRepository) {

		Optional<Item> item = itemRepository.findById(this.id_item);
		Optional<TipoItem> tipo_item = tipoItemRepository.findById(this.id_tipo_item);
		
		estoque.setId_item(item.get());
		estoque.setId_tipo_item(tipo_item.get());
		estoque.setQtde_reservado(this.qtde_reservado);
		estoque.setQtde_disponivel(this.qtde_disponivel);
		estoque.setValor(this.valor);
		
		return estoque;
	}

}
