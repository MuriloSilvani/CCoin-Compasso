package br.com.compasso.resgatestransferencias.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransfereForm {

	@NotNull
	private Long usuario_destino;
	@NotNull
	@Min(0)
	private int valor;

	public Long getUsuario_destino() {
		return usuario_destino;
	}

	public int getValor() {
		return valor;
	}

}
