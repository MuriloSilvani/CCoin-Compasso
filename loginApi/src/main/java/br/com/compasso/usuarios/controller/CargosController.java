package br.com.compasso.usuarios.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.usuarios.form.CargosForm;
import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.repository.CargosRepository;

@RestController
@RequestMapping("/cargos")
public class CargosController {

	@Autowired
	private CargosRepository cargosRepository;

	@GetMapping(value = "")
	public ResponseEntity<Iterable<Cargos>> listarCargos() {

		Iterable<Cargos> cargos = cargosRepository.findAll();

		return ResponseEntity.ok(cargos);
	}

	@GetMapping("/{id_cargo}")
	public ResponseEntity<Optional<Cargos>> listarCargo(@PathVariable Long id_cargo) {

		Optional<Cargos> cargo = cargosRepository.findById(id_cargo);

		if (cargo.isPresent()) {
			return ResponseEntity.ok(cargo);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cargos> cadastraCargos(@RequestBody @Valid CargosForm form) {

		Cargos cargo = cargosRepository.save(new Cargos(form.getDescricao()));

		return ResponseEntity.created(null).body(cargo);
	}

	@PutMapping("/{id_cargo}")
	@Transactional
	public ResponseEntity<Cargos> editarCargo(@RequestBody @Valid CargosForm form, @PathVariable Long id_cargo) {

		Optional<Cargos> cargo = cargosRepository.findById(id_cargo);

		if (cargo.isPresent()) {

			Cargos cargoNovo = form.atualizar(id_cargo, cargosRepository);
			return ResponseEntity.ok().body(cargoNovo);
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id_cargo}")
	@Transactional
	public ResponseEntity<Cargos> deletarCargo(@PathVariable Long id_cargo) {

		Optional<Cargos> cargo = cargosRepository.findById(id_cargo);
		
		if (cargo.isPresent()) {

			cargosRepository.deleteById(id_cargo);
			
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
}
