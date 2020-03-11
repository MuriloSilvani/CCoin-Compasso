package br.com.compasso.resgatestransferencias.config.validation;

public class ErroDeFormularioDto {

	private String campo;
	private String erro;
	
	public ErroDeFormularioDto(String field, String mensagem) {

		this.campo = field;
		this.erro = mensagem;
	}
	public String getCampo() {
		return campo;
	}
	public String getErro() {
		return erro;
	}
	
	
}
