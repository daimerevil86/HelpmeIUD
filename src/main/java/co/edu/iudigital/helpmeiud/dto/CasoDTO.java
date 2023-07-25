package co.edu.iudigital.helpmeiud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CasoDTO {


    Long id;
    @JsonProperty("fecha_hora")
    LocalDateTime fechaHora;

    float latitud;

    float longitud;

    float altitud;

    String descripcion;
    @JsonProperty("es_visible")
    boolean esVisible;
    @JsonProperty("url_map")
    String urlMap;
    @JsonProperty("rmi_url")
    String rmiUrl;
    @JsonProperty("usuario_id")
    Long usuarioId;
    @JsonProperty(value = "delito_id")
    Long delitoId;
}
