package br.com.compasso.itens.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Item id_item;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_item", referencedColumnName = "id")
	private TipoItem id_tipo_item;
	private int qtde_reservado;
	private int qtde_disponivel;
	private float valor;
	private boolean ativo;

	public Estoque() {

	}

	public Estoque(EstoqueForm estoque, ItemRepository itemRepository, TipoItemRepository tipoItemRepository) {

		Optional<Item> item = itemRepository.findById(estoque.getId_item());
		Optional<TipoItem> tipos_itens = tipoItemRepository.findById(estoque.getId_tipo_item());

		this.id_item = item.get();
		this.id_tipo_item = tipos_itens.get();
		this.qtde_reservado = estoque.getQtde_reservado();
		this.qtde_disponivel = estoque.getQtde_disponivel();
		this.valor = estoque.getValor();
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getId_item() {
		return id_item;
	}

	public void setId_item(Item id_item) {
		this.id_item = id_item;
	}

	public TipoItem getId_tipo_item() {
		return id_tipo_item;
	}

	public void setId_tipo_item(TipoItem id_tipo_item) {
		this.id_tipo_item = id_tipo_item;
	}

	public int getQtde_reservado() {
		return qtde_reservado;
	}

	public void setQtde_reservado(int qtde_reservado) {
		this.qtde_reservado = qtde_reservado;
	}

	public int getQtde_disponivel() {
		return qtde_disponivel;
	}

	public void setQtde_disponivel(int qtde_disponivel) {
		this.qtde_disponivel = qtde_disponivel;
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
