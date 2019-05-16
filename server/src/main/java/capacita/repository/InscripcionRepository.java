package capacita.repository;

import capacita.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    public List<Inscripcion> findByLegajo(Integer legajo);

    public List<Inscripcion> findByIdcurso(Integer idCurso);

    @Query(value = "SELECT distinct legajo FROM INSCRIPCION", nativeQuery = true)
    List<Integer> getDistinctLegajosInscriptos();

    @Query(value = "SELECT distinct idcurso FROM INSCRIPCION", nativeQuery = true)
    List<Integer> getDistinctCursos();

    @Query(value = "SELECT insc.idcurso, cur.titulo, cur.tipo, cur.categoria, cur.instructores FROM INSCRIPCION insc" +
                   "    INNER JOIN CURSO cur" +
                   "    ON cur.id = insc.idcurso and insc.legajo=:legajoFilter", nativeQuery = true)
    List<Map<String, Object>> getDistinctCursosPorLegajo(@Param("legajoFilter") Integer legajo);


    @Query(value = "SELECT insc.idcurso, usu.legajo, usu.nombre, usu.username FROM INSCRIPCION insc" +
                   "    INNER JOIN USUARIO usu" +
                   "    ON usu.legajo = insc.legajo and insc.idcurso=:curso", nativeQuery = true)
    List<Map<String, Object>> getDistinctLegajosPorCurso(@Param("curso") Integer curso);

    /*
    SELECT distinct insc.idcurso, cur.titulo, cur.tipo, cur.categoria, cur.instructores FROM INSCRIPCION insc
    LEFT OUTER JOIN CURSO cur
    ON cur.id = insc.idcurso and insc.legajo=3710681
    */
}

