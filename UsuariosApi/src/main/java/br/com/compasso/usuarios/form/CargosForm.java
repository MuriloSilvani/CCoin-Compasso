package br.com.compasso.usuarios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.model.Cargos;
import br.com.compasso.usuarios.repository.CargosRepository;

public class CargosForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Cargos atualizar(Long id_cargo, CargosRepository cargosRepository) {

		Cargos cargo = cargosRepository.getOne(id_cargo);

		cargo.setDescricao(this.descricao);

		return cargo;
	}

}
