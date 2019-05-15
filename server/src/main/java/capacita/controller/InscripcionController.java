package capacita.controller;

import capacita.model.Inscripcion;
import capacita.model.Usuario;
import capacita.repository.dto.CursosPorLegajoResponse;
import capacita.services.InscripcionService;
import capacita.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inscripcion")
public class InscripcionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<ResponseReporteInscripciones> getAllInscripciones(){

        Usuario usuario;
        ResponseReporteInscripciones responseObj;

        List<ResponseReporteInscripciones> responseList = new ArrayList<ResponseReporteInscripciones>();

        List<CursosPorLegajoResponse> listaCursosDeUnLegajo;
        List<Integer> listaLegajos = inscripcionService.getDistinctLegajosInscriptos();

        for (Integer legajo : listaLegajos) {
            usuario = usuarioService.findUsuarioByLegajo(legajo);
            listaCursosDeUnLegajo = inscripcionService.getInscripcionesPorLegajo(legajo);
            responseObj = new ResponseReporteInscripciones(usuario,listaCursosDeUnLegajo);
            responseList.add(responseObj);
        }
        return responseList;
    }

    @PostMapping
    public ResponseEntity<String> createInscripcion(@RequestBody RequestInscripcion reqInscripcion) {
        Inscripcion insc = new Inscripcion(reqInscripcion.getLegajo(), reqInscripcion.getIdCurso());
        inscripcionService.saveInscripcion(insc);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<List<CursosPorLegajoResponse>> getInscripcionesPorLegajo(@PathVariable("legajo") Integer legajo) {
        List<CursosPorLegajoResponse> inscripciones = inscripcionService.getInscripcionesPorLegajo(legajo);
        return new ResponseEntity<List<CursosPorLegajoResponse>>(HttpStatus.OK);
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



class ResponseReporteInscripciones {

    Usuario usuario;
    List<CursosPorLegajoResponse> cursos;

    public ResponseReporteInscripciones(Usuario usuario, List<CursosPorLegajoResponse> cursos) {
        this.usuario = usuario;
        this.cursos = cursos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CursosPorLegajoResponse> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursosPorLegajoResponse> cursos) {
        this.cursos = cursos;
    }
}


