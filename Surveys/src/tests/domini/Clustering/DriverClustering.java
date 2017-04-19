package tests.domini.Clustering;

import com.domini.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * driver de pruebas de la clase de clustering
 */
public class DriverClustering {
    private static Clustering cl;

    public static void TestConstructor(Encuesta E, int k){
        cl = new Clustering(E,3);
    }
    //run es la funcion que llama a kmeans dandole el estado inicial, no tiene sentido probar run y kmeans por separado
    public static void TestRun(){
        cl.run();
    }
    public static void TestRespLib_maxfreq(){
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas libres");
        int cluster_index=0;
        int resp_index   =0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();
        String[] oficios = {"Profesor de FM", "Profesor de EDA", "Profesor de LP",
                "Obispo de la Seu d'Urgell", "Domador de Leones"};

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");

        while(assig.size()!=5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for(Integer i = 0; i!= 5; ++i) {
            String userName = new String("TestUser "+i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespLibre r = new RespLibre(oficios[i]);
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespLibre result = cl.RespLib_maxfreq(cluster_index,resp_index,assig,REs);
        if(result.get().equals("Profesor"))
            System.out.println("Test finalizado con éxito");
        else
            System.out.println("Test fallido: el centroide debería contener la palabra `Profesor`"+
                                " Pero contiene: `"+ result.get()+"`");
    }
    public static void TestRespMUL_maxfreq(){
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas de multiple elección");
        int cluster_index=0;
        int resp_index   =0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();
        String[] colores = {"azul", "verde", "rojo","blanco", "gris"};

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");

        while(assig.size()!=3)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);

        RespuestasEncuesta RE = new RespuestasEncuesta(E, "TestUser1");
        HashMap<Integer,String> h = new HashMap<>();
        h.put(0,"azul");h.put(4,"gris");h.put(2,"rojo");
        RespCualitativaNoOrdenadaMultiple r = new RespCualitativaNoOrdenadaMultiple(h);
        RE.addRespuesta(r);
        REs.add(RE);

        RespuestasEncuesta RE2 = new RespuestasEncuesta(E, "TestUser2");
        HashMap<Integer,String> h2 = new HashMap<>();
        h2.put(3,"blanco");h2.put(4,"gris");h2.put(2,"rojo");
        RespCualitativaNoOrdenadaMultiple r2 = new RespCualitativaNoOrdenadaMultiple(h2);
        RE2.addRespuesta(r2);
        REs.add(RE2);

        RespuestasEncuesta RE3 = new RespuestasEncuesta(E, "TestUser3");
        HashMap<Integer,String> h3 = new HashMap<>();
        h3.put(3,"blanco");h3.put(4,"gris");h3.put(2,"rojo");
        RespCualitativaNoOrdenadaMultiple r3 = new RespCualitativaNoOrdenadaMultiple(h3);
        RE3.addRespuesta(r3);
        REs.add(RE);




        RespCualitativaNoOrdenadaMultiple result = cl.RespMUL_maxfreq(cluster_index,resp_index,assig,REs);
        HashSet<Integer> sol = new HashSet<>();
        sol.add(2);sol.add(3);sol.add(4);
        if(result.get().containsAll(sol))
            System.out.println("Test finalizado con éxito");
        else {
            System.out.println("Test fallido: El centroide no contiene los elementos correctos, sino los siguientes:");
            for(Integer u : result.get()) System.out.println(u);
        }
    }
    public static void TestRespCNOU_mode(){}
    public static void TestRespCO_mode(){}
    public static void TestRespnum_avg(){}
    public static void Testanswer_dist(){}
    public static void Testshow_clusters(){}

    public static void main (String [] args){
        System.out.println("Importando encuesta `Test` para realizar pruebas");
        Encuesta E = Encuesta.importar("src/tests/domini/Clustering/Test.txt");
        cl = new Clustering(E,2);
        Scanner sc = new Scanner(System.in);
        System.out.println("Opciones:\n\t 1 - Probar el constructor \n" +
                                       "\t2 - Probar RespLib_maxfreq\n" +
                                       "\t3 - Probar RespMul_maxfreq\n" +
                                       "\t4 - Probar RespCNOU_mode\n" +
                                       "\t5 - Probar RespCO_mode\n" +
                                       "\t6 - Probar Respnum_avg\n" +
                                       "\t7 - Probar answer_dist\n" +
                                       "\t8 - Probar show_clusters\n" +
                                       "\t9 - Probar el algoritmo K-Means\n"+
                                       "\t10 - Salir");
        while(sc.hasNextInt()){
            switch(sc.next()){
                case "1":   TestConstructor(E,3);
                    break;
                case "2":   TestRespLib_maxfreq();
                    break;
                case "3":   TestRespMUL_maxfreq();
                    break;
                case "4":   TestRespCNOU_mode();
                    break;
                case "5":   TestRespCO_mode();
                    break;
                case "6":   TestRespnum_avg();
                    break;
                case "7":   Testanswer_dist();
                    break;
                case "8":   Testshow_clusters();
                    break;
                case "9":   TestRun();
                    break;
                case "10":   return;

            }

        }
    }
}
