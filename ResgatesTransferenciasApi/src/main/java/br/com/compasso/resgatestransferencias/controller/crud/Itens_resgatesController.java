package br.com.compasso.resgatestransferencias.controller.crud;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.resgatestransferencias.form.Itens_resgatesForm;
import br.com.compasso.resgatestransferencias.model.Itens_resgates;
import br.com.compasso.resgatestransferencias.repository.Itens_resgatesRepository;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;

@RestController
@RequestMapping("/itens_resgates")
public class Itens_resgatesController {

	@Autowired
	private Itens_resgatesRepository itens_resgatesRepository;

	@Autowired
	private ResgatesRepository resgatesRepository;

	@GetMapping("")
	public ResponseEntity<List<Itens_resgates>> listarItens_resgates() {

		List<Itens_resgates> itens_resgates = itens_resgatesRepository.findAll();

		return ResponseEntity.ok(itens_resgates);
	}

	@GetMapping("{id_item_resgate}")
	public ResponseEntity<Itens_resgates> listarItens_resgates(@PathVariable Long id_item_resgate) {

		Optional<Itens_resgates> item_resgate = itens_resgatesRepository.findById(id_item_resgate);

		if (item_resgate.isPresent()) {

			return ResponseEntity.ok(item_resgate.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<Itens_resgates> cadastrarItens_resgates(@Valid @RequestBody Itens_resgatesForm form) {

		Itens_resgates item_resgate = itens_resgatesRepository.save(new Itens_resgates(form, resgatesRepository));

		return ResponseEntity.created(null).body(item_resgate);
	}

	@PutMapping("{id_item_resgate}")
	@Transactional
	public ResponseEntity<Itens_resgates> editarItens_resgates(@PathVariable Long id_item_resgate,
			@RequestBody @Valid Itens_resgatesForm form) {

		Optional<Itens_resgates> resgateFind = itens_resgatesRepository.findById(id_item_resgate);

		if (resgateFind.isPresent()) {

			Itens_resgates item_resgate = form.atualizar(resgateFind.get(), resgatesRepository);

			return ResponseEntity.ok(item_resgate);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id_item_resgate}")
	@Transactional
	public ResponseEntity<Itens_resgates> deletarItens_resgates(@PathVariable Long id_item_resgate) {

		Optional<Itens_resgates> item_resgate = itens_resgatesRepository.findById(id_item_resgate);

		if (item_resgate.isPresent()) {

			itens_resgatesRepository.deleteById(id_item_resgate);

			return ResponseEntity.ok(null);
		}

		return ResponseEntity.notFound().build();
	}
}
