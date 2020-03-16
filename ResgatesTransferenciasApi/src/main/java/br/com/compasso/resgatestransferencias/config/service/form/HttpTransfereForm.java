package br.com.compasso.resgatestransferencias.config.service.form;

public class HttpTransfereForm {

	private int saldo;
	private Long usuario_destino;
	private boolean aprovado;

	public int getSaldo() {
		return saldo;
	}

	public Long getUsuario_destino() {
		return usuario_destino;
	}

	public boolean isAprovado() {
		return aprovado;
	}

}
