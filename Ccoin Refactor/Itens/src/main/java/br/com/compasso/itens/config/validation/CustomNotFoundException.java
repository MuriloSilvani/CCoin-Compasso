package br.com.compasso.itens.config.validation;

public class CustomNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomNotFoundException(String message) {
        super(message);
    }

	public String getMessage() {
		return super.getMessage();
	}
}
