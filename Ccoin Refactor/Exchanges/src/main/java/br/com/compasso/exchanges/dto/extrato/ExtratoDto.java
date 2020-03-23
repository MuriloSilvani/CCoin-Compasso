package br.com.compasso.exchanges.dto.extrato;

import java.text.SimpleDateFormat;

import br.com.compasso.exchanges.model.StatusRequerimentos;
import br.com.compasso.exchanges.model.Transferencias;

public class ExtratoDto {

	private Long id;
	private String data;
	private String tipo;
	private int valor;
	private String status;

	public ExtratoDto(Long idUsuario, Transferencias transferencia, StatusRequerimentos statusRequerimento) {

		this.id = transferencia.getId();
		if (statusRequerimento.getIdStatus() == 0) {
			this.status = "Em analise";
		} else if (statusRequerimento.getIdStatus() == 1) {
			this.status = "Aprovado";
		} else if (statusRequerimento.getIdStatus() == 2) {
			this.status = "A caminho";
		} else if (statusRequerimento.getIdStatus() == 3) {
			this.status = "Entregue";
		} else if (statusRequerimento.getIdStatus() == 4) {
			this.status = "Cancelado";
		} else if (statusRequerimento.getIdStatus() == 5) {
			this.status = "Reprovado";
		}

		if (transferencia.getUsuarioDestino() == idUsuario) {
			this.tipo = "+";
		} else {
			this.tipo = "-";
		}

		this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(statusRequerimento.getDataStatus());
		this.valor = transferencia.getValor();
	}

	public Long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public String getTipo() {
		return tipo;
	}

	public int getValor() {
		return valor;
	}

	public String getStatus() {
		return status;
	}

}
