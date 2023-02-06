package com.zaperoko.notas.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Grado {

	@Id
	private String id;
	private String descripcionGrado;
	private String descripcionNumerica;
	private List<String> cursoId;
	private List<String> asignaturaId;

	public Grado() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcionGrado() {
		return descripcionGrado;
	}

	public String getDescripcionNumerica() {
		return descripcionNumerica;
	}

	public void setDescripcionGrado(String descripcionGrado) {
		this.descripcionGrado = descripcionGrado.toUpperCase();
	}

	public void setDescripcionNumerica(String descripcionNumerica) {
		this.descripcionNumerica = descripcionNumerica;
	}

	public List<String> getCursoId() {
		return cursoId;
	}

	public void setCursoId(List<String> cursoId) {
		this.cursoId = cursoId;
	}

	public List<String> getAsignaturaId() {
		return asignaturaId;
	}

	public void setAsignaturaId(List<String> asignaturaId) {
		this.asignaturaId = asignaturaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((descripcionGrado == null) ? 0 : descripcionGrado.hashCode());
		result = prime * result + ((descripcionNumerica == null) ? 0 : descripcionNumerica.hashCode());
		result = prime * result + ((cursoId == null) ? 0 : cursoId.hashCode());
		result = prime * result + ((asignaturaId == null) ? 0 : asignaturaId.hashCode());
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
		Grado other = (Grado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (descripcionGrado == null) {
			if (other.descripcionGrado != null)
				return false;
		} else if (!descripcionGrado.equals(other.descripcionGrado))
			return false;
		if (descripcionNumerica == null) {
			if (other.descripcionNumerica != null)
				return false;
		} else if (!descripcionNumerica.equals(other.descripcionNumerica))
			return false;
		if (cursoId == null) {
			if (other.cursoId != null)
				return false;
		} else if (!cursoId.equals(other.cursoId))
			return false;
		if (asignaturaId == null) {
			if (other.asignaturaId != null)
				return false;
		} else if (!asignaturaId.equals(other.asignaturaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grado [id=" + id + ", descripcionGrado=" + descripcionGrado + ", descripcionNumerica="
				+ descripcionNumerica + ", cursoId=" + cursoId + ", asignaturaId=" + asignaturaId + "]";
	}

}
