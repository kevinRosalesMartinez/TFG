package com.gestionclubdeportivo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionclubdeportivo.entidad.Entrenamiento;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {

}
