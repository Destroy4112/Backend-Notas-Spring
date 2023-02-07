package com.zaperoko.notas.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Asignatura;
import com.zaperoko.notas.model.Docente;
import com.zaperoko.notas.model.ProfesorAsignatura;
import com.zaperoko.notas.repository.AsignaturaRepository;
import com.zaperoko.notas.repository.DocenteRepository;
import com.zaperoko.notas.repository.ProfesorAsignaturaRepository;

@Service
public class ProfesorAsignaturaService {

	@Autowired
	private ProfesorAsignaturaRepository repositorio;
	@Autowired
	private AsignaturaRepository asignaturaRepositorio;
	@Autowired
	private DocenteRepository docenteRepositorio;

	public ProfesorAsignatura actualizarProfesorAsignatura(ProfesorAsignatura profesorAsignatura) {
		return repositorio.save(profesorAsignatura);
	}
	
	public List<ProfesorAsignatura> getProfesoresAsignatura() {
		return repositorio.findAll();
	}

	public Optional<ProfesorAsignatura> getByAsignatura(String asignatura) {
		return repositorio.findByAsignaturaId(asignatura);
	}
	
	public Optional<ProfesorAsignatura> getById(String id) {
		return repositorio.findById(id);
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
