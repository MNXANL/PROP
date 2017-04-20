package tests.domini.PregCualitativaNoOrdenadaMultiple;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests de PregCualitativaNoOrdenadaMultiple
 */
public class PregCualitativaNoOrdenadaMultipleTest {
    @Test
    public void tipo () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaMultiple p = new PregCualitativaNoOrdenadaMultiple("título", opciones);
        assertEquals("PCNOM", p.tipo());
    }

    @Test
    public void getContenido () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaMultiple p = new PregCualitativaNoOrdenadaMultiple("título", opciones);
        String salida = "4\n0) opción 1\n1) opción 2\n2) opción 3\n3) opción 4\n";
        assertEquals(salida,p.getContenido());
    }

    @Test
    public void getMaxOptions () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaMultiple p = new PregCualitativaNoOrdenadaMultiple("título", opciones);
        assertEquals(4, p.getMaxOptions());
    }

    @Test
    public void getPreguntaIesima () {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("opción 1");
        opciones.add("opción 2");
        opciones.add("opción 3");
        opciones.add("opción 4");
        PregCualitativaNoOrdenadaMultiple p = new PregCualitativaNoOrdenadaMultiple("título", opciones);
        assertEquals("opción 2", p.getPreguntaIesima(1));
    }
}
