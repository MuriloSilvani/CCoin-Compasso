package br.com.compasso.usuarios.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.compasso.usuarios.model.Cargos;

public interface CargosRepository extends CrudRepository<Cargos, Long> {

	Iterable<Cargos> findAll();
	
	@SuppressWarnings("unchecked")
	Cargos save(Cargos cargo);

	Cargos getOne(Long id_cargo);
	
	Cargos findByDescricao(String descricao);
}
