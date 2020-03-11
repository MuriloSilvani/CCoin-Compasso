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
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.Tipos_itensRepository;

@Entity
@Table(name = "estoque")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_item", referencedColumnName = "id")
	private Itens id_item;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_item", referencedColumnName = "id")
	private Tipos_itens id_tipo_item;
	private int qtde_reservado;
	private int qtde_disponivel;
	private float valor;

	public Estoque() {

	}

	public Estoque(EstoqueForm estoque, ItensRepository itensRepository, Tipos_itensRepository tipos_itensRepository) {

		Optional<Itens> item = itensRepository.findById(estoque.getId_item());
		Optional<Tipos_itens> tipos_itens = tipos_itensRepository.findById(estoque.getId_tipo_item());

		this.id_item = item.get();
		this.id_tipo_item = tipos_itens.get();
		this.qtde_reservado = estoque.getQtde_reservado();
		this.qtde_disponivel = estoque.getQtde_disponivel();
		this.valor = estoque.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Itens getId_item() {
		return id_item;
	}

	public void setId_item(Itens id_item) {
		this.id_item = id_item;
	}

	public Tipos_itens getId_tipo_item() {
		return id_tipo_item;
	}

	public void setId_tipo_item(Tipos_itens id_tipo_item) {
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

}
