package br.com.compasso.itens.form.resgate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.config.validation.CustomBadRequestException;
import br.com.compasso.itens.model.Estoque;

public class ResgateForm {

	@NotNull
	@Min(1)
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void addDisponivel(Estoque estoque) {

		estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() + this.quantidade);
	}

	public void removeDisponivel(Estoque estoque) throws CustomBadRequestException {

		if (estoque.getQuantidadeDisponivel() - estoque.getQuantidadeReservado() >= this.quantidade) {

			estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - this.quantidade);
		} else {
			throw new CustomBadRequestException(
					"Não é possivel remover do estoque " + estoque.getId());
		}
	}

	public void addReservado(Estoque estoque) throws CustomBadRequestException {

		if (estoque.getQuantidadeDisponivel() - estoque.getQuantidadeReservado() >= this.quantidade) {

			estoque.setQuantidadeReservado(estoque.getQuantidadeReservado() + this.quantidade);
		} else {
			throw new CustomBadRequestException(
					"Não é possivel reservar do estoque " + estoque.getId());
		}
	}

	public void removeReservado(Estoque estoque) throws CustomBadRequestException {

		if (estoque.getQuantidadeReservado() >= this.quantidade) {

			estoque.setQuantidadeReservado(estoque.getQuantidadeReservado() - this.quantidade);
		} else {
			throw new CustomBadRequestException(
					"Não é possivel liberar do estoque " + estoque.getId());
		}
	}
}
