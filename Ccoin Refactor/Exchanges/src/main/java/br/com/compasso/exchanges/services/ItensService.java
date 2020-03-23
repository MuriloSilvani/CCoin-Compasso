package br.com.compasso.exchanges.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.exchanges.config.validation.CustomBadRequestException;
import br.com.compasso.exchanges.dto.resgate.QuantidadeDto;
import br.com.compasso.exchanges.form.estoque.EstoqueForm;
import br.com.compasso.exchanges.form.resgate.ResgateForm;

@Service
public class ItensService {

	private RestTemplate client = new RestTemplate();

	public int validarEstoque(ResgateForm form) throws CustomBadRequestException {

		ResponseEntity<EstoqueForm> response = client.exchange("http://localhost:8091/estoque/" + form.getIdEstoque(),
				HttpMethod.GET, null, EstoqueForm.class);

		if (form.getQuantidade() > (response.getBody().getQuantidadeDisponivel()
				- response.getBody().getQuantidadeReservado())) {

			throw new CustomBadRequestException("Quantidade ou item indisponivel");
		}

		return form.getQuantidade() * response.getBody().getValor();
	}

	public void retirarEstoque(Long idEstoque, int quantidade) {

		HttpEntity<QuantidadeDto> request = new HttpEntity<QuantidadeDto>(new QuantidadeDto(quantidade));

		client.exchange("http://localhost:8091/catalogo/removerDisponivel/" + idEstoque, HttpMethod.PUT, request,
				Object.class);

	}
}
