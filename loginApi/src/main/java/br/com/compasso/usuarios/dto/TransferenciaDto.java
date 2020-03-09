package br.com.compasso.usuarios.dto;

public class TransferenciaDto {

	private int saldo;
	private Long usuario_destino;
	private boolean aprovado;

	public TransferenciaDto() {

	}

	public TransferenciaDto(int saldo, Long usuario_destino, boolean aprovado) {

		this.saldo = saldo;
		this.usuario_destino = usuario_destino;
		this.aprovado = aprovado;
	}

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
