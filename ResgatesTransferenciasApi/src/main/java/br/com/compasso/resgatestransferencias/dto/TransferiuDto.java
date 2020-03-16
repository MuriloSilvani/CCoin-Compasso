package br.com.compasso.resgatestransferencias.dto;

import br.com.compasso.resgatestransferencias.config.service.UsuariosService;
import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.model.Transferencias;

public class TransferiuDto {
	
	private int saldo;
	private int valor_transferido;
	private String status;

	public TransferiuDto() {
	}

	public TransferiuDto(Transferencias transferencia, Status_requerimentos status_requerimento, UsuariosService usuariosService) {

		this.saldo = usuariosService.getSaldo(transferencia.getUsuario_origem());
		this.valor_transferido = transferencia.getValor();
		this.status = status_requerimento.getId_status();
	}

	public int getSaldo() {
		return saldo;
	}

	public int getValor() {
		return valor_transferido;
	}

	public String getStatus() {
		return status;
	}

}
