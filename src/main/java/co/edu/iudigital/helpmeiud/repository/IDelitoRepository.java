package co.edu.iudigital.helpmeiud.repository;

import co.edu.iudigital.helpmeiud.model.Delito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDelitoRepository extends JpaRepository <Delito, Long> {
    
    List<Delito> findByNombre(String nombre);// en el findby se coloca el nombre del atributo pero inicando den Mayuscula
}
