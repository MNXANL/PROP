package com.domini;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

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

    /**
     *
     * @return
     */
    public String get(){
        return resp;
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
     * @param r respuesta a comparar
     * @return
     */
    public double distance (RespLibre r){
        String s1 = clean(resp.toLowerCase());
        String s2 = clean(r.resp.toLowerCase());

        return levenshtein(s1,s2,s1.length(),s2.length())/ Math.max(s1.length(),s2.length());
    }

    /**
     * calcula la distancia de levenshtein entre dos strings a y b con longitudes i j
     * @param a
     * @param b
     * @param i
     * @param j
     * @return
     */
    private double levenshtein (String a, String b, int i, int j){
        if(Math.min(i,j)==0)    return Math.max(i,j);
        else{
            double x = levenshtein(a,b,i-1,j) + 1;
            double y = levenshtein(a,b,i,j-1) + 1;
            double z = levenshtein(a,b,i-1,j-1);

            if(a.charAt(i-1) != b.charAt(j-1)) z+=1;

            return Math.min(x, Math.min(y,z));
        }
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
        for(String w : words){
            if(!funcWords.contains(w))
                result += w;
        }
        return result;
    }
}
