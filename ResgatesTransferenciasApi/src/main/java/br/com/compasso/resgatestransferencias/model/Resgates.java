package br.com.compasso.resgatestransferencias.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.compasso.resgatestransferencias.form.ResgatesForm;

@Entity
@Table(name = "resgates")
public class Resgates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long id_usuario;

	public Resgates() {
	}

	public Resgates(ResgatesForm form) {
		
		this.id_usuario = form.getId_usuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

}
