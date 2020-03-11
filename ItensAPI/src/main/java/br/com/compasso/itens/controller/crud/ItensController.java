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

import br.com.compasso.itens.form.ItensForm;
import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.repository.ItensRepository;

@CrossOrigin
@RestController
@RequestMapping("/itens")
public class ItensController {

	@Autowired
	ItensRepository itensRepository;

	@GetMapping("")
	public ResponseEntity<List<Itens>> listarItens() {

		List<Itens> itens = itensRepository.findAll();
		
		return ResponseEntity.ok(itens);
	}

	@GetMapping("/{id_item}")
	public ResponseEntity<Itens> listarItem(@PathVariable Long id_item) {

		Optional<Itens> item = itensRepository.findById(id_item);

		if (item.isPresent()) {
			return ResponseEntity.ok(item.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	@Transactional
	public ResponseEntity<Itens> cadastrarItem(@RequestBody @Valid Itens form) {

		Optional<Itens> item = itensRepository.findByDescricao(form.getDescricao());

		if (item.isPresent()) {
			return ResponseEntity.ok(item.get());
		}

		Itens novoItem = itensRepository.save(new Itens(form.getDescricao()));
		
		return ResponseEntity.created(null).body(novoItem);
	}
	
	@PutMapping("/{id_item}")
	@Transactional
	public ResponseEntity<Itens> editarCargo(@RequestBody @Valid ItensForm form, @PathVariable Long id_item) {

		Optional<Itens> item = itensRepository.findById(id_item);

		if (item.isPresent()) {
			
			Itens itemNovo = form.atualizar(item.get(), itensRepository);
			
			return ResponseEntity.ok(itemNovo);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id_item}")
	@Transactional
	public ResponseEntity<Itens> deletarCargo(@PathVariable Long id_item) {

		Optional<Itens> item = itensRepository.findById(id_item);
		
		if (item.isPresent()) {

			itensRepository.deleteById(id_item);
			
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
