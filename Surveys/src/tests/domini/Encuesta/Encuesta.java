package tests.domini.Encuesta;

import com.domini.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * clase encuesta
 */
public class Encuesta {
    private String title;
    private Date fecha;
    private ArrayList<Pregunta> preguntas;
    private ArrayList<RespuestasEncuesta> CjtRespsEnc; //cada elemento de CjtRespsEnc es un conjunto de respuestas de un usuario a E


    /**
     * Creadora
     */
    public Encuesta(){
        fecha = new Date();
        preguntas = new ArrayList<>();
        CjtRespsEnc = new ArrayList<RespuestasEncuesta>();
    };

    /**
     * Creadora
     * @param title el titulo que identifica la encuesta
     */
    public Encuesta(String title){
        this.title = title;
        this.fecha = new Date();
        preguntas = new ArrayList<Pregunta>();
        CjtRespsEnc = new ArrayList<RespuestasEncuesta>();
    }

    /**
     * creador de copia
     * @param title el titulo que identifica la encuesta
     * @param fecha la fecha de la encuesta
     * @param ps las preguntas de la encuesta
     * @param rs el conjunto de respuestas de la encuesta
     */
    public Encuesta (String title, Date fecha, ArrayList<Pregunta> ps, ArrayList<RespuestasEncuesta> rs){
        this.title = title;
        this.fecha = fecha;
        preguntas = ps;
        CjtRespsEnc = rs;
    }
    /**
     * creador de copia
     * @param E encuesta a copiar
     */
    public Encuesta (Encuesta E){
        preguntas = (ArrayList) E.preguntas.clone();
        title = E.title;
        fecha = (Date) E.fecha.clone();
        CjtRespsEnc = (ArrayList) E.CjtRespsEnc.clone();
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

    public ArrayList<Pregunta> getPreguntas () {
        return preguntas;
    }

    public Date getFecha () {
        return fecha;
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
    public static Encuesta importar (String path){

        Encuesta e = new Encuesta();

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

        return e;
    }

    /**
     * exportar una encuesta fuera del programa
     * @param path Donde exportar la encuesta
     */
    public void exportar (String path){
        try {
            FileWriter fileWriter = new FileWriter(path,false);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Escribimos el título
            bufferedWriter.write("Título\n");
            bufferedWriter.write(this.title+"\n");

            bufferedWriter.newLine();

            //Escibimos la fecha
            bufferedWriter.write("Fecha\n");
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String output = format.format(this.fecha);
            bufferedWriter.write(output+"\n");

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
     * responder una encuesta es añadir una RespuestasEncuesta de un usuario al array CjtRespsEnc
     * @param re Conjunto de respuestas de un usuario
     */
    public void responder(RespuestasEncuesta re){
        CjtRespsEnc.add(re);
    }

    public void leer () {
        System.out.println(title + "\n");
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String output = format.format(this.fecha);
        System.out.println(output+"\n");
        for (int i = 0; i < preguntas.size(); i++) {
            preguntas.get(i).leer();
            System.out.println("");
        }
    }

    public ArrayList<RespuestasEncuesta> getCjtRespsEnc(){
        return new ArrayList<RespuestasEncuesta>(CjtRespsEnc);
    }

    public boolean borrarRespuesta(String user) {
        for (int i = 0; i < getCjtRespsEnc().size(); ++i) {
            if (CjtRespsEnc.get(i).getUser().equals(user)) {
                CjtRespsEnc.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Respuesta> responderEncuesta(String user) {
        boolean found = false;
        for (int i = 0; i < getCjtRespsEnc().size() && !found; ++i) {
            if (CjtRespsEnc.get(i).getUser().equals(user)) { //Ya existe; la vamos a modificar
                found = true;
                CjtRespsEnc.get(i).printarRespuestas();
                CjtRespsEnc.remove(i);
            }
        }

        ArrayList<Respuesta> ALR = new ArrayList<>();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println(preguntas.get(i).getTitulo());
            System.out.println(preguntas.get(i).getContenido());

            Scanner sc = new Scanner(System.in);
            if (preguntas.get(i) instanceof PregCualitativaNoOrdenadaMultiple) {
                int n = ((PregCualitativaNoOrdenadaMultiple) preguntas.get(i)).getMaxOptions();
                System.out.println("Escribe el numero de opciones que quieres elegir (de como mucho " + n + "):" );
            }
            else if (preguntas.get(i) instanceof PregCualitativaOrdenada ||
                     preguntas.get(i) instanceof PregCualitativaNoOrdenadaUnica) {
                System.out.println("Escribe el NUMERO de la opcion que quieres elegir: ");
            }
            String respu = sc.nextLine();

            if (respu.equals("")) {
                Respuesta r = new RespVacia();
                ALR.add(r);
            }
            else {
                if (preguntas.get(i) instanceof PregRespuestaLibre) {
                    Respuesta r = new RespLibre(respu);
                    ALR.add(r);
                }
                else if (preguntas.get(i) instanceof PregNumerica) {
                    float resp = Float.parseFloat(respu);
                    PregNumerica p = (PregNumerica) preguntas.get(i);
                    Respuesta r = new RespNumerica(resp, p.getValorMin(), p.getValorMax());
                    ALR.add(r);
                }
                else if (preguntas.get(i) instanceof PregCualitativaOrdenada) {
                    int resp = Integer.parseInt(respu);
                    ++resp;
                    PregCualitativaOrdenada p = (PregCualitativaOrdenada) preguntas.get(i);
                    Respuesta r = new RespCualitativaOrdenada(resp, p.getMaxOptions(), p.getPreguntaIesima(resp));
                    ALR.add(r);
                }
                else if (preguntas.get(i) instanceof PregCualitativaNoOrdenadaUnica) {
                    int resp = Integer.parseInt(respu);
                    PregCualitativaNoOrdenadaUnica p = (PregCualitativaNoOrdenadaUnica) preguntas.get(i);
                    Respuesta r = new RespCualitativaNoOrdenadaUnica(resp, p.getPreguntaIesima(resp));
                    ALR.add(r);
                }
                else {
                    PregCualitativaNoOrdenadaMultiple p = (PregCualitativaNoOrdenadaMultiple) preguntas.get(i);
                    HashMap<Integer, String> CjtResps = new HashMap<>();
                    System.out.println("Escribe los NUMEROS de las " + p.getMaxOptions() + " opciones que quieres elegir: ");
                    int opts = Integer.parseInt(respu);
                    for (int j = 0; j < opts; j++) {
                        int resp = sc.nextInt();
                        String respTxt = p.getPreguntaIesima(resp);
                        CjtResps.put(resp, respTxt);
                    }
                    Respuesta r = new RespCualitativaNoOrdenadaMultiple(CjtResps);
                    ALR.add(r);
                }
            }

        }
        return ALR;
    }


    public void printarRespuestasDeEncuesta() {
        for (int i = 0; i != CjtRespsEnc.size(); ++i) {
            CjtRespsEnc.get(i).printarRespuestas();
        }
    }
}