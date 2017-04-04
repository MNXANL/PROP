package com.domini;

/**
 * Clase que almacena las preguntas en formato numérico
 */
public class PregNumerica {
    private float valorMin;
    private float valorMax;
    //public boolean floatingPoint;     Puede ser que lo necesitemos en un futuro...

    /**
     * Constructora de la clase con valores minimo y maximo en los extremos.
     */
    public PregNumerica() { //Admito clase vacía que coga el máximo intervalo posible.
        valorMin = Float.MIN_VALUE;
        valorMax = Float.MAX_VALUE;
    }

    /**
     * Constructora de la clase con valorMin y valorMax arbitrarios
     * @param valorMin valor minimo
     * @param valorMax valor maximo
     */
    public PregNumerica(float valorMin, float valorMax) {
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    /**
     * Devuelve el valorMin de la clase
     * @return valorMin de la clase
     */
    public float getValorMin() {
        return valorMin;
    }

    /**
     * Devuelve el valorMax de la clase
     * @return valorMax de la clase
     */
    public float getValorMax() {
        return valorMax;
    }

    /**
     * Actualiza el valorMin de la clase
     * @param valorMin valor minimo a actualizar
     */
    public void updateValorMin(float valorMin) {
        this.valorMin = valorMin;
    }
    /**
     * Actualiza el valorMax de la clase
     * @param valorMax valor maximo a actualizar
     */
    public void updateValorMax(float valorMax) {
        this.valorMax = valorMax;
    }
}
