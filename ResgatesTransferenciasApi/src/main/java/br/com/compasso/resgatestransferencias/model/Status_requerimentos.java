package br.com.compasso.resgatestransferencias.model;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.compasso.resgatestransferencias.form.Status_requerimentosForm;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;
import br.com.compasso.resgatestransferencias.repository.TransferenciasRepository;

@Entity
@Table(name = "status_requerimentos")
public class Status_requerimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_resgate", referencedColumnName = "id")
	private Resgates id_resgate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transferencia", referencedColumnName = "id")
	private Transferencias id_transferencia;
	private int id_status;
	private Date data_status;

	public Status_requerimentos() {

	}

	public Status_requerimentos(Status_requerimentosForm form, ResgatesRepository resgatesRepository,
			TransferenciasRepository transferenciasRepository) {

		Optional<Resgates> resgate = resgatesRepository.findById(form.getId_resgate());
		if (resgate.isPresent()) {
			this.id_resgate = resgate.get();
		}
		Optional<Transferencias> transferencia = transferenciasRepository.findById(form.getId_transferencia());
		if (transferencia.isPresent()) {
			this.id_transferencia = transferencia.get();
		}
		this.id_status = form.getId_status();
		this.data_status = new Date();
	}

	public Status_requerimentos(Status_requerimentosForm form, TransferenciasRepository transferenciasRepository) {

		this.id_resgate = null;
		Optional<Transferencias> transferencia = transferenciasRepository.findById(form.getId_transferencia());
		if (transferencia.isPresent()) {
			this.id_transferencia = transferencia.get();
		}
		this.id_status = form.getId_status();
		this.data_status = new Date();
	}

	public Status_requerimentos(Status_requerimentosForm form, ResgatesRepository resgatesRepository) {

		Optional<Resgates> resgate = resgatesRepository.findById(form.getId_resgate());
		if (resgate.isPresent()) {
			this.id_resgate = resgate.get();
		}
		this.id_transferencia = null;
		this.id_status = form.getId_status();
		this.data_status = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resgates getId_resgate() {
		return id_resgate;
	}

	public void setId_resgate(Resgates id_resgate) {
		this.id_resgate = id_resgate;
	}

	public Transferencias getId_transferencia() {
		return id_transferencia;
	}

	public void setId_transferencia(Transferencias id_transferencia) {
		this.id_transferencia = id_transferencia;
	}

	public String getId_status() {
		
		if (this.id_status == 0) {
			return "Em analise";
		} else if (this.id_status == 1) {
			return "Aprovado";
		} else if (this.id_status == 2) {
			return "A caminho";
		} else if (this.id_status == 3) {
			return "Entregue";
		} else if (this.id_status == 4) {
			return "Cancelado";
		} else if (this.id_status == 5) {
			return "Reprovado";
		}
		return "Erro";
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public Date getData_status() {
		return data_status;
	}

	public void setData_status(Date data_status) {
		this.data_status = data_status;
	}

}
