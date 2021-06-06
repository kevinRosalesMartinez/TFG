package com.gestionclubdeportivo.entidad;

import java.awt.Font;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "partidos")
public class Partido implements Comparable<Partido> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechapartido;

	private String rival;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "part_equipo_id")
	private Equipo equipopartidos;

	public String formatoFecha() {
		Format format = new SimpleDateFormat("dd MMM yyyy hh:mm");
		String fecha1 = format.format(fechapartido);
		return fecha1;
	}

	public Partido() {
		super();
	}

	public Partido(Date fechapartido, String rival, Equipo equipopartidos) {
		super();
		this.fechapartido = fechapartido;
		this.rival = rival;
		this.equipopartidos = equipopartidos;
	}

	public Partido(Long id, Date fechapartido, String rival, Equipo equipopartidos) {
		super();
		this.id = id;
		this.fechapartido = fechapartido;
		this.rival = rival;
		this.equipopartidos = equipopartidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipopartidos == null) ? 0 : equipopartidos.hashCode());
		result = prime * result + ((fechapartido == null) ? 0 : fechapartido.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rival == null) ? 0 : rival.hashCode());
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
		Partido other = (Partido) obj;
		if (equipopartidos == null) {
			if (other.equipopartidos != null)
				return false;
		} else if (!equipopartidos.equals(other.equipopartidos))
			return false;
		if (fechapartido == null) {
			if (other.fechapartido != null)
				return false;
		} else if (!fechapartido.equals(other.fechapartido))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rival == null) {
			if (other.rival != null)
				return false;
		} else if (!rival.equals(other.rival))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechapartido() {
		return fechapartido;
	}

	public void setFechapartido(Date fechapartido) {
		this.fechapartido = fechapartido;
	}

	public String getRival() {
		return rival;
	}

	public void setRival(String rival) {
		this.rival = rival;
	}

	public Equipo getEquipopartidos() {
		return equipopartidos;
	}

	public void setEquipopartidos(Equipo equipopartidos) {
		this.equipopartidos = equipopartidos;
	}

	public String mostrarPartidos() {
		return "Enfrentamiento contra " + rival + ", en la fecha: " + formatoFecha();
	}
	
	@Override
	public int compareTo(Partido o) {
		return getFechapartido().compareTo(o.getFechapartido());
	}

}
