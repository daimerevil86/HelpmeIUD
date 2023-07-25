package co.edu.iudigital.helpmeiud.service.impl;

import co.edu.iudigital.helpmeiud.dto.CasoDTO;
import co.edu.iudigital.helpmeiud.exceptions.BadRequestException;
import co.edu.iudigital.helpmeiud.exceptions.ErrorDto;
import co.edu.iudigital.helpmeiud.exceptions.RestException;
import co.edu.iudigital.helpmeiud.model.Caso;
import co.edu.iudigital.helpmeiud.model.Delito;
import co.edu.iudigital.helpmeiud.model.Usuario;
import co.edu.iudigital.helpmeiud.repository.ICasoRepository;
import co.edu.iudigital.helpmeiud.repository.IDelitoRepository;
import co.edu.iudigital.helpmeiud.repository.IUsuarioRepository;
import co.edu.iudigital.helpmeiud.service.iface.ICasoService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service// se anota con services daso que es hijo de Component y se utiliza para colocarlo en el contenedor de Spring
public class CasoServiceImpl implements ICasoService {

    @Autowired
    private ICasoRepository casoRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IDelitoRepository delitoRepository;
    @Override
    public List<CasoDTO> consultarTodos() {
        List<Caso> casos =  casoRepository.findAll();
        // Programacion Imperativa
        /*List<CasoDTO> casosDTO = new ArrayList<>();
        for(Caso caso:casos){
            CasoDTO casoDTO = CasoDTO.builder()
                    .id(caso.getId())
                    .descripcion(caso.getDescripcion())
                    .altitud(caso.getAltitud())
                    .latitud(caso.getLatitud())
                    .longitud(caso.getLongitud())
                    .fechaHora(caso.getFechaHora())
                    .rmiUrl(caso.getRmiUrl())
                    .urlMap(caso.getUrlMap())
                    .usuarioId(caso.getUsuario().getId())
                    .delitoId(caso.getDelito().getId())
                    .esVisible(caso.isEsVisible())
                    .build();
            casosDTO.add(casoDTO);

        }*/
        /*
        Programacion Funcional
         */
        return casos.stream().map(caso ->
                    CasoDTO.builder()
                            .id(caso.getId())
                            .descripcion(caso.getDescripcion())
                            .altitud(caso.getAltitud())
                            .latitud(caso.getLatitud())
                            .longitud(caso.getLongitud())
                            .fechaHora(caso.getFechaHora())
                            .rmiUrl(caso.getRmiUrl())
                            .urlMap(caso.getUrlMap())
                            .usuarioId(caso.getUsuario().getId())
                            .delitoId(caso.getDelito().getId())
                            .esVisible(caso.isEsVisible())
                            .build()

        ).collect(Collectors.toList());



    }

    @Transactional
    @Override
    public Caso crear(CasoDTO casoDTO) throws RestException {
        Optional<Usuario> usuario = usuarioRepository.findById(casoDTO.getUsuarioId());
        Optional <Delito>  delito = delitoRepository.findById(casoDTO.getDelitoId());
        if(!usuario.isPresent() || !delito.isPresent()){
            throw new BadRequestException(
                    ErrorDto.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("No existe usuario o Delito")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        Caso caso = new Caso();
        caso.setDescripcion(casoDTO.getDescripcion());
        caso.setAltitud(casoDTO.getAltitud());
        caso.setLatitud(casoDTO.getLatitud());
        caso.setLongitud(casoDTO.getLongitud());
        caso.setFechaHora(casoDTO.getFechaHora());
        caso.setRmiUrl(casoDTO.getRmiUrl());
        caso.setUrlMap(casoDTO.getUrlMap());
        caso.setEsVisible(true);
        caso.setUsuario(usuario.get());// forma de obtener datos delos Optional
        caso.setDelito(delito.get());

        return casoRepository.save(caso);
    }

    @Transactional
    @Override
    public Boolean visible(Boolean visible, Long id) {

        return casoRepository.setVisible(visible, id);
    }

    @Transactional (readOnly = true)
    @Override
    public CasoDTO consultarPorId(Long id) {
        Optional<Caso> casoOptional = casoRepository.findById(id);
        if(casoOptional.isPresent()){
            Caso caso = casoOptional.get();
            return CasoDTO.builder()
                    .id(caso.getId())
                    .descripcion(caso.getDescripcion())
                    .altitud(caso.getAltitud())
                    .latitud(caso.getLatitud())
                    .longitud(caso.getLongitud())
                    .fechaHora(caso.getFechaHora())
                    .rmiUrl(caso.getRmiUrl())
                    .urlMap(caso.getUrlMap())
                    .usuarioId(caso.getUsuario().getId())
                    .delitoId(caso.getDelito().getId())
                    .esVisible(caso.isEsVisible())
                    .build();
        }

        return  null;




    }
}
