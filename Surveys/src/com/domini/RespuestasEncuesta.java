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
    private ArrayList<Respuesta> resps;
    private Encuesta Encuesta_respondida;
    private Date fecha;
    private String User;


    public ArrayList<Respuesta> getResps() {
        return resps;
    }

    public Encuesta getEncuesta_respondida() {
        return Encuesta_respondida;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getUser() {
        return User;
    }

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
                System.out.println( rc.getText() );
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
            else { //RespVacia
                System.out.println("[vacío]");
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
        Encuesta e = new Encuesta();
        Usuario u = new Usuario(" ");
        RespuestasEncuesta re = new RespuestasEncuesta(e, u.getUsername());

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
                    if (line != null) e.setTitulo(line);
                }
                else if (line.equals("Respuesta pregunta")) {
                    //leer tipo de reespuesta
                    line = bufferedReader.readLine();
                    if (line.equals("RCO")) {
                        //leer indice escogido y Num opciones
                        int idx = Integer.parseInt(bufferedReader.readLine());
                        int nOpts = Integer.parseInt(bufferedReader.readLine());
                        //leer texto opcion de respuesta
                        String text = bufferedReader.readLine();

                        RespCualitativaOrdenada resp = new RespCualitativaOrdenada(idx, nOpts, text);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RN")) {
                        //leer valor, minimo y maximo de la respuesta
                        float val = Float.parseFloat(bufferedReader.readLine());
                        float min = Float.parseFloat(bufferedReader.readLine());
                        float max = Float.parseFloat(bufferedReader.readLine());
                        //comprobar que min < max
                        RespNumerica resp = new RespNumerica(val, min, max);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RL")) {
                        String answer = bufferedReader.readLine();
                        RespLibre resp = new RespLibre(answer);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RCNOU")) {
                        //leer indice escogido
                        int idx = Integer.parseInt(bufferedReader.readLine());
                        //leer texto opcion de respuesta
                        String text = bufferedReader.readLine();

                        RespCualitativaNoOrdenadaUnica resp = new RespCualitativaNoOrdenadaUnica(idx, text);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RCNOM")) {
                        //leer max opciones
                        int max = Integer.parseInt(bufferedReader.readLine());

                        //leer todas las respuestas elegidas
                        HashMap<Integer, String> res = new HashMap<>();
                        for (int i = 0; i != max; ++i) {
                            int opt = Integer.parseInt(bufferedReader.readLine());
                            String text = bufferedReader.readLine();
                            res.put(opt, text);
                        }

                        RespCualitativaNoOrdenadaMultiple resp = new RespCualitativaNoOrdenadaMultiple(res);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else { //line.equals("RV")
                        RespVacia resp = new RespVacia();
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                }
                else if (line.equals("Fecha")) {
                    String f = bufferedReader.readLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    re.fecha = sdf.parse(f);
                }
                else if (line.equals("Usuario")) {
                    String f = bufferedReader.readLine();
                    re.User = f;
                }
                else if (line.equals("Final respuestas encuesta")) {
                    //End
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

        //DEBUG: re.printarRespuestas();
        return re;
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
                    bufferedWriter.write(r.getMin() + "\n");
                    bufferedWriter.write(r.getMax() + "\n");

                }
                else if (resps.get(i) instanceof RespCualitativaOrdenada) {
                    bufferedWriter.write("RCO\n"); //Tipo de respuesta
                    RespCualitativaOrdenada r = (RespCualitativaOrdenada) resps.get(i);
                    bufferedWriter.write(r.get() + "\n");
                    bufferedWriter.write(r.getNoptions() + "\n");
                    bufferedWriter.write(r.getText() + "\n");

                }

                else if (resps.get(i) instanceof RespCualitativaNoOrdenadaUnica) {
                    bufferedWriter.write("RCNOU\n"); //Tipo de respuesta
                    RespCualitativaNoOrdenadaUnica r = (RespCualitativaNoOrdenadaUnica) resps.get(i);
                    bufferedWriter.write(r.get() + "\n");
                    bufferedWriter.write(r.getText() + "\n");
                }
                else if (resps.get(i) instanceof RespCualitativaNoOrdenadaMultiple) {
                    bufferedWriter.write("RCNOM\n"); //Tipo de respuesta
                    RespCualitativaNoOrdenadaMultiple r = (RespCualitativaNoOrdenadaMultiple) resps.get(i);

                    Map<Integer, String> set = r.getMap();
                    bufferedWriter.write(set.size() + "\n");
                    for (Map.Entry<Integer, String> it : set.entrySet()) {
                        bufferedWriter.write(it.getKey() + "\n");   //Number
                        bufferedWriter.write(it.getValue() + "\n"); //String
                    }
                }
                else { //RespVacia
                    bufferedWriter.write("RV\n");
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
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespuestasEncuesta))
            return false;
        RespuestasEncuesta r = (RespuestasEncuesta) o;

        return r.resps.equals(resps);
    }
    @Override
    public int hashCode(){
        return Objects.hash(Encuesta_respondida.getFecha(),User);
    }

    /**
     *
     */
    public Encuesta getEncuesta() {
        return Encuesta_respondida;
    }
}
