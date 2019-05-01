package capacita.repository;

import capacita.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    public List<Usuario> findByNombre(String nombre);

    public Usuario findByUsername(String username);

    public Usuario findByLegajo(Integer legajo);

}


