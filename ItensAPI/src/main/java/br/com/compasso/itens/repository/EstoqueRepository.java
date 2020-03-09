package br.com.compasso.itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.form.EstoqueForm;
import br.com.compasso.itens.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	Estoque save(EstoqueForm estoque);

}
