package evu3_api.evu3_api_grupo11.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evu3_api.evu3_api_grupo11.models.RegistroPractica;
import evu3_api.evu3_api_grupo11.responses.PracticaRequest;
import evu3_api.evu3_api_grupo11.responses.PracticaResponse;
import evu3_api.evu3_api_grupo11.responses.PracticaUpdateRequest;
import evu3_api.evu3_api_grupo11.responses.RegistroPracticaResponse;
import evu3_api.evu3_api_grupo11.services.PracticaService;
import evu3_api.evu3_api_grupo11.services.RegistroPracticaService;

import java.util.List;

@RestController
@RequestMapping("/api/profesores/practicas")
public class ProfesorController {

    @Autowired
    private RegistroPracticaService registroPracticaService;

    @Autowired
    private PracticaService practicaService;

    @GetMapping
    public ResponseEntity<List<RegistroPracticaResponse>> getAllRegistros() {
        return ResponseEntity.ok(registroPracticaService.getAllRegistros());
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<List<RegistroPracticaResponse>> getRegistrosByEstudiante(@PathVariable String id) {
        return ResponseEntity.ok(registroPracticaService.getRegistrosByEstudiante(id));
    }

    @PostMapping
    public ResponseEntity<PracticaResponse> savePractica(@RequestBody PracticaRequest request) {
        return ResponseEntity.ok(practicaService.savePractica(request));
    }

    @PutMapping("/{id}/estudiantes/{estudianteId}")
    public ResponseEntity<PracticaResponse> updatePractica(
            @PathVariable Long id,
            @PathVariable String estudianteId,
            @RequestBody PracticaUpdateRequest request) {
        return ResponseEntity.ok(practicaService.updatePractica(id, estudianteId, request));
    }

    @PutMapping("/registros/{id}/estudiantes/{estudianteId}")
    public ResponseEntity<RegistroPracticaResponse> updateRegistro(
            @PathVariable Long id,
            @PathVariable String estudianteId,
            @RequestBody RegistroPractica registroPractica) {
        // Asegurarse de que el ID del registro esté configurado en el objeto recibido
        registroPractica.setId(id);
        return ResponseEntity.ok(registroPracticaService.updateRegistro(estudianteId, registroPractica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractica(@PathVariable Long id) {
        practicaService.deletePractica(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/registros/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        registroPracticaService.deleteRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
