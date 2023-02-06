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

import com.zaperoko.notas.model.Acudiente;
import com.zaperoko.notas.service.AcudienteService;

@RestController
@RequestMapping("api/acudientes")
@CrossOrigin(origins="*")
public class AcudienteController {
    
    @Autowired
    private AcudienteService acudienteServicio;

    @PostMapping()
    public ResponseEntity<?> crearAcudiente(@RequestBody Acudiente acudiente){
        Acudiente resultado = acudienteServicio.addAcudiente(acudiente);
        if(resultado==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<?> consultarAcudientes(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(acudienteServicio.getAcudiente());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> consultarAcudientePorId(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(acudienteServicio.getById(id));
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<?> consultarAcudientePorDocumento(@PathVariable String documento){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(acudienteServicio.getByDocumento(documento));
    }

    @PutMapping()
    public ResponseEntity<?> actualizarAcudiente(@RequestBody Acudiente acudiente){
        Acudiente resultado = acudienteServicio.updateAcudiente(acudiente);
        if(resultado==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAcudientePorId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(acudienteServicio.deleteAcudiente(id));
    }
    
}
