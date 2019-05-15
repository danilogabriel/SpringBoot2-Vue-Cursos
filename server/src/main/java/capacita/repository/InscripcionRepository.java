package capacita.repository;

import capacita.model.Curso;
import capacita.model.Inscripcion;
import capacita.repository.dto.CursosPorLegajoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    public List<Inscripcion> findByLegajo(Integer legajo);

    public List<Inscripcion> findByIdcurso(Integer idCurso);

    @Query(value = "SELECT distinct legajo FROM INSCRIPCION", nativeQuery = true)
    List<Integer> getDistinctLegajosInscriptos();

    @Query(value = "SELECT NEW dto.CursosPorLegajoResponse(insc.idcurso, cur.titulo, cur.tipo, cur.categoria, cur.instructores) FROM INSCRIPCION insc\n" +
                   "    LEFT OUTER JOIN CURSO cur\n" +
                   "    ON cur.id = insc.idcurso and insc.legajo=:legajoFilter", nativeQuery = true)
    List<CursosPorLegajoResponse> getDistinctCursosPorLegajo(@Param("legajoFilter") Integer legajo);

    /*
    SELECT distinct insc.idcurso, cur.titulo, cur.tipo, cur.categoria, cur.instructores FROM INSCRIPCION insc
    LEFT OUTER JOIN CURSO cur
    ON cur.id = insc.idcurso and insc.legajo=3710681
    */
}

