package br.com.compasso.itens.form;

import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.model.Tipos_itens;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.Tipos_itensRepository;

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
	
	public Estoque atualizar(Estoque estoque, ItensRepository itensRepository, Tipos_itensRepository tipos_itensRepository) {

		Optional<Itens> item = itensRepository.findById(this.id_item);
		Optional<Tipos_itens> tipo_item = tipos_itensRepository.findById(this.id_tipo_item);
		
		estoque.setId_item(item.get());
		estoque.setId_tipo_item(tipo_item.get());
		estoque.setQtde_reservado(this.qtde_reservado);
		estoque.setQtde_disponivel(this.qtde_disponivel);
		estoque.setValor(this.valor);
		
		return estoque;
	}

}
