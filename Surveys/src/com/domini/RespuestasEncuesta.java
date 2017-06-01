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
    private String Encuesta_respondida;
    private Date fecha;
    private String User;

    /**
     * Creadora
     * @param e (El titulo de la) La encuesta a la que corresponden las preguntas
     * @param user El usuario que ha respondido
     */
    public RespuestasEncuesta(String e, String user) {
        Encuesta_respondida = e;
        User = user;
        resps = new ArrayList<>();
        fecha = new Date();
    }

    /**
     * Creadora
     * @param e (El titulo de la) La encuesta a la que corresponden las preguntas
     * @param user El usuario que ha respondido
     * @param resps Las respuestas de la encuesta en sí
     */
    public RespuestasEncuesta(Encuesta e, String user, ArrayList<Respuesta> resps) {
        Encuesta_respondida = e.getTitulo();
        User = user;
        this.resps = (ArrayList<Respuesta>) resps.clone();
        this.fecha = new Date();
    }

    /**
     * Metodo para obtener las respuestas de la encuesta
     * @return Lista de respuestas de la encuesta
     */
    public ArrayList<Respuesta> getResps() {
        return resps;
    }

    /**
     * Obtener el nombre de la encuesta respondida
     * @return El nombre de la encuesta respondida
     */
    public String getNombreEncuesta_respondida() {
        return Encuesta_respondida;
    }

    /**
     * Obtener la fecha en la que se respondió la encuesta
     * @return La fecha de respuesta
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Obtener el usuario que respondió la encuesta
     * @return La usuario de la respuesta
     */
    public String getUser() {
        return User;
    }

    /**
     * Metodo para insertar una respuesta a la encuesta
     * @param r Respuesta a insertar
     */
    public void addRespuesta(Respuesta r){
        resps.add(r);
    }

    /**
     * Escribe las respuestas por consila
     */
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

    /**
     * Obtener el nombre del fichero
     * @return Formato del nombre del fichero (sin extension)
     */
    public String getNombreFichero() {
        return (Encuesta_respondida + "_" + User);
    }

    /**
     * importar una encuesta externa al programa
     * @param path El lugar donde encontrar la encuesta
     */
    public static RespuestasEncuesta importar (String path) throws ExcFormatoIncorrecto{
        RespuestasEncuesta re = null;
        // This will reference one line at a time
        String line = null;
        int contLinea = 0;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int indexPreg = 0;
            String TitleEnc = "";
            boolean hasTitulo = false;
            boolean hasUsuario = false;
            boolean hasFinished = false;
            while((line = bufferedReader.readLine()) != null) {
                ++contLinea;
                if (line.equals("Titulo")) {
                    line = bufferedReader.readLine(); ++contLinea;
                    if (line == null) {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                        throw exc;
                    }
                    else if (line.equals("")){
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de encuesta vacío");
                        throw exc;
                    }
                    else {
                        TitleEnc = line;
                    }
                    hasTitulo = true;
                }
                else if (line.equals("Usuario") && hasTitulo) {
                    String username = bufferedReader.readLine(); ++contLinea;
                    if (username == null) {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                        throw exc;
                    }
                    else if (username.equals("")) {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Usuario vacío");
                        throw exc;
                    }
                    re = new RespuestasEncuesta(TitleEnc, username);
                    hasUsuario = true;
                }
                else if (line.equals("Fecha") && hasUsuario) {
                    String f = bufferedReader.readLine(); ++contLinea;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    re.fecha = sdf.parse(f);
                }
                else if (line.equals("Respuesta pregunta") && hasUsuario) {
                    //leer tipo de reespuesta
                    line = bufferedReader.readLine(); ++contLinea;
                    if (line.equals("RCO")) {
                        //leer indice escogido y Num opciones
                        int idx = Integer.parseInt(bufferedReader.readLine()); ++contLinea;
                        if (idx < 0) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Indice negativo.");
                            throw exc;
                        }
                        int nOpts = Integer.parseInt(bufferedReader.readLine()); ++contLinea;
                        if (nOpts < idx) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Rangos de indice y opciones incorrectos.");
                            throw exc;
                        }
                        //leer texto opcion de respuesta
                        String text = bufferedReader.readLine(); ++contLinea;

                        RespCualitativaOrdenada resp = null;
                        resp = new RespCualitativaOrdenada(idx, nOpts, text);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RN")) {
                        //leer valor, minimo y maximo de la respuesta
                        double val = Double.parseDouble(bufferedReader.readLine()); ++contLinea;
                        double min = Double.parseDouble(bufferedReader.readLine()); ++contLinea;
                        double max = Double.parseDouble(bufferedReader.readLine()); ++contLinea;
                        if (min > val || min > max || val > max) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Rangos numéricos incorrectos");
                            throw exc;
                        }
                        RespNumerica resp = null;
                        resp = new RespNumerica(val, min, max);

                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RL")) {
                        String answer = bufferedReader.readLine(); ++contLinea;
                        if (answer == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        RespLibre resp = new RespLibre(answer);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RCNOU")) {
                        //leer indice escogido
                        int idx = Integer.parseInt(bufferedReader.readLine()); ++contLinea;
                        //leer texto opcion de respuesta
                        String text = bufferedReader.readLine(); ++contLinea;

                        RespCualitativaNoOrdenadaUnica resp = null;
                        resp = new RespCualitativaNoOrdenadaUnica(idx, text);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RCNOM")) {
                        //leer max opciones
                        int max = Integer.parseInt(bufferedReader.readLine()); ++contLinea;

                        //leer todas las respuestas elegidas
                        HashMap<Integer, String> res = new HashMap<>();
                        for (int i = 0; i != max; ++i) {
                            int opt = Integer.parseInt(bufferedReader.readLine()); ++contLinea;
                            if (0 > opt) {
                                ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Formato de fecha incorrecto. Formato a seguir: dd/MM/aa hh:mm:ss");
                                throw exc;
                            };
                            String text = bufferedReader.readLine(); ++contLinea;
                            res.put(opt, text);
                        }

                        RespCualitativaNoOrdenadaMultiple resp = new RespCualitativaNoOrdenadaMultiple(res);
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else if (line.equals("RV")) {
                        RespVacia resp = new RespVacia();
                        re.resps.add(indexPreg, resp);
                        indexPreg++;
                    }
                    else {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en línea "+Integer.toString(contLinea)+". Tipo de respuesta incorrecto.");
                        throw exc;
                    }
                }
                else if (line.equals("Final respuestas encuesta") && hasUsuario) {
                    hasFinished = true;
                }
                else if (line.equals("")) {}
                else {
                    ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en línea "+Integer.toString(contLinea)+". Palabra clave incorrecta.");
                    throw exc;
                }
                //System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
            if (!hasFinished)  {
                ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                throw exc;
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");
        }
        catch (ParseException e1) {
            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Formato de fecha incorrecto. Formato a seguir: dd/MM/aa hh:mm:ss");
            throw exc;
        }
        catch (NumberFormatException nfe) {
            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Número mal definido.");
            throw exc;
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
            bufferedWriter.write("Titulo\n");
            bufferedWriter.write(Encuesta_respondida + "\n");

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

    /**
     * Comparador entre clase y un objeto
     * @param o Objeto a comparar
     * @return Si el objeto es igual a la clase
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespuestasEncuesta))
            return false;
        RespuestasEncuesta r = (RespuestasEncuesta) o;

        return r.resps.equals(resps);
    }

    /**
     * Consultora del codigo de hash
     * @return El codigo de hash de la lcase
     */
    @Override
    public int hashCode(){
        return Objects.hash(Encuesta_respondida, User);
    }


}
