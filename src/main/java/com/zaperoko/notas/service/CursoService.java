package com.zaperoko.notas.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.model.Grado;
import com.zaperoko.notas.model.Grupo;
import com.zaperoko.notas.model.Year;
import com.zaperoko.notas.repository.CursosRepository;
import com.zaperoko.notas.repository.GradoRepository;
import com.zaperoko.notas.repository.GruposRepository;
import com.zaperoko.notas.repository.YearRepository;

@Service
public class CursoService {

	@Autowired
	private CursosRepository repositorio;
	@Autowired
	private GruposRepository grupoRepositorio;
	@Autowired
	private GradoRepository gradoRepositorio;
	@Autowired
	private YearRepository yearRepositorio;

	public Curso addCurso(Curso curso) {
		Optional<Curso> validacion = repositorio.buscarCurso(curso.getIdGrado(), curso.getIdGrupo(), curso.getIdYear());
		if (validacion.isPresent()) {
			validacion.get().setDescripcionGrado("registrado");
			return validacion.get();
		}
		Optional<Grado> gradoExiste = gradoRepositorio.findById(curso.getIdGrado());
		Optional<Grupo> grupoEncontrado = grupoRepositorio.findById(curso.getIdGrupo());
		Optional<Year> yearEncontrado = yearRepositorio.findById(curso.getIdYear());

		if (gradoExiste.isPresent() && yearEncontrado.isPresent() && grupoEncontrado.isPresent()) {
			Curso cursoCreado = repositorio.insert(curso);

			ArrayList<String> listGrado = new ArrayList<String>();
			if (gradoExiste.get().getCursoId().size() > 0) {
				listGrado.addAll(gradoExiste.get().getCursoId());
				listGrado.add(cursoCreado.getIdCurso());
			} else {
				listGrado.add(cursoCreado.getIdCurso());
			}
			gradoExiste.get().setCursoId(listGrado);
			gradoRepositorio.save(gradoExiste.get());

			ArrayList<String> listGrupo = new ArrayList<String>();
			if (grupoEncontrado.get().getCursoId().size() > 0) {
				listGrupo.addAll(grupoEncontrado.get().getCursoId());
				listGrupo.add(cursoCreado.getIdCurso());
			} else {
				listGrupo.add(cursoCreado.getIdCurso());
			}
			grupoEncontrado.get().setCursoId(listGrupo);
			grupoRepositorio.save(grupoEncontrado.get());
			
			ArrayList<String> listYear = new ArrayList<String>();
			if (yearEncontrado.get().getCurso().size() > 0) {
				listYear.addAll(yearEncontrado.get().getCurso());
				listYear.add(cursoCreado.getIdCurso());
			} else {
				listYear.add(cursoCreado.getIdCurso());
			}
			yearEncontrado.get().setCurso(listYear);
			yearRepositorio.save(yearEncontrado.get());

			return cursoCreado;
		}
		return null;
	}

	public List<Curso> getCursoByDescripcion(String descripcion) {
		List<Curso> cursosEncontrados = repositorio.findByDescripcionCurso(descripcion);
		if (cursosEncontrados.size()>0) {
			for (int i = 0; i < cursosEncontrados.size(); i++){
				if (!cursosEncontrados.get(i).getIdGrado().equals("")) {
					cursosEncontrados.get(i).setDescripcionGrado(
							gradoRepositorio.findById(cursosEncontrados.get(i).getIdGrado()).get().getDescripcionGrado());
				}
				if (!cursosEncontrados.get(i).getIdGrupo().equals("")) {
					cursosEncontrados.get(i).setDescripcionGrupo(grupoRepositorio.findById(cursosEncontrados.get(i).getIdGrupo()).get().getNombreGrupo());
				}
				if (!cursosEncontrados.get(i).getIdYear().equals("")) {
					cursosEncontrados.get(i).setDescripcionYear(yearRepositorio.findById(cursosEncontrados.get(i).getIdYear()).get().getDescripcionYear());
				}
			}
			return cursosEncontrados;
		} else {
			return null;
		}		
	}

	public List<Curso> getCursos() {
		List<Curso> cursos = repositorio.findAll();
		for (int i = 0; i < cursos.size(); i++) {
			if (!cursos.get(i).getIdGrado().equals("")) {
				cursos.get(i).setDescripcionGrado(
						gradoRepositorio.findById(cursos.get(i).getIdGrado()).get().getDescripcionGrado());
			}
			if (!cursos.get(i).getIdGrupo().equals("")) {
				cursos.get(i).setDescripcionGrupo(
						grupoRepositorio.findById(cursos.get(i).getIdGrupo()).get().getNombreGrupo());
			}
			if (!cursos.get(i).getIdYear().equals("")) {
				cursos.get(i).setDescripcionYear(
						yearRepositorio.findById(cursos.get(i).getIdYear()).get().getDescripcionYear());
			}
		}
		cursos.sort(Comparator.comparing(Curso::getDescripcionCurso));
		return cursos;
	}

