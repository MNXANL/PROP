package com.domini;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

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

    /**
     * Modificador de titulo de encuesta
     * @param titulo Nuevo titulo para la encuesta
     */
    public void setTitulo (String titulo) {
        title = titulo;
    }

    /**
     * Consultora de titulo de encuesta
     * @return Titulo actual de la encuesta
     */
    public String getTitulo () {
        return title;
    }

    /**
     * Consultora de las preguntas de la encuesta
     * @return Todas las preguntas que constituyen la encuesta
     */
    public ArrayList<Pregunta> getPreguntas () {
        return preguntas;
    }

    /**
     * Consultora de la fecha en la que fue creada la encuesta
     * @return Fecha en la que fue creada la encuesta
     */
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

    /**
     * importar una encuesta externa al programa
     * @param path El lugar donde encontrar la encuesta
     */
    public static Encuesta importar (String path) throws ExcFormatoIncorrecto{

        Encuesta e = new Encuesta();

        // This will reference one line at a time
        String line = null;
        int contLinea = 0;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int indexPreg = 0;
            while((line = bufferedReader.readLine()) != null) {
                contLinea++;
                if (line.equals("Titulo")) {
                    line = bufferedReader.readLine();
                    contLinea++;
                    if (line == null) {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                        throw exc;
                    }
                    else if (line.equals("")){
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de encuesta vacío");
                        throw exc;
                    }
                    else {
                        e.title = line;
                    }
                }
                else if (line.equals("Pregunta")) {
                    //leer tipo de pregunta
                    line = bufferedReader.readLine();
                    contLinea++;
                    if (line == null) {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en la linea "+contLinea+". Encuesta inacabada.");
                        throw exc;
                    }
                    else if (line.equals("PCO")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        contLinea++;
                        if (tituloP == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        else if (tituloP.equals("")) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de pregunta vacío");
                            throw exc;
                        }
                        //leer todas las opciones de respuesta
                        ArrayList<String> opciones = new ArrayList<>();
                        int index = 0;
                        while ((line = bufferedReader.readLine()) != null && !line.equals("")){
                            contLinea++;
                            if (line.equals("Titulo") || line.equals("Pregunta") || line.equals("Fecha") || line.equals("Final encuesta")) {
                                ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Debes dejar una línea en blanco entre el final de una pregunta y el siguiente contenido.");
                                throw exc;
                            }
                            opciones.add(index,line);
                            index++;
                        }
                        contLinea++;
                        if (line == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP,opciones);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PCNOU")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        contLinea++;
                        if (tituloP == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        else if (tituloP.equals("")) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de pregunta vacío");
                            throw exc;
                        }
                        //leer todas las opciones de respuesta
                        ArrayList<String> opciones = new ArrayList<>();
                        int index = 0;
                        while ((line = bufferedReader.readLine()) != null && !line.equals("")){
                            contLinea++;
                            if (line.equals("Titulo") || line.equals("Pregunta") || line.equals("Fecha") || line.equals("Final encuesta")) {
                                ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Debes dejar una línea en blanco entre el final de una pregunta y el siguiente contenido.");
                                throw exc;
                            }
                            opciones.add(index,line);
                            index++;
                        }
                        contLinea++;
                        if (line == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP,opciones);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PCNOM")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        contLinea++;
                        if (tituloP == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        else if (tituloP.equals("")) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de pregunta vacío");
                            throw exc;
                        }
                        //leer max opciones
                        contLinea++;
                        String m = bufferedReader.readLine();
                        if (m == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        int max = Integer.parseInt(m);
                        //leer todas las opciones de respuesta
                        ArrayList<String> opciones = new ArrayList<>();
                        int index = 0;
                        while ((line = bufferedReader.readLine()) != null && !line.equals("")){
                            contLinea++;
                            if (line.equals("Titulo") || line.equals("Pregunta") || line.equals("Fecha") || line.equals("Final encuesta")) {
                                ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Debes dejar una línea en blanco entre el final de una pregunta y el siguiente contenido.");
                                throw exc;
                            }
                            opciones.add(index,line);
                            index++;
                        }
                        contLinea++;
                        if (line == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        if (max < 0 || max > opciones.size()) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+(contLinea - opciones.size() - 1)+". Número máximo de respuestas incorrecto");
                            throw exc;
                        }
                        PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP,opciones,max);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PN")) {
                        //leer titulo de la pregunta
                        String tituloP = bufferedReader.readLine();
                        contLinea++;
                        if (tituloP == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        else if (tituloP.equals("")) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de pregunta vacío");
                            throw exc;
                        }
                        contLinea++;
                        String mi = bufferedReader.readLine();
                        if (mi == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        float min = Float.parseFloat(mi);
                        contLinea++;
                        String ma = bufferedReader.readLine();
                        if (ma == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        float max = Float.parseFloat(ma);
                        if (max <= min) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". El valor maximo debe ser mayor que el valor mínimo.");
                            throw exc;
                        }
                        PregNumerica preg = new PregNumerica(tituloP,min,max);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else if (line.equals("PRL")) {
                        String tituloP = bufferedReader.readLine();
                        contLinea++;
                        if (tituloP == null) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                            throw exc;
                        }
                        else if (tituloP.equals("")) {
                            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Titulo de pregunta vacío");
                            throw exc;
                        }
                        PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                        e.preguntas.add(indexPreg,preg);
                        indexPreg++;
                    }
                    else {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en la linea "+contLinea+". El tipo de pregunta no existe.");
                        throw exc;
                    }
                }
                else if (line.equals("Fecha")) {
                    String f = bufferedReader.readLine();
                    contLinea++;
                    if (f == null) {
                        ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Encuesta inacabada");
                        throw exc;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                    e.fecha = sdf.parse(f);
                }
                else if (line.equals("Final encuesta") || line.equals("")) {

                }
                else if (line.charAt(0)=='#'){
                    contLinea++;
                }
                else {
                    //throw exception
                    ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en línea "+Integer.toString(contLinea)+". Palabra clave incorrecta.");
                    throw exc;
                }
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
            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Formato de fecha incorrecto. Formato a seguir: dd/MM/aa hh:mm:ss");
            throw exc;
        }
        catch (NumberFormatException nfe) {
            ExcFormatoIncorrecto exc = new ExcFormatoIncorrecto("Error en linea "+contLinea+". Número mal definido.");
            throw exc;
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
            bufferedWriter.write("Titulo\n");
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
    public boolean responder(RespuestasEncuesta re){
        for (int i = 0; i < CjtRespsEnc.size(); i++) {
            if (CjtRespsEnc.get(i).getUser().equals(re.getUser())) {
                CjtRespsEnc.remove(i);
                CjtRespsEnc.add(re);
                return true;
            }
        }
        CjtRespsEnc.add(re);
        return false;
    }

    /**
     * Metodo que comprueba si el usuario ha respondido la encuesta
     * @param user El usuario
     * @return Si el usuario ha respondido la encuesta o no
     */
    public boolean respondida(String user) {
        for (int i = 0; i < CjtRespsEnc.size(); i++) {
            if (CjtRespsEnc.get(i).getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para obtener las respuestas de un usuario
     * @param user El usuario
     * @return Las respuestas del usuario en la encuesta
     */
    public RespuestasEncuesta getRespuesta(String user) {
        for (int i = 0; i < CjtRespsEnc.size(); i++) {
            if (CjtRespsEnc.get(i).getUser().equals(user)) {
                return CjtRespsEnc.get(i);
            }
        }
        return null;
    }

    /**
     * Metodo para obtener el numero de veces que se ha respondido la encuesta
     * @return El numero de respuestas de la encuesta
     */
    public int getNumResps () {
        return CjtRespsEnc.size();
    }

    /**
     * Metodo para escribir por consola el contenido de la pregunta
     */
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

    /**
     * Metodo para obtener una matriz de la encuesta
     * @return Matriz representativa de la encuesta
     */
    public ArrayList<ArrayList<String>> getMatrix () {
        ArrayList<ArrayList<String>> pregs = new ArrayList<>();
        for (Pregunta p : preguntas) {
            ArrayList<String> preg = new ArrayList<>();
            if (p.tipo().equals("PRL")) {
                preg.add(p.tipo()); preg.add(p.getTitulo());
            }
            else if (p.tipo().equals("PN")) {
                PregNumerica pn = (PregNumerica) p;
                preg.add(pn.tipo()); preg.add(pn.getTitulo());
                preg.add( String.valueOf(pn.getValorMin()));
                preg.add( String.valueOf(pn.getValorMax()));
            }
            else if (p.tipo().equals("PCO")) {
                PregCualitativaOrdenada pc = (PregCualitativaOrdenada) p;
                preg.add(pc.tipo()); preg.add(pc.getTitulo());
                for (int i = 0; i != pc.getSize(); ++i) {
                    preg.add(pc.getPreguntaIesima(i));
                }
            }
            else if (p.tipo().equals("PCNOU")) {
                PregCualitativaNoOrdenadaUnica pc = (PregCualitativaNoOrdenadaUnica) p;
                preg.add(pc.tipo()); preg.add(pc.getTitulo());
                for (int i = 0; i != pc.getSize(); ++i) {
                    preg.add(pc.getPreguntaIesima(i));
                }
            }
            else if (p.tipo().equals("PCNOM")) {
                PregCualitativaNoOrdenadaMultiple pc = (PregCualitativaNoOrdenadaMultiple) p;
                preg.add(pc.tipo()); preg.add(pc.getTitulo()); preg.add(String.valueOf(pc.getMaxOptions()));
                for (int i = 0; i != pc.getSize(); ++i) {
                    preg.add(pc.getPreguntaIesima(i));
                }
            }
            pregs.add(preg);
        }
        return pregs;
    }

    /**
     * Metodo para tener todas las respuestas de la encuesta
     * @return La lista de respuestas de la pregunta
     */
    public ArrayList<RespuestasEncuesta> getCjtRespsEnc(){
        return new ArrayList<>(CjtRespsEnc);
    }

    /**
     * Metodo para borrar las respuestas de un usuario
     * @param user El usuario
     * @return Si la respuesta se ha borrado satisfactoriamente
     */
    public boolean borrarRespuesta(String user) {
        for (int i = 0; i < getCjtRespsEnc().size(); ++i) {
            if (CjtRespsEnc.get(i).getUser().equals(user)) {
                CjtRespsEnc.remove(i);
                return true;
            }
        }
        return false;
    }

}
