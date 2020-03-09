package br.com.compasso.usuarios.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.model.Niveis_acesso;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.model.Usuarios;
import br.com.compasso.usuarios.repository.CargosRepository;
import br.com.compasso.usuarios.repository.Niveis_acessoRepository;
import br.com.compasso.usuarios.repository.UnidadesRepository;
import br.com.compasso.usuarios.repository.UsuariosRepository;

public class UsuariosForm {

	@Autowired
	private Niveis_acessoRepository niveis_acessoRepository;

	@Autowired
	private CargosRepository cargosRepository;

	@Autowired
	private UnidadesRepository unidadesRepository;

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
	@NotEmpty
	private Long id_nivel_acesso;
	@NotNull
	@NotEmpty
	private Long id_cargo;
	@NotNull
	@NotEmpty
	private Long id_unidade;
	@NotNull
	private boolean responsavel_unidade;

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

	public Long getId_nivel_acesso() {
		return id_nivel_acesso;
	}

	public Long getId_cargo() {
		return id_cargo;
	}

	public Long getId_unidade() {
		return id_unidade;
	}

	public boolean isResponsavel_unidade() {
		return responsavel_unidade;
	}

	public Usuarios atualizar(Long id_usuario, UsuariosRepository usuariosRepository) {

		Optional<Usuarios> usuarioFind = usuariosRepository.findById(id_usuario);

		if (usuarioFind.isPresent()) {
			Usuarios usuario = usuarioFind.get();

//			Optional<Niveis_acesso> nivel_acesso = niveis_acessoRepository.findById(this.id_nivel_acesso);
//			if (nivel_acesso.isPresent()) {
//				usuario.setId_nivel_acesso(nivel_acesso.get());
//			}
//			Optional<Cargos> cargo = cargosRepository.findById(this.id_cargo);
//			if (cargo.isPresent()) {
//				usuario.setId_cargo(cargo.get());
//			}
//			Optional<Unidades> unidade = unidadesRepository.findById(this.id_unidade);
//			if (unidade.isPresent()) {
//				usuario.setId_unidade(unidade.get());
//			}

			usuario.setLogin(this.login);
			usuario.setEmail(this.email);
			usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
			usuario.setNome(this.nome);
			usuario.setResponsavel_unidade(this.responsavel_unidade);

			return usuario;
		}

		return null;

	}

}
