package br.com.compasso.itens.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.com.compasso.itens.config.validation.CustomBadRequestException;
import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.dto.catalogo.CatalogoDto;
import br.com.compasso.itens.form.resgate.ResgateForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.repository.EstoqueRepository;

@CrossOrigin
@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping("")
	public ResponseEntity<List<CatalogoDto>> catalogoItens(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 20) Pageable paginacao) {

		Page<Estoque> estoque = estoqueRepository.buscaDisponiveis(paginacao);

		List<CatalogoDto> catalogo = estoque.stream().map(CatalogoDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(catalogo);
	}

	@PutMapping("/adicionarDisponivel/{idEstoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> adicionarEstoque(@PathVariable Long idEstoque,
			@RequestBody @Valid ResgateForm form) throws CustomNotFoundException, CustomBadRequestException {

		Optional<Estoque> estoque = estoqueRepository.findById(idEstoque);
		if (estoque.isPresent()) {

			form.addDisponivel(estoque.get());

			return ResponseEntity.ok(new CatalogoDto(estoque.get()));

		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}

	@PutMapping("/removerDisponivel/{idEstoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> removerEstoque(@PathVariable Long idEstoque,
			@RequestBody @Valid ResgateForm form) throws CustomNotFoundException, CustomBadRequestException {

		Optional<Estoque> estoque = estoqueRepository.findById(idEstoque);
		if (estoque.isPresent()) {
			form.removeDisponivel(estoque.get());

			return ResponseEntity.ok(new CatalogoDto(estoque.get()));
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}

	@PutMapping("/adicionarReservado/{idEstoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> reservaEstoque(@PathVariable Long idEstoque,
			@RequestBody @Valid ResgateForm form) throws CustomNotFoundException, CustomBadRequestException {

		Optional<Estoque> estoque = estoqueRepository.findById(idEstoque);
		if (estoque.isPresent()) {
			form.addReservado(estoque.get());

			return ResponseEntity.ok(new CatalogoDto(estoque.get()));
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}

	@PutMapping("/removerReservado/{idEstoque}")
	@Transactional
	public ResponseEntity<CatalogoDto> desreservaEstoque(@PathVariable Long idEstoque,
			@RequestBody @Valid ResgateForm form) throws CustomNotFoundException, CustomBadRequestException {

		Optional<Estoque> estoque = estoqueRepository.findById(idEstoque);
		if (estoque.isPresent()) {

			form.removeReservado(estoque.get());

			return ResponseEntity.ok(new CatalogoDto(estoque.get()));
		}

		throw new CustomNotFoundException("Estoque '" + idEstoque + "' não encontrado");
	}
}
