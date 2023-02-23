package com.zaperoko.notas.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.model.Grupo;
import com.zaperoko.notas.repository.CursosRepository;
import com.zaperoko.notas.repository.GruposRepository;

@Service
public class GrupoService {

	@Autowired
	private GruposRepository repositorio;
	@Autowired
	private CursosRepository repositorioCurso;

	public Grupo addGrupo(Grupo grupo) {
		Optional<Grupo> grupoEncontrado = repositorio.findByNombreGrupo(grupo.getNombreGrupo());
		if (grupoEncontrado.isPresent()) {
			String grupoRegistrado = grupoEncontrado.get().getNombreGrupo() + " Registrado";
			grupoEncontrado.get().setNombreGrupo(grupoRegistrado);
			return grupoEncontrado.get();
		}
		return repositorio.insert(grupo);
	}

	public List<Grupo> getGrupos() {
		List<Grupo> grupos = repositorio.findAll();
		grupos.sort(Comparator.comparing(Grupo::getNombreGrupo));
		return grupos;
	}

	public Optional<Grupo> getGrupo(String id) {
		return repositorio.findById(id);
	}

	public Grupo actualizarGrupos(Grupo grupo) {
		Optional<Grupo> grupoEncontrado = repositorio.findById(grupo.getId());
		if (grupoEncontrado.isPresent()) {
			Optional<Grupo> resultado = repositorio.findByNombreGrupo(grupo.getNombreGrupo());
			if (resultado.isPresent()) {
				if (resultado.get().getId().equals(grupo.getId())) {
					return repositorio.save(grupo);
				} else {
					String grupoRegistrado = resultado.get().getNombreGrupo() + " Registrado";
					resultado.get().setNombreGrupo(grupoRegistrado);
					return resultado.get();
				}
			}
			return repositorio.save(grupo);
		}
		return null;
	}

	public String deleteGrupo(String id) {
		Optional<Grupo> grupoEncontrado = repositorio.findById(id);
		if (grupoEncontrado.isPresent()) {
			List<Curso> listaGrupos = repositorioCurso.findByIdGrupo(grupoEncontrado.get().getId());
			if (listaGrupos.size() > 0) {
				for (int i = 0; i < listaGrupos.size(); i++) {
					listaGrupos.get(i).setIdGrupo("");
				}
				repositorioCurso.saveAll(listaGrupos);
			}
			repositorio.deleteById(id);
			return "eliminado " + id;
		}
		return "No encontrado";
	}
}
