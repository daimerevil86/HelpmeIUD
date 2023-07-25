package co.edu.iudigital.helpmeiud.service.iface;

import co.edu.iudigital.helpmeiud.dto.request.DelitoDTORequest;
import co.edu.iudigital.helpmeiud.dto.response.DelitoDTO;
import co.edu.iudigital.helpmeiud.exceptions.BadRequestException;

import java.util.List;

public interface IDelitoService {

    /*
    Consultar todos los delitos
     */
    List<DelitoDTO> consultarTodos();//Throw Exception
//consultar delitos po ID
    DelitoDTO consultarPorId(Long id); //throw Exception

    // guardar Delitos
    DelitoDTO guardarDelito(DelitoDTORequest delitoDTORequest) throws BadRequestException;

    //borrar delito por ID
    void borrarDelitoPorId(Long id);


}
