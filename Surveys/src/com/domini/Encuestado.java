package com.domini;

/**
 * Usuario de tipo Encuestado, que solo pueden ver y responder encuestas
 */
public class Encuestado extends Usuario {

    /**
     * Crear un nuevo usuario
     *
     * @param name el User Name del nuevo usuario, debe ser unico
     */
    public Encuestado(String name) {
        super(name);
    }
}
