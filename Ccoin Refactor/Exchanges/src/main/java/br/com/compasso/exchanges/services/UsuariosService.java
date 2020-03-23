package br.com.compasso.exchanges.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.exchanges.form.transferencia.SaldoForm;
import br.com.compasso.exchanges.form.transferencia.ValorForm;

@Service
public class UsuariosService {

	private RestTemplate client = new RestTemplate();

	public void validarUsuario(Long idUsuario, String authorization) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);

		HttpEntity<?> request = new HttpEntity<>(null, headers);

		client.exchange("http://localhost:8090/usuarios/" + idUsuario, HttpMethod.GET, request, Object.class);
	}

	public boolean verificaSaldo(Long idUsuario, int valor, String authorization) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);

		HttpEntity<?> request = new HttpEntity<>(null, headers);

		ResponseEntity<SaldoForm> response = client.exchange("http://localhost:8090/saldo/" + idUsuario, HttpMethod.GET,
				request, SaldoForm.class);

		int saldo = response.getBody().getSaldo();

		if (saldo >= valor) {
			return true;
		}

		return false;
	}

	public int saque(Long idUsuario, int valor, String authorization) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);

		HttpEntity<ValorForm> request = new HttpEntity<ValorForm>(new ValorForm(valor), headers);

		ResponseEntity<SaldoForm> response = client.exchange("http://localhost:8090/saldo/saque/" + idUsuario,
				HttpMethod.PUT, request, SaldoForm.class);

		return response.getBody().getSaldo();
	}

	public int deposito(Long idUsuario, int valor, String authorization) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		headers.add("RequestToken", "123456789");

		HttpEntity<ValorForm> request = new HttpEntity<ValorForm>(new ValorForm(valor), headers);

		ResponseEntity<SaldoForm> response = client.exchange("http://localhost:8090/saldo/deposito/" + idUsuario,
				HttpMethod.PUT, request, SaldoForm.class);

		return response.getBody().getSaldo();
	}
}
