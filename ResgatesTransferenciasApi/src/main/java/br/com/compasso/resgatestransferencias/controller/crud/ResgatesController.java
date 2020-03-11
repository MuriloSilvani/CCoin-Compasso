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

import br.com.compasso.resgatestransferencias.form.ResgatesForm;
import br.com.compasso.resgatestransferencias.model.Resgates;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;

@RestController
@RequestMapping("/resgates")
public class ResgatesController {

	@Autowired
	private ResgatesRepository resgatesRepository;
	
	@GetMapping("")
	public ResponseEntity<List<Resgates>> listarResgates(){
		
		List<Resgates> resgate = resgatesRepository.findAll();
		
		return ResponseEntity.ok(resgate);
	}
	
	@GetMapping("{id_resgate}")
	public ResponseEntity<Resgates> listarResgate(@PathVariable Long id_resgate){
		
		Optional<Resgates> resgate = resgatesRepository.findById(id_resgate);
		
		if(resgate.isPresent()) {
						
			return ResponseEntity.ok(resgate.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	@Transactional
	public ResponseEntity<Resgates> cadastrarResgate(@RequestBody @Valid ResgatesForm form){
		
		Resgates resgate = resgatesRepository.save(new Resgates(form));
		
		return ResponseEntity.created(null).body(resgate);
	}
	
	@PutMapping("{id_resgate}")
	@Transactional
	public ResponseEntity<Resgates> editarResgate(@PathVariable Long id_resgate, @RequestBody @Valid ResgatesForm form){
		
		Optional<Resgates> resgateFind = resgatesRepository.findById(id_resgate);
		
		if(resgateFind.isPresent()) {
			
			Resgates resgate = form.atualizar(resgateFind.get());
			
			return ResponseEntity.ok(resgate);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id_resgate}")
	@Transactional
	public ResponseEntity<Resgates> deletarResgate(@PathVariable Long id_resgate){
		
		Optional<Resgates> resgateFind = resgatesRepository.findById(id_resgate);
		
		if(resgateFind.isPresent()) {
			
			resgatesRepository.deleteById(id_resgate);
			
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.notFound().build();
	}
}
