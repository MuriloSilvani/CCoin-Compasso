package br.com.compasso.usuarios.dto;

public class DepositoDto {

	private int saldo;
	private boolean aprovado;

	public DepositoDto() {

	}

	public DepositoDto(int saldo, boolean aprovado) {

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
