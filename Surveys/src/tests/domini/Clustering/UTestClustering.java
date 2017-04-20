package tests.domini.Clustering;

import com.domini.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * Unit Test de Clustering
 */
class UTestClustering {
    private static Clustering cl;

    @org.junit.jupiter.api.Test
    void respLib_maxfreq() {
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas libres");
        int cluster_index = 0;
        int resp_index = 0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();
        String[] oficios = {"Profesor de FM", "Profesor de EDA", "Profesor de LP",
                "Obispo de la Seu d'Urgell", "Domador de Leones"};

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");
        cl = new Clustering(E,2);
        while (assig.size() != 5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for (Integer i = 0; i != 5; ++i) {
            String userName = new String("TestUser " + i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespLibre r = new RespLibre(oficios[i]);
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespLibre result = cl.RespLib_maxfreq(cluster_index, resp_index, assig, REs);
        assertEquals("Profesor", result.get());
    }

    @org.junit.jupiter.api.Test
    void respMUL_maxfreq() {
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas de multiple elección");
        int cluster_index = 0;
        int resp_index = 0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();
        String[] colores = {"azul", "verde", "rojo", "blanco", "gris"};

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");
        cl = new Clustering(E,2);
        while (assig.size() != 3)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);

        RespuestasEncuesta RE = new RespuestasEncuesta(E, "TestUser1");
        HashMap<Integer, String> h = new HashMap<>();
        h.put(0, "azul");
        h.put(4, "gris");
        h.put(2, "rojo");
        RespCualitativaNoOrdenadaMultiple r = new RespCualitativaNoOrdenadaMultiple(h);
        RE.addRespuesta(r);
        REs.add(RE);

        RespuestasEncuesta RE2 = new RespuestasEncuesta(E, "TestUser2");
        HashMap<Integer, String> h2 = new HashMap<>();
        h2.put(3, "blanco");
        h2.put(4, "gris");
        h2.put(2, "rojo");
        RespCualitativaNoOrdenadaMultiple r2 = new RespCualitativaNoOrdenadaMultiple(h2);
        RE2.addRespuesta(r2);
        REs.add(RE2);

        RespuestasEncuesta RE3 = new RespuestasEncuesta(E, "TestUser3");
        HashMap<Integer, String> h3 = new HashMap<>();
        h3.put(3, "blanco");
        h3.put(4, "gris");
        h3.put(2, "rojo");
        RespCualitativaNoOrdenadaMultiple r3 = new RespCualitativaNoOrdenadaMultiple(h3);
        RE3.addRespuesta(r3);
        REs.add(RE3);


        RespCualitativaNoOrdenadaMultiple result = cl.RespMUL_maxfreq(cluster_index, resp_index, assig, REs);
        HashSet<Integer> sol = new HashSet<>();
        sol.add(2);
        sol.add(3);
        sol.add(4);
        assertEquals(sol, result.get());
    }

    @org.junit.jupiter.api.Test
    void respCNOU_mode() {
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas Cualitativas no Ordenadas");
        int cluster_index = 0;
        int resp_index = 0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();
        String[] num_fav = {"uno", "dos", "tres", "cuatro", "cinco"};

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");
        cl = new Clustering(E,2);
        while (assig.size() != 5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for (Integer i = 0; i != 5; ++i) {
            String userName = new String("TestUser " + i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespCualitativaNoOrdenadaUnica r = new RespCualitativaNoOrdenadaUnica(i % 4, "da igual");
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespCualitativaNoOrdenadaUnica result = cl.RespCNOU_mode(cluster_index, resp_index, assig, REs);
        assertEquals(0, result.get());
    }

    @org.junit.jupiter.api.Test
    void respCO_mode() {
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas Cualitativas Ordenadas");
        int cluster_index = 0;
        int resp_index = 0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");
        cl = new Clustering(E,2);
        while (assig.size() != 5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for (Integer i = 0; i != 5; ++i) {
            String userName = new String("TestUser " + i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespCualitativaOrdenada r = new RespCualitativaOrdenada(i % 4 + 1, 5, "da igual");
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespCualitativaOrdenada result = cl.RespCO_mode(cluster_index, resp_index, assig, REs);
        assertEquals(1, result.get());
        if (result.get() == 1)
            System.out.println("Test finalizado con éxito");
        else
            System.out.println("Test fallido: el centroide debería contener la seleccion 1" +
                    " Pero contiene: `" + result.get() + "`");
    }

    @org.junit.jupiter.api.Test
    void respnum_avg() {
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas numéricas");
        int cluster_index = 0;
        int resp_index = 0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");
        cl = new Clustering(E,2);
        while (assig.size() != 5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for (Integer i = 0; i != 5; ++i) {
            String userName = new String("TestUser " + i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespNumerica r = new RespNumerica(10 * i, 0, 1000000);
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespNumerica result = cl.Respnum_avg(cluster_index, resp_index, assig, REs);
        assertEquals(20, result.get());
    }

}