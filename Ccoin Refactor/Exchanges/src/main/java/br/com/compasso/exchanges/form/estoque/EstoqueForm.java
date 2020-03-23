package br.com.compasso.exchanges.form.estoque;

public class EstoqueForm {

	private Long id;
	private String item;
	private String tipoItem;
	private int quantidadeReservado;
	private int quantidadeDisponivel;
	private int valor;
	private boolean ativo;

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

	public boolean isAtivo() {
		return ativo;
	}

}
