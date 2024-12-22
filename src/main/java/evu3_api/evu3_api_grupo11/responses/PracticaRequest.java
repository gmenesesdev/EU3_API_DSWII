package evu3_api.evu3_api_grupo11.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticaRequest {
    private String fechaInicio;
    private String fechaTermino;
    private Long empresaId;
    private String estudianteId;
    private String jefeDirectoId;
}
