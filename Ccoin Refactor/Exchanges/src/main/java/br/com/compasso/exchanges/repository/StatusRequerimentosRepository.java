package br.com.compasso.exchanges.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.compasso.exchanges.model.StatusRequerimentos;
import br.com.compasso.exchanges.model.Transferencias;

public interface StatusRequerimentosRepository extends JpaRepository<StatusRequerimentos, Long> {

	@Query("SELECT st FROM StatusRequerimentos st WHERE id_transferencia = :id")
	List<StatusRequerimentos> findByTransferencia(@Param("id") Transferencias id);

}
