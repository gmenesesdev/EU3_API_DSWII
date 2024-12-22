package evu3_api.evu3_api_grupo11.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}
