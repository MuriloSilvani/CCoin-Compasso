package br.com.compasso.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.usuarios.model.Cargos;

public interface CargosRepository extends JpaRepository<Cargos, Long> {

	Optional<Cargos> findByDescricao(String descricao);

}
