package tests.domini.Encuestado;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests Encuestado
 */
public class EncuestadoTest {
    @Test
    public void nombre () {
        Encuestado e = new Encuestado("nombre");
        assertEquals("nombre", e.getUsername());
    }
}
