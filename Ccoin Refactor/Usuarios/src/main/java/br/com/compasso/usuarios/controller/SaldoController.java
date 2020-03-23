package br.com.compasso.usuarios.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.usuarios.config.security.TokenService;
import br.com.compasso.usuarios.config.validation.CustomBadRequestException;
import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.dto.saldo.SaldoDto;
import br.com.compasso.usuarios.form.saldo.TransferenciaForm;
import br.com.compasso.usuarios.model.Usuarios;
import br.com.compasso.usuarios.repository.UsuariosRepository;

@CrossOrigin
@RestController
@RequestMapping("/saldo")
public class SaldoController {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/{idUsuario}")
	public ResponseEntity<SaldoDto> consultaSaldo(@PathVariable Long idUsuario,
			@RequestHeader(value = "Authorization") String authorization)
			throws CustomNotFoundException, CustomBadRequestException {

		Optional<Usuarios> usuarioFind = usuariosRepository.findById(idUsuario);

		if (usuarioFind.isPresent()) {

			if (idUsuario != tokenService.getIdUsuario(authorization.substring(7, authorization.length()))) {
				throw new CustomBadRequestException("O id de busca não é o mesmo do usuário logado");
			}

			Usuarios usuario = usuarioFind.get();

			SaldoDto saldoDto = new SaldoDto(usuario.getCredito());
			return ResponseEntity.ok(saldoDto);
		}

		throw new CustomNotFoundException("Usuário '" + idUsuario + "' não encontrado");
	}

	@PutMapping("/deposito/{idUsuario}")
	@Transactional
	public ResponseEntity<SaldoDto> deposito(@PathVariable Long idUsuario, @Valid @RequestBody TransferenciaForm form,
			@Nullable @RequestHeader(value = "RequestToken") String requestToken)
			throws CustomNotFoundException, CustomBadRequestException {

		if (requestToken == null || !requestToken.matches("123456789")) {
			throw new CustomBadRequestException("Não pode usar a rota deposito sem ser a api de exchanges");
		}

		Optional<Usuarios> usuario = usuariosRepository.findById(idUsuario);

		if (usuario.isPresent()) {

			form.deposito(usuario.get());

			return ResponseEntity.ok(new SaldoDto(usuario.get().getCredito()));
		}

		throw new CustomNotFoundException("Usuário '" + idUsuario + "' não encontrado");
	}

	@PutMapping("/saque/{idUsuario}")
	@Transactional
	public ResponseEntity<SaldoDto> saque(@PathVariable Long idUsuario, @Valid @RequestBody TransferenciaForm form)
			throws CustomNotFoundException, CustomBadRequestException {

		Optional<Usuarios> usuario = usuariosRepository.findById(idUsuario);

		if (usuario.isPresent()) {

			form.saque(usuario.get());

			return ResponseEntity.ok(new SaldoDto(usuario.get().getCredito()));
		}

		throw new CustomNotFoundException("Usuário '" + idUsuario + "' não encontrado");
	}

}
