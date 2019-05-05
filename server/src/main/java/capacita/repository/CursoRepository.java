package capacita.repository;

import capacita.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    List<Curso> findByPublicadoTrue();

    List<Curso> findByUsuarioLegajo(Integer legajo);

    @Query(value = "SELECT * FROM Curso cur WHERE cur.publicado = true OR cur.usuario_legajo = :legajo", nativeQuery = true)
    List<Curso> getPublicadoYPropios(@Param("legajo") Integer legajo);
}


