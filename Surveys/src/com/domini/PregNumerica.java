package com.domini;

/**
 * Clase que almacena las preguntas en formato numérico
 */
public class PregNumerica {
    private float valorMin;
    private float valorMax;
    //public boolean floatingPoint;     Puede ser que lo necesitemos en un futuro...

    public PregNumerica() { //Admito clase vacía que coga el máximo intervalo posible.
        valorMin = Float.MIN_VALUE;
        valorMax = Float.MAX_VALUE;
    }
    public PregNumerica(float valorMin, float valorMax) {
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    public float getValorMin() {
        return valorMin;
    }
    public float getValorMax() {
        return valorMax;
    }


    public float updateValorMin(float valorMin) {
        this.valorMin = valorMin;
    }

    public float updateValorMax(float valorMax) {
        this.valorMax = valorMax;
    }
}
