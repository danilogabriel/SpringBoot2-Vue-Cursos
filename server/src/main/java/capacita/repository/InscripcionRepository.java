package capacita.repository;

import capacita.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    public List<Inscripcion> findByLegajo(Integer legajo);

    public List<Inscripcion> findByIdCurso(Integer idCurso);

}
