package tests.domini.Pregunta;

/**
 * Clase con pregunta
 */
public class Pregunta {
    private String titulo;

    /**
     * Creadora por defecto
     */
    public Pregunta() {
        titulo = "";
    }

    /**
     * Creadora de Pregunta con título
     * @param titulo
     */
    public Pregunta(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtener el título de la pregunta
     * @return
     */
    public String getTitulo() {
        return titulo;

    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtener toda la información de la pregunta a excepción del título
     * @return
     */
    public String getContenido () {
        return "";
    }


    public void leer ()     {}

    /**
     * Obtener el tipo de la pregunta
     * @return
     */
    public String tipo () {
        return null;
    }

    public void respuesta() {}
}
