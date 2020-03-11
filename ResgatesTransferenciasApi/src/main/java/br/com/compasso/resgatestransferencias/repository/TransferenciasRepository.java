package br.com.compasso.resgatestransferencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.compasso.resgatestransferencias.model.Transferencias;

public interface TransferenciasRepository extends JpaRepository<Transferencias, Long> {

	@Query("SELECT ex FROM Transferencias AS ex WHERE ex.usuario_origem = :id_usuario OR ex.usuario_destino = :id_usuario")
	List<Transferencias> buscaTransferenciasDeEntrada(@Param("id_usuario") Long id_usuario);

}
