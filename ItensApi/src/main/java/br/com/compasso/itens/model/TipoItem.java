package br.com.compasso.itens.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipos_itens")
public class TipoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String descricao;

	@OneToMany(mappedBy = "tipoItem")
	private List<Estoque> tiposItensEstoque = new ArrayList<>();

	public TipoItem() {

	}

	public List<Estoque> getTiposItensEstoque() {
		return tiposItensEstoque;
	}

	public void setTiposItensEstoque(List<Estoque> tiposItensEstoque) {
		this.tiposItensEstoque = tiposItensEstoque;
	}

	public TipoItem(String descricao) {
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
