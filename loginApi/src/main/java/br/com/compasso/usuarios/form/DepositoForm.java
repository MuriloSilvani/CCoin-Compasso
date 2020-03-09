package br.com.compasso.usuarios.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.model.Usuarios;

public class DepositoForm {

	@NotNull
	@Min(0)
	private int valor;

	public DepositoForm() {
		
	}

	public DepositoForm(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}

	public boolean deposito(Usuarios usuario) {

		usuario.setCredito(this.valor + usuario.getCredito());
		
		return true;
	}

	public boolean saque(Usuarios usuario) {

		if(usuario.getCredito() >= this.valor) {
		
			usuario.setCredito(usuario.getCredito() - this.valor);
			
			return true;
		}
		
		return false;
	}	
	
}
