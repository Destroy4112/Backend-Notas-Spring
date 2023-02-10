package com.zaperoko.notas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zaperoko.notas.model.AlumnoCurso;

@Repository
public interface AlumnoCursoRepository extends MongoRepository<AlumnoCurso, String> {

    public List<AlumnoCurso> findByIdEstudiante(String idestudiante);

    
}