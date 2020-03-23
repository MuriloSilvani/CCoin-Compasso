package br.com.compasso.itens.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.model.Itens;
import br.com.compasso.itens.model.TiposItem;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	@Query("SELECT est FROM Estoque est WHERE qtde_disponivel > 0 AND qtde_disponivel > qtde_reservado AND ativo = true")
	Page<Estoque> buscaDisponiveis(Pageable paginacao);

	List<Estoque> findByItem(Itens item);

	List<Estoque> findByTipoItem(TiposItem tipoItem);
}
