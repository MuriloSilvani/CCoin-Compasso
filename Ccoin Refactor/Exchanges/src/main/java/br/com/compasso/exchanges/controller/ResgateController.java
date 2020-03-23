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
import br.com.compasso.exchanges.dto.resgate.ResgateDto;
import br.com.compasso.exchanges.form.resgate.ResgateForm;
import br.com.compasso.exchanges.model.ItensResgates;
import br.com.compasso.exchanges.model.Resgates;
import br.com.compasso.exchanges.model.StatusRequerimentos;
import br.com.compasso.exchanges.model.Transferencias;
import br.com.compasso.exchanges.repository.ItensResgatesRepository;
import br.com.compasso.exchanges.repository.ResgatesRepository;
import br.com.compasso.exchanges.repository.StatusRequerimentosRepository;
import br.com.compasso.exchanges.repository.TransferenciasRepository;
import br.com.compasso.exchanges.services.ItensService;
import br.com.compasso.exchanges.services.UsuariosService;

@CrossOrigin
@RestController
@RequestMapping("/resgate")
public class ResgateController {

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@Autowired
	private StatusRequerimentosRepository statusRequerimentosRepository;

	@Autowired
	private ResgatesRepository resgatesRepository;

	@Autowired
	private ItensResgatesRepository itensResgatesRepository;

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private ItensService itensService;

	@PostMapping("/{idUsuario}")
	@Transactional
	public ResponseEntity<ResgateDto> resgate(@PathVariable Long idUsuario, @Valid @RequestBody ResgateForm form,
			@RequestHeader(value = "Authorization") String authorization) throws CustomBadRequestException {

		usuariosService.validarUsuario(idUsuario, authorization);

		int valor = itensService.validarEstoque(form);

		Resgates resgate = resgatesRepository.save(new Resgates(idUsuario));
		itensResgatesRepository.save(new ItensResgates(resgate, form.getIdEstoque(), form.getQuantidade()));
		StatusRequerimentos statusRequerimento = statusRequerimentosRepository
				.save(new StatusRequerimentos(resgate, 0));

		int saldoAtualizado;
		if (usuariosService.verificaSaldo(idUsuario, valor, authorization)) {

			saldoAtualizado = usuariosService.saque(idUsuario, valor, authorization);
			Transferencias transferencia = transferenciasRepository.save(new Transferencias(idUsuario, valor));
			itensService.retirarEstoque(form.getIdEstoque(), form.getQuantidade());
			statusRequerimento.setIdStatus(1);
			statusRequerimento.setIdTransferencia(transferencia);
		} else {

			statusRequerimento.setIdStatus(5);
			throw new CustomBadRequestException("Saldo insuficiente");
		}
		return ResponseEntity.ok(new ResgateDto(valor, "item nome", form.getQuantidade(),
				statusRequerimento.getIdStatus(), saldoAtualizado));
	}
}
