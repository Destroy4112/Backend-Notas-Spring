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

import com.zaperoko.notas.model.Curso;
import com.zaperoko.notas.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursosController {

	@Autowired
	private CursoService servicio;

	@PostMapping
	public ResponseEntity<?> crearCurso(@RequestBody Curso curso) {
		Curso resultado = servicio.addCurso(curso);
		if (resultado == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salío mal");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	@GetMapping
	public ResponseEntity<?> consultarCursos() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursos());
	}

<<<<<<< HEAD
	@GetMapping("/{id}")
	public ResponseEntity<?> consultarCursoPorId(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosById(id));
	}

	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<?> consultarCursoPorDescripcion(@PathVariable String descripcion) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursoByDescripcion(descripcion));
	}
=======
    @GetMapping("/{id}")
    public ResponseEntity<?> consultarCursoPorId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosById(id));
    }
    
    @GetMapping("/descripcion/{descripcion}")
    public ResponseEntity<?> consultarCursoPorDescripcion(@PathVariable String descripcion) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursoByDescripcion(descripcion));
    }

    @GetMapping("/grado/{grado}")
    public ResponseEntity<?> consultarCursoPorGrado(@PathVariable String grado) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosByGrado(grado));
    }
    
    @GetMapping("/asignatura/{asignatura}")
    public ResponseEntity<?> consultarCursoPorAsignatura(@PathVariable String asignatura) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosByAsignatura(asignatura));
    }

    @GetMapping("/alumnoCurso/{alumnoCursoId}/{idYear}")
    public ResponseEntity<?> consultarCursoPorAlumnoCurso(@PathVariable String alumnoCursoId, @PathVariable String idYear) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursoByIdAlumnoCursoAndYear(alumnoCursoId, idYear));
    }

    @GetMapping("/year/{idYear}")
    public ResponseEntity<?> consultarCursoYear(@PathVariable String idYear) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursoByIdYear(idYear));
    }

    @GetMapping("/alumnoCursos/{alumnoCursoId}")
    public ResponseEntity<?> consultarCursoPorAlumnoCurso(@PathVariable String alumnoCursoId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosByIdAlumnoCurso(alumnoCursoId));
    }

    @PutMapping
    public ResponseEntity<?> actualizarCurso(@RequestBody Curso curso) {
        Curso resultado = servicio.updateCurso(curso);
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salío mal");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
>>>>>>> c84f5bc7515b35517ea4c85ef5ac6bc4bb0d0d34

	@GetMapping("/grado/{grado}")
	public ResponseEntity<?> consultarCursoPorGrado(@PathVariable String grado) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosByGrado(grado));
	}

	@GetMapping("/asignatura/{asignatura}")
	public ResponseEntity<?> consultarCursoPorAsignatura(@PathVariable String asignatura) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCursosByAsignatura(asignatura));
	}

	@GetMapping("/alumnoCurso/{alumnoCursoId}/{idYear}")
	public ResponseEntity<?> consultarCursoPorAlumnoCurso(@PathVariable String alumnoCursoId,
			@PathVariable String idYear) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(servicio.getCursoByIdAlumnoCursoAndYear(alumnoCursoId, idYear));
	}

	@PutMapping
	public ResponseEntity<?> actualizarCurso(@RequestBody Curso curso) {
		Curso resultado = servicio.updateCurso(curso);
		if (resultado == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salío mal");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCurso(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.deleteCurso(id));
	}
}
