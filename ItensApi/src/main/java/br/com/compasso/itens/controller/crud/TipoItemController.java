package br.com.compasso.itens.controller.crud;

import java.util.List;
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

import br.com.compasso.itens.form.TipoItemForm;
import br.com.compasso.itens.model.TipoItem;
import br.com.compasso.itens.repository.TipoItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/tipos_itens")
public class TipoItemController {

	@Autowired
	TipoItemRepository tipoItemRepository;

	@GetMapping("")
	public ResponseEntity<List<TipoItem>> listarItens() {

		List<TipoItem> tipos_itens = tipoItemRepository.findAll();

		return ResponseEntity.ok(tipos_itens);
	}

	@GetMapping("/{id_tipo_item}")
	public ResponseEntity<TipoItem> listarItem(@PathVariable Long id_tipo_item) {

		Optional<TipoItem> tipo_item = tipoItemRepository.findById(id_tipo_item);

		if (tipo_item.isPresent()) {
			return ResponseEntity.ok(tipo_item.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<TipoItem> cadastrarItem(@RequestBody @Valid TipoItemForm form) {

		Optional<TipoItem> tipo_item = tipoItemRepository.findByDescricao(form.getDescricao());

		if (tipo_item.isPresent()) {
			return ResponseEntity.ok(tipo_item.get());
		}

		TipoItem novoTipo_item = tipoItemRepository.save(new TipoItem(form.getDescricao()));
		
		return ResponseEntity.created(null).body(novoTipo_item);
	}

	@PutMapping("/{id_tipo_item}")
	@Transactional
	public ResponseEntity<TipoItem> editarCargo(@RequestBody @Valid TipoItemForm form, @PathVariable Long id_tipo_item) {
		
		Optional<TipoItem> tipo_item = tipoItemRepository.findById(id_tipo_item);

		if (tipo_item.isPresent()) {
			
			TipoItem tipo_itemNovo = form.atualizar(tipo_item.get(), tipoItemRepository);
			
			return ResponseEntity.ok(tipo_itemNovo);
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_tipo_item}")
	@Transactional
	public ResponseEntity<TipoItem> deletarCargo(@PathVariable Long id_tipo_item) {

		Optional<TipoItem> tipo_item = tipoItemRepository.findById(id_tipo_item);
		
		if (tipo_item.isPresent()) {

			tipoItemRepository.deleteById(id_tipo_item);
			
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
