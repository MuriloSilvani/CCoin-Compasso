package br.com.compasso.usuarios.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.compasso.usuarios.model.Usuarios;

public class TransferenciaForm {

	@NotNull
	private Long usuario_destino;
	@NotNull
	@Min(0)
	private int valor;

	public Long getUsuario_destino() {
		return usuario_destino;
	}

	public int getValor() {
		return valor;
	}

	public boolean transferencia(Usuarios usuario_destino, Usuarios usuario_origem) {
		
		DepositoForm depositoForm = new DepositoForm(this.valor);
		
		boolean saque = depositoForm.saque(usuario_origem);
		
		if(saque) {
			
			boolean deposito = depositoForm.deposito(usuario_destino);
			
			if(deposito) {
				
				return true;
			}else {
				depositoForm.deposito(usuario_origem);
			}
		}
		
		return false;
	}

}
