package br.com.compasso.resgatestransferencias.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import br.com.compasso.resgatestransferencias.form.TransferenciasForm;

@Entity
@Table(name = "transferencias")
public class Transferencias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long usuario_origem;
	private Long usuario_destino;
	private int valor;

	public Transferencias() {
	}

	public Transferencias(@Valid TransferenciasForm form) {
		
		this.usuario_origem = form.getUsuario_origem();
		this.usuario_destino = form.getUsuario_destino();
		this.valor = form.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuario_origem() {
		return usuario_origem;
	}

	public void setUsuario_origem(Long usuario_origem) {
		this.usuario_origem = usuario_origem;
	}

	public Long getUsuario_destino() {
		return usuario_destino;
	}

	public void setUsuario_destino(Long usuario_destino) {
		this.usuario_destino = usuario_destino;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
