package br.com.compasso.exchanges.form.resgate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ResgateForm {

	@NotNull
	private Long idEstoque;
	@NotNull
	@Min(1)
	private int quantidade;

	public Long getIdEstoque() {
		return idEstoque;
	}

	public int getQuantidade() {
		return quantidade;
	}

}
