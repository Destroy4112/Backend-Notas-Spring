package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.AlumnoCurso;
import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.repository.AlumnoCursoRepository;
import com.zaperoko.notas.repository.CursosRepository;

@Service
public class AlumnoCursoService {

	@Autowired
	private AlumnoCursoRepository repositorio;
	@Autowired
	private CursosRepository repositorioCurso;


	public AlumnoCurso addAlumnoCurso(AlumnoCurso detalle) {
		Optional<AlumnoCurso> registroEncontrado = repositorio.findAlumnoCurso(detalle.getIdCurso(),
				detalle.getIdEstudiante());
		if (registroEncontrado.isPresent()) {
			registroEncontrado.get().setIdCurso(registroEncontrado.get().getIdCurso() + " registrado");
			return registroEncontrado.get();
		}
		List<AlumnoCurso> estudianteCursoEncontrado = repositorio.findByIdEstudiante(detalle.getIdEstudiante());
		Optional<Curso> curso = repositorioCurso.findById(detalle.getIdCurso());
		if (curso.isPresent()) {
			if (estudianteCursoEncontrado.size()==1 && estudianteCursoEncontrado.get(0).getIdCurso()==null) {
				estudianteCursoEncontrado.get(0).setIdCurso(detalle.getIdCurso());
				return repositorio.save(estudianteCursoEncontrado.get(0));
			} else {
				return repositorio.save(detalle);
			}
		}
		return null;
	}

	public List<AlumnoCurso> getAlumnoCursos() {
		return repositorio.findAll();
	}

	public Optional<AlumnoCurso> getAlumnoCursosById(String id) {
		return repositorio.findById(id);
	}
	
	public List<AlumnoCurso> getAlumnoCursosByAlumno(String idAlumno) {
		return repositorio.findByIdEstudiante(idAlumno);
	}
	
	public Optional<AlumnoCurso> getAlumnoCursosByAlumnoAndCurso(String curso, String idAlumno) {
		return repositorio.findAlumnoCurso(curso, idAlumno);
	}

	public AlumnoCurso updateAlumnoCurso(AlumnoCurso actualizacion) {
		return null;
	}


}