package tests.domini.Pregunta;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test de la clase pregunta
 */
public class PreguntaTest {
    @Test
    public void getTituloVacio () {
        Pregunta p = new Pregunta();
        String t = p.getTitulo();
        assertEquals("", t);
    }

    @Test
    public void getTitulo () {
        Pregunta p = new Pregunta("Título");
        String t = p.getTitulo();
        assertEquals("Título", t);
    }

    @Test
    public void setTituloPreguntaVacia () {
        Pregunta p = new Pregunta();
        p.setTitulo("Nuevo título");
        assertEquals("Nuevo título", p.getTitulo());
    }

    @Test
    public void setTitulo () {
        Pregunta p = new Pregunta("Titulo");
        p.setTitulo("Nuevo título");
        assertEquals("Nuevo título", p.getTitulo());
    }
}
