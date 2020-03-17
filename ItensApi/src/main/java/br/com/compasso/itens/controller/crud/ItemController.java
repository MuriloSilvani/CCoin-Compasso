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

import br.com.compasso.itens.form.ItemForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Item;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/itens")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping("")
	public ResponseEntity<List<Item>> listarItens() {

		List<Item> itens = itemRepository.findAll();

		return ResponseEntity.ok(itens);
	}

	@GetMapping("/{id_item}")
	public ResponseEntity<Item> listarItem(@PathVariable Long id_item) {

		Optional<Item> item = itemRepository.findById(id_item);

		if (item.isPresent()) {
			return ResponseEntity.ok(item.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<Item> cadastrarItem(@RequestBody @Valid Item form) {

		Optional<Item> item = itemRepository.findByDescricao(form.getDescricao());

		if (item.isPresent()) {
			return ResponseEntity.ok(item.get());
		}

		Item novoItem = itemRepository.save(new Item(form.getDescricao()));

		return ResponseEntity.created(null).body(novoItem);
	}

	@PutMapping("/{id_item}")
	@Transactional
	public ResponseEntity<Item> editarCargo(@RequestBody @Valid ItemForm form, @PathVariable Long id_item) {

		Optional<Item> item = itemRepository.findById(id_item);

		if (item.isPresent()) {

			Item itemNovo = form.atualizar(item.get(), itemRepository);

			return ResponseEntity.ok(itemNovo);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_item}")
	@Transactional
	public ResponseEntity<Item> deletarCargo(@PathVariable Long id_item) {

		Optional<Item> item = itemRepository.findById(id_item);

		if (item.isPresent()) {
			List<Estoque> findAll = estoqueRepository.findAll();
			int aux = 0;
			
			for (Estoque estoque : findAll) {
				if (estoque.getItem().getId() == item.get().getId()) {
					aux = 1;
				}
			}

			if (aux != 1) {

				itemRepository.deleteById(id_item);
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.notFound().build();
	}
}
