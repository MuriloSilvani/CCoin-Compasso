package br.com.compasso.usuarios.dto;

import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.model.Niveis_acesso;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.model.Usuarios;

public class UsuariosDto {

	private Long id;
	private String email;
	private String nome;
	private Niveis_acesso id_nivel_acesso;
	private Cargos id_cargo;
	private Unidades id_unidade;
	private boolean responsavel_unidade;

	public UsuariosDto() {
	}

	public UsuariosDto(Usuarios usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.id_nivel_acesso = usuario.getId_nivel_acesso();
		this.id_cargo = usuario.getId_cargo();
		this.id_unidade = usuario.getId_unidade();
		this.responsavel_unidade = usuario.isResponsavel_unidade();
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public Niveis_acesso getId_nivel_acesso() {
		return id_nivel_acesso;
	}

	public Cargos getId_cargo() {
		return id_cargo;
	}

	public Unidades getId_unidade() {
		return id_unidade;
	}

	public boolean isResponsavel_unidade() {
		return responsavel_unidade;
	}

}
