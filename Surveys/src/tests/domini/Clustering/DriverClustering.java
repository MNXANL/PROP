package tests.domini.Clustering;

import com.domini.*;

import java.lang.reflect.Array;
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
    public static void TestRun(Encuesta E){
        RespuestasEncuesta r1 = new RespuestasEncuesta(E,"Joan");
        RespuestasEncuesta r2 = new RespuestasEncuesta(E,"Oriol");
        RespuestasEncuesta r3 = new RespuestasEncuesta(E,"Marta");
        RespuestasEncuesta r4 = new RespuestasEncuesta(E,"Anna");

        RespCualitativaOrdenada ro1 = new RespCualitativaOrdenada(0,5,"Muy poco");
        RespCualitativaOrdenada ro2 = new RespCualitativaOrdenada(4,5,"Mucho");
        RespCualitativaOrdenada ro3 = new RespCualitativaOrdenada(3,5,"Regular");
        RespCualitativaOrdenada ro4 = new RespCualitativaOrdenada(3,5,"Regular");

        RespCualitativaNoOrdenadaUnica ru1 = new RespCualitativaNoOrdenadaUnica(1,"azul");
        RespCualitativaNoOrdenadaUnica ru2 = new RespCualitativaNoOrdenadaUnica(0,"rojo");
        RespCualitativaNoOrdenadaUnica ru3 = new RespCualitativaNoOrdenadaUnica(0,"rojo");
        RespCualitativaNoOrdenadaUnica ru4 = new RespCualitativaNoOrdenadaUnica(3,"verde");

        HashMap<Integer,String> h1 = new HashMap<>();h1.put(0,"Opel");h1.put(1,"Renault");
        HashMap<Integer,String> h2 = new HashMap<>();h2.put(1,"Renault");h2.put(2,"Hyundai");
        HashMap<Integer,String> h3 = new HashMap<>();h3.put(0,"Opel");h3.put(2,"Hyundai");
        HashMap<Integer,String> h4 = new HashMap<>();h4.put(0,"Opel");h4.put(3,"Nissan");
        RespCualitativaNoOrdenadaMultiple rm1 = new RespCualitativaNoOrdenadaMultiple(h1);
        RespCualitativaNoOrdenadaMultiple rm2 = new RespCualitativaNoOrdenadaMultiple(h2);
        RespCualitativaNoOrdenadaMultiple rm3 = new RespCualitativaNoOrdenadaMultiple(h3);
        RespCualitativaNoOrdenadaMultiple rm4 = new RespCualitativaNoOrdenadaMultiple(h4);

        RespNumerica rn1 = new RespNumerica(2,0,10);
        RespNumerica rn2 = new RespNumerica(3,0,10);
        RespNumerica rn3 = new RespNumerica(5,0,10);
        RespNumerica rn4 = new RespNumerica(8,0,10);


        RespLibre l1 = new RespLibre("Soy una Respuesta Libre e independiente, no necesito a ningún hombre");
        RespLibre l2 = new RespLibre("Soy una Respuesta Libre pero lo de independiente no está tan claro");
        RespLibre l3 = new RespLibre("Las Respuestas independientes son lo mismo que las libres?");
        RespLibre l4 = new RespLibre("No sé qué soy, ni si una respuesta puede ser libre en un universo determinista");

        r1.addRespuesta(ro1);r1.addRespuesta(ru1);r1.addRespuesta(rm1);r1.addRespuesta(rn1);r1.addRespuesta(l1);
        r2.addRespuesta(ro2);r2.addRespuesta(ru2);r2.addRespuesta(rm2);r2.addRespuesta(rn2);r2.addRespuesta(l2);
        r3.addRespuesta(ro3);r3.addRespuesta(ru3);r3.addRespuesta(rm3);r3.addRespuesta(rn3);r3.addRespuesta(l3);
        r4.addRespuesta(ro4);r4.addRespuesta(ru4);r4.addRespuesta(rm4);r4.addRespuesta(rn4);r4.addRespuesta(l4);
        E.responder(r1);
        E.responder(r2);
        E.responder(r3);
        E.responder(r4);
        System.out.println("\n----2 Clusters----");
        cl = new Clustering(E,2);
        cl.run();
        System.out.println("\n----3 Clusters----");
        cl = new Clustering(E,3);
        cl.run();
        System.out.println("\n----4 Clusters----");
        cl = new Clustering(E,4);
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
        REs.add(RE3);




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
    public static void TestRespCNOU_mode(){
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas Cualitativas no Ordenadas");
        int cluster_index=0;
        int resp_index   =0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();
        String[] num_fav = {"uno", "dos", "tres", "cuatro", "cinco"};

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");

        while(assig.size()!=5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for(Integer i = 0; i!= 5; ++i) {
            String userName = new String("TestUser "+i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespCualitativaNoOrdenadaUnica r = new RespCualitativaNoOrdenadaUnica(i%4,"da igual");
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespCualitativaNoOrdenadaUnica result = cl.RespCNOU_mode(cluster_index,resp_index,assig,REs);
        if(result.get() == 0)
            System.out.println("Test finalizado con éxito");
        else
            System.out.println("Test fallido: el centroide debería contener la seleccion 0"+
                    " Pero contiene: `"+ result.get()+"`");
    }
    public static void TestRespCO_mode(){
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas Cualitativas Ordenadas");
        int cluster_index=0;
        int resp_index   =0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");

        while(assig.size()!=5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for(Integer i = 0; i!= 5; ++i) {
            String userName = new String("TestUser "+i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespCualitativaOrdenada r = new RespCualitativaOrdenada(i%4+1,5,"da igual");
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespCualitativaOrdenada result = cl.RespCO_mode(cluster_index,resp_index,assig,REs);
        if(result.get() == 1)
            System.out.println("Test finalizado con éxito");
        else
            System.out.println("Test fallido: el centroide debería contener la seleccion 1"+
                    " Pero contiene: `"+ result.get()+"`");
    }
    public static void TestRespnum_avg(){
        System.out.println("Esta función genera un centroide a partir de un conjunto de respuestas numéricas");
        int cluster_index=0;
        int resp_index   =0;    //la encuesta ficticia solo tendra 1 pregunta
        ArrayList<Integer> assig = new ArrayList<>();
        ArrayList<RespuestasEncuesta> REs = new ArrayList<>();

        Encuesta E = new Encuesta("Sólo existo para poder inicializar RespuestasEncuesta");

        while(assig.size()!=5)     //a funciones del test todas las respuestas son de un cluster 0
            assig.add(0);
        for(Integer i = 0; i!= 5; ++i) {
            String userName = new String("TestUser "+i);
            RespuestasEncuesta RE = new RespuestasEncuesta(E, userName);
            RespNumerica r = new RespNumerica(10*i,0,1000000);
            RE.addRespuesta(r);
            REs.add(RE);
        }

        RespNumerica result = cl.Respnum_avg(cluster_index,resp_index,assig,REs);
        if(result.get() == 20)
            System.out.println("Test finalizado con éxito");
        else
            System.out.println("Test fallido: el centroide debería contener el numero 20"+
                    " Pero contiene: `"+ result.get()+"`");
    }
    public static void Testanswer_dist(){
        Encuesta aux = new Encuesta("aux");
        RespuestasEncuesta r1 = new RespuestasEncuesta(aux,"Joan");
        RespuestasEncuesta r2 = new RespuestasEncuesta(aux,"Oriol");

        RespLibre l1 = new RespLibre("Soy una Respuesta Libre e independiente, no necesito a ningún hombre");
        RespLibre l2 = new RespLibre("Soy una Respuesta Libre pero lo de independiente no está tan claro");

        RespCualitativaOrdenada ro1 = new RespCualitativaOrdenada(4,10,"cuatro");
        RespCualitativaOrdenada ro2 = new RespCualitativaOrdenada(6,10,"seis");

        RespCualitativaNoOrdenadaUnica ru1 = new RespCualitativaNoOrdenadaUnica(2,"azul");
        RespCualitativaNoOrdenadaUnica ru2 = new RespCualitativaNoOrdenadaUnica(5,"negro");

        HashMap<Integer,String> h1 = new HashMap<>();h1.put(1,"uno");h1.put(3,"tres");h1.put(5,"cinco");
        HashMap<Integer,String> h2 = new HashMap<>();h1.put(2,"dos");h1.put(3,"tres");h1.put(5,"cinco");
        RespCualitativaNoOrdenadaMultiple rm1 = new RespCualitativaNoOrdenadaMultiple(h1);
        RespCualitativaNoOrdenadaMultiple rm2 = new RespCualitativaNoOrdenadaMultiple(h2);

        RespNumerica rn1 = new RespNumerica(21,0,100);
        RespNumerica rn2 = new RespNumerica(34,0,100);

        r1.addRespuesta(l1);r1.addRespuesta(ro1);r1.addRespuesta(ru1);r1.addRespuesta(rm1);r1.addRespuesta(rn1);
        r2.addRespuesta(l2);r2.addRespuesta(ro2);r2.addRespuesta(ru2);r2.addRespuesta(rm2);r2.addRespuesta(rn2);

        System.out.println("Distancia de prueba = " + cl.answer_dist(r1,r2));
        System.out.println("Test finalizado con éxito");
    }
    public static void Testshow_clusters(){
        Encuesta aux = new Encuesta("aux");
        RespuestasEncuesta r1 = new RespuestasEncuesta(aux,"Joan");
        RespuestasEncuesta r2 = new RespuestasEncuesta(aux,"Oriol");
        RespuestasEncuesta r3 = new RespuestasEncuesta(aux,"Enric");
        RespuestasEncuesta r4 = new RespuestasEncuesta(aux,"Pere");
        RespuestasEncuesta r5 = new RespuestasEncuesta(aux,"Jaume");
        RespuestasEncuesta r6 = new RespuestasEncuesta(aux,"Pau");
        RespuestasEncuesta r7 = new RespuestasEncuesta(aux,"Albert");
        RespuestasEncuesta r8 = new RespuestasEncuesta(aux,"Anna");
        RespuestasEncuesta r9 = new RespuestasEncuesta(aux,"Maria");
        ArrayList<RespuestasEncuesta> Rs = new ArrayList<>();
        Rs.add(r1);Rs.add(r2);Rs.add(r3);Rs.add(r4);Rs.add(r5);
        Rs.add(r6);Rs.add(r7);Rs.add(r8);Rs.add(r9);

        ArrayList<Integer> assig = new ArrayList<>();
        assig.add(0);assig.add(1);assig.add(0);assig.add(1);assig.add(3);
        assig.add(4);assig.add(5);assig.add(5);assig.add(3);

        cl.show_clusters(Rs,assig,6);

        System.out.println("Test finalizado con éxito");
    }

    public static void main (String [] args) throws ExcFormatoIncorrecto{
        Encuesta E = Encuesta.importar("src/tests/domini/Clustering/JpruebaClustering");
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
                case "9":   TestRun(E);
                    break;
                case "10":   return;

            }

        }
    }
}
