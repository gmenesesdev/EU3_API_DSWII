package evu3_api.evu3_api_grupo11.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import evu3_api.evu3_api_grupo11.responses.PracticaResponse;
import evu3_api.evu3_api_grupo11.services.PracticaService;

@RestController
@RequestMapping("/api/practicas")
public class PracticaController {

    @Autowired
    private PracticaService practicaService;

    @GetMapping
    public ResponseEntity<List<PracticaResponse>> getAllPracticas() {
        return ResponseEntity.ok(practicaService.getAllPracticas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracticaResponse> getPracticaById(@PathVariable Long id) {
        return ResponseEntity.ok(practicaService.getPracticaById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractica(@PathVariable Long id) {
        practicaService.deletePractica(id);
        return ResponseEntity.noContent().build();
    }
}
