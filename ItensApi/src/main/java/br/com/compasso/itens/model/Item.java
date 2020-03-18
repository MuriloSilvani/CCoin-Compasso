package br.com.compasso.itens.model;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "itens")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String descricao;

	@OneToMany(mappedBy = "item")
	private List<Estoque> itensEstoque = new ArrayList<>();

	public Item() {

	}

	public List<Estoque> getItensEstoque() {
		return itensEstoque;
	}

	public void setItensEstoque(List<Estoque> itensEstoque) {
		this.itensEstoque = itensEstoque;
	}

	public Item(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
