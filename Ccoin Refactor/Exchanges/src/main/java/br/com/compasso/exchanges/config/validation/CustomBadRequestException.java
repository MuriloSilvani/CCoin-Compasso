package br.com.compasso.exchanges.config.validation;

public class CustomBadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomBadRequestException(String message) {
		super(message);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
