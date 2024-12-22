package evu3_api.evu3_api_grupo11.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import evu3_api.evu3_api_grupo11.models.RegistroPractica;
import evu3_api.evu3_api_grupo11.responses.RegistroPracticaResponse;
import evu3_api.evu3_api_grupo11.services.RegistroPracticaService;

@RestController
@RequestMapping("/api/registros")
public class RegistroPracticaController {

    @Autowired
    private RegistroPracticaService registroPracticaService;

    @GetMapping
    public ResponseEntity<List<RegistroPracticaResponse>> getAllRegistros() {
        return ResponseEntity.ok(registroPracticaService.getAllRegistros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroPracticaResponse> getRegistroById(@PathVariable Long id) {
        return ResponseEntity.ok(registroPracticaService.getRegistroById(id));
    }

    @PostMapping
    public ResponseEntity<RegistroPracticaResponse> saveRegistro(@RequestBody RegistroPractica registroPractica) {
        return ResponseEntity.ok(registroPracticaService.saveRegistro(registroPractica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        registroPracticaService.deleteRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
