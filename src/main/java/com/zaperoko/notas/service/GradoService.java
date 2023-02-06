package com.zaperoko.notas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Asignatura;
import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.model.Grado;
import com.zaperoko.notas.repository.AsignaturaRepository;
import com.zaperoko.notas.repository.CursosRepository;
import com.zaperoko.notas.repository.GradoRepository;

@Service
public class GradoService {

	@Autowired
	private GradoRepository repositorio;
	@Autowired
	private AsignaturaRepository asignaturaRepositorio;
	@Autowired
	private CursosRepository RepositorioCurso;

	public Grado addGrado(Grado grado) {
		Optional<Grado> gradoEncontrado = repositorio.findByDescripcionGrado(grado.getDescripcionGrado());
		if (gradoEncontrado.isPresent()) {
			String gradoRegistrado = gradoEncontrado.get().getDescripcionGrado() + " Registrado";
			gradoEncontrado.get().setDescripcionGrado(gradoRegistrado);
			return gradoEncontrado.get();
		}
		return repositorio.insert(grado);
	}

	public List<Grado> getGrados() {
		List<Grado> listaGrado = repositorio.findAll();
		for (int i = 0; i < listaGrado.size(); i++) {
			
			if (listaGrado.get(i).getCursoId().size() > 0) {
				List<String> curso = new ArrayList<>();
				for (int j = 0; j < listaGrado.get(i).getCursoId().size(); j++) {
					curso.add(RepositorioCurso.findById(listaGrado.get(i).getCursoId().get(j)).get()
							.getDescripcionCurso());
				}
				listaGrado.get(i).setCursoId(curso);
			}
			
			if (listaGrado.get(i).getAsignaturaId().size() > 0) {
				List<String> asignaturas = new ArrayList<>();
				for (int k = 0; k < listaGrado.get(i).getAsignaturaId().size(); k++) {
					asignaturas.add(asignaturaRepositorio.findById(listaGrado.get(i).getAsignaturaId().get(k)).get()
							.getDescripcionAsignatura());
				}
				listaGrado.get(i).setAsignaturaId(asignaturas);
			}
		}
		return listaGrado;
	}

	public Optional<Grado> getGradoById(String id) {
		return repositorio.findById(id);
	}

	public Grado updateGrado(Grado grado) {
		Optional<Grado> gradoEncontrado = repositorio.findByDescripcionGrado(grado.getDescripcionGrado());
		if (gradoEncontrado.isPresent()) {
			if (!gradoEncontrado.get().getId().equals(grado.getId())){
				String gradoRegistrado = gradoEncontrado.get().getDescripcionGrado() + " Registrado";
				gradoEncontrado.get().setDescripcionGrado(gradoRegistrado);
				return gradoEncontrado.get();
			} else {
				return repositorio.save(grado);
			}

		} else {
			return repositorio.save(grado);
		}
	}

	public String deleteGrado(String id) {
		Optional<Grado> gradoEncontrado = repositorio.findById(id);
		if (gradoEncontrado.isPresent()) {
			List<Asignatura> listaAsignaturas = asignaturaRepositorio.findByGrado(gradoEncontrado.get().getId());
			if (listaAsignaturas.size() > 0) {
				for (int i = 0; i < listaAsignaturas.size(); i++) {
					asignaturaRepositorio.delete(listaAsignaturas.get(i));
				}
			}
			List<Curso> listaCursos = RepositorioCurso.findByIdGrado(gradoEncontrado.get().getId());
			if (listaCursos.size() > 0) {
				for (int i = 0; i < listaCursos.size(); i++) {
					listaCursos.get(i).setIdGrado("");
				}
				RepositorioCurso.saveAll(listaCursos);
			}
			repositorio.deleteById(id);
			return "eliminado correctamente";
		}
		return "No encontrado";
	}
}
