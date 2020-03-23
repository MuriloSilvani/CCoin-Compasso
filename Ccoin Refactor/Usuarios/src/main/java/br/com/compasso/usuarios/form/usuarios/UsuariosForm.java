package br.com.compasso.usuarios.form.usuarios;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.model.NiveisAcesso;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.model.Usuarios;
import br.com.compasso.usuarios.repository.CargosRepository;
import br.com.compasso.usuarios.repository.NiveisAcessoRepository;
import br.com.compasso.usuarios.repository.UnidadesRepository;
import br.com.compasso.usuarios.repository.UsuariosRepository;

public class UsuariosForm {

	@NotNull
	@NotEmpty
	private String login;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String senha;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	private Long idNivelAcesso;
	@NotNull
	private Long idCargo;
	@NotNull
	private Long idUnidade;
	@NotNull
	private boolean responsavelUnidade;

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdNivelAcesso() {
		return idNivelAcesso;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public boolean getResponsavelUnidade() {
		return responsavelUnidade;
	}

	public Usuarios atualizar(Long idUsuario, UsuariosRepository usuariosRepository,
			NiveisAcessoRepository niveisAcessoRepository, CargosRepository cargosRepository,
			UnidadesRepository unidadesRepository) throws CustomNotFoundException {

		Optional<Usuarios> usuarioFind = usuariosRepository.findById(idUsuario);

		if (usuarioFind.isPresent()) {

			Usuarios usuario = usuarioFind.get();

			Optional<Usuarios> usuarioExist;
			if (usuario.getLogin() != this.login) {
				usuarioExist = usuariosRepository.findByLogin(this.login);
				if (usuarioExist.isPresent()) {
					if (usuarioExist.get().getId() != idUsuario)
						throw new CustomNotFoundException("Login '" + this.login + "' já existe");
				}
			}
			if (usuario.getEmail() != this.email) {
				usuarioExist = usuariosRepository.findByEmail(this.email);
				if (usuarioExist.isPresent()) {
					if (usuarioExist.get().getId() != idUsuario)
						throw new CustomNotFoundException("Email '" + this.email + "' já existe");
				}
			}

			Optional<NiveisAcesso> nivelAcesso = niveisAcessoRepository.findById(this.idNivelAcesso);
			if (nivelAcesso.isPresent()) {
				usuario.setIdNivelAcesso(nivelAcesso.get());
			} else {
				throw new CustomNotFoundException("Nivel acesso '" + this.idNivelAcesso + "' não encontrado");
			}

			Optional<Cargos> cargo = cargosRepository.findById(this.idCargo);
			if (cargo.isPresent()) {
				usuario.setIdCargo(cargo.get());
			} else {
				throw new CustomNotFoundException("Cargo '" + this.idCargo + "' não encontrado");
			}

			Optional<Unidades> unidade = unidadesRepository.findById(this.idUnidade);
			if (unidade.isPresent()) {
				usuario.setIdUnidade(unidade.get());
			} else {
				throw new CustomNotFoundException("Unidade '" + this.idUnidade + "' não encontrada");
			}

			usuario.setLogin(this.login);
			usuario.setEmail(this.email);
			usuario.setNome(this.nome);
			usuario.setResponsavelUnidade(this.responsavelUnidade);
			usuario.setSenha(this.senha);

			return usuario;
		}

		throw new CustomNotFoundException("Usuário '" + idUsuario + "' não encontrado");
	}

}
