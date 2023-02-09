package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.ProfesorAsignatura;
import com.zaperoko.notas.repository.ProfesorAsignaturaRepository;

@Service
public class ProfesorAsignaturaService {

	@Autowired
	private ProfesorAsignaturaRepository repositorio;
	
	public ProfesorAsignatura addProfesorAsignatura(ProfesorAsignatura profesorAsignatura) {
		return repositorio.insert(profesorAsignatura);
	}
	
	public List<ProfesorAsignatura> getProfesoresAsignatura() {
		return repositorio.findAll();
	}

	public List<ProfesorAsignatura> getByAsignatura(String asignatura) {
		return repositorio.findByAsignaturaId(asignatura);
	}
	
	public List<ProfesorAsignatura> getByProfesor(String profesor) {
		return repositorio.findByProfesorId(profesor);
	}
	
	public Optional<ProfesorAsignatura> getById(String id) {
		return repositorio.findById(id);
	}
	
	public ProfesorAsignatura actualizarProfesorAsignatura(ProfesorAsignatura profesorAsignatura) {
		return repositorio.save(profesorAsignatura);
	}

	public String deleteProfesorAsignatura(String id) {
		Optional<ProfesorAsignatura> profesorAsignatura = repositorio.findById(id);
		if (profesorAsignatura.isPresent()) {
			repositorio.delete(profesorAsignatura.get());
			return "Eliminado Correctamente";
		}
		return "No existe ninguna asignatura-docente con ese id";
	}

}
