package br.com.compasso.resgatestransferencias.config.service.form;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.resgatestransferencias.form.CompraForm;

public class HttpCompraForm {

	@NotNull
	@Min(1)
	private int qtde;

	public HttpCompraForm(@Valid CompraForm form) {

		this.qtde = form.getQtde();
	}

	public int getQtde() {
		return qtde;
	}

}
