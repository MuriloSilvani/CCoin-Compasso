package br.com.compasso.itens.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.itens.form.Tipos_itensForm;
import br.com.compasso.itens.model.Tipos_itens;
import br.com.compasso.itens.repository.Tipos_itensRepository;

@RestController
@RequestMapping("/tipos_itens")
public class Tipos_itensController {

	@Autowired
	Tipos_itensRepository tipos_itensRepository;

	@GetMapping("")
	public ResponseEntity<List<Tipos_itens>> listarItens() {

		List<Tipos_itens> tipos_itens = tipos_itensRepository.findAll();

		return ResponseEntity.ok(tipos_itens);
	}

	@GetMapping("/{id_tipo_item}")
	public ResponseEntity<Tipos_itens> listarItem(@PathVariable Long id_tipo_item) {

		Optional<Tipos_itens> tipo_item = tipos_itensRepository.findById(id_tipo_item);

		if (tipo_item.isPresent()) {
			return ResponseEntity.ok(tipo_item.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<Tipos_itens> cadastrarItem(@RequestBody @Valid Tipos_itensForm form) {

		Optional<Tipos_itens> tipo_item = tipos_itensRepository.findByDescricao(form.getDescricao());

		if (tipo_item.isPresent()) {
			return ResponseEntity.ok(tipo_item.get());
		}

		Tipos_itens novoTipo_item = tipos_itensRepository.save(new Tipos_itens(form.getDescricao()));
		
		return ResponseEntity.created(null).body(novoTipo_item);
	}

	@PutMapping("/{id_tipo_item}")
	@Transactional
	public ResponseEntity<Tipos_itens> editarCargo(@RequestBody @Valid Tipos_itensForm form, @PathVariable Long id_tipo_item) {
		
		Optional<Tipos_itens> tipo_item = tipos_itensRepository.findById(id_tipo_item);

		if (tipo_item.isPresent()) {
			
			Tipos_itens tipo_itemNovo = form.atualizar(tipo_item.get(), tipos_itensRepository);
			
			return ResponseEntity.ok(tipo_itemNovo);
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id_tipo_item}")
	@Transactional
	public ResponseEntity<Tipos_itens> deletarCargo(@PathVariable Long id_tipo_item) {

		Optional<Tipos_itens> tipo_item = tipos_itensRepository.findById(id_tipo_item);
		
		if (tipo_item.isPresent()) {

			tipos_itensRepository.deleteById(id_tipo_item);
			
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
