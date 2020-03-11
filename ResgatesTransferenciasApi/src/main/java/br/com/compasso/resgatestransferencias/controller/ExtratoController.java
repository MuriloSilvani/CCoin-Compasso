package br.com.compasso.resgatestransferencias.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.resgatestransferencias.dto.ExtratoDto;
import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.model.Transferencias;
import br.com.compasso.resgatestransferencias.repository.Status_requerimentosRepository;
import br.com.compasso.resgatestransferencias.repository.TransferenciasRepository;

@CrossOrigin
@RestController
@RequestMapping("/extrato")
public class ExtratoController {

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@Autowired
	private Status_requerimentosRepository status_requerimentosRepository;

	@GetMapping("/{id_usuario}")
	public ResponseEntity<List<ExtratoDto>> consultaExtrato(@PathVariable Long id_usuario) {

		List<Transferencias> transferencias = transferenciasRepository.buscaTransferenciasDeEntrada(id_usuario);

		List<ExtratoDto> entradas = new ArrayList<ExtratoDto>();

		for (Transferencias transferencia : transferencias) {
			
			Status_requerimentos status_requerimento = status_requerimentosRepository.getOne(transferencia.getId());
			
			entradas.add(new ExtratoDto(id_usuario, transferencia, status_requerimento));
		}

		return ResponseEntity.ok(entradas);
	}
}
