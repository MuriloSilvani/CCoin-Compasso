package br.com.compasso.itens.config.validation;

public class ErroDto {

	private String erro;

	public ErroDto(String mensagem) {

		this.erro = mensagem;
	}

	public String getErro() {
		return erro;
	}

}
