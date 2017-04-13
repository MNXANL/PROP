package com.domini;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * clase encuesta
 */
public class Encuesta {
    private String title;
    private ArrayList<Pregunta> preguntas;
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
     * creador de copia
     * @param E encuesta a copiar
     */
    public Encuesta (Encuesta E){
        preguntas = (ArrayList) E.preguntas.clone();
        title = new String(E.title);
        X = (ArrayList) E.X.clone();
    }

    public void setTitulo (String titulo) {
        title = titulo;
    }

    public String getTitulo () {
        return title;
    }

    public Pregunta getPregunta (int index) {
        return preguntas.get(index);
    }

    /**
     * anyadir una pregunta a la encuesta
     * @param p Pregunta a anyadir
     */
    public void add_question(Pregunta p){
        preguntas.add(p);
    }

    public void add_question(int index, Pregunta p) {
        preguntas.set(index,p);
    }

    /**
     * borra la pregunta con indice i en la encuesta (de 0 a size-1)
     * @param index Indice de la pregunta a borrar
     */
    public void delete_question(int index){
        preguntas.remove(index);
    }

    /**
     * importar una encuesta externa al programa
     * @param path El lugar donde encontrar la encuesta
     */
    public void importar (String path){

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

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
                    else if (line.equals("PCNOU")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        //leer todas las opciones de respuesta
                        ArrayList<String> opciones = new ArrayList<>();
                        int index = 0;
                        while (!(line = bufferedReader.readLine()).equals("")){
                            opciones.add(index,line);
                            index++;
                        }
                        PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP,opciones);
                        preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PCNOM")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        //leer max opciones
                        int max = Integer.parseInt(bufferedReader.readLine());
                        //leer todas las opciones de respuesta
                        ArrayList<String> opciones = new ArrayList<>();
                        int index = 0;
                        while (!(line = bufferedReader.readLine()).equals("")){
                            opciones.add(index,line);
                            index++;
                        }
                        PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP,opciones,max);
                        preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PN")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        float min = Float.parseFloat(bufferedReader.readLine());
                        float max = Float.parseFloat(bufferedReader.readLine());
                        //comprobar que min < max
                        PregNumerica preg = new PregNumerica(tituloP,min,max);
                        preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PRL")) {
                        String tituloP = bufferedReader.readLine();
                        PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
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
        try {
            FileWriter fileWriter = new FileWriter(path);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Escribimos el título
            bufferedWriter.write("Título\n");
            bufferedWriter.write(this.title+"\n");

            bufferedWriter.newLine();

            //Escribimos toda la info de cada pregunta
            for (int i = 0; i < preguntas.size(); i++) {
                bufferedWriter.write("Pregunta\n");
                String tipo = preguntas.get(i).tipo();
                bufferedWriter.write(tipo+"\n");
                bufferedWriter.write(preguntas.get(i).getTitulo()+"\n");
                bufferedWriter.write(preguntas.get(i).getContenido());
                bufferedWriter.newLine();
            }
            //Marcamos el final de la encuesta
            bufferedWriter.write("Final encuesta");

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + path + "'");
        }
    }

    /**
     * responder una encuesta es añadir una RespuestasEncuesta de un usuario al array X
     * @param re Conjunto de respuestas de un usuario
     */
    public void responder(RespuestasEncuesta re){
        X.add(re);
    }

    public void leer () {
        System.out.println(title + "\n");
        for (int i = 0; i < preguntas.size(); i++) {
            preguntas.get(i).leer();
            System.out.println("");
        }
    }
    public ArrayList<RespuestasEncuesta> getX (){
        return new ArrayList<RespuestasEncuesta>(X);
    }

    public ArrayList<Respuesta> ResponderEncuesta() {
        ArrayList<Respuesta> ALR = new ArrayList<>();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println(preguntas.get(i).getTitulo());
            System.out.println(preguntas.get(i).getContenido());

            Scanner sc = new Scanner(System.in);
            String resp = sc.next();

            Respuesta r = new RespLibre(resp);
            ALR.add(r);
        }
        return ALR;
    }

    public String getTitle() {
        return title;
    }
}
