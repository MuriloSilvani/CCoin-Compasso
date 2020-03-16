package br.com.compasso.itens.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.itens.dto.CatalogoDto;
import br.com.compasso.itens.form.CatalogoForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.repository.EstoqueRepository;

@CrossOrigin
@RestController
@RequestMapping("/catalogo")
public class catalogoController {

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@GetMapping("")
	public ResponseEntity<List<CatalogoDto>> catalogoItens(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 20) Pageable paginacao){
		
		Page<Estoque> estoque = estoqueRepository.buscaDisponiveis(paginacao);
		
		List<CatalogoDto> catalogo = new ArrayList<CatalogoDto>();
		
		estoque.forEach(item -> {
			catalogo.add(new CatalogoDto(item));
		});
		
		return ResponseEntity.ok(catalogo);
	}
	
	@PutMapping("/adicionarDisponivel/{id_estoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> adicionarEstoque(@PathVariable Long id_estoque, @RequestBody @Valid CatalogoForm form) {
		
		Estoque estoque = estoqueRepository.getOne(id_estoque);
		if(form.addDisponivel(estoque)) {
			
			return ResponseEntity.ok(new CatalogoDto(estoque));
		};
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/removerDisponivel/{id_estoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> removerEstoque(@PathVariable Long id_estoque, @RequestBody @Valid CatalogoForm form) {
				
		Estoque estoque = estoqueRepository.getOne(id_estoque);
		if(form.removeDisponivel(estoque)) {
			
			return ResponseEntity.ok(new CatalogoDto(estoque));
		};
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/adicionarReservado/{id_estoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> reservaEstoque(@PathVariable Long id_estoque, @RequestBody @Valid CatalogoForm form) {
		
		Estoque estoque = estoqueRepository.getOne(id_estoque);
		if(form.addReservado(estoque)) {
			
			return ResponseEntity.ok(new CatalogoDto(estoque));
		};
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/removerReservado/{id_estoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> desreservaEstoque(@PathVariable Long id_estoque, @RequestBody @Valid CatalogoForm form) {
		
		Estoque estoque = estoqueRepository.getOne(id_estoque);
		if(form.removeReservado(estoque)) {
			
			return ResponseEntity.ok(new CatalogoDto(estoque));
		};
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/statusEstoque/{id_estoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> statusEstoque(@PathVariable Long id_estoque, @RequestBody @Valid CatalogoForm form) {
		
		Estoque estoque = estoqueRepository.getOne(id_estoque);
		if(form.statusEstoque(estoque)) {
			
			return ResponseEntity.ok(new CatalogoDto(estoque));
		};
		
		return ResponseEntity.badRequest().build();
	}
}
