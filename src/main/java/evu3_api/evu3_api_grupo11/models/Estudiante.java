package evu3_api.evu3_api_grupo11.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    private String id; // Rut

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private String carrera;

    @Column(nullable = false, unique = true)
    private String email;
}
