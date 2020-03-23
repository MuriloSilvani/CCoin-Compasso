package br.com.compasso.usuarios.config.validation;

public class ErroDto {

	private String erro;

	public ErroDto(String mensagem) {

		this.erro = mensagem;
	}

	public String getErro() {
		return erro;
	}

}
