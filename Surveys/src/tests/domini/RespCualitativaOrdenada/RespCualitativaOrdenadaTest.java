package tests.domini.RespCualitativaOrdenada;

import org.junit.Test;
import tests.domini.PregRespuestaLibre.PregRespuestaLibre;
import tests.domini.RespCualitativaOrdenada.RespCualitativaOrdenada;

import static org.junit.Assert.assertEquals;

/**
 * Tests unitarios PregRespuestaLibre
 */
public class RespCualitativaOrdenadaTest {
    @Test
    public void getRespVacia () {
        RespCualitativaOrdenada r = new RespCualitativaOrdenada(10, 20, "");
        String t = r.getText();
        assertEquals("", t);
    }

    @Test
    public void getResp() {
        RespCualitativaOrdenada r = new RespCualitativaOrdenada(10, 20, "Normal");
        String t = r.getText();
        assertEquals("Normal", t);
    }
    @Test
    public void getRespNumero() {
        RespCualitativaOrdenada r = new RespCualitativaOrdenada(10, 20, "Normal");
        assertEquals(10, r.get());
    }

    @Test
    public void set () {
        RespCualitativaOrdenada r = new RespCualitativaOrdenada(10, 20, "Normal");
        r.set(12);
        assertEquals(12, r.get());
    }

}
