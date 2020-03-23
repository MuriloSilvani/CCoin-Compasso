package br.com.compasso.exchanges.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transferencias")
public class Transferencias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long usuarioOrigem;
	private Long usuarioDestino;
	private int valor;

	public Transferencias() {
	}

	public Transferencias(Long idUsuarioOrigem, Long idUsuarioDestino, int valor) {

		this.usuarioOrigem = idUsuarioOrigem;
		this.usuarioDestino = idUsuarioDestino;
		this.valor = valor;
	}

	public Transferencias(Long idUsuarioOrigem, int valor) {

		this.usuarioOrigem = idUsuarioOrigem;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioOrigem() {
		return usuarioOrigem;
	}

	public void setUsuarioOrigem(Long usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}

	public Long getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(Long usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
