package br.com.compasso.exchanges.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.exchanges.config.validation.CustomBadRequestException;
import br.com.compasso.exchanges.dto.transferencia.TransferenciaDto;
import br.com.compasso.exchanges.form.transferencia.TransferenciaForm;
import br.com.compasso.exchanges.model.StatusRequerimentos;
import br.com.compasso.exchanges.model.Transferencias;
import br.com.compasso.exchanges.repository.StatusRequerimentosRepository;
import br.com.compasso.exchanges.repository.TransferenciasRepository;
import br.com.compasso.exchanges.services.UsuariosService;

@CrossOrigin
@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@Autowired
	private StatusRequerimentosRepository statusRequerimentosRepository;

	@Autowired
	private UsuariosService usuariosService;

	@PostMapping("/{idUsuarioOrigem}")
	@Transactional
	public ResponseEntity<TransferenciaDto> transferir(@PathVariable Long idUsuarioOrigem,
			@RequestBody @Valid TransferenciaForm form, @RequestHeader(value = "Authorization") String authorization)
			throws CustomBadRequestException {

		usuariosService.validarUsuario(idUsuarioOrigem, authorization);
		usuariosService.validarUsuario(form.getIdUsuarioDestino(), authorization);
		Transferencias transferencia = transferenciasRepository
				.save(new Transferencias(idUsuarioOrigem, form.getIdUsuarioDestino(), form.getValor()));
		StatusRequerimentos statusRequerimento = statusRequerimentosRepository
				.save(new StatusRequerimentos(transferencia, 0));

		int saldoAtualizado;
		if (usuariosService.verificaSaldo(idUsuarioOrigem, form.getValor(), authorization)) {

			saldoAtualizado = usuariosService.saque(idUsuarioOrigem, form.getValor(), authorization);
			usuariosService.deposito(form.getIdUsuarioDestino(), form.getValor(), authorization);
			statusRequerimento.setIdStatus(1);
		} else {

			statusRequerimento.setIdStatus(5);
			throw new CustomBadRequestException("Saldo insuficiente");
		}

		return ResponseEntity.ok(new TransferenciaDto(form.getValor(), statusRequerimento.getIdStatus(),
				statusRequerimento.getDataStatus(), saldoAtualizado));
	}
}
