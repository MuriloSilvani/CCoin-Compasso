package br.com.compasso.itens.dto;

import br.com.compasso.itens.model.Estoque;

public class EstoqueDto {
    private Long id;
    private ItemDto item;
    private TipoItemDto tipo_item;
    private int quantidade_reservado;
    private int quantidade_disponivel;
    private float valor;
    private boolean ativo;

    public EstoqueDto(Estoque estoque) {
        this.id = estoque.getId();
        this.item = new ItemDto(estoque.getItem());
        this.tipo_item = new TipoItemDto(estoque.getTipoItem());
        this.quantidade_reservado = estoque.getQuantidadeReservado();
        this.quantidade_disponivel = estoque.getQuantidadeDisponivel();
        this.valor = estoque.getValor();
        this.ativo = estoque.getAtivo();
    }
}
