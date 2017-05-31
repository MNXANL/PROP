package com.domini;

import java.util.Objects;

/**
 * una selección de entre k opciones ordenadas
 */
public class RespCualitativaOrdenada extends Respuesta {

    private int seleccion;
    private String textoSelec;
    private int noptions;

    /**
     * Constructora
     * @param ns numero de la seleccion que ha hecho el usuario
     * @param nopts numero de opcciones totales a elegir
     */
    public RespCualitativaOrdenada(int ns, int nopts, String text){
        seleccion = ns;
        textoSelec = text;
        noptions = nopts;
    }

    /**
     * creador de copia
     * @param r copia de RespCualitativaOrdenada
     */
    public RespCualitativaOrdenada(RespCualitativaOrdenada r){
        seleccion = r.seleccion;
        textoSelec = r.textoSelec;
        noptions = r.noptions;
    }
    /**
     * Obtener indice de respuesta
     * @return seleccion de respuesta
     */
    public int get(){
        return seleccion;
    }
    /**
     * Obtener texto de respuesta
     * @return texto correspondiente a la seleccion
     */
    public String getText(){
        return textoSelec;
    }

    /**
     * Metodo para obtener el numero de opciones
     * @return El numero de opciones de la encuesta
     */
    public int getNoptions(){
        return noptions;
    }

    /**
     * Fijar numero de seleccion
     * @param n seleccion
     */
    public void set(int n){
        seleccion = n;
    }

    /**
     * distancia entre dos respuestas cualitativas ordenadas
     * @param x respuesta a comparar
     * @return valor entre 0 y 1 que representa la distancia
     */
    public double distance(Respuesta x){
        RespCualitativaOrdenada re = (RespCualitativaOrdenada) x;
        if(noptions == 1) return 0;
        else{
            return ((double) Math.abs(re.seleccion-seleccion))/(noptions-1);
        }
    }

    /**
     * Metodo para comparar la clase con otro objeto
     * @param o Objeto a comparar
     * @return Si los objetos son los mismos o no
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespCualitativaOrdenada))
            return false;
        RespCualitativaOrdenada r = (RespCualitativaOrdenada) o;

        return r.seleccion==seleccion && r.noptions==noptions;
    }

    /**
     * Metodo para obtener el codigo de hash
     * @return El codigo de hash
     */
    @Override
    public int hashCode(){
        return Objects.hash(seleccion,noptions);
    }

}
