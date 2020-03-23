package br.com.compasso.exchanges.dto.resgate;

import java.util.Date;

public class ResgateDto {

	private int valor;
	private String item;
	private int quantidade;
	private String status;
	private Date dataStatus;
	private int saldoAtualizado;

	public ResgateDto(int valor, String item, int quantidade, int idStatus, int saldoAtualizado) {

		this.valor = valor;
		this.item = item;
		this.quantidade = quantidade;
		if (idStatus == 0) {
			this.status = "Em analise";
		} else if (idStatus == 1) {
			this.status = "Aprovado";
		} else if (idStatus == 2) {
			this.status = "A caminho";
		} else if (idStatus == 3) {
			this.status = "Entregue";
		} else if (idStatus == 4) {
			this.status = "Cancelado";
		} else if (idStatus == 5) {
			this.status = "Reprovado";
		}
		this.dataStatus = new Date();
		this.saldoAtualizado = saldoAtualizado;
	}

	public ResgateDto() {

	}

	public int getValor() {
		return valor;
	}

	public String getItem() {
		return item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getStatus() {
		return status;
	}

	public Date getDataStatus() {
		return dataStatus;
	}

	public int getSaldoAtualizado() {
		return saldoAtualizado;
	}

}
