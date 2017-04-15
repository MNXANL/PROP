package com.domini;

/**
 * Clase que representa un usuario que interacciona con el sistema
 */
public class Usuario {
    String userName;

    /**
     * Crear un nuevo usuario
     * @param name el User Name del nuevo usuario, debe ser unico
     */
    public Usuario(String name){
        userName=name;
    }

    public void leer () {
        System.out.println(userName);
    }
}
