package br.com.compasso.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.usuarios.config.security.TokenService;
import br.com.compasso.usuarios.dto.LoginDto;
import br.com.compasso.usuarios.form.LoginForm;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<LoginDto> autenticar(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {

			Authentication authenticate = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);

			return ResponseEntity.ok(new LoginDto(token, tokenService.getIdUsuario(token)));

		} catch (Exception e) {

			return ResponseEntity.badRequest().build();
		}
	}
	
}
