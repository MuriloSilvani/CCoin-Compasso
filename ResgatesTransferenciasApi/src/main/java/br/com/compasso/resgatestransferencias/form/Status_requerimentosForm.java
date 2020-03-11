package br.com.compasso.resgatestransferencias.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.compasso.resgatestransferencias.model.Resgates;
import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.model.Transferencias;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;
import br.com.compasso.resgatestransferencias.repository.TransferenciasRepository;

public class Status_requerimentosForm {

	private Long id_resgate;
	private Long id_transferencia;
	@NotNull
	private int id_status;

	public Long getId_resgate() {
		return id_resgate;
	}

	public Long getId_transferencia() {
		return id_transferencia;
	}

	public int getId_status() {
		return id_status;
	}

	public Status_requerimentos atualizar(Status_requerimentos status_requerimento, ResgatesRepository resgatesRepository, TransferenciasRepository transferenciasRepository) {

		Optional<Resgates> resgate = resgatesRepository.findById(this.id_resgate);
		if(resgate.isPresent()) {			
			status_requerimento.setId_resgate(resgate.get());
		}
		Optional<Transferencias> transferencia = transferenciasRepository.findById(this.id_transferencia);
		if(transferencia.isPresent()) {
			status_requerimento.setId_transferencia(transferencia.get());
		}
		status_requerimento.setId_status(this.id_status);
		
		return status_requerimento;
	}

}
