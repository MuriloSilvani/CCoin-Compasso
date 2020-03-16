package br.com.compasso.usuarios.dto;

public class TransferenciaDto {

	private int saldo;
	private boolean aprovado;

	public TransferenciaDto() {

	}

	public TransferenciaDto(int saldo, boolean aprovado) {

		this.saldo = saldo;
		this.aprovado = aprovado;
	}

	public int getSaldo() {
		return saldo;
	}

	public boolean isAprovado() {
		return aprovado;
	}
}
