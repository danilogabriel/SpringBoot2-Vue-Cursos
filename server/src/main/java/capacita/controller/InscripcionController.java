package capacita.controller;

import capacita.model.Curso;
import capacita.model.Inscripcion;
import capacita.services.CursoService;
import capacita.services.InscripcionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripcion")
public class InscripcionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<String> createInscripcion(@RequestBody RequestInscripcion reqInscripcion) {

        Inscripcion insc = new Inscripcion(reqInscripcion.getLegajo(), reqInscripcion.getIdCurso());
        inscripcionService.saveInscripcion(insc);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

class RequestInscripcion {

    Integer legajo;
    Integer idCurso;

    public RequestInscripcion(Integer legajo, Integer idCurso) {
        this.legajo = legajo;
        this.idCurso = idCurso;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }
}


