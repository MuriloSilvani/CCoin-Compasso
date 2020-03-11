package br.com.compasso.resgatestransferencias.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.compasso.resgatestransferencias.form.Itens_resgatesForm;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;

@Entity
@Table(name = "itens_resgates")
public class Itens_resgates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_resgate", referencedColumnName = "id")
	private Resgates id_resgate;
	private Long id_item;
	private Long id_tipo_item;
	private int qtde;
	private Date data_agendamento;

	public Itens_resgates() {
	}

	public Itens_resgates(Itens_resgatesForm form, ResgatesRepository resgatesRepository) {
		
		this.id_resgate = resgatesRepository.findById(form.getId_resgate()).get();
		this.id_item = form.getId_item();
		this.id_tipo_item = form.getId_tipo_item();
		this.qtde = form.getQtde();
		this.data_agendamento = form.getData_agendamento();
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

	public Long getId_item() {
		return id_item;
	}

	public void setId_item(Long id_item) {
		this.id_item = id_item;
	}

	public Long getId_tipo_item() {
		return id_tipo_item;
	}

	public void setId_tipo_item(Long id_tipo_item) {
		this.id_tipo_item = id_tipo_item;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public Date getData_agendamento() {
		return data_agendamento;
	}

	public void setData_agendamento(Date data_agendamento) {
		this.data_agendamento = data_agendamento;
	}

}
