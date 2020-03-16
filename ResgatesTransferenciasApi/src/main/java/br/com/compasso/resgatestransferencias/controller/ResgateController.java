package br.com.compasso.resgatestransferencias.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.resgatestransferencias.config.service.ItensService;
import br.com.compasso.resgatestransferencias.config.service.UsuariosService;
import br.com.compasso.resgatestransferencias.dto.CompraDto;
import br.com.compasso.resgatestransferencias.form.CompraForm;
import br.com.compasso.resgatestransferencias.form.Itens_resgatesForm;
import br.com.compasso.resgatestransferencias.form.ResgatesForm;
import br.com.compasso.resgatestransferencias.form.Status_requerimentosForm;
import br.com.compasso.resgatestransferencias.model.Itens_resgates;
import br.com.compasso.resgatestransferencias.model.Resgates;
import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.repository.Itens_resgatesRepository;
import br.com.compasso.resgatestransferencias.repository.ResgatesRepository;
import br.com.compasso.resgatestransferencias.repository.Status_requerimentosRepository;

@CrossOrigin
@RestController
@RequestMapping("/resgate")
public class ResgateController {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private ItensService itensService;

	@Autowired
	private ResgatesRepository resgatesRepository;

	@Autowired
	private Itens_resgatesRepository itens_resgatesRepository;

	@Autowired
	private Status_requerimentosRepository status_requerimentosRepository;

	@PostMapping("/{id_usuario}")
	public ResponseEntity<CompraDto> comprar(@PathVariable Long id_usuario, @RequestBody @Valid CompraForm form) {

		if (usuariosService.findUsuario(id_usuario)) {

			// criar resgate
			// criar itens_resgates
			// criar status_req
			// verificar estoque do item
			// verificar saldo
			// diminuir estoque
			// diminuir saldo

			Resgates resgate = resgatesRepository.save(new Resgates(new ResgatesForm(id_usuario)));
			itens_resgatesRepository
					.save(new Itens_resgates(new Itens_resgatesForm(resgate, form), resgatesRepository));

			int status = 0;
			int valor = itensService.getValor(form);

			if (usuariosService.getSaldo(id_usuario) > valor && itensService.temEstoque(form)) {

				status = 1;

				try {
					itensService.diminuiEstoque(form);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				try {
					usuariosService.diminuiSaldo(id_usuario, valor);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			} else {
				status = 5;
			}


			Status_requerimentos status_requerimento = status_requerimentosRepository.save(new Status_requerimentos(
					new Status_requerimentosForm(status, resgate.getId()), resgatesRepository));		

			return ResponseEntity.ok(new CompraDto(status_requerimento.getId_status()));

		}

		return ResponseEntity.notFound().build();
	}
}
