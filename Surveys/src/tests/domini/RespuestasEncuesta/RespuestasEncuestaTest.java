package tests.domini.RespuestasEncuesta;

import org.junit.Test;
import tests.domini.RespNumerica.RespNumerica;

import static org.junit.Assert.assertEquals;

/**
 * Unit test de la clase RespLibre
 */
public class RespuestasEncuestaTest {
    @Test
    public void getRespEnc () {
        RespuestasEncuesta re = new RespuestasEncuesta();
        double t = r.get();
        assertEquals(0, t, 0.1);
    }

    @Test
    public void getRespEnc2 () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        double t = r.getMin();
        assertEquals(-1000, t, 0.1);
    }

    @Test
    public void getRespEnc3 () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        double t = r.getMax();
        assertEquals(1000, r.getMax(), 0.1);
    }

    @Test
    public void setRespEnc () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        r.set(555);
        assertEquals(555, r.get(), 0.1);
    }
}