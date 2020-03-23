package br.com.compasso.itens.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroExcepptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDto> handle(MethodArgumentNotValidException exception) {
		
		List<ErroDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDto erro = new ErroDto("campo '" + e.getField() + "' " + mensagem);
			dto.add(erro);
		});

		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErroDto handle(DataIntegrityViolationException exception) {
				
		ErroDto dto = new ErroDto(exception.getMessage());

		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomNotFoundException.class)
	public ErroDto handle(CustomNotFoundException exception) {
				
		ErroDto dto = new ErroDto(exception.getMessage());

		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomBadRequestException.class)
	public ErroDto handle(CustomBadRequestException exception) {
				
		ErroDto dto = new ErroDto(exception.getMessage());

		return dto;
	}
}
