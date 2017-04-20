package tests.domini.Encuesta;

import com.domini.PregRespuestaLibre;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests de Encuesta
 */
public class EncuestaTest {
    @Test
    public void getTitulo () {
        Encuesta e = new Encuesta("título");
        assertEquals("título", e.getTitulo());
    }

    @Test
    public void setTitulo () {
        Encuesta e = new Encuesta("título");
        e.setTitulo("Nuevo título");
        assertEquals("Nuevo título", e.getTitulo());
    }

    @Test
    public void añadirPregunta () {
        Encuesta e = new Encuesta("título");
        PregRespuestaLibre p = new PregRespuestaLibre("pregunta");
        e.add_question(p);
        assertEquals("pregunta", e.getPregunta(0).getTitulo());
    }
}
