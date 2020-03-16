package br.com.compasso.resgatestransferencias.config.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgatestransferencias.config.service.form.HttpSaqueForm;
import br.com.compasso.resgatestransferencias.config.service.form.HttpTransfereForm;
import br.com.compasso.resgatestransferencias.form.TransfereForm;

@Service
public class UsuariosService {

	private RestTemplate client = new RestTemplate();

	public boolean transferencia(Long id_usuario, TransfereForm form) {

		HttpEntity<TransfereForm> request = new HttpEntity<TransfereForm>((form));

		ResponseEntity<HttpTransfereForm> sucesso = client.exchange(
				"http://localhost:8080/saldo/transferencia/" + id_usuario, HttpMethod.PUT, request,
				HttpTransfereForm.class);

		return sucesso.getBody().isAprovado();

	}

	public boolean findUsuario(Long id_usuario) {

		try {

			ResponseEntity<HttpTransfereForm> sucesso = client.exchange("http://localhost:8080/usuarios/" + id_usuario,
					HttpMethod.GET, null, HttpTransfereForm.class);

			if (sucesso.hasBody())
				return true;
		} catch (Exception e) {

			return false;
		}
		return false;
	}

	public int getSaldo(Long id_usuario) {

		ResponseEntity<HttpTransfereForm> sucesso = client.exchange("http://localhost:8080/saldo/" + id_usuario,
				HttpMethod.GET, null, HttpTransfereForm.class);

		return sucesso.getBody().getSaldo();
	}

	public boolean diminuiSaldo(Long id_usuario, int valor) {
		
		HttpEntity<HttpSaqueForm> request = new HttpEntity<HttpSaqueForm>(new HttpSaqueForm(valor));

		ResponseEntity<HttpSaqueForm> sucesso = client.exchange(
				"http://localhost:8080/saldo/saque/"+ id_usuario, HttpMethod.PUT, request,
				HttpSaqueForm.class);
		
		if(sucesso.hasBody()) {
			
			return true;
		}
			
		return false;
	}

}
