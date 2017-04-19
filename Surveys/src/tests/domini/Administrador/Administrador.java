package tests.domini.Administrador;

import com.domini.Usuario;

/**
 *Usuario de tipo Administrador
 */
public class Administrador extends Usuario {

    /**
     * Crear un nuevo usuario
     *
     * @param name el User Name del nuevo usuario, debe ser unico
     */
    public Administrador(String name) {
        super(name);
    }
}
