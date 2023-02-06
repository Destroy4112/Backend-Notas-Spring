package com.zaperoko.notas.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zaperoko.notas.model.Grupo;

@Repository
public interface GruposRepository extends MongoRepository<Grupo, String>{
    
    
    public Optional<Grupo> findByNombreGrupo(String nombreGrupo);

    @Query("{ cursoId: { $in : ['?0'] } }")
    public Optional<Grupo> findByCurso(String id);
}