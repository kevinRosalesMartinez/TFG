package com.gestionclubdeportivo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.User;

public interface PersonaService {

	public List<Persona> findAll();

	public Page<Persona> findAll(Pageable pageable);

	public Optional<Persona> findById(Long id);

	public Persona findByUser(User user);

	public Persona save(Persona persona);

	public void deleteById(Long id);
	
	public List<Persona> findAllByEquipo(Equipo equipo);
	
}
