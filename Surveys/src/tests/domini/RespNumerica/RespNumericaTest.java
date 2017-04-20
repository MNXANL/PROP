package tests.domini.RespNumerica;

import org.junit.Test;
import tests.domini.RespLibre.RespLibre;

import static org.junit.Assert.assertEquals;

/**
 * Unit test de la clase RespLibre
 */
public class RespNumericaTest {
    @Test
    public void getRespNum () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        double t = r.get();
        assertEquals(0, t, 0.1);
    }

    @Test
    public void getRespNumMin () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        double t = r.getMin();
        assertEquals(-1000, t, 0.1);
    }

    @Test
    public void getRespNumMax () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        double t = r.getMax();
        assertEquals(1000, r.getMax(), 0.1);
    }

    @Test
    public void setNumero () {
        RespNumerica r = new RespNumerica(0, -1000, 1000);
        r.set(555);
        assertEquals(555, r.get(), 0.1);
    }
}