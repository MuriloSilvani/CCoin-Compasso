package br.com.compasso.resgatestransferencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.compasso.resgatestransferencias.model.Status_requerimentos;
import br.com.compasso.resgatestransferencias.model.Transferencias;

public interface Status_requerimentosRepository extends JpaRepository<Status_requerimentos, Long> {

	@SuppressWarnings("unchecked")
	Status_requerimentos save(Status_requerimentos status_requerimentos);

	@Query("SELECT st FROM Status_requerimentos st WHERE id_transferencia = :id")
	List<Status_requerimentos> findByTransferencia(@Param("id") Transferencias id);

}
