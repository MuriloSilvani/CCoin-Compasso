package br.com.compasso.exchanges.dto.transferencia;

import java.util.Date;

public class TransferenciaDto {

	private int valor;
	private String idStatus;
	private Date dataStatus;
	private int saldoAtualizado;

	public TransferenciaDto() {
	}

	public TransferenciaDto(int valor, int idStatus, Date dataStatus, int saldoAtualizado) {

		this.valor = valor;
		if (idStatus == 0) {
			this.idStatus = "Em analise";
		} else if (idStatus == 1) {
			this.idStatus = "Aprovado";
		} else if (idStatus == 2) {
			this.idStatus = "A caminho";
		} else if (idStatus == 3) {
			this.idStatus = "Entregue";
		} else if (idStatus == 4) {
			this.idStatus = "Cancelado";
		} else if (idStatus == 5) {
			this.idStatus = "Reprovado";
		}
		this.dataStatus = dataStatus;
		this.saldoAtualizado = saldoAtualizado;
	}

	public int getValor() {
		return valor;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public Date getDataStatus() {
		return dataStatus;
	}

	public int getSaldoAtualizado() {
		return saldoAtualizado;
	}

}
