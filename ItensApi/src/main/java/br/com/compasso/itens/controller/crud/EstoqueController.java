package br.com.compasso.itens.controller.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.compasso.itens.dto.EstoqueDto;
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

import br.com.compasso.itens.form.EstoqueForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Item;
import br.com.compasso.itens.model.TipoItem;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItemRepository;
import br.com.compasso.itens.repository.TipoItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	EstoqueRepository estoqueRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	TipoItemRepository tipoItemRepository;

	@GetMapping("")
	public ResponseEntity<List<EstoqueDto>> listarEstoque() {

		List<Estoque> estoque = estoqueRepository.findAll();

		List<EstoqueDto> estoquesDto = estoque.stream().map(EstoqueDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(estoquesDto);
	}

	@GetMapping("/{id_estoque}")
	public ResponseEntity<EstoqueDto> listarItemEmEstoque(@PathVariable Long id_estoque) {

		Optional<Estoque> estoque = estoqueRepository.findById(id_estoque);

		if (estoque.isPresent()) {
			return ResponseEntity.ok(new EstoqueDto(estoque.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<EstoqueDto> cadastrarEstoque(@RequestBody @Valid EstoqueForm form) {

		// TODO: buscar se ja existe com o mesmo item e tipo de item pra so aumentar o disponivel ou algo assim

		Optional<Item> item = itemRepository.findById(form.getIdItem());
		Optional<TipoItem> tipo_item = tipoItemRepository.findById(form.getIdTipoItem());
		
		if(item.isPresent() && tipo_item.isPresent()) {
			
			Estoque estoque = estoqueRepository.save(new Estoque(form, itemRepository, tipoItemRepository));
			return ResponseEntity.created(null).body(new EstoqueDto(estoque));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id_estoque}")
	@Transactional
	public ResponseEntity<EstoqueDto> editarEstoque(@RequestBody @Valid EstoqueForm form, @PathVariable Long id_estoque) {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(id_estoque);
		
		if(estoqueFind.isPresent()) {
			
			Estoque estoque = form.atualizar(estoqueFind.get(), itemRepository, tipoItemRepository);
			
			return ResponseEntity.ok(new EstoqueDto(estoque));
		}
		
		return ResponseEntity.notFound().build(); 
	}

	@DeleteMapping("/{id_estoque}")
	@Transactional
	public ResponseEntity inativarEstoque(@PathVariable Long id_estoque) {

		Optional<Estoque> estoque = estoqueRepository.findById(id_estoque);
		
		if(estoque.isPresent()) {
			estoque.get().setAtivo(false);
			
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.notFound().build(); 
	}
}
