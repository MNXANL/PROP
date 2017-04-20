package tests.domini.PregCualitativaNoOrdenadaUnica;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests de PregCualitativaNoOrdenadaUnica
 */
public class PregCualitativaNoOrdenadaUnicaTest {
    @Test
    public void tipo () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaUnica p = new PregCualitativaNoOrdenadaUnica("título", opciones);
        assertEquals("PCNOU", p.tipo());
    }

    @Test
    public void getContenido () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaUnica p = new PregCualitativaNoOrdenadaUnica("título", opciones);
        String salida = "0) opción 1\n1) opción 2\n2) opción 3\n3) opción 4\n";
        assertEquals(salida,p.getContenido());
    }

    @Test
    public void getPreguntaIesima () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaUnica p = new PregCualitativaNoOrdenadaUnica("título", opciones);
        assertEquals("opción 2", p.getPreguntaIesima(1));
    }
}
