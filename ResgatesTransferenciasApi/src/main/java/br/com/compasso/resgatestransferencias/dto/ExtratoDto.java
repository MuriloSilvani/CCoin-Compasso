package br.com.compasso.resgatestransferencias.dto;

import java.util.Date;

import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.model.Transferencias;

public class ExtratoDto {

	private Date data;
	private String tipo;
	private int valor;
	private String status;

	public ExtratoDto(Long id_usuario, Transferencias transferencia, Status_requerimentos status_requerimento) {

		if (status_requerimento.getId_status() == 0) {
			this.status = "Em analise";
		} else if (status_requerimento.getId_status() == 1) {
			this.status = "Aprovado";
		} else if (status_requerimento.getId_status() == 2) {
			this.status = "A caminho";
		} else if (status_requerimento.getId_status() == 3) {
			this.status = "Entregue";
		} else if (status_requerimento.getId_status() == 4) {
			this.status = "Cancelado";
		} else if (status_requerimento.getId_status() == 5) {
			this.status = "Reprovado";
		}

		if (transferencia.getUsuario_destino() == id_usuario) {
			this.tipo = "+";
		} else {
			this.tipo = "-";
		}

		this.data = status_requerimento.getData_status();
		this.valor = transferencia.getValor();
	}

	public Date getData() {
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
