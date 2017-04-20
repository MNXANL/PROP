package tests.domini.RespCualitativaNoOrdenadaUnica;

import org.junit.Test;
import tests.domini.RespCualitativaNoOrdenadaUnica.RespCualitativaNoOrdenadaUnica;

import static org.junit.Assert.assertEquals;

/**
 * Tests unitarios PregRespuestaLibre
 */
public class RespCualitativaNoOrdenadaUnicaTest {
    @Test
    public void getRespVacia () {
        RespCualitativaNoOrdenadaUnica r = new RespCualitativaNoOrdenadaUnica(10, "");
        String t = r.getText();
        assertEquals("", t);
    }

    @Test
    public void getResp() {
        RespCualitativaNoOrdenadaUnica r = new RespCualitativaNoOrdenadaUnica(10,  "Normal");
        String t = r.getText();
        assertEquals("Normal", t);
    }

    @Test
    public void set1 () {
        RespCualitativaNoOrdenadaUnica r = new RespCualitativaNoOrdenadaUnica(10,  "Normal");
        r.set(9, "Modified");
        assertEquals(9, r.get());
    }

    @Test
    public void set2 () {
        RespCualitativaNoOrdenadaUnica r = new RespCualitativaNoOrdenadaUnica(10,  "Normal");
        r.set(9, "Modified");
        assertEquals("Modified", r.getText());
    }

}
