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

import br.com.compasso.itens.form.EstoqueForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.Tipos_itensRepository;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	EstoqueRepository estoqueRepository;

	@Autowired
	ItensRepository itensRepository;

	@Autowired
	Tipos_itensRepository tipos_itensRepository;

	@GetMapping("")
	public ResponseEntity<List<Estoque>> listarItens() {
		
		List<Estoque> estoque = estoqueRepository.findAll();
		
		return ResponseEntity.ok(estoque);
	}

	@GetMapping("/{id_estoque}")
	public ResponseEntity<Estoque> listarItem(@PathVariable Long id_estoque) {
		
		Optional<Estoque> estoque = estoqueRepository.findById(id_estoque);
		
		if (estoque.isPresent()) {
			return ResponseEntity.ok(estoque.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	@Transactional
	public ResponseEntity<Estoque> cadastrarItem(@RequestBody @Valid EstoqueForm form) {

//		Optional<Itens> item = itensRepository.findById(form.getId_item().getId());

		System.out.println("teste");
		
//		if (estoque.isPresent()) {
//			return ResponseEntity.ok(estoque.get());
//		}
//
//		Estoque novoEstoque = estoqueRepository.save(form);
//		
//		System.out.println(novoEstoque);
//		
//		return ResponseEntity.created(null).body(novoEstoque);
		
		
		
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/{id_estoque}")
	@Transactional
	public ResponseEntity<Estoque> editarCargo(@RequestBody @Valid Estoque form, @PathVariable Long id_estoque) {

		return null;
	}
	
	@DeleteMapping("/{id_estoque}")
	@Transactional
	public ResponseEntity<Estoque> deletarCargo(@PathVariable Long id_estoque) {

		return null;
	}
}
