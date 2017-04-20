package tests.domini.RespVacia;

import org.junit.Test;
import tests.domini.RespVacia.RespVacia;

import static org.junit.Assert.assertEquals;

/**
 * Unit test de la clase RespVacia
 */
public class RespVaciaTest {
    @Test
    public void equals () {
        RespVacia r = new RespVacia();
        assertEquals(true, r.equals(r));
    }

    @Test
    public void distance () {
        RespVacia r = new RespVacia();
        assertEquals(0, r.distance(r));
    }

    @Test
    public void getHashCode () {
        RespVacia r = new RespVacia();
        assertEquals(42, r.hashCode());
    }
}
