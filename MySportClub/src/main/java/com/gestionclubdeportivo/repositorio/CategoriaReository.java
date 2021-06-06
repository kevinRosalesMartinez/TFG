package com.gestionclubdeportivo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionclubdeportivo.entidad.Categoria;

@Repository
public interface CategoriaReository extends JpaRepository<Categoria, Long> {

}
