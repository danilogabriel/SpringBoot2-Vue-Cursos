package capacita.controller;

import capacita.model.Curso;
import capacita.model.Usuario;
import capacita.repository.CursoRepository;
import capacita.repository.UsuarioRepository;

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
    private CursoRepository repoCurso;

    @Autowired
    private UsuarioRepository repoUsuario;

    @RequestMapping
    public List<Curso> getCursos() {
        LOGGER.info("Obtener solo cursos publicados");
        return repoCurso.findByPublicadoTrue();
    }

    @PostMapping
    public ResponseEntity<String> addCurso(@RequestBody Curso newCurso) {
        repoCurso.save(newCurso);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/{id}")
    public Optional<Curso> getCursoPorId(@PathVariable("id") Integer id) {
        return repoCurso.findById(id);
    }


    @PostMapping("/usuario/{legajo}")
    public Curso createCurso(@PathVariable(value = "legajo") Integer legajo, @Valid @RequestBody Curso curso) {
        Usuario usu=null;
        try{
            usu = repoUsuario.findByLegajo(legajo);
            if (usu == null) throw new RoleInfoNotFoundException("Usuario legajo: " + legajo + " not found");
            
        }catch(RoleInfoNotFoundException e){ 
            LOGGER.error("Error: {}", e.getMessage());
        }
        curso.setUsuario(usu);
        return repoCurso.save(curso);            
    }

}
