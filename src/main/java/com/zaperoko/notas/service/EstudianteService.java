package com.zaperoko.notas.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Acudiente;
import com.zaperoko.notas.model.AlumnoCurso;
import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.model.Estudiante;
import com.zaperoko.notas.model.Nota;
import com.zaperoko.notas.model.Usuario;
import com.zaperoko.notas.repository.AcudienteRepository;
import com.zaperoko.notas.repository.AlumnoCursoRepository;
import com.zaperoko.notas.repository.CursosRepository;
import com.zaperoko.notas.repository.EstudianteRepository;
import com.zaperoko.notas.repository.NotaRepository;
import com.zaperoko.notas.repository.UsuariosRepository;

@Service
public class EstudianteService {

	@Autowired
	private EstudianteRepository repositorio;
	@Autowired
	private UsuariosRepository repoUser;
	@Autowired
	private AlumnoCursoRepository repoAlumnoCurso;
	@Autowired
	private AcudienteRepository repoAcudiente;
	@Autowired
	private NotaRepository repoNotas;
	@Autowired
	private CursosRepository repoCursos;

	public Estudiante addEstudiante(Estudiante estudiante) {
		Optional<Usuario> estudianteDocumento = repoUser.findByUsuario(estudiante.getNumeroDocumento());
		if (estudianteDocumento.isPresent()) {
			estudiante.setNombres("REGISTRADO");
			return estudiante;
		}

		Estudiante resultado = repositorio.insert(estudiante);

		Usuario user = new Usuario();
		user.setUsuario(estudiante.getNumeroDocumento());
		user.setClave(estudiante.getNumeroDocumento());
		user.setIdRol(estudiante.getIdRol());
		repoUser.insert(user);

		AlumnoCurso alumnoCurso = new AlumnoCurso();
		alumnoCurso.setIdEstudiante(resultado.getId());
		alumnoCurso.setIdCurso("");
		repoAlumnoCurso.insert(alumnoCurso);

		return resultado;
	}

	public List<Estudiante> getEstudiantes() {
		List<Estudiante> estudiante = repositorio.findAll();

		for (int i = 0; i < estudiante.size(); i++) {
			if (!estudiante.get(i).getIdAcudiente().equals("")) {
				estudiante.get(i).setNombreAcudiente(
						repoAcudiente.findById(estudiante.get(i).getIdAcudiente()).get().getNombres());
			}
		}
		estudiante.sort(Comparator.comparing(Estudiante::getNombres));
		return estudiante;
	}

	public List<String> getlista(String id) {

		Optional<Acudiente> buscarPorAcudiente = repoAcudiente.findByEstudiante(id);
		List<String> lista = new ArrayList<>();
		lista.addAll(buscarPorAcudiente.get().getIdAlumno());
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).equals(id)) {
				lista.remove(i);
			}
		}
		return lista;
	}

	public Optional<Estudiante> getEstudianteById(String id) {
		Optional<Estudiante> estudiante = repositorio.findById(id);
		if (!estudiante.get().getIdAcudiente().equals("")) {
			estudiante.get()
					.setNombreAcudiente(repoAcudiente.findById(estudiante.get().getIdAcudiente()).get().getNombres());
		}
		return estudiante;
	}

	public Optional<Estudiante> getEstudianteByDocumento(String id) {
		return repositorio.findByNumeroDocumento(id);
	}

	public Estudiante updateEstudiante(Estudiante estudiante) {
		Optional<Usuario> estudianteDocumento = repoUser.findByUsuario(estudiante.getNumeroDocumento());
		Optional<Estudiante> busquedaEstudiante = repositorio.findByNumeroDocumento(estudiante.getNumeroDocumento());
		if (busquedaEstudiante.isPresent() && busquedaEstudiante.get().getId().equals(estudiante.getId())) {
			return repositorio.save(estudiante);
		}
		if (estudianteDocumento.isPresent()) {
			estudiante.setNombres("REGISTRADO");
			return busquedaEstudiante.get();
		}

		Optional<Estudiante> busquedaEstudianteId = repositorio.findById(estudiante.getId());
		if (busquedaEstudianteId.isPresent()) {
			Optional<Usuario> busquedaUsuario = repoUser.findByUsuario(busquedaEstudianteId.get().getNumeroDocumento());
			if (busquedaUsuario.isPresent()) {
				repoUser.delete(busquedaUsuario.get());
				busquedaUsuario.get().setUsuario(estudiante.getNumeroDocumento());
				busquedaUsuario.get().setClave(estudiante.getNumeroDocumento());
				repoUser.save(busquedaUsuario.get());
			}
			return null;
		}
		return repositorio.save(estudiante);
	}

	public Optional<Acudiente> buscarPorAlumno(String alumno) {
		return repoAcudiente.findByEstudiante(alumno);
	}

	public String deleteEstudiante(String id) {
		Optional<Estudiante> estudiante = repositorio.findById(id);
		if (estudiante.isPresent()) {

			Optional<Usuario> busquedaEstudiante = repoUser.findByUsuario(estudiante.get().getNumeroDocumento());
			if (busquedaEstudiante.isPresent()) {
				repoUser.delete(busquedaEstudiante.get());
			}

			Optional<Acudiente> busquedaEnAcudiente = repoAcudiente.findByEstudiante(estudiante.get().getId());
			if (busquedaEnAcudiente.isPresent()) {
				List<String> listEstudiante = new ArrayList<>();
				listEstudiante.addAll(busquedaEnAcudiente.get().getIdAlumno());
				for (int i = 0; i < listEstudiante.size(); i++) {
					if (listEstudiante.get(i).equals(id)) {
						listEstudiante.remove(i);
					}
					busquedaEnAcudiente.get().setIdAlumno(listEstudiante);
					repoAcudiente.save(busquedaEnAcudiente.get());
				}
			}
			List<AlumnoCurso> buscarEstudiantes = repoAlumnoCurso.findByIdEstudiante(estudiante.get().getId());
			if (buscarEstudiantes.size() > 0) {
				for (int i = 0; i < buscarEstudiantes.size(); i++) {
					List<Nota> nota = repoNotas.findByIdAlumnoCurso(buscarEstudiantes.get(i).getId());
					if (nota.size() > 0) {
						for (int j = 0; j < nota.size(); j++) {
							repoNotas.delete(nota.get(j));
						}
					}
					Optional<Curso> cursos = repoCursos.findByIdAlumnoCurso(buscarEstudiantes.get(i).getId());
					if (cursos.isPresent()) {
						List<String> listAlumnoCurso = new ArrayList<>();
						listAlumnoCurso.addAll(cursos.get().getAlumnoCurso());
						for (int k = 0; k < listAlumnoCurso.size(); k++) {
							if (listAlumnoCurso.get(k).equals(buscarEstudiantes.get(i).getId())) {
								listAlumnoCurso.remove(k);
							}
							cursos.get().setAlumnoCurso(listAlumnoCurso);
							repoCursos.save(cursos.get());
							listAlumnoCurso.clear();
						}
					}
					repoAlumnoCurso.delete(buscarEstudiantes.get(i));
				}
			}
			repositorio.delete(estudiante.get());
			return "Eliminado Correctamente";
		}
		return "Estudiante no encontrado";
	}
}
