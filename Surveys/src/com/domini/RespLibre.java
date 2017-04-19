package com.domini;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

//respuesta libre, un string conteniendo cualquier cosa
public class RespLibre extends Respuesta{
    private String resp;

    /**
     *
     * @param s
     */

    public RespLibre(String s){
        resp=s;
    }

    public RespLibre(RespLibre rl){
        resp = new String(rl.get());
    }

    /**
     *
     * @return
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

   /*
    private double levenshtein (String a, String b, int i, int j){
        System.out.println("i: " +i + "j: " +j);
        if(Math.min(i,j)==0)
            return Math.max(i,j);
        else{
            System.out.println("levenstein");
            double x = levenshtein(a,b,i-1,j) + 1;
            double y = levenshtein(a,b,i,j-1) + 1;
            double z = levenshtein(a,b,i-1,j-1);
            if(a.charAt(i-1) != b.charAt(j-1)) z+=1;

            return Math.min(x, Math.min(y,z));
        }
    }*/

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
     * Coge un String y devuelve el conjunto de palabras no funcionales para ser tratado en distance
     * @param r string de entrada
     * @return r sin palabras funcionales ni espacios
     */
    private String clean (String r){
        String [] words = r.split(" ");
        String path = "src/com/domini/PalabrasFuncionales";
        String result = "";
        HashSet<String> funcWords = new HashSet<>();

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

        //poner las palabras no funcionales en el string de salida
        for(int i = 0; i!= words.length; ++i){
            String w = words[i];
            if(!funcWords.contains(w)) {
                result += w;
                if(i!= words.length-1)
                    result+=" ";
            }
        }
        return result;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespLibre))
            return false;
        RespLibre r = (RespLibre) o;

        return resp.equals(r.resp);
    }
    @Override
    public int hashCode(){
        return Objects.hash(resp);
    }
}
