package com.gestionclubdeportivo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionclubdeportivo.entidad.Entrenamiento;
import com.gestionclubdeportivo.repositorio.EntrenamientoRepository;

@Service
public class EntrenamientoService {

	@Autowired
	EntrenamientoRepository entrenerepository;

	public List<Entrenamiento> findAll() {
		return entrenerepository.findAll();
	}

	public Entrenamiento findById(Long id) {
		return entrenerepository.findById(id).orElse(null);
	}

	public void save(Entrenamiento entrenamiento) {
		entrenerepository.save(entrenamiento);
	}

	public void deleted(Long id) {
		entrenerepository.deleteById(id);
	}

}
