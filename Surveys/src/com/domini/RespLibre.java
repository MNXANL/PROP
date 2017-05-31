package com.domini;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

/**
 * Respuesta libre, un string de tamaño y contenido arbitrarios
 */
public class RespLibre extends Respuesta{
    private String resp;
    HashSet<String> funcWords;

    /**
     *
     * @param s respuesta en formato libre
     */

    public RespLibre(String s){
        resp=s;
        Functionals();
    }

    /**
     * creadora con copia
     * @param rl respuesta libre a copiar
     */
    public RespLibre(RespLibre rl){
        resp = new String(rl.get());
        Functionals();
    }

    /**
     *
     * @return la respuesta tras haber eliminado las palabras funcionales
     */
    public String get(){
        return clean(resp);
    }

    /**
     *
     * @param s
     */
    public void set(String s){
        resp=s;
    }

    /**
     * distancia entre dos respuestas libres
     * @param x respuesta a comparar
     * @return
     */
    public double distance (Respuesta x){
        RespLibre r = (RespLibre) x;
        String s1 = clean(resp.toLowerCase());
        String s2 = clean(r.resp.toLowerCase());

        return levenshtein(s1,s2)/ Math.max(s1.length(),s2.length());
    }

    /**
     * distancia levenshtein entre dos strings
     * @param lhs primer string
     * @param rhs segundo string
     * @return
     */
    public double levenshtein(CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        double[] cost = new double[len0];
        double[] newcost = new double[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                double cost_replace = cost[i - 1] + match;
                double cost_insert  = cost[i] + 1;
                double cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            double[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

    /**
     * coger las palabras funcionales y guardarlas en un HashSet
     */
    private void Functionals(){
        funcWords = new HashSet<>();
        String path = "src/com/dades/PalabrasFuncionales";
        try{
            String line = null;
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                funcWords.add(line);
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");
        }
    }

    /**
     * Coge un String y devuelve el conjunto de palabras no funcionales para ser tratado en distance
     * @param r string de entrada
     * @return r sin palabras funcionales ni espacios
     */
    private String clean (String r){
        String [] words = r.split(" ");
        String result = "";


        //poner las palabras no funcionales en el string de salida
        for(int i = 0; i!= words.length; ++i){
            String w = words[i];
            if(!funcWords.contains(w.toLowerCase())) {
                result += w;
                if(i!= words.length-1)
                    result+=" ";
            }
        }
        return result;
    }

    /**
     * Metodo para comparar la clase con otro objeto
     * @param o Objeto a comparar
     * @return Si los objetos son los mismos o no
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespLibre))
            return false;
        RespLibre r = (RespLibre) o;

        return resp.equals(r.resp);
    }

    /**
     * Metodo para obtener el codigo de hash
     * @return El codigo de hash
     */
    @Override
    public int hashCode(){
        return Objects.hash(resp);
    }
}
