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

import com.zaperoko.notas.model.Nota;
import com.zaperoko.notas.service.NotaService;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*")
public class NotasController {
	@Autowired
	private NotaService servicio;

	@PostMapping
	public ResponseEntity<?> crearNotas(@RequestBody Nota nota) {
		Nota resultado = servicio.addNota(nota);
		if (resultado == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultado);
		}
	}

	@GetMapping
	public ResponseEntity<?> cargarNotas() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getNotas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> cargarNotasByAlumno(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getNotasByAlumno(id));
	}

	@GetMapping("/alumno_Asignatura/{alumnoCurso}/{asignatura}")
	public ResponseEntity<?> cargarNotasPorAlumnoYAsignatura(@PathVariable String alumnoCurso,
			@PathVariable String asignatura) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(servicio.getNotasByAlumnoAndAsignatura(alumnoCurso, asignatura));
	}

	@PutMapping
	public ResponseEntity<?> updateGrado(@RequestBody Nota nota) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.updateNota(nota));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarNotas(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.deleteNota(id));
	}

}