package br.com.compasso.usuarios.form;

import java.util.Optional;

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

		Optional<Niveis_acesso> nivel_acessoFind = niveis_acessoRepository.findById(id_nivel_acesso);

		if(nivel_acessoFind.isPresent()) {
			
			Niveis_acesso nivel_acesso = nivel_acessoFind.get();
			nivel_acesso.setDescricao(this.descricao);
	
			return nivel_acesso;
		}
	
		return null;
	}
}
