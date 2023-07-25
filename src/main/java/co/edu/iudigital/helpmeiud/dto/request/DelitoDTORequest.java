package co.edu.iudigital.helpmeiud.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DelitoDTORequest {
    @NotNull(message = "El Nombre No Puede ser Nulo")
    @NotEmpty(message = "El nombre no puede ser vacio")
    String nombre;
    String descripcion;
    @NotNull(message = "Debe Proporcional el ID del usuario")
    @JsonProperty("usuario_id")// Cuando se envie el dato al JSON se puede enviar como usuario_id en ellabel del json
    Long usuarioId;

}
