package com.domini;

/**
 * Created by aleixballetbo on 25/4/17.
 */
public class ExcFormatoIncorrecto extends Exception {
    public ExcFormatoIncorrecto() {
        super();
    }

    public ExcFormatoIncorrecto(String s) {
        super(s);
    }
}
