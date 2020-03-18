package br.com.compasso.itens.dto;

import br.com.compasso.itens.model.Item;

public class ItemDto {
    private Long id;
    private String descricao;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.descricao = item.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
