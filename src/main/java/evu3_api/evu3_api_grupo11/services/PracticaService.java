package evu3_api.evu3_api_grupo11.services;

import evu3_api.evu3_api_grupo11.models.Empresa;
import evu3_api.evu3_api_grupo11.models.Estudiante;
import evu3_api.evu3_api_grupo11.models.JefeDirecto;
import evu3_api.evu3_api_grupo11.models.Practica;
import evu3_api.evu3_api_grupo11.responses.PracticaRequest;
import evu3_api.evu3_api_grupo11.responses.PracticaResponse;
import evu3_api.evu3_api_grupo11.responses.PracticaUpdateRequest;
import evu3_api.evu3_api_grupo11.repositories.EmpresaRepository;
import evu3_api.evu3_api_grupo11.repositories.EstudianteRepository;
import evu3_api.evu3_api_grupo11.repositories.PracticaRepository;
import evu3_api.evu3_api_grupo11.repositories.jefeDirectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PracticaService {

    @Autowired
    private PracticaRepository practicaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private jefeDirectoRepository jefeDirectoRepository;

    public List<PracticaResponse> getAllPracticas() {
        return practicaRepository.findAll().stream().map(prac -> new PracticaResponse(
                prac.getId(),
                prac.getFechaInicio(),
                prac.getFechaTermino(),
                prac.getEmpresa().getNombre(),
                prac.getJefeDirecto().getNombre(),
                prac.getEstudiante().getNombreCompleto())).collect(Collectors.toList());
    }

    public PracticaResponse getPracticaById(Long id) {
        Practica practica = practicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Practica no encontrada"));
        return new PracticaResponse(
                practica.getId(),
                practica.getFechaInicio(),
                practica.getFechaTermino(),
                practica.getEmpresa().getNombre(),
                practica.getJefeDirecto().getNombre(),
                practica.getEstudiante().getNombreCompleto());
    }

    public PracticaResponse savePractica(PracticaRequest request) {
        Empresa empresa = empresaRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        JefeDirecto jefeDirecto = jefeDirectoRepository.findById(request.getJefeDirectoId())
                .orElseThrow(() -> new RuntimeException("Jefe Directo no encontrado"));

        Practica practica = new Practica();
        practica.setFechaInicio(request.getFechaInicio());
        practica.setFechaTermino(request.getFechaTermino());
        practica.setEmpresa(empresa);
        practica.setEstudiante(estudiante);
        practica.setJefeDirecto(jefeDirecto);

        Practica savedPractica = practicaRepository.save(practica);

        return new PracticaResponse(
                savedPractica.getId(),
                savedPractica.getFechaInicio(),
                savedPractica.getFechaTermino(),
                empresa.getNombre(),
                estudiante.getNombreCompleto(),
                jefeDirecto.getNombre());
    }

    public PracticaResponse updatePractica(Long practicaId, String estudianteId, PracticaUpdateRequest request) {
        Practica practica = practicaRepository.findById(practicaId)
                .orElseThrow(() -> new RuntimeException("Práctica no encontrada"));

        // Validar que la práctica pertenece al estudiante
        if (!practica.getEstudiante().getId().equals(estudianteId)) {
            throw new RuntimeException("La práctica no pertenece al estudiante indicado");
        }

        // Actualizar empresa si es necesario
        if (request.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(request.getEmpresaId())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
            practica.setEmpresa(empresa);
        }

        // Actualizar jefe directo si es necesario
        if (request.getJefeDirectoId() != null) {
            JefeDirecto jefeDirecto = jefeDirectoRepository.findById(request.getJefeDirectoId())
                    .orElseThrow(() -> new RuntimeException("Jefe Directo no encontrado"));
            practica.setJefeDirecto(jefeDirecto);
        }

        // Actualizar fechas
        if (request.getFechaInicio() != null) {
            practica.setFechaInicio(request.getFechaInicio());
        }
        if (request.getFechaTermino() != null) {
            practica.setFechaTermino(request.getFechaTermino());
        }

        Practica updatedPractica = practicaRepository.save(practica);

        return new PracticaResponse(
                updatedPractica.getId(),
                updatedPractica.getFechaInicio(),
                updatedPractica.getFechaTermino(),
                updatedPractica.getEmpresa().getNombre(),
                updatedPractica.getEstudiante().getNombreCompleto(),
                updatedPractica.getJefeDirecto().getNombre());
    }

    public void deletePractica(Long id) {
        // Verifica que la práctica exista antes de eliminarla
        if (!practicaRepository.existsById(id)) {
            throw new RuntimeException("Práctica no encontrada");
        }
        practicaRepository.deleteById(id);
    }
}
