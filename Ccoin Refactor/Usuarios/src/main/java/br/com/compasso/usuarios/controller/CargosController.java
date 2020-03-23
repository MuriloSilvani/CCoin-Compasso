package br.com.compasso.usuarios.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import br.com.compasso.usuarios.form.cargos.CargosForm;
import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.repository.CargosRepository;

@CrossOrigin
@RestController
@RequestMapping("/cargos")
public class CargosController {

	@Autowired
	private CargosRepository cargosRepository;

	@GetMapping("")
	public ResponseEntity<List<Cargos>> listarCargos() {

		List<Cargos> cargos = cargosRepository.findAll();

		return ResponseEntity.ok(cargos);
	}

	@GetMapping("/{idCargo}")
	public ResponseEntity<Cargos> listarCargo(@PathVariable Long idCargo) throws CustomNotFoundException {

		Optional<Cargos> cargo = cargosRepository.findById(idCargo);

		if (cargo.isPresent()) {
			return ResponseEntity.ok(cargo.get());
		}

		throw new CustomNotFoundException("Cargo '" + idCargo +"' não encontrado");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cargos> cadastraCargos(@RequestBody @Valid CargosForm form, UriComponentsBuilder uriBuilder) {

		Optional<Cargos> cargoExist = cargosRepository.findByDescricao(form.getDescricao());

		if (cargoExist.isPresent()) {

			return ResponseEntity.ok(cargoExist.get());
		}
		
		Cargos cargo = cargosRepository.save(new Cargos(form.getDescricao()));
		URI uri = uriBuilder.path("/cargos/{id}").buildAndExpand(cargo.getId()).toUri();

		return ResponseEntity.created(uri).body(cargo);
	}

	@PutMapping("/{idCargo}")
	@Transactional
	public ResponseEntity<Cargos> editarCargo(@RequestBody @Valid CargosForm form, @PathVariable Long idCargo) throws CustomNotFoundException {

		Optional<Cargos> cargo = cargosRepository.findById(idCargo);

		if (cargo.isPresent()) {

			Cargos cargoNovo = form.atualizar(idCargo, cargosRepository);
			return ResponseEntity.ok().body(cargoNovo);
		}

		throw new CustomNotFoundException("Cargo '" + idCargo +"' não encontrado");
	}

	@DeleteMapping("/{idCargo}")
	@Transactional
	public ResponseEntity<Cargos> deletarCargo(@PathVariable Long idCargo) throws CustomNotFoundException {

		Optional<Cargos> cargo = cargosRepository.findById(idCargo);

		if (cargo.isPresent()) {

			cargosRepository.deleteById(idCargo);

			return ResponseEntity.ok().build();
		}

		throw new CustomNotFoundException("Cargo '" + idCargo +"' não encontrado");
	}
}
