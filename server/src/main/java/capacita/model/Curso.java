package capacita.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private String titulo;
    private String tipo;
    private String categoria;
    private String descripcion;
    private String contenidos;
    private String instructores;
    private String perfilAlumno;
    private Integer cantHoras;
    private Integer maxAlumnos;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_legajo", referencedColumnName = "legajo")
    @JsonIgnore
    private Usuario usuario;

    boolean publicado;

    @JsonProperty("usuario")
    public String getUsuarioUsername() {
        return usuario.getUsername();
    }
}
