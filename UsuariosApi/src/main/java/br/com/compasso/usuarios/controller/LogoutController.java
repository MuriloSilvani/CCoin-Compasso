package br.com.compasso.usuarios.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/logout")
public class LogoutController {

	@DeleteMapping("/{token}")
	public ResponseEntity<?> autenticar(@RequestBody @Valid String token) {

		
		return ResponseEntity.ok(null);
	}
}
