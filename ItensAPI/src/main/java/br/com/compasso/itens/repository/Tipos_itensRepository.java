package br.com.compasso.itens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.form.Tipos_itensForm;
import br.com.compasso.itens.model.Tipos_itens;

public interface Tipos_itensRepository extends JpaRepository<Tipos_itens, Long> {

	Optional<Tipos_itens> findByDescricao(String descricao);

	Tipos_itens save(Tipos_itensForm form);

}
