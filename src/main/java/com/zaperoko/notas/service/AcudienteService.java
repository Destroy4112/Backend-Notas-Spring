package com.zaperoko.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Acudiente;
import com.zaperoko.notas.model.Estudiante;
import com.zaperoko.notas.repository.AcudienteRepository;
import com.zaperoko.notas.repository.EstudianteRepository;

@Service
public class AcudienteService {

	@Autowired
	private AcudienteRepository repositorio;
	@Autowired
	private EstudianteRepository estudianteRepo;

	public Acudiente addAcudiente(Acudiente acudiente) {
		Optional<Acudiente> acudienteEncontrado = repositorio.findByNumeroDocumento(acudiente.getNumeroDocumento());
		if (acudienteEncontrado.isPresent()) {
			String acudienteRegistrado = acudienteEncontrado.get().getNombres() + " Registrado";
			acudienteEncontrado.get().setNombres(acudienteRegistrado);
			return acudienteEncontrado.get();
		}
		return repositorio.insert(acudiente);
	}

	public List<Acudiente> getAcudiente() {
		return repositorio.findAll();
	}

	public Optional<Acudiente> getById(String id) {
		return repositorio.findById(id);
	}

	public Optional<Acudiente> getByDocumento(String documento) {
		return repositorio.findByNumeroDocumento(documento);
	}

	public Acudiente updateAcudiente(Acudiente acudiente) {

		Optional<Acudiente> acudienteEncontrado = repositorio.findByNumeroDocumento(acudiente.getNumeroDocumento());
		if (acudienteEncontrado.isPresent()) {
			if (acudienteEncontrado.get().getId().equals(acudiente.getId())) {
				return repositorio.save(acudiente);
			} else {
				String acudienteRegistrado = acudienteEncontrado.get().getNombres() + " Registrado";
				acudienteEncontrado.get().setNombres(acudienteRegistrado);
				return acudienteEncontrado.get();
			}
		}
		Acudiente acudienteNuevo = new Acudiente();
        acudienteNuevo.setNombres(acudiente.getNombres());
        acudienteNuevo.setApellidos(acudiente.getApellidos());
        acudienteNuevo.setTipoDocumento(acudiente.getTipoDocumento());
        acudienteNuevo.setNumeroDocumento(acudiente.getNumeroDocumento());
        acudienteNuevo.setTelefono(acudiente.getTelefono());
        acudienteNuevo.setDireccion(acudiente.getDireccion());
        acudienteNuevo.setIdAlumno(acudiente.getIdAlumno());

        return repositorio.save(acudienteNuevo);
	}

	public String deleteAcudiente(String idAcudiente) {
		Optional<Acudiente> acudienteEncontrado = repositorio.findById(idAcudiente);
		if (acudienteEncontrado.isPresent()) {
			List<Estudiante> listaEstudiantes = estudianteRepo.findByIdAcudiente(idAcudiente);
			if (listaEstudiantes.size() > 0) {
				for (int i = 0; i < listaEstudiantes.size(); i++) {
					listaEstudiantes.get(i).setIdAcudiente("");
				}
				estudianteRepo.saveAll(listaEstudiantes);
			}
			repositorio.deleteById(idAcudiente);
			return "eliminado correctamente";
		}
		return "Error, No se encontró el acudiente";
	}

}
