package com.gestionclubdeportivo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.repositorio.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService{

	@Autowired
	private PersonaRepository personarepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return personarepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Persona> findAll(Pageable pageable) {
		return personarepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> findById(Long id) {
		return personarepository.findById(id);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		return personarepository.save(persona);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		personarepository.deleteById(id);;
	}

	@Override
	public Persona findByUser(User user) {
		return personarepository.findByUser(user);
	}

	@Override
	public List<Persona> findAllByEquipo(Equipo equipo) {
		return personarepository.findAllByEquipo(equipo);
	}
	
}
