package com.gestionclubdeportivo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionclubdeportivo.entidad.Categoria;
import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.repositorio.EquipoRepository;

@Service
public class EquipoService {

	@Autowired
	EquipoRepository equiporepository;

	public List<Equipo> findAll() {
		return equiporepository.findAll();
	}

	public Equipo findById(Long id) {
		return equiporepository.findById(id).orElse(null);
	}

	public void save(Equipo equipo) {
		equiporepository.save(equipo);
	}

	public void delete(Long id) {
		equiporepository.deleteById(id);
	}
	
	public List<Equipo> findAllByCategoria(Categoria categoria){
		return equiporepository.findAllByCategoria(categoria);
	}
	
	public List<Equipo> findAllByOrderByCategoria(){
		return equiporepository.findAllByOrderByCategoria();
	}
}
