package evu3_api.evu3_api_grupo11.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticaResponse {
    private Long id;
    private String fechaInicio;
    private String fechaTermino;
    private String empresaNombre;
    private String jefeDirectoNombre;
    private String estudianteNombre;
}
