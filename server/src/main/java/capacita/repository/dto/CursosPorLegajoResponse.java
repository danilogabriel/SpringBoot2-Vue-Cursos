package capacita.repository.dto;

public class CursosPorLegajoResponse {

    Integer idcurso;
    String titulo;
    String tipo;
    String categoria;
    String instructores;

    public CursosPorLegajoResponse(Integer idcurso, String titulo, String tipo, String categoria, String instructores) {
        this.idcurso = idcurso;
        this.titulo = titulo;
        this.tipo = tipo;
        this.categoria = categoria;
        this.instructores = instructores;
    }
    public Integer getIdcurso() {
        return idcurso;
    }
    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getInstructores() { return instructores;  }
    public void setInstructores(String instructores) { this.instructores = instructores; }
}




