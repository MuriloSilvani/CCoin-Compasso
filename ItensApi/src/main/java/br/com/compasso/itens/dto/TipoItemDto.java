package br.com.compasso.itens.dto;

import br.com.compasso.itens.model.TipoItem;

public class TipoItemDto {
    private Long id;
    private String descricao;

    public  TipoItemDto(TipoItem tipoItem) {
        this.id = tipoItem.getId();
        this.descricao = tipoItem.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
