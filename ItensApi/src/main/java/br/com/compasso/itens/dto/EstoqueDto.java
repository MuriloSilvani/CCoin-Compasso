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

    public Long getId() {
        return id;
    }

    public ItemDto getItem() {
        return item;
    }

    public TipoItemDto getTipo_item() {
        return tipo_item;
    }

    public int getQuantidade_reservado() {
        return quantidade_reservado;
    }

    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public float getValor() {
        return valor;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
