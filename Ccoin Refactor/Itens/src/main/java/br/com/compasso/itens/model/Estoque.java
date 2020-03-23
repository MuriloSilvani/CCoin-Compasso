package br.com.compasso.itens.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.compasso.itens.config.validation.CustomNotFoundException;
import br.com.compasso.itens.form.estoque.EstoqueForm;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.TiposItemRepository;

@Entity
@Table(name = "estoque")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_item", referencedColumnName = "id")
	private Itens item;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_item", referencedColumnName = "id")
	private TiposItem tipoItem;
	@Column(name = "qtde_reservado", columnDefinition = "int default 0")
	private int quantidadeReservado;
	@Column(name = "qtde_disponivel", columnDefinition = "int default 0")
	private int quantidadeDisponivel;
	private int valor;
	@Column(columnDefinition = "bit default 1")
	private boolean ativo;

	public Estoque() {

	}

	public Estoque(EstoqueForm estoque, ItensRepository itensRepository, TiposItemRepository tiposItemRepository)
			throws CustomNotFoundException {

		Optional<Itens> item = itensRepository.findById(estoque.getIdItem());
		if (item.isPresent()) {
			this.item = item.get();
		} else {
			throw new CustomNotFoundException("Item '" + estoque.getIdItem() + "' não encontrado");
		}
		Optional<TiposItem> tipoItem = tiposItemRepository.findById(estoque.getIdTipoItem());
		if (tipoItem.isPresent()) {
			this.tipoItem = tipoItem.get();
		} else {
			throw new CustomNotFoundException("TipoItem '" + estoque.getIdTipoItem() + "' não encontrado");
		}

		this.quantidadeReservado = 0;
		this.quantidadeDisponivel = estoque.getQuantidadeDisponivel();
		this.quantidadeReservado = estoque.getQuantidadeReservado();
		this.valor = estoque.getValor();
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Itens getItem() {
		return item;
	}

	public void setItem(Itens item) {
		this.item = item;
	}

	public TiposItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TiposItem tipoItem) {
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
