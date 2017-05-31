package com.domini;


/**
 * Clase con pregunta de formato libre
 */
public class PregRespuestaLibre extends Pregunta {
    /**
     * Constructora por defecto
     * @param titulo Titulo de la pregunta
     */
    public PregRespuestaLibre(String titulo) {
        super(titulo);
    }

    /**
     * Obtener el tipo de la pregunta
     * @return Tipo de la pregunta
     */
    public String tipo () {
        return "PRL";
    }

    /**
     * Metodo para escribir por consola el contenido de la pregunta
     */
    public void leer () {
        System.out.println(getTitulo());
    }
}
