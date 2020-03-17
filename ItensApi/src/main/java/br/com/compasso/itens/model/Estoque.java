package br.com.compasso.itens.model;

import java.util.Optional;

import javax.persistence.*;

import br.com.compasso.itens.form.EstoqueForm;
import br.com.compasso.itens.repository.ItemRepository;
import br.com.compasso.itens.repository.TipoItemRepository;

@Entity
@Table(name = "estoque")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_item", referencedColumnName = "id")
	private Item item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_item", referencedColumnName = "id")
	private TipoItem tipoItem;

	@Column(name = "qtde_reservado")
	private int quantidadeReservado;

	@Column(name = "qtde_disponivel")
	private int quantidadeDisponivel;

	private float valor;
	private boolean ativo;

	public Estoque() {

	}

	public Estoque(EstoqueForm estoque, ItemRepository itemRepository, TipoItemRepository tipoItemRepository) {

		Optional<Item> item = itemRepository.findById(estoque.getIdItem());
		Optional<TipoItem> tipos_itens = tipoItemRepository.findById(estoque.getIdTipoItem());

		this.item = item.get();
		this.tipoItem = tipos_itens.get();
		this.quantidadeReservado = estoque.getQuantidadeReservado();
		this.quantidadeDisponivel = estoque.getQuantidadeDisponivel();
		this.valor = estoque.getValor();
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int getQuantidadeReservado() {
		return quantidadeReservado;
	}

	public void setQuantidadeReservado(int quantidadeReservado) {
		this.quantidadeReservado = quantidadeReservado;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
