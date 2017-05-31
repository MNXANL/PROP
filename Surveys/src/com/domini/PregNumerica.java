package com.domini;

/**
 * Clase que almacena las preguntas en formato numérico
 */
public class PregNumerica extends Pregunta {
    private double valorMin;
    private double valorMax;
    //public boolean doubleingPoint;     Puede ser que lo necesitemos en un futuro...

    /**
     * Constructora de la clase con valores minimo y maximo en los extremos.
     * @param titulo Titulo de la pregunta
     */
    public PregNumerica(String titulo) { //Admito clase vacía que coga el máximo intervalo posible.
        super(titulo);
        valorMin = Double.MIN_VALUE;
        valorMax = Double.MAX_VALUE;
    }

    /**
     * Constructora de la clase con valorMin y valorMax
     * @param titulo Titulo de la pregunta
     * @param valorMin valor minimo
     * @param valorMax valor maximo
     */
    public PregNumerica(String titulo, double valorMin, double valorMax)  {
        super(titulo);
        //if (valorMin > valorMax) throw new ExcFormatoIncorrecto("Formato incorrecto! Valor min <= Valor max");
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    /**
     * Devuelve el valorMin de la pregunta
     * @return valorMin de la pregunta
     */
    public double getValorMin() {
        return valorMin;
    }

    /**
     * Devuelve el valorMax de la pregunta
     * @return valorMax de la pregunta
     */
    public double getValorMax() {
        return valorMax;
    }

    /**
     * Obtener el tipo de la pregunta
     * @return Tipo de la pregunta
     */
    public String tipo () {
        return "PN";
    }

    /**
     * Obtener toda la información de la pregunta a excepción del título
     * @return Contenido de la pregunta
     */
    public String getContenido () {
        return valorMin+"\n"+valorMax+"\n";
    }

    /**
     * Metodo para escribir por consola el contenido de la pregunta
     */
    public void leer () {
        System.out.println(getTitulo());
        System.out.println(valorMin);
        System.out.println(valorMax);
    }
}
