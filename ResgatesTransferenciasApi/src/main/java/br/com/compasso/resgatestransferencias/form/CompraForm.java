package br.com.compasso.resgatestransferencias.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CompraForm {

	@NotNull
	private Long id_estoque;
	@NotNull
	@Min(1)
	private int qtde;

	public Long getId_estoque() {
		return id_estoque;
	}

	public int getQtde() {
		return qtde;
	}

}
