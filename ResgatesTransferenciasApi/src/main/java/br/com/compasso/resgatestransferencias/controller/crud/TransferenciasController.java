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

import br.com.compasso.resgatestransferencias.form.TransferenciasForm;
import br.com.compasso.resgatestransferencias.model.Transferencias;
import br.com.compasso.resgatestransferencias.repository.TransferenciasRepository;

@RestController
@RequestMapping("/transferencias")
public class TransferenciasController {

	@Autowired
	private TransferenciasRepository transferenciasRepository;

	@GetMapping("")
	public ResponseEntity<List<Transferencias>> listarTransferencias() {

		List<Transferencias> transferencia = transferenciasRepository.findAll();

		return ResponseEntity.ok(transferencia);
	}

	@GetMapping("{id_transferencia}")
	public ResponseEntity<Transferencias> listarTransferencia(@PathVariable Long id_transferencia) {

		Optional<Transferencias> transferencia = transferenciasRepository.findById(id_transferencia);

		if (transferencia.isPresent()) {

			return ResponseEntity.ok(transferencia.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<Transferencias> cadastrarTransferencia(@RequestBody @Valid TransferenciasForm form) {

		Transferencias transferencia = transferenciasRepository.save(new Transferencias(form));

		return ResponseEntity.created(null).body(transferencia);
	}

	@PutMapping("{id_transferencia}")
	@Transactional
	public ResponseEntity<Transferencias> editarTransferencia(@PathVariable Long id_transferencia,
			@RequestBody @Valid TransferenciasForm form) {

		Optional<Transferencias> transferenciaFind = transferenciasRepository.findById(id_transferencia);

		if (transferenciaFind.isPresent()) {

			Transferencias transferencia = form.atualizar(transferenciaFind.get());

			return ResponseEntity.ok(transferencia);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id_transferencia}")
	@Transactional
	public ResponseEntity<Transferencias> deletarTransferencia(@PathVariable Long id_transferencia) {

		Optional<Transferencias> transferencia = transferenciasRepository.findById(id_transferencia);

		if (transferencia.isPresent()) {

			transferenciasRepository.deleteById(id_transferencia);

			return ResponseEntity.ok(null);
		}

		return ResponseEntity.notFound().build();
	}
}
