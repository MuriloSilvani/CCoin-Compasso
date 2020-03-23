package br.com.compasso.usuarios.dto.login;

public class LoginDto {

	private String token;
	private Long idUsuario;

	public LoginDto() {

	}

	public LoginDto(String token, Long idUsuario) {
		this.token = token;
		this.idUsuario = idUsuario;
	}

	public String getToken() {
		return token;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
}
