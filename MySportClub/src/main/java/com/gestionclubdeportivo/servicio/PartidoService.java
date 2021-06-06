package com.gestionclubdeportivo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionclubdeportivo.entidad.Entrenamiento;
import com.gestionclubdeportivo.entidad.Partido;
import com.gestionclubdeportivo.repositorio.PartidoRepository;

@Service
public class PartidoService {

	@Autowired
	PartidoRepository partidorepository;

	public List<Partido> findAll() {
		return partidorepository.findAll();
	}

	public Partido findById(Long id) {
		return partidorepository.findById(id).orElse(null);
	}

	public void save(Partido partido) {
		partidorepository.save(partido);
	}

	public void deleted(Long id) {
		partidorepository.deleteById(id);
	}

}
