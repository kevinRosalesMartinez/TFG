package com.gestionclubdeportivo.entidad;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Entrenamiento implements Comparable<Entrenamiento>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaentrenamiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entrene_equipo_id")
	private Equipo equipoentrenamientos;

	public String formatoFecha() {	
		Format format = new SimpleDateFormat("dd MMM yyyy hh:mm");
		String fecha1 = format.format(fechaentrenamiento);
		return fecha1;
	}
	
	public Entrenamiento() {
		super();
	}

	public Entrenamiento(Date fechaentrenamiento, Equipo equipoentrenamientos) {
		super();
		this.fechaentrenamiento = fechaentrenamiento;
		this.equipoentrenamientos = equipoentrenamientos;
	}

	public Entrenamiento(Long id, Date fechaentrenamiento, Equipo equipoentrenamientos) {
		super();
		this.id = id;
		this.fechaentrenamiento = fechaentrenamiento;
		this.equipoentrenamientos = equipoentrenamientos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipoentrenamientos == null) ? 0 : equipoentrenamientos.hashCode());
		result = prime * result + ((fechaentrenamiento == null) ? 0 : fechaentrenamiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrenamiento other = (Entrenamiento) obj;
		if (equipoentrenamientos == null) {
			if (other.equipoentrenamientos != null)
				return false;
		} else if (!equipoentrenamientos.equals(other.equipoentrenamientos))
			return false;
		if (fechaentrenamiento == null) {
			if (other.fechaentrenamiento != null)
				return false;
		} else if (!fechaentrenamiento.equals(other.fechaentrenamiento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaentrenamiento() {
		return fechaentrenamiento;
	}

	public void setFechaentrenamiento(Date fechaentrenamiento) {
		this.fechaentrenamiento = fechaentrenamiento;
	}

	public Equipo getEquipoentrenamientos() {
		return equipoentrenamientos;
	}

	public void setEquipoentrenamientos(Equipo equipoentrenamientos) {
		this.equipoentrenamientos = equipoentrenamientos;
	}

	@Override
	public int compareTo(Entrenamiento o) {
		return getFechaentrenamiento().compareTo(o.getFechaentrenamiento());
	}	
}
