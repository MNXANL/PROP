package com.domini;

/**
 * Created by aleixballetbo on 14/5/17.
 */
public class ExcUsuarioExistente extends Exception{
    public ExcUsuarioExistente() {
        super();
    }

    public ExcUsuarioExistente(String s) {
        super(s);
    }
}
