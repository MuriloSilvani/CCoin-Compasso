package br.com.compasso.itens.controller;

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

import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.form.itens.ItensForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItensRepository;

@CrossOrigin
@RestController
@RequestMapping("/itens")
public class ItensController {

	@Autowired
	private ItensRepository itensRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping("")
	public ResponseEntity<List<Itens>> listarItens() {

		List<Itens> itens = itensRepository.findAll();

		return ResponseEntity.ok(itens);
	}

	@GetMapping("/{idItem}")
	public ResponseEntity<Itens> listarItem(@PathVariable Long idItem) throws CustomNotFoundException {

		Optional<Itens> item = itensRepository.findById(idItem);

		if (item.isPresent()) {
			return ResponseEntity.ok(item.get());
		}

		throw new CustomNotFoundException("Item '" + idItem + "' não encontrado");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Itens> cadastraItens(@RequestBody @Valid ItensForm form, UriComponentsBuilder uriBuilder) {

		Optional<Itens> itemExist = itensRepository.findByDescricao(form.getDescricao());

		if (itemExist.isPresent()) {

			return ResponseEntity.ok(itemExist.get());
		}

		Itens item = itensRepository.save(new Itens(form.getDescricao()));
		URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();

		return ResponseEntity.created(uri).body(item);
	}

	@PutMapping("/{idItem}")
	@Transactional
	public ResponseEntity<Itens> editarItem(@RequestBody @Valid ItensForm form, @PathVariable Long idItem)
			throws CustomNotFoundException {

		Optional<Itens> item = itensRepository.findById(idItem);

		if (item.isPresent()) {

			Itens itemNovo = form.atualizar(idItem, itensRepository);
			return ResponseEntity.ok().body(itemNovo);
		}

		throw new CustomNotFoundException("Item '" + idItem + "' não encontrado");
	}

	@PutMapping("/ativar/{idItem}")
	@Transactional
	public ResponseEntity<Itens> ativarDesativarItem(@PathVariable Long idItem) throws CustomNotFoundException {

		Optional<Itens> itemFind = itensRepository.findById(idItem);

		if (itemFind.isPresent()) {

			Itens item = itemFind.get();
			item.setAtivo(!item.getAtivo());
			
			if(!item.getAtivo()) {				
				List<Estoque> estoques = estoqueRepository.findByItem(item);
				for (Estoque estoque : estoques) {
					estoque.setAtivo(item.getAtivo());
				}
			}
			
			return ResponseEntity.ok(item);
		}

		throw new CustomNotFoundException("Item '" + idItem + "' não encontrado");
	}

	@DeleteMapping("/{idItem}")
	@Transactional
	public ResponseEntity<Itens> deletarItem(@PathVariable Long idItem) throws CustomNotFoundException {

		Optional<Itens> item = itensRepository.findById(idItem);

		if (item.isPresent()) {

			itensRepository.deleteById(idItem);

			return ResponseEntity.ok().build();
		}

		throw new CustomNotFoundException("Item '" + idItem + "' não encontrado");
	}
}
