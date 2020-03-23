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
import br.com.compasso.itens.form.tiposItem.TiposItemForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.TiposItem;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.TiposItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/tiposItem")
public class TiposItemController {

	@Autowired
	private TiposItemRepository tiposItemRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping("")
	public ResponseEntity<List<TiposItem>> listarTiposTipoItem() {

		List<TiposItem> tiposItem = tiposItemRepository.findAll();

		return ResponseEntity.ok(tiposItem);
	}

	@GetMapping("/{idTipoItem}")
	public ResponseEntity<TiposItem> listarTipoItem(@PathVariable Long idTipoItem) throws CustomNotFoundException {

		Optional<TiposItem> tipoItem = tiposItemRepository.findById(idTipoItem);

		if (tipoItem.isPresent()) {
			return ResponseEntity.ok(tipoItem.get());
		}

		throw new CustomNotFoundException("TipoItem '" + idTipoItem + "' não encontrado");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TiposItem> cadastraTiposTipoItem(@RequestBody @Valid TiposItemForm form, UriComponentsBuilder uriBuilder) {

		Optional<TiposItem> tipoItemExist = tiposItemRepository.findByDescricao(form.getDescricao());

		if (tipoItemExist.isPresent()) {

			return ResponseEntity.ok(tipoItemExist.get());
		}

		TiposItem tipoItem = tiposItemRepository.save(new TiposItem(form.getDescricao()));
		URI uri = uriBuilder.path("/tiposItem/{id}").buildAndExpand(tipoItem.getId()).toUri();

		return ResponseEntity.created(uri).body(tipoItem);
	}

	@PutMapping("/{idTipoItem}")
	@Transactional
	public ResponseEntity<TiposItem> editarTipoItem(@RequestBody @Valid TiposItemForm form, @PathVariable Long idTipoItem)
			throws CustomNotFoundException {

		Optional<TiposItem> tipoItem = tiposItemRepository.findById(idTipoItem);

		if (tipoItem.isPresent()) {

			TiposItem tipoItemNovo = form.atualizar(idTipoItem, tiposItemRepository);
			return ResponseEntity.ok().body(tipoItemNovo);
		}

		throw new CustomNotFoundException("TipoItem '" + idTipoItem + "' não encontrado");
	}

	@PutMapping("/ativar/{idTipoItem}")
	@Transactional
	public ResponseEntity<TiposItem> ativarDesativarItem(@PathVariable Long idTipoItem) throws CustomNotFoundException {

		Optional<TiposItem> tipoItemFind = tiposItemRepository.findById(idTipoItem);

		if (tipoItemFind.isPresent()) {

			TiposItem tipoItem = tipoItemFind.get();
			tipoItem.setAtivo(!tipoItem.getAtivo());
			
			if(!tipoItem.getAtivo()) {				
				List<Estoque> estoques = estoqueRepository.findByTipoItem(tipoItem);
				for (Estoque estoque : estoques) {
					estoque.setAtivo(tipoItem.getAtivo());
				}
			}
			
			return ResponseEntity.ok(tipoItem);
		}

		throw new CustomNotFoundException("TipoItem '" + idTipoItem + "' não encontrado");
	}

	@DeleteMapping("/{idTipoItem}")
	@Transactional
	public ResponseEntity<TiposItem> deletarTipoItem(@PathVariable Long idTipoItem) throws CustomNotFoundException {

		Optional<TiposItem> tipoItem = tiposItemRepository.findById(idTipoItem);

		if (tipoItem.isPresent()) {

			tiposItemRepository.deleteById(idTipoItem);

			return ResponseEntity.ok().build();
		}

		throw new CustomNotFoundException("TipoItem '" + idTipoItem + "' não encontrado");
	}
}
