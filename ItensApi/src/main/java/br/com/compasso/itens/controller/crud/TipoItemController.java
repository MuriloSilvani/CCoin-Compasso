package br.com.compasso.itens.controller.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.compasso.itens.dto.TipoItemDto;
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
	public ResponseEntity<List<TipoItemDto>> listarTipoItens() {

		List<TipoItem> tiposItens = tipoItemRepository.findAll();
		List<TipoItemDto> tiposItensDto = tiposItens.stream().map(TipoItemDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(tiposItensDto);
	}

	@GetMapping("/{id_tipo_item}")
	public ResponseEntity<TipoItemDto> listarTipoItem(@PathVariable Long id_tipo_item) {

		Optional<TipoItem> tipoItem = tipoItemRepository.findById(id_tipo_item);

		if (tipoItem.isPresent()) {
			return ResponseEntity.ok(new TipoItemDto(tipoItem.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<TipoItemDto> cadastrarTipoItem(@RequestBody @Valid TipoItemForm form) {

		Optional<TipoItem> tipoItem = tipoItemRepository.findByDescricao(form.getDescricao());

		if (tipoItem.isPresent()) {
			return ResponseEntity.ok(new TipoItemDto(tipoItem.get()));
		}

		TipoItem novoTipoItem = tipoItemRepository.save(new TipoItem(form.getDescricao()));
		
		return ResponseEntity.created(null).body(new TipoItemDto(novoTipoItem));
	}

	@PutMapping("/{id_tipo_item}")
	@Transactional
	public ResponseEntity<TipoItemDto> editarTipoItem(@RequestBody @Valid TipoItemForm form, @PathVariable Long id_tipo_item) {
		
		Optional<TipoItem> tipoItem = tipoItemRepository.findById(id_tipo_item);

		if (tipoItem.isPresent()) {
			
			TipoItem novoTipoItem = form.atualizar(tipoItem.get(), tipoItemRepository);
			
			return ResponseEntity.ok(new TipoItemDto(novoTipoItem));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_tipo_item}")
	@Transactional
	public ResponseEntity deletarTipoItem(@PathVariable Long id_tipo_item) {

		Optional<TipoItem> tipoItem = tipoItemRepository.findById(id_tipo_item);
		
		if (tipoItem.isPresent()) {
			int quantidadeTiposItensEstoque = tipoItem.get().getTiposItensEstoque().size();

			if (quantidadeTiposItensEstoque == 0) {
				tipoItemRepository.deleteById(id_tipo_item);
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
}
