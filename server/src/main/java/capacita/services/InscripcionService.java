package capacita.services;

import capacita.model.Curso;
import capacita.model.Inscripcion;
import capacita.model.Usuario;
import capacita.repository.InscripcionRepository;
import capacita.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InscripcionService.class);

    @Autowired
    private InscripcionRepository repoInscripcion;

    public List<Inscripcion> getInscripciones(){
        return repoInscripcion.findAll();
    }

    public Inscripcion saveInscripcion (Inscripcion newInscripcion){
        return repoInscripcion.save(newInscripcion);
    }

}
