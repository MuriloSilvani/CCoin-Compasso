package br.com.compasso.usuarios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.usuarios.dto.UsuariosDto;
import br.com.compasso.usuarios.form.UsuariosForm;
import br.com.compasso.usuarios.model.Usuarios;
import br.com.compasso.usuarios.repository.CargosRepository;
import br.com.compasso.usuarios.repository.Niveis_acessoRepository;
import br.com.compasso.usuarios.repository.UnidadesRepository;
import br.com.compasso.usuarios.repository.UsuariosRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private Niveis_acessoRepository niveis_acessoRepository;

	@Autowired
	private CargosRepository cargosRepository;

	@Autowired
	private UnidadesRepository unidadesRepository;

	@GetMapping(value = "")
	public ResponseEntity<List<UsuariosDto>> listarUsuarios() {

		List<Usuarios> usuariosFind = usuariosRepository.findAll();
		List<UsuariosDto> usuarios = new ArrayList<UsuariosDto>();
		
		for (Usuarios us : usuariosFind) {
			usuarios.add(new UsuariosDto(us));
		}

		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id_usuario}")
	public ResponseEntity<UsuariosDto> listarUsuario(@PathVariable Long id_usuario) {

		Optional<Usuarios> usuarioFind = usuariosRepository.findById(id_usuario);

		if (usuarioFind.isPresent()) {
			UsuariosDto usuario = new UsuariosDto(usuarioFind.get());
			return ResponseEntity.ok(usuario);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<UsuariosDto> cadastraUsuarios(@RequestBody  UsuariosForm form) {

		Usuarios usuarioNovo = usuariosRepository.save(new Usuarios(form.getLogin(), form.getEmail(), form.getSenha(),
				form.getNome(), form.getId_nivel_acesso(), form.getId_cargo(), form.getId_unidade(), 
				form.isResponsavel_unidade(), niveis_acessoRepository, cargosRepository, unidadesRepository));

		UsuariosDto usuario = new UsuariosDto(usuarioNovo);
		
		return ResponseEntity.created(null).body(usuario);

	}

	@PutMapping("/{id_usuario}")
	@Transactional
	public ResponseEntity<UsuariosDto> editarUsuario(@RequestBody @Valid UsuariosForm form,
			@PathVariable Long id_usuario) {

		Optional<Usuarios> usuario = usuariosRepository.findById(id_usuario);

		if (usuario.isPresent()) {

			UsuariosDto usuarioNova = new UsuariosDto(form.atualizar(id_usuario, usuariosRepository));
			
			return ResponseEntity.ok().body(usuarioNova);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_usuario}")
	@Transactional
	public ResponseEntity<UsuariosDto> deletarUsuario(@PathVariable Long id_usuario) {

		Optional<Usuarios> usuario = usuariosRepository.findById(id_usuario);

		if (usuario.isPresent()) {

			usuariosRepository.deleteById(id_usuario);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
