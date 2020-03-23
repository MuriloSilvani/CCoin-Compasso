package br.com.compasso.usuarios.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.dto.usuarios.UsuariosDto;
import br.com.compasso.usuarios.form.usuarios.UsuariosForm;
import br.com.compasso.usuarios.model.Usuarios;
import br.com.compasso.usuarios.repository.CargosRepository;
import br.com.compasso.usuarios.repository.NiveisAcessoRepository;
import br.com.compasso.usuarios.repository.UnidadesRepository;
import br.com.compasso.usuarios.repository.UsuariosRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private NiveisAcessoRepository niveisAcessoRepository;

	@Autowired
	private CargosRepository cargosRepository;

	@Autowired
	private UnidadesRepository unidadesRepository;

	@GetMapping("")
	public ResponseEntity<List<UsuariosDto>> listarUsuarios() {

		List<Usuarios> usuariosFind = usuariosRepository.findAll();

		List<UsuariosDto> usuarios = usuariosFind.stream().map(UsuariosDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuariosDto> listarUsuario(@PathVariable Long idUsuario) throws CustomNotFoundException {

		Optional<Usuarios> usuarioFind = usuariosRepository.findById(idUsuario);

		if (usuarioFind.isPresent()) {

			UsuariosDto usuario = new UsuariosDto(usuarioFind.get());
			return ResponseEntity.ok(usuario);
		}

		throw new CustomNotFoundException("Usuario '" + idUsuario + "' não encontrado");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuariosDto> cadastraUsuarios(@RequestBody @Valid UsuariosForm form,
			UriComponentsBuilder uriBuilder) throws CustomNotFoundException {

		Optional<Usuarios> usuarioExist = usuariosRepository.findByLogin(form.getLogin());
		if (usuarioExist.isPresent()) {
			throw new CustomNotFoundException("Login '" + form.getLogin() + "' já existe");
		}
		usuarioExist = usuariosRepository.findByEmail(form.getEmail());
		if (usuarioExist.isPresent()) {
			throw new CustomNotFoundException("Email '" + form.getEmail() + "' já existe");
		}

		UsuariosDto usuario = new UsuariosDto(usuariosRepository
				.save(new Usuarios(form, niveisAcessoRepository, cargosRepository, unidadesRepository)));
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(uri).body(usuario);
	}

	@PutMapping("/{idUsuario}")
	@Transactional
	public ResponseEntity<UsuariosDto> editarUsuario(@RequestBody @Valid UsuariosForm form,
			@PathVariable Long idUsuario) throws CustomNotFoundException {

		Optional<Usuarios> usuario = usuariosRepository.findById(idUsuario);

		if (usuario.isPresent()) {

			UsuariosDto usuarioNovo = new UsuariosDto(form.atualizar(idUsuario, usuariosRepository,
					niveisAcessoRepository, cargosRepository, unidadesRepository));
			return ResponseEntity.ok().body(usuarioNovo);
		}

		throw new CustomNotFoundException("Usuario '" + idUsuario + "' não encontrado");
	}

	@DeleteMapping("/{idUsuario}")
	@Transactional
	public ResponseEntity<UsuariosDto> deletarUsuario(@PathVariable Long idUsuario) throws CustomNotFoundException {

		Optional<Usuarios> usuario = usuariosRepository.findById(idUsuario);

		if (usuario.isPresent()) {

			usuariosRepository.deleteById(idUsuario);

			return ResponseEntity.ok().build();
		}

		throw new CustomNotFoundException("Usuario '" + idUsuario + "' não encontrado");
	}
}
