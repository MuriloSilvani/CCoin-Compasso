package br.com.compasso.itens.controller.crud;

import java.util.ArrayList;
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

import br.com.compasso.itens.dto.EstoqueDto;
import br.com.compasso.itens.form.EstoqueForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.Tipos_itensRepository;

@CrossOrigin
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
	public ResponseEntity<List<EstoqueDto>> listarItens() {

		List<Estoque> estoqueFind = estoqueRepository.findAll();
		List<EstoqueDto> estoque = new ArrayList<EstoqueDto>();

		for (Estoque es : estoqueFind) {
			estoque.add(new EstoqueDto(es));
		}

		return ResponseEntity.ok(estoque);
	}

	@GetMapping("/{id_estoque}")
	public ResponseEntity<EstoqueDto> listarItem(@PathVariable Long id_estoque) {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(id_estoque);

		if (estoqueFind.isPresent()) {

			EstoqueDto estoque = new EstoqueDto(estoqueFind.get());

			return ResponseEntity.ok(estoque);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<EstoqueDto> cadastrarItem(@RequestBody @Valid EstoqueForm form) {

		// buscar se ja existe com o mesmo item e tipo de item pra so aumentar o disponivel ou algo assim

		Estoque estoque = estoqueRepository.save(new Estoque(form, itensRepository, tipos_itensRepository));
		
		return ResponseEntity.created(null).body(new EstoqueDto(estoque));

	}

	@PutMapping("/{id_estoque}")
	@Transactional
	public ResponseEntity<EstoqueDto> editarCargo(@RequestBody @Valid EstoqueForm form, @PathVariable Long id_estoque) {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(id_estoque);
		
		if(estoqueFind.isPresent()) {
			
			Estoque estoque = form.atualizar(estoqueFind.get(), itensRepository, tipos_itensRepository);
			
			return ResponseEntity.ok(new EstoqueDto(estoque));
		}
		
		return ResponseEntity.notFound().build(); 
	}

	@DeleteMapping("/{id_estoque}")
	@Transactional
	public ResponseEntity<EstoqueDto> deletarCargo(@PathVariable Long id_estoque) {

		Optional<Estoque> estoqueFind = estoqueRepository.findById(id_estoque);
		
		if(estoqueFind.isPresent()) {
			
			estoqueRepository.deleteById(id_estoque);
			
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
//	@GetMapping("teste/{id_item}/{id_tipo_item}")
//	public ResponseEntity<List<Estoque>> teste(@PathVariable Long id_item, @PathVariable Long id_tipo_item){
//		
//
//		for (Estoque est : estoque) {
//			System.out.println(est.getId());
//		}
//		
//		return ResponseEntity.ok(estoque);
//	}
}
