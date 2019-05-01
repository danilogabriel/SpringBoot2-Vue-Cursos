package capacita.controller;

import capacita.model.Usuario;
import capacita.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioRepository repoUsuario;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return repoUsuario.findAll();
    }

    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usu){
        //usu.setPassword(bCryptPasswordEncoder.encode(usu.getPassword()));
        repoUsuario.save(usu);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{legajo}")
    public Usuario getUsuariosPorLegajo(@PathVariable("legajo") Integer legajo){
        return repoUsuario.findByLegajo(legajo);
    }

    @GetMapping("/{username}")
    public Usuario getUsuarioPorMail(@PathVariable String username) {
        return repoUsuario.findByUsername(username);
    }
}
