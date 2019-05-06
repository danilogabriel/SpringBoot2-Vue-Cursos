package capacita.services;

import capacita.model.Curso;
import capacita.model.Usuario;
import capacita.repository.CursoRepository;
import capacita.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursoService.class);

    @Autowired
    private CursoRepository repoCurso;

    @Autowired
    private UsuarioRepository repoUsuario;

    public Optional<Curso> getCursoPorId(Integer id) {
        return repoCurso.findById(id);
    }

    public List<Curso> getCursosPorLegajo(Integer legajo) {
        return repoCurso.findByUsuarioLegajo(legajo);
    }

    public List<Curso> getCursosPublicadoYPropios(Integer legajo){
        return repoCurso.getPublicadoYPropios(legajo);
    }

    public Curso saveCurso (Integer legajo, Curso cursoParam){
        Usuario usu = null;
        try {
            usu = repoUsuario.findByLegajo(legajo);
            if (usu == null) throw new UsuarioNotFoundException("Usuario legajo: " + legajo + " not found");

        } catch (UsuarioNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
        }
        cursoParam.setUsuario(usu);
        return repoCurso.save(cursoParam);
    }

    private class UsuarioNotFoundException extends Throwable {
        public UsuarioNotFoundException(String s) {
        }
    }
}
