package com.domini;

import java.util.ArrayList;

/**
 * clase encuesta
 */
public class Encuesta {
    private ArrayList<Pregunta> preguntas;
    private String title;
    private ArrayList<RespuestasEncuesta> X;

    public Encuesta(){};
    /**
     * @param title el titulo que identifica la encuesta
     */
    public Encuesta(String title){
        this.title = title;
    }

    /**
     * anyadir una pregunta a la encuesta
     * @param p
     */
    public void add_question(Pregunta p){
        preguntas.add(p);
    }

    /**
     * borra la pregunta con indice i en la encuesta (de 0 a size-1)
     * @param index
     */
    public void delete_question(int index){
        preguntas.remove(index);
    }

    /**
     * importar una encuesta externa al programa
     * @param path el lugar donde encontrar la encuesta
     */
    public void importar (String path){

    }

    /**
     * exportar una encuesta fuera del programa
     * @param path Donde exportar la encuesta
     */
    public void export(String path){

    }

}
