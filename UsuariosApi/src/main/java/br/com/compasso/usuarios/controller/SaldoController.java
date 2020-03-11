package br.com.compasso.usuarios.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.usuarios.dto.DepositoDto;
import br.com.compasso.usuarios.dto.SaldoDto;
import br.com.compasso.usuarios.dto.TransferenciaDto;
import br.com.compasso.usuarios.form.DepositoForm;
import br.com.compasso.usuarios.form.TransferenciaForm;
import br.com.compasso.usuarios.model.Usuarios;
import br.com.compasso.usuarios.repository.UsuariosRepository;

@CrossOrigin
@RestController
@RequestMapping("/saldo")
public class SaldoController {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<SaldoDto> consultaSaldo(@PathVariable Long id_usuario) {
		
		Optional<Usuarios> usuario = usuariosRepository.findById(id_usuario);
		
		if(usuario.isPresent()) {			
			
			return ResponseEntity.ok(new SaldoDto(usuario.get().getCredito()));
		}
		
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/deposito/{id_usuario}")
	@Transactional
	public ResponseEntity<DepositoDto> deposito(@PathVariable Long id_usuario, @Valid @RequestBody DepositoForm form){
		
		Optional<Usuarios> usuario = usuariosRepository.findById(id_usuario);
		
		if(usuario.isPresent()) {
			
			boolean deposito = form.deposito(usuario.get());
			
			return ResponseEntity.ok(new DepositoDto(usuario.get().getCredito(), deposito));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/saque/{id_usuario}")
	@Transactional
	public ResponseEntity<DepositoDto> saque(@PathVariable Long id_usuario, @Valid @RequestBody DepositoForm form){
		
		Optional<Usuarios> usuario = usuariosRepository.findById(id_usuario);
		
		if(usuario.isPresent()) {
			
			boolean saque = form.saque(usuario.get());
			
			return ResponseEntity.ok(new DepositoDto(usuario.get().getCredito(), saque));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/transferencia/{id_usuario}")
	@Transactional
	public ResponseEntity<TransferenciaDto> transferencia(@PathVariable Long id_usuario, @Valid @RequestBody TransferenciaForm form){
		
		Optional<Usuarios> usuario_origem = usuariosRepository.findById(id_usuario);
		Optional<Usuarios> usuario_destino = usuariosRepository.findById(form.getUsuario_destino());
		
		if(usuario_destino.isPresent() && usuario_origem.isPresent()) {
			
			boolean transferencia = form.transferencia(usuario_destino.get(), usuario_origem.get());
			
			return ResponseEntity.ok(new TransferenciaDto(usuario_origem.get().getCredito(), usuario_destino.get().getId(), transferencia));
		}
		
		return ResponseEntity.notFound().build();
	}
}
