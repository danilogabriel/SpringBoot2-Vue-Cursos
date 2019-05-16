package capacita.controller;

import capacita.model.Curso;
import capacita.model.Inscripcion;
import capacita.model.Usuario;
import capacita.repository.dto.CursosPorLegajoResponse;
import capacita.services.CursoService;
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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripcion")
public class InscripcionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/cursosporlegajo")
    public List<ResponseReporteInscripcionesPorLegajo> getAllInscripciones(){

        Usuario usuario;
        ResponseReporteInscripcionesPorLegajo responseObj;

        List<ResponseReporteInscripcionesPorLegajo> responseList = new ArrayList<ResponseReporteInscripcionesPorLegajo>();

        List<Map<String, Object>> listaCursosDeUnLegajo;
        List<Integer> listaLegajos = inscripcionService.getDistinctLegajosInscriptos();

        for (Integer legajo : listaLegajos) {
            usuario = usuarioService.findUsuarioByLegajo(legajo);
            listaCursosDeUnLegajo = inscripcionService.getInscripcionesPorLegajo(legajo);
            responseObj = new ResponseReporteInscripcionesPorLegajo(usuario,listaCursosDeUnLegajo);
            responseList.add(responseObj);
        }
        return responseList;
    }

    @GetMapping("/inscriptosporcurso")
    public List<ResponseReporteInscripcionesPorCurso> getAllInscripcionesPorCurso(){

        Curso curso;
        ResponseReporteInscripcionesPorCurso responseObj;
        List listaAlumnos = new ArrayList();
        List<ResponseReporteInscripcionesPorCurso> responseList = new ArrayList<ResponseReporteInscripcionesPorCurso>();

        List<Map<String, Object>> listaLegajosDeUnCurso;
        List<Integer> listaIDsCursos = inscripcionService.getDistinctCursos();

        for (Integer idcurso : listaIDsCursos) {
            curso = cursoService.getCursoPorId(idcurso).get();
            listaAlumnos = inscripcionService.getInscripcionesPorCurso(idcurso);
            responseObj = new ResponseReporteInscripcionesPorCurso(curso,listaAlumnos);
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
        List<Map<String, Object>> inscripciones = inscripcionService.getInscripcionesPorLegajo(legajo);
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



class ResponseReporteInscripcionesPorLegajo {
    Usuario usuario;
    List<Map<String, Object>> cursos;

    public ResponseReporteInscripcionesPorLegajo(Usuario usuario, List<Map<String, Object>> cursos) {
        this.usuario = usuario;
        this.cursos = cursos;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Map<String, Object>> getCursos() { return cursos; }
    public void setCursos(List<Map<String, Object>> cursos) { this.cursos = cursos; }
}


class ResponseReporteInscripcionesPorCurso {
    Curso curso;
    List<Map<String, Object>> usuarios;

    public ResponseReporteInscripcionesPorCurso(Curso curso, List<Map<String, Object>> usuarios) {
        this.curso = curso;
        this.usuarios = usuarios;
    }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public List<Map<String, Object>> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Map<String, Object>> usuarios) { this.usuarios = usuarios; }
}