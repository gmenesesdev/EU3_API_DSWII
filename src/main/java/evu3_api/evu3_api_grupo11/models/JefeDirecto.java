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
@Table(name = "jefes_directos")
public class JefeDirecto {
    @Id
    private String id; // Rut

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;
}
