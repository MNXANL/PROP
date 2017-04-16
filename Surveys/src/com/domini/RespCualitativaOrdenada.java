package com.domini;

/**
 * una selección de entre k opciones ordenadas
 */
public class RespCualitativaOrdenada extends Respuesta {

    private int seleccion;
    private String textoSelec;
    private int noptions;

    /**
     *
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
     *
     * @return
     */
    public int get(){
        return seleccion;
    }
    /**
     *
     * @return
     */
    public String getText(){
        return textoSelec;
    }

    public int getNoptions(){
        return noptions;
    }

    /**
     *
     * @param n
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
        if(noptions == 1) return 1;
        else{
            return Math.abs(re.seleccion-seleccion)/(noptions-1);
        }
    }

}
