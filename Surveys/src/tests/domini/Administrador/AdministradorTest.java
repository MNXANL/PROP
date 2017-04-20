package tests.domini.Administrador;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests Administrador
 */
public class AdministradorTest {
    @Test
    public void nombre () {
        Administrador a = new Administrador("nombre");
        assertEquals("nombre", a.getUsername());
    }
}
