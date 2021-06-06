package com.gestionclubdeportivo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionclubdeportivo.entidad.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

}
