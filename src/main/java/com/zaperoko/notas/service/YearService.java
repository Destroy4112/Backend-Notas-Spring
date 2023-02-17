package com.zaperoko.notas.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.model.Year;
import com.zaperoko.notas.repository.CursosRepository;
import com.zaperoko.notas.repository.YearRepository;

@Service
public class YearService {

	@Autowired
	private YearRepository repositorio;
	@Autowired
	private CursosRepository repositorioCurso;

	public Year addYear(Year year) {

		Optional<Year> years = repositorio.findBydescripcionYear(year.getDescripcionYear());
		if (years.isPresent()) {
			String anio = years.get().getDescripcionYear() + " Registrado";
			years.get().setDescripcionYear(anio);
			return years.get();
		}
		return repositorio.insert(year);

	}

	public List<Year> getYears() {
		List<Year> years = repositorio.findAll();
		years.sort(Comparator.comparing(Year::getDescripcionYear));
		return years;
	}

	public Optional<Year> getYearsById(String id) {
		return repositorio.findById(id);
	}

	public Optional<Year> getYearByDescripcion(String descripcion) {
		return repositorio.findBydescripcionYear(descripcion);
	}

	public Year updateYears(Year year) {
		Optional<Year> resultado = repositorio.findBydescripcionYear(year.getDescripcionYear());
		if (resultado.isPresent()) {
			String anio = resultado.get().getDescripcionYear() + " Registrado";
			resultado.get().setDescripcionYear(anio);
			return resultado.get();
		}
		Optional<Year> years = repositorio.findById(year.getId());
		if (years.isPresent()) {
			return repositorio.save(year);
		}
		return null;
	}

	public String deleteYear(String id) {
		Optional<Year> anio = repositorio.findById(id);
		if (anio.isPresent()) {
			List<Curso> yearEnCurso = repositorioCurso.findByIdYear(anio.get().getId());
			if (yearEnCurso.size() > 0) {
				for (int i = 0; i < yearEnCurso.size(); i++) {
					yearEnCurso.get(i).setIdYear("");
				}
				repositorioCurso.saveAll(yearEnCurso);
			}
			repositorio.delete(anio.get());
			return "Eliminado Correctamente";
		}
		return "No existe ningun año registrado con ese id";
	}

}