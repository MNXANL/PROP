package tests.domini.Usuario;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests Usuario
 */
public class UsuarioTest {
    @Test
    public void nombre() {
        Usuario u = new Usuario("nombre");
        assertEquals("nombre",u.getUsername());
    }
}
