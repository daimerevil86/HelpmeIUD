package co.edu.iudigital.helpmeiud.dto.response;
/*
* se coloca en el paquete response porque de esta forma quiero que se presenten los Delitos
* */
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DelitoDTO {

    Long id;
    String nombre;
    String descripcion;

}
