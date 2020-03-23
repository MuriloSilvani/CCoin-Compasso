package br.com.compasso.exchanges.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.exchanges.dto.extrato.ExtratoDto;
import br.com.compasso.exchanges.model.StatusRequerimentos;
import br.com.compasso.exchanges.model.Transferencias;
import br.com.compasso.exchanges.repository.StatusRequerimentosRepository;
import br.com.compasso.exchanges.repository.TransferenciasRepository;
import br.com.compasso.exchanges.services.UsuariosService;

@CrossOrigin
@RestController
@RequestMapping("/extrato")
public class ExtratoController {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@Autowired
	private StatusRequerimentosRepository statusRequerimentosRepository;

	@GetMapping("/{idUsuario}")
	public ResponseEntity<List<ExtratoDto>> consultaExtrato(@PathVariable Long idUsuario,
			@RequestHeader(value = "Authorization") String authorization) {

		usuariosService.validarUsuario(idUsuario, authorization);

		List<Transferencias> transferencias = transferenciasRepository.buscaTransferencias(idUsuario);

		List<ExtratoDto> entradas = new ArrayList<ExtratoDto>();

		transferencias.forEach(transferencia -> {

			List<StatusRequerimentos> statusRequerimento = statusRequerimentosRepository
					.findByTransferencia(transferencia);

			entradas.add(new ExtratoDto(idUsuario, transferencia, statusRequerimento.get(0)));
		});

		return ResponseEntity.ok(entradas);
	}
}
