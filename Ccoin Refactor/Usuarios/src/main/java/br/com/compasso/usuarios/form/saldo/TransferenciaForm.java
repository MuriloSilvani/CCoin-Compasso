package br.com.compasso.usuarios.form.saldo;

import javax.validation.constraints.Min;

import br.com.compasso.usuarios.config.validation.CustomBadRequestException;
import br.com.compasso.usuarios.model.Usuarios;

public class TransferenciaForm {

	@Min(1)
	private int valor;

	public int getValor() {
		return valor;
	}

	public void deposito(Usuarios usuario) {

		usuario.setCredito(this.valor + usuario.getCredito());
	}

	public void saque(Usuarios usuario) throws CustomBadRequestException {

		if(usuario.getCredito() >= this.valor) {
		
			usuario.setCredito(usuario.getCredito() - this.valor);
		}else {

			throw new CustomBadRequestException("Saldo insuficiente");
		}
	}
}


