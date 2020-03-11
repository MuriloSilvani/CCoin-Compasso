package br.com.compasso.resgatestransferencias.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.resgatestransferencias.model.Transferencias;

public class TransferenciasForm {

	@NotNull
	private Long usuario_origem;
	@NotNull
	private Long usuario_destino;
	@NotNull @Min(0)
	private int valor;

	public Long getUsuario_origem() {
		return usuario_origem;
	}

	public Long getUsuario_destino() {
		return usuario_destino;
	}

	public int getValor() {
		return valor;
	}

	public Transferencias atualizar(Transferencias transferencia) {
		
		transferencia.setUsuario_destino(this.usuario_destino);
		transferencia.setUsuario_origem(this.usuario_origem);
		transferencia.setValor(this.valor);
		
		return transferencia;
	}

}
