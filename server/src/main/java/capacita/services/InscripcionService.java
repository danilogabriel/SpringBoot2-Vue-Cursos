package capacita.services;

import capacita.model.Inscripcion;
import capacita.repository.InscripcionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InscripcionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InscripcionService.class);

    @Autowired
    private InscripcionRepository repoInscripcion;

    public Inscripcion saveInscripcion (Inscripcion newInscripcion){
        return repoInscripcion.save(newInscripcion);
    }

    public List<Inscripcion> getInscripciones(){
        return repoInscripcion.findAll();
    }

    public List<Map<String, Object>> getInscripcionesPorLegajo(Integer legajo){
        return repoInscripcion.getDistinctCursosPorLegajo(legajo);
    }

    public List<Map<String, Object>> getInscripcionesPorCurso(Integer idcurso){
        return repoInscripcion.getDistinctLegajosPorCurso(idcurso);
    }

    public List<Integer> getDistinctLegajosInscriptos() {
        return repoInscripcion.getDistinctLegajosInscriptos();
    }

    public List<Integer> getDistinctCursos() {
        return repoInscripcion.getDistinctCursos();
    }
}
