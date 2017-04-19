package tests.domini.PregRespuestaLibre;

import org.junit.Test;

import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tests unitarios PregRespuestaLibre
 */
public class PregRespuestaLibreTest {
    @Test
    public void tipo () {
        PregRespuestaLibre p = new PregRespuestaLibre("Título");
        assertEquals("PRL", p.tipo());
    }
}
