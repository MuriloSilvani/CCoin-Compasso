package br.com.compasso.resgatestransferencias.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.resgatestransferencias.config.service.UsuariosService;
import br.com.compasso.resgatestransferencias.dto.TransferiuDto;
import br.com.compasso.resgatestransferencias.form.Status_requerimentosForm;
import br.com.compasso.resgatestransferencias.form.TransfereForm;
import br.com.compasso.resgatestransferencias.form.TransferenciasForm;
import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.model.Transferencias;
import br.com.compasso.resgatestransferencias.repository.Status_requerimentosRepository;
import br.com.compasso.resgatestransferencias.repository.TransferenciasRepository;

@CrossOrigin
@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@Autowired
	private Status_requerimentosRepository status_requerimentosRepository;

	@PutMapping("/{id_usuario}")
	@Transactional
	public ResponseEntity<TransferiuDto> transferir(@PathVariable Long id_usuario,
			@RequestBody @Valid TransfereForm form) {

		if (usuariosService.findUsuario(id_usuario) && usuariosService.findUsuario(form.getUsuario_destino())) {

			Transferencias transferencia = transferenciasRepository
					.save(new Transferencias(new TransferenciasForm(id_usuario, form)));
			
			System.out.println(transferencia.getId());

			int status = 5;

			if (usuariosService.transferencia(id_usuario, form))
				status = 1;

			Status_requerimentos status_requerimento = status_requerimentosRepository.save(new Status_requerimentos(
					new Status_requerimentosForm(transferencia.getId(), status), transferenciasRepository));

			return ResponseEntity.ok(new TransferiuDto(transferencia, status_requerimento, usuariosService));
		}

		return ResponseEntity.notFound().build();
	}
}
