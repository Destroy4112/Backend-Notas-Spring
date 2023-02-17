package com.zaperoko.notas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zaperoko.notas.model.Nota;

@Repository
public interface NotaRepository extends MongoRepository<Nota, String> {
	
	public List<Nota> findByIdAlumnoCurso(String idAlumnoCurso);
	
	@Query("{idAlumnoCurso:'?0', idAsignatura:'?1'}")
    public List<Nota> findByAlumnoAndAsignatura(String alumnoCurso, String asignatura);

} 