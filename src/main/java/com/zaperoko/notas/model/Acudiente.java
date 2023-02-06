package com.zaperoko.notas.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Acudiente {

	@Id
	private String id;
	private String nombres;
	private String apellidos;
	private String tipoDocumento;
	private String numeroDocumento;
	private String telefono;
	private String direccion;
	private List<String> idAlumno;

	public Acudiente() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<String> getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(List<String> idAlumno) {
		this.idAlumno = idAlumno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((idAlumno == null) ? 0 : idAlumno.hashCode());
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
		Acudiente other = (Acudiente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		if (numeroDocumento == null) {
			if (other.numeroDocumento != null)
				return false;
		} else if (!numeroDocumento.equals(other.numeroDocumento))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (idAlumno == null) {
			if (other.idAlumno != null)
				return false;
		} else if (!idAlumno.equals(other.idAlumno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Acudiente [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", tipoDocumento="
				+ tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", telefono=" + telefono + ", direccion="
				+ direccion + ", idAlumno=" + idAlumno +"]";
	}
	
			
}