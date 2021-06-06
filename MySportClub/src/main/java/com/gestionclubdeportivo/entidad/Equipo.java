package com.gestionclubdeportivo.entidad;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "equipos")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El campo nombre no puede estar vacio")
	@Column(unique = true)
	private String nombre;

	@OneToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@OneToMany(mappedBy = "equipopartidos", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Partido> partidosLista;

	@OneToMany(mappedBy = "equipoentrenamientos", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Entrenamiento> entrenamientosLista;

	public Equipo() {
		super();
	}

	public Equipo(String nombre, Categoria categoria, List<Partido> partidosLista,
			List<Entrenamiento> entrenamientosLista) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.partidosLista = partidosLista;
		this.entrenamientosLista = entrenamientosLista;
	}

	public Equipo(Long id, String nombre, Categoria categoria, List<Partido> partidosLista,
			List<Entrenamiento> entrenamientosLista) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.partidosLista = partidosLista;
		this.entrenamientosLista = entrenamientosLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((entrenamientosLista == null) ? 0 : entrenamientosLista.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((partidosLista == null) ? 0 : partidosLista.hashCode());
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
		Equipo other = (Equipo) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (entrenamientosLista == null) {
			if (other.entrenamientosLista != null)
				return false;
		} else if (!entrenamientosLista.equals(other.entrenamientosLista))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (partidosLista == null) {
			if (other.partidosLista != null)
				return false;
		} else if (!partidosLista.equals(other.partidosLista))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Partido> getPartidosLista() {
		Collections.sort(partidosLista);
		return partidosLista;
	}

	public void setPartidosLista(List<Partido> partidosLista) {
		this.partidosLista = partidosLista;
	}

	public List<Entrenamiento> getEntrenamientosLista() {
		Collections.sort(entrenamientosLista);
		return entrenamientosLista;
	}

	public void setEntrenamientosLista(List<Entrenamiento> entrenamientosLista) {
		this.entrenamientosLista = entrenamientosLista;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", partidosLista="
				+ partidosLista + ", entrenamientosLista=" + entrenamientosLista + "]";
	}

}
