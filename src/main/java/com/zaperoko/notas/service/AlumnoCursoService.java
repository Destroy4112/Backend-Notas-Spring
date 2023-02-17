package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.AlumnoCurso;
import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.repository.AlumnoCursoRepository;
import com.zaperoko.notas.repository.CursosRepository;
import com.zaperoko.notas.repository.EstudianteRepository;

@Service
public class AlumnoCursoService {

	@Autowired
	private AlumnoCursoRepository repositorio;
	@Autowired
	private CursosRepository repositorioCurso;
	@Autowired
	private EstudianteRepository repositorioAlumno;

	public AlumnoCurso addAlumnoCurso(AlumnoCurso alumnoCurso) {
		System.out.println(alumnoCurso);
		Optional<AlumnoCurso> registroEncontrado = repositorio.findAlumnoCurso(alumnoCurso.getIdCurso(), alumnoCurso.getIdEstudiante());
		if (registroEncontrado.isPresent()) {
			registroEncontrado.get().setIdCurso(registroEncontrado.get().getIdCurso() + " registrado");
			return registroEncontrado.get();
		}
		List<AlumnoCurso> estudianteCursoEncontrado = repositorio.findByIdEstudiante(alumnoCurso.getIdEstudiante());
		System.out.println(estudianteCursoEncontrado);
		Optional<Curso> curso = repositorioCurso.findById(alumnoCurso.getIdCurso());
		if (curso.isPresent()) {
			if (estudianteCursoEncontrado.size()==1 && estudianteCursoEncontrado.get(0).getIdCurso()=="") {
				estudianteCursoEncontrado.get(0).setIdCurso(alumnoCurso.getIdCurso());
				return repositorio.save(estudianteCursoEncontrado.get(0));
			} else {
				return repositorio.save(alumnoCurso);
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

	public AlumnoCurso updateAlumnoCurso(AlumnoCurso actualizacion) {
		return null;
	}

	public Optional<AlumnoCurso> getAlumnoCursosByAlumnoAndCurso(String curso, String idAlumno) {
		return repositorio.findAlumnoCurso(curso, idAlumno);
	}

}