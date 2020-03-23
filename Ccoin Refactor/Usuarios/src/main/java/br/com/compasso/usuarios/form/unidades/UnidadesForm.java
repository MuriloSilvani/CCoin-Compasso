package br.com.compasso.usuarios.form.unidades;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.model.Unidades;
import br.com.compasso.usuarios.repository.UnidadesRepository;

public class UnidadesForm {

	@NotNull
	@NotEmpty
	@Length(max= 2, min= 2)
	private String uf;
	@NotNull
	@NotEmpty
	@Length(max= 50)
	private String cidade;
	@NotNull
	@NotEmpty
	@Length(max= 150)
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

	public Unidades atualizar(Long idUnidade, UnidadesRepository unidadesRepository)
			throws CustomNotFoundException {

		Optional<Unidades> unidadeFind = unidadesRepository.findById(idUnidade);

		if (unidadeFind.isPresent()) {

			Unidades unidade = unidadeFind.get();
			unidade.setUf(this.uf);
			unidade.setCidade(this.cidade);
			unidade.setEndereco(this.endereco);

			return unidade;
		}

		throw new CustomNotFoundException("Unidade '" + idUnidade + "' não encontrada");
	}

}
