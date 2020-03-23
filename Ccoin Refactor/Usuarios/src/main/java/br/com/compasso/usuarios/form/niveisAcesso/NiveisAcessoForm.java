package br.com.compasso.usuarios.form.niveisAcesso;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.model.NiveisAcesso;
import br.com.compasso.usuarios.repository.NiveisAcessoRepository;

public class NiveisAcessoForm {

	@NotNull
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public NiveisAcesso atualizar(Long idNivelAcesso, NiveisAcessoRepository niveisAcessoRepository) throws CustomNotFoundException {

		Optional<NiveisAcesso> nivelAcessoFind = niveisAcessoRepository.findById(idNivelAcesso);

		if (nivelAcessoFind.isPresent()) {

			NiveisAcesso nivelAcesso = nivelAcessoFind.get();
			nivelAcesso.setDescricao(this.descricao);

			return nivelAcesso;
		}

		throw new CustomNotFoundException("Nivel acesso '" + idNivelAcesso +"' não encontrado");
	}

}
