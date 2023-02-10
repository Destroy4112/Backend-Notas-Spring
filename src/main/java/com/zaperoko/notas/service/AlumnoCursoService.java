package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.AlumnoCurso;
import com.zaperoko.notas.repository.AlumnoCursoRepository;

@Service
public class AlumnoCursoService {

	@Autowired
	private AlumnoCursoRepository repositorio;

	public List<AlumnoCurso> getAlumnoCursos() {
		return repositorio.findAll();
	}

	public Optional<AlumnoCurso> getAlumnoCursosById(String id) {
		return repositorio.findById(id);
	}
	
	public List<AlumnoCurso> getAlumnoCursosByAlumno(String idAlumno) {
		return repositorio.findByIdEstudiante(idAlumno);
	}

	public AlumnoCurso updateAlumnoCurso(AlumnoCurso actualizacion) {
		return null;
	}


}