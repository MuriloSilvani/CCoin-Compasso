package br.com.compasso.exchanges.form.transferencia;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferenciaForm {

	@NotNull
	@Min(1)
	private int valor;
	@NotNull
	private Long idUsuarioDestino;

	public int getValor() {
		return valor;
	}

	public Long getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

}
