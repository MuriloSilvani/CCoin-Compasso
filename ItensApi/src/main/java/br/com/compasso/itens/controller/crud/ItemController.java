package br.com.compasso.itens.controller.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.compasso.itens.dto.ItemDto;
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
	public ResponseEntity<List<ItemDto>> listarItens() {

		List<Item> itens = itemRepository.findAll();

		List<ItemDto> itensDto = itens.stream().map(ItemDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(itensDto);
	}

	@GetMapping("/{id_item}")
	public ResponseEntity<ItemDto> listarItem(@PathVariable Long id_item) {

		Optional<Item> item = itemRepository.findById(id_item);

		if (item.isPresent()) {
			return ResponseEntity.ok(new ItemDto(item.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<ItemDto> cadastrarItem(@RequestBody @Valid ItemForm itemForm) {

		Optional<Item> item = itemRepository.findByDescricao(itemForm.getDescricao());

		if (item.isPresent()) {
			return ResponseEntity.ok(new ItemDto(item.get()));
		}

		Item novoItem = itemRepository.save(new Item(itemForm.getDescricao()));

		return ResponseEntity.created(null).body(new ItemDto(novoItem));
	}

	@PutMapping("/{id_item}")
	@Transactional
	public ResponseEntity<ItemDto> editarItem(@RequestBody @Valid ItemForm form, @PathVariable Long id_item) {

		Optional<Item> item = itemRepository.findById(id_item);

		if (item.isPresent()) {

			Item novoItem = form.atualizar(item.get(), itemRepository);

			return ResponseEntity.ok(new ItemDto(novoItem));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_item}")
	@Transactional
	public ResponseEntity deletarItem(@PathVariable Long id_item) {

		Optional<Item> item = itemRepository.findById(id_item);

		if (item.isPresent()) {
			int quantidadeDeItens = item.get().getItensEstoque().size();

			if (quantidadeDeItens == 0) {
				itemRepository.deleteById(id_item);
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.notFound().build();
	}
}
