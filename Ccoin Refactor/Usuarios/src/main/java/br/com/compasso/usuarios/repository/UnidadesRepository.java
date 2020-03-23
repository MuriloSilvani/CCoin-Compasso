package br.com.compasso.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.usuarios.model.Unidades;

public interface UnidadesRepository extends JpaRepository<Unidades, Long> {

	Optional<Unidades> findByUfAndCidadeAndEndereco(String uf, String cidade, String endereco);

}
