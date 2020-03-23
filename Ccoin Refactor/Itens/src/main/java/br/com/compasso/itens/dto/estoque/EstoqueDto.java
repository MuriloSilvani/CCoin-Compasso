package br.com.compasso.itens.dto.estoque;

import br.com.compasso.itens.model.Estoque;

public class EstoqueDto {

	private Long id;
	private String item;
	private String tipoItem;
	private int quantidadeReservado;
	private int quantidadeDisponivel;
	private int valor;
	private boolean ativo;

	public EstoqueDto() {

	}

	public EstoqueDto(Estoque estoque) {

		this.id = estoque.getId();
		this.item = estoque.getItem().getDescricao();
		this.tipoItem = estoque.getTipoItem().getDescricao();
		this.quantidadeReservado = estoque.getQuantidadeReservado();
		this.quantidadeDisponivel = estoque.getQuantidadeDisponivel();
		this.valor = estoque.getValor();
		this.ativo = estoque.getAtivo();
	}

	public Long getId() {
		return id;
	}

	public String getItem() {
		return item;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public int getQuantidadeReservado() {
		return quantidadeReservado;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public int getValor() {
		return valor;
	}

	public boolean getAtivo() {
		return ativo;
	}
}
