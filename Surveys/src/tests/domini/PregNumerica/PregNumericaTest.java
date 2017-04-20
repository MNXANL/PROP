package tests.domini.PregNumerica;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test de PregNumerica
 */
public class PregNumericaTest {
    @Test
    public void getValorMin () {
        PregNumerica p = new PregNumerica("título",0,10);
        assertEquals(0,p.getValorMin(),0);
    }

    @Test
    public void getValorMax () {
        PregNumerica p = new PregNumerica("título",0,10);
        assertEquals(10,p.getValorMax(),0);
    }

    @Test
    public void setValorMin () {
        PregNumerica p = new PregNumerica("título",0,10);
        p.setValorMin(1);
        assertEquals(1,p.getValorMin(),0);
    }

    @Test
    public void setValorMax () {
        PregNumerica p = new PregNumerica("título",0,10);
        p.setValorMax(100);
        assertEquals(100,p.getValorMax(),0);
    }

    @Test
    public void getContenido () {
        PregNumerica p = new PregNumerica("título",0,10);
        assertEquals("0.0\n10.0\n", p.getContenido());
    }


    @Test
    public void tipo () {
        PregNumerica p = new PregNumerica("título");
        assertEquals("PN", p.tipo());
    }
}
