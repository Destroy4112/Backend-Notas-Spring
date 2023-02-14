package com.zaperoko.notas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaperoko.notas.model.AlumnoCurso;
import com.zaperoko.notas.service.AlumnoCursoService;

@RestController
@RequestMapping("api/alumno_curso")
@CrossOrigin(origins="*")
public class AlumnoCursoController {

    @Autowired
    private AlumnoCursoService servicio;

    @PostMapping
    public ResponseEntity<?> agregarAlumnoCursos(@RequestBody AlumnoCurso detalle) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.addAlumnoCurso(detalle));
    }
    
    @GetMapping
    public ResponseEntity<?> consultarAlumnoCursos() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getAlumnoCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarAlumnoCursoPorId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getAlumnoCursosById(id));
    }
    
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<?> consultarAlumnoCursoPorEstudiante(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getAlumnoCursosByAlumno(id));
    }

}