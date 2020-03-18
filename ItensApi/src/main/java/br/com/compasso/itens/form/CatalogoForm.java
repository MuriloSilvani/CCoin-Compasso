package br.com.compasso.itens.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.Estoque;

public class CatalogoForm {

	@NotNull @Min(0)
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public boolean addDisponivel(Estoque estoque) {

		estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() + this.quantidade);
		return true;
	}

	public boolean removeDisponivel(Estoque estoque) {

		if(estoque.getQuantidadeDisponivel() - estoque.getQuantidadeReservado() >= this.quantidade) {
			
			estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - this.quantidade);
			return true;
		}
		return false;
	}
	
	public boolean addReservado(Estoque estoque) {

		if(estoque.getQuantidadeDisponivel() - estoque.getQuantidadeReservado() >= this.quantidade) {
			
			estoque.setQuantidadeReservado(estoque.getQuantidadeReservado() + this.quantidade);
			return true;
		}
		return false;
	}

	public boolean removeReservado(Estoque estoque) {

		if(estoque.getQuantidadeReservado() >= this.quantidade) {
			
			estoque.setQuantidadeReservado(estoque.getQuantidadeReservado() - this.quantidade);
			return true;
		}
		return false;
	}
}
