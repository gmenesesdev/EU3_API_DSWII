package evu3_api.evu3_api_grupo11.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evu3_api.evu3_api_grupo11.models.Estudiante;
import evu3_api.evu3_api_grupo11.repositories.EstudianteRepository;
import evu3_api.evu3_api_grupo11.responses.EstudianteResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<EstudianteResponse> getAllEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(est -> new EstudianteResponse(est.getId(), est.getNombreCompleto(), est.getCarrera(), est.getEmail()))
                .collect(Collectors.toList());
    }

    public EstudianteResponse getEstudianteById(String id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return new EstudianteResponse(estudiante.getId(), estudiante.getNombreCompleto(), estudiante.getCarrera(), estudiante.getEmail());
    }

    public EstudianteResponse saveEstudiante(EstudianteResponse estudianteResponse) {
        Estudiante estudiante = new Estudiante(estudianteResponse.getId(), estudianteResponse.getNombreCompleto(), estudianteResponse.getCarrera(), estudianteResponse.getEmail());
        estudianteRepository.save(estudiante);
        return estudianteResponse;
    }

    public void deleteEstudiante(String id) {
        estudianteRepository.deleteById(id);
    }
}
