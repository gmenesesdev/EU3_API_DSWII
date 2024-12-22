package evu3_api.evu3_api_grupo11.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import evu3_api.evu3_api_grupo11.models.RegistroPractica;
import evu3_api.evu3_api_grupo11.responses.RegistroPracticaResponse;
import evu3_api.evu3_api_grupo11.services.RegistroPracticaService;


@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private RegistroPracticaService registroPracticaService;

    @GetMapping("/{id}/registros")
    public ResponseEntity<List<RegistroPracticaResponse>> getRegistrosByEstudiante(@PathVariable String id) {
        return ResponseEntity.ok(registroPracticaService.getRegistrosByEstudiante(id));
    }

    @PostMapping("/{id}/registros")
    public ResponseEntity<RegistroPracticaResponse> saveRegistro(@PathVariable String id, @RequestBody RegistroPractica registroPractica) {
        return ResponseEntity.ok(registroPracticaService.saveRegistro(id, registroPractica));
    }
}

