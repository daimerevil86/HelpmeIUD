package co.edu.iudigital.helpmeiud.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="casos")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Caso implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column(name = "fecha_hora", nullable = false)
    LocalDateTime fechaHora;
    @Column
    float latitud;
    @Column
    float longitud;
    @Column
    float altitud;
    @Column
    String descripcion;
    @Column(name = "es_visible")
    boolean esVisible;
    @Column(name = "url_map")
    String urlMap;
    @Column(name = "rmi_url")
    String rmiUrl;
    @ManyToOne
            @JoinColumn(name = "usuario_id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "delito_id")
    Delito delito;

    @PrePersist
    public void prePersist(){
        if(Objects.isNull(fechaHora)){
            fechaHora=LocalDateTime.now();
        }
    }



}
