package capacita.repository;

import capacita.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    public List<Curso> findByPublicadoTrue();
}


