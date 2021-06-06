package com.gestionclubdeportivo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionclubdeportivo.entidad.Categoria;
import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.Partido;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

	List<Equipo> findAllByCategoria(Categoria categoria);
	
	List<Equipo> findAllByOrderByCategoria();
	
}
