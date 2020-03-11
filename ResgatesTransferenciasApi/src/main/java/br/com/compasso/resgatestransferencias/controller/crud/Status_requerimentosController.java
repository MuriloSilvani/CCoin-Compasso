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

import br.com.compasso.resgatestransferencias.form.Status_requerimentosForm;
import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;
import br.com.compasso.resgatestransferencias.repository.Status_requerimentosRepository;
import br.com.compasso.resgatestransferencias.repository.TransferenciasRepository;

@RestController
@RequestMapping("/status_requerimentos")
public class Status_requerimentosController {

	@Autowired
	private Status_requerimentosRepository status_requerimentosRepository;

	@Autowired
	private ResgatesRepository resgatesRepository;

	@Autowired
	private TransferenciasRepository transferenciasRepository;
	
	@GetMapping("")
	public ResponseEntity<List<Status_requerimentos>> listarStatus_requerimentos(){
		
		List<Status_requerimentos> status_requerimento = status_requerimentosRepository.findAll();
		
		return ResponseEntity.ok(status_requerimento);
	}
	
	@GetMapping("{id_status_requerimento}")
	public ResponseEntity<Status_requerimentos> listarStatus_requerimento(@PathVariable Long id_status_requerimento){
		
		Optional<Status_requerimentos> status_requerimento = status_requerimentosRepository.findById(id_status_requerimento);
		
		if(status_requerimento.isPresent()) {
						
			return ResponseEntity.ok(status_requerimento.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	@Transactional
	public ResponseEntity<Status_requerimentos> cadastrarStatus_requerimento(@RequestBody @Valid Status_requerimentosForm form){
		
		Status_requerimentos status_requerimento = status_requerimentosRepository.save(new Status_requerimentos(form, resgatesRepository, transferenciasRepository));
		
		return ResponseEntity.created(null).body(status_requerimento);
	}
	
	@PutMapping("{id_status_requerimento}")
	@Transactional
	public ResponseEntity<Status_requerimentos> editarStatus_requerimento(@PathVariable Long id_status_requerimento, @RequestBody @Valid Status_requerimentosForm form){
		
		Optional<Status_requerimentos> status_requerimentoFind = status_requerimentosRepository.findById(id_status_requerimento);
		
		if(status_requerimentoFind.isPresent()) {
			
			Status_requerimentos status_requerimento = form.atualizar(status_requerimentoFind.get(), resgatesRepository, transferenciasRepository);
			
			return ResponseEntity.ok(status_requerimento);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id_status_requerimento}")
	@Transactional
	public ResponseEntity<Status_requerimentos> deletarStatus_requerimento(@PathVariable Long id_status_requerimento){
		
		Optional<Status_requerimentos> status_requerimento = status_requerimentosRepository.findById(id_status_requerimento);
		
		if(status_requerimento.isPresent()) {
			
			status_requerimentosRepository.deleteById(id_status_requerimento);
			
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.notFound().build();
	}
}
