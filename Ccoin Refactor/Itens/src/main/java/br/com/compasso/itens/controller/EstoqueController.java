package br.com.compasso.itens.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.dto.estoque.EstoqueDto;
import br.com.compasso.itens.form.estoque.EstoqueForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.TiposItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	ItensRepository itensRepository;

	@Autowired
	TiposItemRepository tiposItemRepository;

	@GetMapping("")
	public ResponseEntity<List<EstoqueDto>> listarEstoque() {

		List<Estoque> estoque = estoqueRepository.findAll();
		
		List<EstoqueDto> estoqueDto = estoque.stream().map(EstoqueDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(estoqueDto);
	}

	@GetMapping("/{idEstoque}")
	public ResponseEntity<EstoqueDto> listarItemEstoque(@PathVariable Long idEstoque) throws CustomNotFoundException {

		Optional<Estoque> estoque = estoqueRepository.findById(idEstoque);

		if (estoque.isPresent()) {
			
			return ResponseEntity.ok(new EstoqueDto(estoque.get()));
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EstoqueDto> cadastraEstoque(@RequestBody @Valid EstoqueForm form,
			UriComponentsBuilder uriBuilder) throws CustomNotFoundException {

		Estoque estoque = estoqueRepository.save(new Estoque(form, itensRepository, tiposItemRepository));
		URI uri = uriBuilder.path("/estoque/{id}").buildAndExpand(estoque.getId()).toUri();

		return ResponseEntity.created(uri).body(new EstoqueDto(estoque));
	}

	@PutMapping("/{idEstoque}")
	@Transactional
	public ResponseEntity<EstoqueDto> editarEstoque(@RequestBody @Valid EstoqueForm form, @PathVariable Long idEstoque)
			throws CustomNotFoundException {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(idEstoque);

		if (estoqueFind.isPresent()) {

			Estoque estoque = form.atualizar(idEstoque, estoqueRepository, itensRepository, tiposItemRepository);
			return ResponseEntity.ok().body(new EstoqueDto(estoque));
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}

	@PutMapping("/ativar/{idEstoque}")
	@Transactional
	public ResponseEntity<EstoqueDto> ativarDesativarEstoque(@PathVariable Long idEstoque) throws CustomNotFoundException {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(idEstoque);

		if (estoqueFind.isPresent()) {

			Estoque estoque = estoqueFind.get();
			estoque.setAtivo(!estoque.getAtivo());
			
			return ResponseEntity.ok(new EstoqueDto(estoque));
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}
}
