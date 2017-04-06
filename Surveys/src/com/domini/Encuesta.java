package com.domini;

import java.util.ArrayList;
import java.io.*;

/**
 * clase encuesta
 */
public class Encuesta {
    private ArrayList<Pregunta> preguntas;
    private String title;
    private ArrayList<RespuestasEncuesta> X; //cada elemento de X es un conjunto de respuestas de un usuario a E

    public Encuesta(){
        preguntas = new ArrayList<>();
    };
    /**
     * @param title el titulo que identifica la encuesta
     */
    public Encuesta(String title){
        this.title = title;
        X = new ArrayList<>();
        preguntas = new ArrayList<>();
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

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            int indexPreg = 0;
            while((line = bufferedReader.readLine()) != null) {
                if (line.equals("Título")) {
                    line = bufferedReader.readLine();
                    if (line != null) title = line;
                }
                else if (line.equals("Pregunta")) {
                    //leer tipo de pregunta
                    line = bufferedReader.readLine();
                    if (line.equals("PCO")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        //leer todas las opciones de respuesta
                        ArrayList<String> opciones = new ArrayList<>();
                        int index = 0;
                        while (!(line = bufferedReader.readLine()).equals("")){
                            opciones.add(index,line);
                            index++;
                        }
                        PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP,opciones);
                        preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                }
                else if (line.equals("Final encuesta")) {

                }

                //System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            path + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + path + "'");
        }
    }

    /**
     * exportar una encuesta fuera del programa
     * @param path Donde exportar la encuesta
     */
    public void exportar (String path){

    }

    /**
     * responder una encuesta es añadir una RespuestasEncuesta de un usuario al array X
     * @param re Conjunto de respuestas de un usuario
     */
    public void responder(RespuestasEncuesta re){
        X.add(re);
    }

    public void leer () {
        System.out.println(title);
        for (int i = 0; i < preguntas.size(); i++) {
            preguntas.get(i).leer();
        }
    }

}
