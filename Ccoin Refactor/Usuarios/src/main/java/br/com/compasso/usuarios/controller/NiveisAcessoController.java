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
import br.com.compasso.usuarios.dto.niveisAcesso.NiveisAcessoDto;
import br.com.compasso.usuarios.form.niveisAcesso.NiveisAcessoForm;
import br.com.compasso.usuarios.model.NiveisAcesso;
import br.com.compasso.usuarios.repository.NiveisAcessoRepository;

@CrossOrigin
@RestController
@RequestMapping("/niveisAcesso")
public class NiveisAcessoController {

	@Autowired
	private NiveisAcessoRepository niveisAcessoRepository;

	@GetMapping("")
	public ResponseEntity<List<NiveisAcessoDto>> listarNiveisAcesso() {

		List<NiveisAcesso> niveisAcessoFind = niveisAcessoRepository.findAll();

		List<NiveisAcessoDto> niveisAcesso = niveisAcessoFind.stream().map(NiveisAcessoDto::new)
				.collect(Collectors.toList());

		return ResponseEntity.ok(niveisAcesso);
	}

	@GetMapping("/{idNivelAcesso}")
	public ResponseEntity<NiveisAcessoDto> listarNivelAcesso(@PathVariable Long idNivelAcesso)
			throws CustomNotFoundException {

		Optional<NiveisAcesso> nivelAcessoFind = niveisAcessoRepository.findById(idNivelAcesso);

		if (nivelAcessoFind.isPresent()) {

			NiveisAcessoDto nivelAcesso = new NiveisAcessoDto(nivelAcessoFind.get());
			return ResponseEntity.ok(nivelAcesso);
		}

		throw new CustomNotFoundException("NivelAcesso '" + idNivelAcesso + "' não encontrado");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<NiveisAcessoDto> cadastraNiveisAcesso(@RequestBody @Valid NiveisAcessoForm form,
			UriComponentsBuilder uriBuilder) {

		Optional<NiveisAcesso> nivelAcessoExist = niveisAcessoRepository.findByDescricao(form.getDescricao());

		if (nivelAcessoExist.isPresent()) {

			return ResponseEntity.ok(new NiveisAcessoDto(nivelAcessoExist.get()));
		}

		NiveisAcessoDto nivelAcesso = new NiveisAcessoDto(
				niveisAcessoRepository.save(new NiveisAcesso(form.getDescricao())));
		URI uri = uriBuilder.path("/niveisAcesso/{id}").buildAndExpand(nivelAcesso.getId()).toUri();

		return ResponseEntity.created(uri).body(nivelAcesso);
	}

	@PutMapping("/{idNivelAcesso}")
	@Transactional
	public ResponseEntity<NiveisAcessoDto> editarNivelAcesso(@RequestBody @Valid NiveisAcessoForm form,
			@PathVariable Long idNivelAcesso) throws CustomNotFoundException {

		Optional<NiveisAcesso> nivelAcesso = niveisAcessoRepository.findById(idNivelAcesso);

		if (nivelAcesso.isPresent()) {

			NiveisAcessoDto nivelAcessoNovo = new NiveisAcessoDto(
					form.atualizar(idNivelAcesso, niveisAcessoRepository));
			return ResponseEntity.ok().body(nivelAcessoNovo);
		}

		throw new CustomNotFoundException("NivelAcesso '" + idNivelAcesso + "' não encontrado");
	}

	@DeleteMapping("/{idNivelAcesso}")
	@Transactional
	public ResponseEntity<NiveisAcessoDto> deletarNivelAcesso(@PathVariable Long idNivelAcesso)
			throws CustomNotFoundException {

		Optional<NiveisAcesso> nivelAcesso = niveisAcessoRepository.findById(idNivelAcesso);

		if (nivelAcesso.isPresent()) {

			niveisAcessoRepository.deleteById(idNivelAcesso);

			return ResponseEntity.ok().build();
		}

		throw new CustomNotFoundException("NivelAcesso '" + idNivelAcesso + "' não encontrado");
	}
}
