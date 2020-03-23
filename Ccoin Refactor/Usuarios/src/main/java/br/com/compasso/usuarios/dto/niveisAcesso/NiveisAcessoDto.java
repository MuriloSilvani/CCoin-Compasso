package br.com.compasso.usuarios.dto.niveisAcesso;

import br.com.compasso.usuarios.model.NiveisAcesso;

public class NiveisAcessoDto {

	private Long id;
	private String descricao;

	public NiveisAcessoDto() {

	}

	public NiveisAcessoDto(NiveisAcesso nivelAcesso) {

		this.id = nivelAcesso.getId();
		this.descricao = nivelAcesso.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
