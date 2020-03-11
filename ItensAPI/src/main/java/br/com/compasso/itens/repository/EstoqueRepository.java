package br.com.compasso.itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.form.EstoqueForm;
import br.com.compasso.itens.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	Estoque save(EstoqueForm estoque);
//
//	@Query("SELECT est FROM Estoque est WHERE id_item = :id_item AND id_tipo_item = :id_tipo_item")
//	List<Estoque> buscaEstoque(@Param("id_item") BigInteger id_item, @Param("id_tipo_item") BigInteger id_tipo_item);
	
}
