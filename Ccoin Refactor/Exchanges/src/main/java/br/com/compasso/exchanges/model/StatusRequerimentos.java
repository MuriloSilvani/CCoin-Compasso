package br.com.compasso.exchanges.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "status_requerimentos")
public class StatusRequerimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_resgate", referencedColumnName = "id")
	private Resgates idResgate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transferencia", referencedColumnName = "id")
	private Transferencias idTransferencia;
	private int idStatus;
	private Date dataStatus;

	public StatusRequerimentos() {

	}

	public StatusRequerimentos(Transferencias transferencia, int status) {

		this.idTransferencia = transferencia;
		this.idStatus = status;
		this.dataStatus = new Date();
	}

	public StatusRequerimentos(Resgates resgate, int status) {

		this.idResgate = resgate;
		this.idStatus = status;
		this.dataStatus = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resgates getIdResgate() {
		return idResgate;
	}

	public void setIdResgate(Resgates idResgate) {
		this.idResgate = idResgate;
	}

	public Transferencias getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(Transferencias idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public int getIdStatus() {

		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public Date getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Date dataStatus) {
		this.dataStatus = dataStatus;
	}

}
