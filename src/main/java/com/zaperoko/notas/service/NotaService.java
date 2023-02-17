package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.AlumnoCurso;
import com.zaperoko.notas.model.Asignatura;
import com.zaperoko.notas.model.Nota;
import com.zaperoko.notas.repository.AlumnoCursoRepository;
import com.zaperoko.notas.repository.AsignaturaRepository;
import com.zaperoko.notas.repository.NotaRepository;

@Service
public class NotaService {
	@Autowired
	private NotaRepository repositorio;

	@Autowired
	private AsignaturaRepository repositorioAsignatura;

	@Autowired
	private AlumnoCursoRepository repositorioAlumnoCurso;

	public Nota addNota(Nota nota) {
		return repositorio.save(nota);
	}

	public List<Nota> getNotas() {
		return repositorio.findAll();
	}

	public List<Nota> getNotasByAlumnoAndAsignatura(String alumnoCurso, String asignatura) {
		return repositorio.findByAlumnoAndAsignatura(alumnoCurso, asignatura);
	}

	public List<Nota> getNotasByAlumno(String idAlumnoCurso) {
		return repositorio.findByIdAlumnoCurso(idAlumnoCurso);
	}

	public Nota updateNota(Nota nota) {
		Optional<Asignatura> busquedaAsignatura = repositorioAsignatura.findById(nota.getIdAsignatura());
		Optional<AlumnoCurso> busquedaAlumnoCurso = repositorioAlumnoCurso.findById(nota.getIdAlumnoCurso());
		if (busquedaAsignatura.isPresent() && busquedaAlumnoCurso.isPresent()) {
			return repositorio.save(nota);
		}
		return null;
	}

	public String deleteNota(String id) {
		Optional<Nota> notas = repositorio.findById(id);
		if (notas.isPresent()) {
			repositorio.delete(notas.get());
			return "Eliminadas Correctamente";
		}
		return "No existe ningun registro de notas con ese id";
	}
}