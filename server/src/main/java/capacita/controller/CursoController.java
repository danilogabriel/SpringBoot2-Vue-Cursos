package capacita.controller;

import capacita.model.Curso;
import capacita.model.Usuario;
import capacita.repository.CursoRepository;
import capacita.repository.UsuarioRepository;

import capacita.services.CursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RoleInfoNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<String> addCurso(@RequestBody Curso newCurso) {
        cursoService.createCurso(newCurso.getUsuario().getLegajo(), newCurso);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{legajo}")
    public List<Curso> getCursosPorLegajo(@PathVariable Integer legajo) {
        return cursoService.getCursosPublicadoYPropios(legajo);
    }

    @RequestMapping("/{id}")
    public Optional<Curso> getCursoPorId(@PathVariable("id") Integer id) {
        return cursoService.getCursoPorId(id);
    }

    @PostMapping("/usuario/{legajo}")
    public Curso createCurso(@PathVariable(value = "legajo") Integer legajo, @Valid @RequestBody Curso curso) {
        return cursoService.createCurso(legajo, curso);
    }

}
