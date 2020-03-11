package br.com.compasso.itens.dto;

import br.com.compasso.itens.model.Estoque;

public class EstoqueDto {

	private Long id;
//	private Itens id_item;
//	private Tipos_itens id_tipo_item;
	private String item;
	private int qtde_reservado;
	private int qtde_disponivel;
	private float valor;

	public EstoqueDto() {

	}

	public EstoqueDto(Estoque estoque) {
		this.id = estoque.getId();
		this.item = estoque.getId_item().getDescricao() + " " + estoque.getId_tipo_item().getDescricao();
		this.qtde_reservado = estoque.getQtde_reservado();
		this.qtde_disponivel = estoque.getQtde_disponivel();
		this.valor = estoque.getValor();
	}

	public Long getId() {
		return id;
	}

	public String getItem() {
		return item;
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

}
