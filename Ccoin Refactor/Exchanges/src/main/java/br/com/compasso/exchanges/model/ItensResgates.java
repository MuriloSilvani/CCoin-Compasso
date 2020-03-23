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
@Table(name = "itens_resgates")
public class ItensResgates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_resgate", referencedColumnName = "id")
	private Resgates idResgate;
	private Long idEstoque;
	private int qtde;
	private Date dataAgendamento;

	public ItensResgates() {
	}

	public ItensResgates(Resgates idResgate, Long idEstoque, int qtde) {

		this.idResgate = idResgate;
		this.idEstoque = idEstoque;
		this.qtde = qtde;
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

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

}
