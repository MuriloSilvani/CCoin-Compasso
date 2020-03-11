package br.com.compasso.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.usuarios.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

	Optional<Usuarios> findByLogin(String login);
}
