package br.com.compasso.resgatestransferencias.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.resgatestransferencias.model.Itens_resgates;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;

public class Itens_resgatesForm {

	@NotNull
	private Long id_resgate;
	@NotNull
	private Long id_item;
	@NotNull
	private Long id_tipo_item;
	@NotNull @Min(0)
	private int qtde;
	@NotNull
	private Date data_agendamento;

	public Long getId_resgate() {
		return id_resgate;
	}

	public Long getId_item() {
		return id_item;
	}

	public Long getId_tipo_item() {
		return id_tipo_item;
	}

	public int getQtde() {
		return qtde;
	}

	public Date getData_agendamento() {
		return data_agendamento;
	}

	public Itens_resgates atualizar(Itens_resgates itens_resgates, ResgatesRepository resgatesRepository) {

		itens_resgates.setId_resgate(resgatesRepository.findById(this.id_resgate).get());
		itens_resgates.setId_item(this.id_item);
		itens_resgates.setId_tipo_item(this.id_tipo_item);
		itens_resgates.setQtde(this.qtde);
		itens_resgates.setData_agendamento(this.data_agendamento);
		
		return itens_resgates;
	}

}
