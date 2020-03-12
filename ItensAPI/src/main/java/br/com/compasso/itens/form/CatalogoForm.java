package br.com.compasso.itens.form;

import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Estoque;

public class CatalogoForm {

	@NotNull
	private int qtde;

	public int getQtde() {
		return qtde;
	}

	public boolean addDisponivel(Estoque estoque) {

		estoque.setQtde_disponivel(estoque.getQtde_disponivel() + this.qtde);
		return true;
	}

	public boolean removeDisponivel(Estoque estoque) {

		if(estoque.getQtde_disponivel() >= this.qtde) {		
			
			estoque.setQtde_disponivel(estoque.getQtde_disponivel() - this.qtde);
			return true;
		}
		return false;
	}
	
	public boolean addReservado(Estoque estoque) {

		if(estoque.getQtde_disponivel() >= estoque.getQtde_reservado()) {		
			
			estoque.setQtde_reservado(estoque.getQtde_reservado() + this.qtde);
			return true;
		}
		return false;
	}

	public boolean removeReservado(Estoque estoque) {

		if(estoque.getQtde_reservado() >= this.qtde) {		
			
			estoque.setQtde_reservado(estoque.getQtde_reservado() - this.qtde);
			return true;
		}
		return false;
	}

	public boolean statusEstoque(Estoque estoque) {

		System.out.println(estoque);
		estoque.setAtivo(!estoque.getAtivo());
		
		return true;
	}

}
