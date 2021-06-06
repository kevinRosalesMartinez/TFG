package com.gestionclubdeportivo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionclubdeportivo.entidad.Categoria;
import com.gestionclubdeportivo.repositorio.CategoriaReository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaReository categoriareository;
	
	public List<Categoria> findAll() {
		return categoriareository.findAll();
	}
	
	public Categoria findById(Long id) {
		return categoriareository.findById(id).orElse(null);
	}

}
