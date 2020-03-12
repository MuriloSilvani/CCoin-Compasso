package br.com.compasso.itens.dto;

import br.com.compasso.itens.model.Estoque;

public class CatalogoDto {

	private Long id;
	private String item;
	private int qtde_disponivel;
	private float valor;
	private boolean ativo;

	public CatalogoDto() {
	}

	public CatalogoDto(Estoque item) {
		this.id = item.getId();
		this.item = item.getId_item().getDescricao() + " " + item.getId_tipo_item().getDescricao();
		this.qtde_disponivel = item.getQtde_disponivel() - item.getQtde_reservado();
		this.valor = item.getValor();
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public String getItem() {
		return item;
	}

	public int getQtde_disponivel() {
		return qtde_disponivel;
	}

	public float getValor() {
		return valor;
	}

	public boolean getAtivo() {
		return ativo;
	}

}
