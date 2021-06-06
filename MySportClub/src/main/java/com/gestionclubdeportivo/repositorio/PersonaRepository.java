package com.gestionclubdeportivo.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.User;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	// Al hacer control + click en JpaRepository podemos ver que metodos extiende

	//EJEMPLO - Buscar persona por el email
	Persona findFirstByEmail(String email);
	
	Persona findByUser(User user);
	
	List<Persona> findAllByEquipo(Equipo equipo);
		
}
