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

import br.com.compasso.resgatestransferencias.config.service.UsuariosService;
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
	private UsuariosService usuariosService;

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@Autowired
	private Status_requerimentosRepository status_requerimentosRepository;

	@GetMapping("/{id_usuario}")
	public ResponseEntity<List<ExtratoDto>> consultaExtrato(@PathVariable Long id_usuario) {

		if (usuariosService.findUsuario(id_usuario)) {

			List<Transferencias> transferencias = transferenciasRepository.buscaTransferenciasDeEntrada(id_usuario);
			List<ExtratoDto> entradas = new ArrayList<ExtratoDto>();

			transferencias.forEach(transferencia -> {

				List<Status_requerimentos> status_requerimento = status_requerimentosRepository
						.findByTransferencia(transferencia);

				entradas.add(new ExtratoDto(id_usuario, transferencia, status_requerimento.get(0)));
			});

			return ResponseEntity.ok(entradas);
		}
		
		return ResponseEntity.notFound().build();

	}
}
