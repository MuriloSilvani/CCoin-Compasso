package br.com.compasso.usuarios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotNull @NotEmpty
	private String login;
	@NotNull @NotEmpty
	private String senha;

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {

		return new UsernamePasswordAuthenticationToken(login, senha);
	}

}
