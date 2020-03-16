package br.com.compasso.resgatestransferencias.config.service.form;

public class HttpEstoqueForm {

	private Long id;
	private String item;
	private int qtde_disponivel;
	private float valor;
	private boolean ativo;

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

	public boolean isAtivo() {
		return ativo;
	}

}
