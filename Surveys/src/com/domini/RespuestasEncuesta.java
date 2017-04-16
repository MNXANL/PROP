package com.domini;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Esta clase es el conjunto de respuestas a una encuesta en concreto
 */
public class RespuestasEncuesta {
    ArrayList<Respuesta> resps;
    Encuesta Encuesta_respondida;
    Date fecha;
    String User;

    /**
     * Creadora
     * @param e (El titulo de la) La encuesta a la que corresponden las preguntas
     * @param user El usuario que ha respondido
     */
    public RespuestasEncuesta(Encuesta e, String user) {
        Encuesta_respondida = e;
        User = user;
        resps = new ArrayList<>();
        fecha = new Date();
    }
    public void addRespuesta(Respuesta r){
        resps.add(r);
    }

    /**
     * Creadora
     * @param e (El titulo de la) La encuesta a la que corresponden las preguntas
     * @param user El usuario que ha respondido
     * @param resps Las respuestas de la encuesta en sí
     */
    public RespuestasEncuesta(Encuesta e, String user, ArrayList<Respuesta> resps) {
        Encuesta_respondida = e;
        User = user;
        this.resps = (ArrayList<Respuesta>) resps.clone();
        this.fecha = new Date();
    }


    public void printarRespuestas() {
        System.out.println("Respuestas del usuario " + User);
        System.out.println("----------------------");
        for (int i = 0; i < resps.size(); i++) {
            System.out.print("Respuesta pregunta " + i + ": ");

            if (resps.get(i) instanceof RespLibre) {
                RespLibre rl = (RespLibre) resps.get(i);
                System.out.println( rl.get() );
            }
            else if (resps.get(i) instanceof RespNumerica) {
                RespNumerica rn = (RespNumerica) resps.get(i);
                System.out.println( rn.get() );
            }
            else if (resps.get(i) instanceof RespCualitativaOrdenada) {
                RespCualitativaOrdenada rc = (RespCualitativaOrdenada) resps.get(i);
                System.out.println( rc.getText() + " (" + rc.get() + ")");
            }
            else if (resps.get(i) instanceof RespCualitativaNoOrdenadaUnica) {
                RespCualitativaNoOrdenadaUnica rc = (RespCualitativaNoOrdenadaUnica) resps.get(i);
                System.out.println( rc.getTxt() );
            }

            // !acabame!
            else if (resps.get(i) instanceof RespCualitativaNoOrdenadaMultiple) {
                RespCualitativaNoOrdenadaMultiple rc = (RespCualitativaNoOrdenadaMultiple) resps.get(i);
                Collection<String> colResps = rc.getMap().values();

                if (colResps.size() == 1) System.out.println("1 opción elegida");
                else                 System.out.println(colResps.size() + " opciones elegidas");
                int j = 0;
                for (String col: colResps) {
                    System.out.println(" - Opción " + j + ": " + col);
                    ++j;
                }
            }
        }
        System.out.println();
    }

    public String getNombreFichero() {
        return (Encuesta_respondida.getTitulo() + "_" + User);
    }

    /**
     * importar una encuesta externa al programa
     * @param path El lugar donde encontrar la encuesta
     */
    public static RespuestasEncuesta importar (String path){

        /*RespuestasEncuesta re = new RespuestasEncuesta(Encuesta_respondida, User);

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
                    if (line != null) e.title = line;
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
                        e.preguntas.add(indexPreg,preg);
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
                        e.preguntas.add(indexPreg,preg);
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
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PN")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        float min = Float.parseFloat(bufferedReader.readLine());
                        float max = Float.parseFloat(bufferedReader.readLine());
                        //comprobar que min < max
                        PregNumerica preg = new PregNumerica(tituloP,min,max);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PRL")) {
                        String tituloP = bufferedReader.readLine();
                        PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                }
                else if (line.equals("Fecha")) {
                    String f = bufferedReader.readLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    e.fecha = sdf.parse(f);
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
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        return re;*/
        return null;
    }

    /**
     * exportar las respuestas de la encuesta fuera del programa
     * @param path Donde exportar las respuestas de la encuesta
     */
    public void exportar (String path){
        try {
            FileWriter fileWriter = new FileWriter(path,false);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Escribimos el título de la encuesta respondida
            bufferedWriter.write("Título\n");
            bufferedWriter.write(Encuesta_respondida.getTitulo() + "\n");

            bufferedWriter.newLine();

            //Escribimos el usuario
            bufferedWriter.write("Usuario\n");
            bufferedWriter.write(User+"\n");

            bufferedWriter.newLine();

            //Escibimos la fecha
            bufferedWriter.write("Fecha\n");
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String output = format.format(this.fecha);
            bufferedWriter.write(output+"\n");

            bufferedWriter.newLine();

            //Escribimos toda la info de cada pregunta
            for (int i = 0; i < resps.size(); i++) {
                bufferedWriter.write("Respuesta pregunta\n");
                if (resps.get(i) instanceof RespLibre) {
                    bufferedWriter.write("RL\n"); //Tipo de respuesta
                    RespLibre r = (RespLibre) resps.get(i);
                    bufferedWriter.write(r.get() + "\n");

                }
                else if (resps.get(i) instanceof RespNumerica) {
                    bufferedWriter.write("RN\n"); //Tipo de respuesta
                    RespNumerica r = (RespNumerica) resps.get(i);
                    bufferedWriter.write(r.get() + "\n");

                }
                else if (resps.get(i) instanceof RespCualitativaOrdenada) {
                    bufferedWriter.write("RCO\n"); //Tipo de respuesta
                    RespCualitativaOrdenada r = (RespCualitativaOrdenada) resps.get(i);
                    bufferedWriter.write(r.get() + "\n");

                }

                else if (resps.get(i) instanceof RespCualitativaNoOrdenadaUnica) {
                    bufferedWriter.write("RCNOU\n"); //Tipo de respuesta
                    RespCualitativaNoOrdenadaUnica r = (RespCualitativaNoOrdenadaUnica) resps.get(i);
                    bufferedWriter.write(r.get() + "\n");

                }
                else /*if (resps.get(i) instanceof RespCualitativaNoOrdenadaMultiple)*/ {
                    bufferedWriter.write("RCNOM\n"); //Tipo de respuesta
                    RespCualitativaNoOrdenadaMultiple r = (RespCualitativaNoOrdenadaMultiple) resps.get(i);

                    Set<Integer> set = r.get();
                    bufferedWriter.write(set.size() + "\n");
                    for (Integer result: set) {
                        bufferedWriter.write(result + "\n");
                    }

                }
                bufferedWriter.newLine();
            }
            //Marcamos el final
            bufferedWriter.write("Final respuestas encuesta");

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + path + "'");
        }
    }
}
