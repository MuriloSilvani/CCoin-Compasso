package br.com.compasso.itens.dto.catalogo;

import br.com.compasso.itens.model.Estoque;

public class CatalogoDto {

	private Long id;
	private String item;
	private int quantidadeDisponivel;
	private int valor;

	public CatalogoDto() {
	}

	public CatalogoDto(Estoque item) {
		this.id = item.getId();
		this.item = item.getItem().getDescricao() + " " + item.getTipoItem().getDescricao();
		this.quantidadeDisponivel = item.getQuantidadeDisponivel() - item.getQuantidadeReservado();
		this.valor = item.getValor();
	}

	public Long getId() {
		return id;
	}

	public String getItem() {
		return item;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public int getValor() {
		return valor;
	}
}