	public Optional<Curso> getCursosById(String id) {
		return repositorio.findById(id);
	}

	public List<Curso> getCursosByGrado(String grado) {
		return repositorio.findByIdGrado(grado);
	}
	
	public Optional<Curso> getCursosByAsignatura(String asignatura) {
		return repositorio.findByIdProfesorAsignatura(asignatura);
	}
	
	public Optional<Curso> getCursoByIdAlumnoCursoAndYear(String alumnoCursoId, String idYear) {
		return repositorio.findByIdAlumnoCursoAndIdYear(alumnoCursoId, idYear);
	}

	public List<Curso> getCursoByIdYear(String idYear) {
		return repositorio.findByIdYear(idYear);
	}

	public Optional<Curso> getCursosByIdAlumnoCurso(String alumnoCursoId) {
		return repositorio.findByIdAlumnoCurso(alumnoCursoId);
	}

	public Curso updateCurso(Curso curso) {
		System.out.println("curso: " + curso.toString());
		List<Curso> busquedaCursos = repositorio.findByDescripcionCurso(curso.getDescripcionCurso());
		Curso cursoRevisado = new Curso();
		if (busquedaCursos.size()>0) {
			for (int i=0; i<busquedaCursos.size(); i++) {
				if (busquedaCursos.get(i).getIdCurso().equals(curso.getIdCurso())) {
					//return repositorio.save(curso);
					cursoRevisado=curso;
				} else {
					if (busquedaCursos.get(i).getIdYear().equals(curso.getIdYear())) {
						busquedaCursos.get(i).setDescripcionCurso(busquedaCursos.get(i).getDescripcionCurso() + " registrado");
						return busquedaCursos.get(i);
						//cursoRevisado=busquedaCursos.get(i);
					} else {
						//return repositorio.save(curso);
						cursoRevisado=curso;
					}
				}
			}
			System.out.println("cursoRevisado: " + cursoRevisado.toString());
			return repositorio.save(cursoRevisado);
		} else {
			Optional<Curso> cursoById = repositorio.findById(curso.getIdCurso());
			if (cursoById.isPresent()) {
				return repositorio.save(curso);
			}
			curso.setDescripcionCurso(curso.getDescripcionCurso() + " No encontrado");
			return curso;
		}
	}

	public String deleteCurso(String id) {
		Optional<Curso> busquedaCurso = repositorio.findById(id);
		if (busquedaCurso.isPresent()) {
			Optional<Grado> cursoEnGrado = gradoRepositorio.findByCurso(busquedaCurso.get().getIdCurso());
			if (cursoEnGrado.isPresent()) {
				for (int i = 0; i < cursoEnGrado.get().getCursoId().size(); i++) {
					if (cursoEnGrado.get().getCursoId().get(i).equals(busquedaCurso.get().getIdCurso())) {
						cursoEnGrado.get().getCursoId().remove(i);
					}
				}
				gradoRepositorio.save(cursoEnGrado.get());
			}
			Optional<Grupo> cursoEnGrupo = grupoRepositorio.findByCurso(busquedaCurso.get().getIdCurso());
			if (cursoEnGrupo.isPresent()) {
				for (int i = 0; i < cursoEnGrupo.get().getCursoId().size(); i++) {
					if (cursoEnGrupo.get().getCursoId().get(i).equals(busquedaCurso.get().getIdCurso())) {
						cursoEnGrupo.get().getCursoId().remove(i);
					}
				}
				grupoRepositorio.save(cursoEnGrupo.get());
			}
			Optional<Year> cursoEnYear = yearRepositorio.findByCurso(busquedaCurso.get().getIdCurso());
			if (cursoEnYear.isPresent()) {
				for (int i = 0; i < cursoEnYear.get().getCurso().size(); i++) {
					if (cursoEnYear.get().getCurso().get(i).equals(busquedaCurso.get().getIdCurso())) {
						cursoEnYear.get().getCurso().remove(i);
					}
				}
				yearRepositorio.save(cursoEnYear.get());
			}
			repositorio.delete(busquedaCurso.get());
			return "Eliminado Correctamente";
		}
		return "No encontrado";
	}

}