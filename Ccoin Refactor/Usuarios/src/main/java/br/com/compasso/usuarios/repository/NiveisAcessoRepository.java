package br.com.compasso.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.usuarios.model.NiveisAcesso;

public interface NiveisAcessoRepository extends JpaRepository<NiveisAcesso, Long> {

	Optional<NiveisAcesso> findByDescricao(String descricao);

}
