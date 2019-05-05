package capacita.services;

import capacita.controller.AuthController;
import capacita.model.Usuario;
import capacita.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repoUsuario;

    public List<Usuario> getUsuarios(){
        return repoUsuario.findAll();
    }

    public Usuario findUsuarioByLegajo(Integer legajo) {
        Usuario usuarioDB=null;
        try{
            usuarioDB = repoUsuario.findByLegajo(legajo);
            if (usuarioDB == null) throw new Exception("Usuario legajo: " + legajo + " not found");
        }catch(Exception e){
            LOGGER.error("Error: {}", e.getMessage());
        }
        return usuarioDB;
    }

    public Usuario findUsuarioByMail(String mail) {
        Usuario usuarioDB=null;
        try{
            usuarioDB = repoUsuario.findByUsername(mail);
            if (usuarioDB == null) throw new Exception("Usuario mail: " + mail + " not found");
        }catch(Exception e){
            LOGGER.error("Error: {}", e.getMessage());
        }
        return usuarioDB;
    }

    public Boolean setPassword(Integer legajo, String passwd) {
        Usuario usuarioDB=findUsuarioByLegajo(legajo);
        usuarioDB.setPassword(passwd);
        repoUsuario.save(usuarioDB);
        try {
            repoUsuario.save(usuarioDB);
        }catch(Exception e){
            LOGGER.error("Error: {}", e.getMessage());
            return false;
        }
        return true;
    }

}
