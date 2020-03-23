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
import br.com.compasso.usuarios.form.unidades.UnidadesForm;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.repository.UnidadesRepository;

@CrossOrigin
@RestController
@RequestMapping("/unidades")
public class UnidadesController {

	@Autowired
	private UnidadesRepository unidadesRepository;

	@GetMapping("")
	public ResponseEntity<List<Unidades>> listarUnidades() {

		List<Unidades> unidades = unidadesRepository.findAll();

		return ResponseEntity.ok(unidades);
	}

	@GetMapping("/{idUnidade}")
	public ResponseEntity<Unidades> listarUnidade(@PathVariable Long idUnidade) throws CustomNotFoundException {

		Optional<Unidades> unidade = unidadesRepository.findById(idUnidade);

		if (unidade.isPresent()) {
			return ResponseEntity.ok(unidade.get());
		}

		throw new CustomNotFoundException("Unidade '" + idUnidade +"' não encontrada");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Unidades> cadastraUnidades(@RequestBody @Valid UnidadesForm form, UriComponentsBuilder uriBuilder) {

		Optional<Unidades> unidadeExist = unidadesRepository.findByUfAndCidadeAndEndereco(form.getUf(), form.getCidade(), form.getEndereco());

		if (unidadeExist.isPresent()) {

			return ResponseEntity.ok(unidadeExist.get());
		}

		Unidades unidade = unidadesRepository.save(new Unidades(form.getUf(), form.getCidade(), form.getEndereco()));
		URI uri = uriBuilder.path("/unidades/{id}").buildAndExpand(unidade.getId()).toUri();

		return ResponseEntity.created(uri).body(unidade);
	}

	@PutMapping("/{idUnidade}")
	@Transactional
	public ResponseEntity<Unidades> editarUnidade(@RequestBody @Valid UnidadesForm form, @PathVariable Long idUnidade) throws CustomNotFoundException {

		Optional<Unidades> unidade = unidadesRepository.findById(idUnidade);

		if (unidade.isPresent()) {

			Unidades unidadeNovo = form.atualizar(idUnidade, unidadesRepository);
			return ResponseEntity.ok().body(unidadeNovo);
		}

		throw new CustomNotFoundException("Unidade '" + idUnidade +"' não encontrada");
	}

	@DeleteMapping("/{idUnidade}")
	@Transactional
	public ResponseEntity<Unidades> deletarUnidade(@PathVariable Long idUnidade) throws CustomNotFoundException {

		Optional<Unidades> unidade = unidadesRepository.findById(idUnidade);

		if (unidade.isPresent()) {

			unidadesRepository.deleteById(idUnidade);

			return ResponseEntity.ok().build();
		}

		throw new CustomNotFoundException("Unidade '" + idUnidade +"' não encontrada");
	}
}
