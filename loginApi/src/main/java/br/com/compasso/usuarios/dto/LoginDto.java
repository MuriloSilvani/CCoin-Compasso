package br.com.compasso.usuarios.dto;

public class LoginDto {
	
	private String token;
	private Long id_usuario;

	public LoginDto() {

	}

	public LoginDto(String token, Long id_usuario) {
		this.token = token;
		this.id_usuario = id_usuario;
	}

	public String getToken() {
		return token;
	}

	public Long getId_usuario() {
		return id_usuario;
	}
}
