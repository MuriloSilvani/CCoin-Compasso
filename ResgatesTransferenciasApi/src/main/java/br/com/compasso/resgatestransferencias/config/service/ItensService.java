package br.com.compasso.resgatestransferencias.config.service;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgatestransferencias.config.service.form.HttpCompraForm;
import br.com.compasso.resgatestransferencias.config.service.form.HttpEstoqueForm;
import br.com.compasso.resgatestransferencias.form.CompraForm;

@Service
public class ItensService {

	private RestTemplate client = new RestTemplate();

	public boolean temEstoque(CompraForm form) {


		ResponseEntity<HttpEstoqueForm> sucesso = client.exchange(
				"http://localhost:8081/estoque/" + form.getId_estoque(), HttpMethod.GET, null, HttpEstoqueForm.class);

		
		if(sucesso.getBody().getQtde_disponivel() > form.getQtde()) {
			
			return true;
		}
		
		return false;
	}

	public int getValor(@Valid CompraForm form) {

		ResponseEntity<HttpEstoqueForm> sucesso = client.exchange(
				"http://localhost:8081/estoque/" + form.getId_estoque(), HttpMethod.GET, null, HttpEstoqueForm.class);
		
		return (int) sucesso.getBody().getValor() * form.getQtde();
	}

	public boolean diminuiEstoque(@Valid CompraForm form) {

		HttpEntity<HttpCompraForm> request = new HttpEntity<HttpCompraForm>(new HttpCompraForm(form));

		ResponseEntity<HttpCompraForm> sucesso = client.exchange(
				"http://localhost:8081/catalogo/removerDisponivel/"+ form.getId_estoque(), HttpMethod.PUT, request,
				HttpCompraForm.class);
		
		if(sucesso.hasBody()) {
			
			return true;
		}
			
		return false;
	}
	
}
