package tests.domini.RespLibre;

import org.junit.Test;
import tests.domini.RespLibre.RespLibre;

import static org.junit.Assert.assertEquals;

/**
 * Unit test de la clase RespLibre
 */
public class RespLibreTest {
    @Test
    public void getTituloVacio () {
        RespLibre r = new RespLibre("");
        String t = r.get();
        assertEquals("", t);
    }

    @Test
    public void getTitulo () {
        RespLibre r = new RespLibre("Título");
        String t = r.get();
        assertEquals("Título", t);
    }

    @Test
    public void setTitulo () {
        RespLibre r = new RespLibre("Título");
        r.set("Nuevo título");
        assertEquals("Nuevo título", r.get());
    }

    @Test
    public void equalsTitulo () {
        RespLibre r = new RespLibre("Titulo");
        assertEquals(true, r.equals(r));
    }
}
