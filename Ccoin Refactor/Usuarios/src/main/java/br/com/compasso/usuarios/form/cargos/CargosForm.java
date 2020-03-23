package br.com.compasso.usuarios.form.cargos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.repository.CargosRepository;

public class CargosForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Cargos atualizar(Long idCargo, CargosRepository cargosRepository) throws CustomNotFoundException {

		Optional<Cargos> cargoFind = cargosRepository.findById(idCargo);

		if (cargoFind.isPresent()) {

			Cargos cargo = cargoFind.get();
			cargo.setDescricao(this.descricao);

			return cargo;
		}

		throw new CustomNotFoundException("Cargo '" + idCargo + "' não encontrado");
	}

}
