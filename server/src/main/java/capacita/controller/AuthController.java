package capacita.controller;

import capacita.model.Usuario;
import capacita.repository.UsuarioRepository;

import capacita.services.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UsuarioRepository repoUsuario;

    @Autowired
    private UsuarioService usuarioService;


    //-------------  JWT PARAMETERS ------------
    @Value("${jwt.expiration}")
    private Integer EXPIRATION;

    @Value("${jwt.secret}")
    private String SECRET;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //String hashedPassword = passwordEncoder.encode(password);

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin reqLogin){

        String token;
        Usuario usuarioDB=null;
        ResponseLogin response = new ResponseLogin(null, null , null);

        try{
            usuarioDB = usuarioService.findUsuarioByLegajo(reqLogin.getLegajo());
            if (usuarioDB == null) throw new Exception("Usuario legajo: " + reqLogin.getLegajo() + " not found");
        }catch(Exception e){
            LOGGER.error("Error: {}", e.getMessage());
        }

        if (usuarioDB == null) {
            response.setMessage("No existe usuario con legajo " + reqLogin.getLegajo());
        } else {
            if (reqLogin.getUsername().equals(usuarioDB.getUsername()) && reqLogin.getCuit().compareTo(usuarioDB.getCuit()) == 0) {

                if (usuarioDB.getPassword() == null){
                    response.setMessage("PASSWORD_REQUIRED");
                } else {

                    response.setMessage("LOGIN_SUCCESS");
                }
                token = getJWTToken(reqLogin.getUsername());
                response.setToken(token);
                response.setUsuario(usuarioDB);
            } else {
                LOGGER.info("Usuario inconsistente: [" + reqLogin.getUsername() + "/"+ reqLogin.getLegajo().toString() + "/" + reqLogin.getCuit().toString() + "]");
                response.setMessage("El username o CUIT no corresponden al usuario con legajo " + reqLogin.getLegajo().toString());
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/firstaccess")
    public ResponseEntity<Boolean> verifyPass(@RequestBody Map<String, Long> params) {
        Long cuit = params.get("cuit");
        Usuario usu = usuarioService.findUsuarioByCuit(cuit);
        if (usu.getPassword()==null) return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }


    @PostMapping("/updatepass")
    public ResponseEntity<String> updatePass(@RequestBody Map<String, String> params) {

        Integer legajo = Integer.valueOf(params.get("legajo"));

        String message=null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(params.get("password"));

        if ( usuarioService.setPassword(legajo, hashedPassword) )
            message = "Password actualizada correctamente";
        else
            message = "El password no se puedo actualizar";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private String getJWTToken(String username) {

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("SITcursosJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

        return "Bearer " + token;
    }


}


class ResponseLogin {

    String token;
    String message;
    Usuario usuario;



    public ResponseLogin(String token, String message, Usuario usuario) {
        this.token = token;
        this.message = message;
        this.usuario = usuario;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

class RequestLogin {

    String username;
    Integer legajo;
    Long cuit;

    public RequestLogin (String username, Integer legajo, Long cuit) {
        this.username = username;
        this.legajo = legajo;
        this.cuit = cuit;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getLegajo() {
        return legajo;
    }
    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }
    public Long getCuit() {
       return cuit;
    }
    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }
}
