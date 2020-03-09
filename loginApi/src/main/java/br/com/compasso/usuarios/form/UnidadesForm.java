package br.com.compasso.usuarios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.repository.UnidadesRepository;

public class UnidadesForm {

	@NotNull
	@NotEmpty
	private String uf;
	@NotNull
	@NotEmpty
	private String cidade;
	@NotNull
	@NotEmpty
	private String endereco;

	public String getUf() {
		return uf;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public Unidades atualizar(Long id_unidade, UnidadesRepository unidadesRepository) {

		Unidades unidade = unidadesRepository.getOne(id_unidade);

		unidade.setUf(this.uf);
		unidade.setCidade(this.cidade);
		unidade.setEndereco(this.endereco);

		return unidade;
	}

}
