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

import br.com.compasso.usuarios.form.UnidadesForm;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.repository.UnidadesRepository;

@CrossOrigin
@RestController
@RequestMapping("/unidades")
public class UnidadesController {

	@Autowired
	private UnidadesRepository unidadesRepository;

	@GetMapping(value = "")
	public ResponseEntity<Iterable<Unidades>> listarUnidades() {

		Iterable<Unidades> unidades = unidadesRepository.findAll();

		return ResponseEntity.ok(unidades);
	}

	@GetMapping("/{id_unidade}")
	public ResponseEntity<Optional<Unidades>> listarUnidade(@PathVariable Long id_unidade) {

		Optional<Unidades> unidade = unidadesRepository.findById(id_unidade);

		if (unidade.isPresent()) {
			return ResponseEntity.ok(unidade);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<Unidades> cadastraUnidades(@RequestBody @Valid UnidadesForm form) {

		Unidades unidade = unidadesRepository.save(new Unidades(form.getUf(), form.getCidade(), form.getEndereco()));

		return ResponseEntity.created(null).body(unidade);
	}

	@PutMapping("/{id_unidade}")
	@Transactional
	public ResponseEntity<Unidades> editarUnidade(@RequestBody @Valid UnidadesForm form,
			@PathVariable Long id_unidade) {

		Optional<Unidades> unidade = unidadesRepository.findById(id_unidade);

		if (unidade.isPresent()) {

			Unidades unidadeNovo = form.atualizar(id_unidade, unidadesRepository);
			return ResponseEntity.ok().body(unidadeNovo);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_unidade}")
	@Transactional
	public ResponseEntity<Unidades> deletarUnidade(@PathVariable Long id_unidade) {

		Optional<Unidades> unidade = unidadesRepository.findById(id_unidade);

		if (unidade.isPresent()) {

			unidadesRepository.deleteById(id_unidade);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
