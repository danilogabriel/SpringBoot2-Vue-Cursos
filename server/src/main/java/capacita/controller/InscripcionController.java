package capacita.controller;

import capacita.model.Inscripcion;
import capacita.services.InscripcionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripcion")
public class InscripcionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> getAllInscripciones(){
        return inscripcionService.getInscripciones();
    }

    @PostMapping
    public ResponseEntity<String> createInscripcion(@RequestBody RequestInscripcion reqInscripcion) {

        Inscripcion insc = new Inscripcion(reqInscripcion.getLegajo(), reqInscripcion.getIdCurso());
        inscripcionService.saveInscripcion(insc);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

class RequestInscripcion {

    Integer legajo;
    Integer idcurso;

    public RequestInscripcion(Integer legajo, Integer idcurso) {
        this.legajo = legajo;
        this.idcurso = idcurso;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Integer getIdCurso() {
        return idcurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idcurso = idCurso;
    }
}


