package br.com.compasso.exchanges.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.compasso.exchanges.model.Transferencias;

public interface TransferenciasRepository extends JpaRepository<Transferencias, Long> {

	@Query("SELECT ex FROM Transferencias AS ex WHERE ex.usuarioOrigem = :idUsuario OR ex.usuarioDestino = :idUsuario ORDER BY id DESC")
	List<Transferencias> buscaTransferencias(@Param("idUsuario") Long idUsuario);
}
