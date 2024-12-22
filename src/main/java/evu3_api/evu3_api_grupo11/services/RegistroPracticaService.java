package evu3_api.evu3_api_grupo11.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evu3_api.evu3_api_grupo11.models.Practica;
import evu3_api.evu3_api_grupo11.models.RegistroPractica;
import evu3_api.evu3_api_grupo11.repositories.PracticaRepository;
import evu3_api.evu3_api_grupo11.repositories.RegistroPracticaRepository;
import evu3_api.evu3_api_grupo11.responses.RegistroPracticaResponse;

@Service
public class RegistroPracticaService {

    @Autowired
    private RegistroPracticaRepository registroPracticaRepository;

    @Autowired
    private PracticaRepository practicaRepository;

    public List<RegistroPracticaResponse> getAllRegistros() {
        return registroPracticaRepository.findAll().stream().map(reg -> new RegistroPracticaResponse(
                reg.getId(),
                reg.getFecha(),
                reg.getRegistroActividad(),
                reg.getPractica().getEstudiante().getNombreCompleto())).collect(Collectors.toList());
    }

    public RegistroPracticaResponse getRegistroById(Long id) {
        RegistroPractica registro = registroPracticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        // Validar que la práctica asociada no sea nula
        if (registro.getPractica() == null) {
            throw new RuntimeException("El registro no tiene una práctica asociada");
        }

        // Validar que el estudiante asociado no sea nulo
        if (registro.getPractica().getEstudiante() == null) {
            throw new RuntimeException("La práctica no tiene un estudiante asociado");
        }

        return new RegistroPracticaResponse(
                registro.getId(),
                registro.getFecha(),
                registro.getRegistroActividad(),
                registro.getPractica().getEstudiante().getNombreCompleto());
    }

    public RegistroPracticaResponse saveRegistro(RegistroPractica registro) {
        registroPracticaRepository.save(registro);
        return new RegistroPracticaResponse(
                registro.getId(),
                registro.getFecha(),
                registro.getRegistroActividad(),
                registro.getPractica().getEstudiante().getNombreCompleto());
    }

    public void deleteRegistro(Long id) {
        // Verifica que el registro exista antes de eliminarlo
        if (!registroPracticaRepository.existsById(id)) {
            throw new RuntimeException("Registro de práctica no encontrado");
        }
        registroPracticaRepository.deleteById(id);
    }

    public List<RegistroPracticaResponse> getRegistrosByEstudiante(String estudianteId) {
        return registroPracticaRepository.findAll().stream()
                .filter(reg -> reg.getPractica() != null && reg.getPractica().getEstudiante() != null)
                .filter(reg -> reg.getPractica().getEstudiante().getId().equals(estudianteId))
                .map(reg -> new RegistroPracticaResponse(
                        reg.getId(),
                        reg.getFecha(),
                        reg.getRegistroActividad(),
                        reg.getPractica().getEstudiante().getNombreCompleto()
                ))
                .collect(Collectors.toList());
    }

    public RegistroPracticaResponse updateRegistro(String estudianteId, RegistroPractica registroPractica) {
        // Buscar el registro existente
        RegistroPractica existingRegistro = registroPracticaRepository.findById(registroPractica.getId())
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    
        // Validar que la práctica asociada pertenece al estudiante indicado
        if (!existingRegistro.getPractica().getEstudiante().getId().equals(estudianteId)) {
            throw new RuntimeException("El registro no pertenece al estudiante indicado");
        }
    
        // Actualizar los campos del registro
        existingRegistro.setFecha(registroPractica.getFecha());
        existingRegistro.setRegistroActividad(registroPractica.getRegistroActividad());
        registroPracticaRepository.save(existingRegistro);
    
        // Retornar el DTO con todos los campos necesarios
        return new RegistroPracticaResponse(
                existingRegistro.getId(),
                existingRegistro.getFecha(),
                existingRegistro.getRegistroActividad(),
                existingRegistro.getPractica().getEstudiante().getNombreCompleto() // Incluido en la respuesta
        );
    }
    

    public RegistroPracticaResponse saveRegistro(String estudianteId, RegistroPractica registroPractica) {
        if (registroPractica.getPractica() == null || registroPractica.getPractica().getId() == null) {
            throw new RuntimeException("La práctica no está definida o no tiene un ID válido");
        }

        // Usar la instancia de practicaRepository para buscar la práctica
        Practica practica = practicaRepository.findById(registroPractica.getPractica().getId())
                .orElseThrow(() -> new RuntimeException("La práctica no existe"));

        // Validar que la práctica pertenece al estudiante indicado
        if (!practica.getEstudiante().getId().equals(estudianteId)) {
            throw new RuntimeException("La práctica no pertenece al estudiante indicado");
        }

        // Asociar la práctica encontrada al registro
        registroPractica.setPractica(practica);

        // Guardar el registro en la base de datos
        RegistroPractica savedRegistro = registroPracticaRepository.save(registroPractica);

        // Crear la respuesta
        return new RegistroPracticaResponse(
                savedRegistro.getId(),
                savedRegistro.getFecha(),
                savedRegistro.getRegistroActividad(),
                savedRegistro.getPractica().getEstudiante().getNombreCompleto()
        );
    }

}
