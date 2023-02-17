package com.zaperoko.notas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zaperoko.notas.model.Curso;

@Repository
public interface CursosRepository extends MongoRepository<Curso, String> {

	public Optional<Curso> findByDescripcionCurso(String descripcionCurso);
	
	@Query("{idGrado:'?0', idGrupo:'?1', idYear:'?2'}")
	public Optional<Curso> buscarCurso(String grado, String grupo, String year);

	@Query("{ descripcionCurso:?0, idYear:'?1' }")
	public Optional<Curso> findCursoByDescripcion(String descripcionCurso, String idYear);

	public List<Curso> findByIdGrado(String idGrado);

	public List<Curso> findByIdGrupo(String idGrupo);

	public List<Curso> findByIdYear(String idYear);

	@Query("{ idProfesorAsignatura: { $in : ['?0'] } }")
	public Optional<Curso> findByIdProfesorAsignatura(String asignatura);

	
	@Query("{alumnoCurso: {$in: ['?0']}, idYear:'?1'}")
	public Optional<Curso> findByIdAlumnoCursoAndIdYear(String alumnoCursoId, String idYear);

	@Query("{alumnoCurso: {$in: ['?0']} }")
	public Optional<Curso> findByIdAlumnoCurso(String alumnoCursoId);
	
}