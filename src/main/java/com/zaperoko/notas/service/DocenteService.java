package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Docente;
import com.zaperoko.notas.model.ProfesorAsignatura;
import com.zaperoko.notas.model.Usuario;
import com.zaperoko.notas.repository.DocenteRepository;
import com.zaperoko.notas.repository.ProfesorAsignaturaRepository;
import com.zaperoko.notas.repository.UsuariosRepository;

@Service
public class DocenteService {

	@Autowired
	private DocenteRepository docenteRepositorio;

	@Autowired
	private UsuariosRepository usuarioRepositorio;

	@Autowired
	private ProfesorAsignaturaRepository proAsigrepositorio;

	public Docente addDocente(Docente docente) {
		Optional<Usuario> busquedaDocente = usuarioRepositorio.findByUsuario(docente.getNumeroDocumento());
		if (busquedaDocente.isPresent()) {
			docente.setNombres("REGISTRADO");
			return docente;
		}
		Usuario user = new Usuario();
		user.setUsuario(docente.getNumeroDocumento());
		user.setClave(docente.getNumeroDocumento());
		user.setIdRol(docente.getIdRol());
		usuarioRepositorio.insert(user);

		return docenteRepositorio.insert(docente);

	}

	public List<Docente> getConsultarDocentes() {
		return docenteRepositorio.findAll();
	}

	public Optional<Docente> getDocenteById(String id) {
		return docenteRepositorio.findById(id);
	}

	public Optional<Docente> getDocenteByDocumento(String descripcion) {
		return docenteRepositorio.findByNumeroDocumento(descripcion);
	}
	
	public Optional<Docente> getDocenteByNombreCompleto(String nombre, String apellidos) {
		return docenteRepositorio.findByNombresAndApellidos(nombre, apellidos);
	}

	public Docente updateDocente(Docente docente) {
		Optional<Docente> busquedaDocente = docenteRepositorio.findByNumeroDocumento(docente.getNumeroDocumento());
		Optional<Usuario> busquedaNumero = usuarioRepositorio.findByUsuario(docente.getNumeroDocumento());
		if (busquedaDocente.isPresent() && busquedaDocente.get().getId().equals(docente.getId())) {
			return docenteRepositorio.save(docente);
		}
		if (busquedaNumero.isPresent()) {
			docente.setNombres("REGISTRADO");
			return docente;
		}
		Optional<Docente> busquedaDocenteId = docenteRepositorio.findById(docente.getId());
		if (busquedaDocenteId.isPresent()) {
			Optional<Usuario> busquedaUsuario = usuarioRepositorio
					.findById(busquedaDocenteId.get().getNumeroDocumento());
			if (busquedaUsuario.isPresent()) {
				usuarioRepositorio.delete(busquedaUsuario.get());
				busquedaUsuario.get().setUsuario(docente.getNumeroDocumento());
				busquedaUsuario.get().setClave(docente.getNumeroDocumento());
				usuarioRepositorio.save(busquedaUsuario.get());
			}
		}

		return docenteRepositorio.save(docente);
	}

	public String deleteDocente(String id) {
		Optional<Docente> docente = docenteRepositorio.findById(id);
		if (docente.isPresent()) {
			Optional<Usuario> busquedaDocente = usuarioRepositorio.findByUsuario(docente.get().getNumeroDocumento());
			if (busquedaDocente.isPresent()) {
				usuarioRepositorio.delete(busquedaDocente.get());
			}
			List<ProfesorAsignatura> busquedaProfesor = proAsigrepositorio.findByProfesorId(docente.get().getId());
			if (busquedaProfesor.size() > 0) {
				for (int i = 0; i < busquedaProfesor.size(); i++) {
					busquedaProfesor.get(i).setProfesorId("");
					proAsigrepositorio.save(busquedaProfesor.get(i));
				}
			}
			docenteRepositorio.delete(docente.get());
			return "Eliminado Correctamente";
		}
		return "No existe ningun registro con ese id";
	}

}