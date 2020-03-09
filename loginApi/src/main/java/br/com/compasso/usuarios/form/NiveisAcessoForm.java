package br.com.compasso.usuarios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.model.Niveis_acesso;
import br.com.compasso.usuarios.repository.Niveis_acessoRepository;

public class NiveisAcessoForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Niveis_acesso atualizar(Long id_nivel_acesso, Niveis_acessoRepository niveis_acessoRepository) {

		Niveis_acesso nivel_acesso = niveis_acessoRepository.getOne(id_nivel_acesso);

		nivel_acesso.setDescricao(this.descricao);

		return nivel_acesso;
	}
}
