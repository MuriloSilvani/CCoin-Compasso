package br.com.compasso.usuarios.controller.crud;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.usuarios.form.NiveisAcessoForm;
import br.com.compasso.usuarios.model.Niveis_acesso;
import br.com.compasso.usuarios.repository.Niveis_acessoRepository;

@CrossOrigin
@RestController
@RequestMapping("/niveis_acesso")
public class Niveis_acessoController {

	@Autowired
	private Niveis_acessoRepository niveis_acessoRepository;

	@GetMapping(value = "")
	public ResponseEntity<Iterable<Niveis_acesso>> listarNiveisDeAcesso() {

		Iterable<Niveis_acesso> niveis_acesso = niveis_acessoRepository.findAll();

		return ResponseEntity.ok(niveis_acesso);
	}

	@GetMapping("/{id_nivel_acesso}")
	public ResponseEntity<Optional<Niveis_acesso>> listarNivel_acesso(@PathVariable Long id_nivel_acesso) {

		Optional<Niveis_acesso> nivel_acesso = niveis_acessoRepository.findById(id_nivel_acesso);

		if (nivel_acesso.isPresent()) {
			return ResponseEntity.ok(nivel_acesso);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<Niveis_acesso> cadastraNivel_acesso(@RequestBody @Valid NiveisAcessoForm form) {

		Niveis_acesso nivel_acesso = niveis_acessoRepository.save(new Niveis_acesso(form.getDescricao()));

		return ResponseEntity.created(null).body(nivel_acesso);
	}

	@PutMapping("/{id_nivel_acesso}")
	@Transactional
	public ResponseEntity<Niveis_acesso> editarNivel_acesso(@RequestBody @Valid NiveisAcessoForm form, @PathVariable Long id_nivel_acesso) {

		Optional<Niveis_acesso> nivel_acesso = niveis_acessoRepository.findById(id_nivel_acesso);

		if (nivel_acesso.isPresent()) {

			Niveis_acesso nivel_acessoNovo = form.atualizar(id_nivel_acesso, niveis_acessoRepository);
			return ResponseEntity.ok().body(nivel_acessoNovo);
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id_nivel_acesso}")
	@Transactional
	public ResponseEntity<Niveis_acesso> deletarNivel_acesso(@PathVariable Long id_nivel_acesso) {

		Optional<Niveis_acesso> nivel_acesso = niveis_acessoRepository.findById(id_nivel_acesso);
		
		if (nivel_acesso.isPresent()) {

			niveis_acessoRepository.deleteById(id_nivel_acesso);
			
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
}
