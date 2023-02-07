package com.zaperoko.notas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaperoko.notas.model.ProfesorAsignatura;
import com.zaperoko.notas.service.ProfesorAsignaturaService;

@RestController
@RequestMapping("/api/profesores_asignaturas")
@CrossOrigin(origins="*")
public class ProfesorAsignaturaController {

    @Autowired
    private ProfesorAsignaturaService servicio;

    @PostMapping
    public ResponseEntity<?> actualizarProfesorAsignatura(@RequestBody ProfesorAsignatura profesorAsignatura) {
        ProfesorAsignatura resultado = servicio.actualizarProfesorAsignatura(profesorAsignatura);
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salío mal");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<?> consultarProfesoresAsignatura() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getProfesoresAsignatura());
    }
    
    @GetMapping("/{asignatura}")
    public ResponseEntity<?> consultarPorProfesor(@PathVariable String asignatura) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getByAsignatura(asignatura));
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesorAsignatura(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.deleteProfesorAsignatura(id));
    }

}
