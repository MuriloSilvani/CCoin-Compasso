package br.com.compasso.resgatestransferencias.form;

import javax.validation.constraints.NotNull;

import br.com.compasso.resgatestransferencias.model.Resgates;

public class ResgatesForm {

	@NotNull
	private Long id_usuario;

	public ResgatesForm() {
	}

	public ResgatesForm(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public Long getId_usuario() {
		return id_usuario;
	}

	public Resgates atualizar(Resgates resgates) {
		
		resgates.setId_usuario(id_usuario);
		
		return resgates;
	}

}
