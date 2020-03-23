package br.com.compasso.usuarios.dto.usuarios;

import br.com.compasso.usuarios.dto.niveisAcesso.NiveisAcessoDto;
import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.model.Usuarios;

public class UsuariosDto {

	private Long id;
	private String email;
	private String nome;
	private NiveisAcessoDto idNivelAcesso;
	private Cargos idCargo;
	private Unidades idUnidade;
	private boolean responsavelUnidade;

	public UsuariosDto() {
	}

	public UsuariosDto(Usuarios usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.idNivelAcesso = new NiveisAcessoDto(usuario.getIdNivelAcesso());
		this.idCargo = usuario.getIdCargo();
		this.idUnidade = usuario.getIdUnidade();
		this.responsavelUnidade = usuario.getResponsavelUnidade();
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

	public NiveisAcessoDto getIdNivelAcesso() {
		return idNivelAcesso;
	}

	public Cargos getIdCargo() {
		return idCargo;
	}

	public Unidades getIdUnidade() {
		return idUnidade;
	}

	public boolean isResponsavelUnidade() {
		return responsavelUnidade;
	}

}
