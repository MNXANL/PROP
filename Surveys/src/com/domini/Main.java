package com.domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static void Presentacion() {
        System.out.println("*** Gestión de encuestas de PROP ***");
        System.out.println("Elige tu opcion:");

        System.out.println("   0. Menú");
        System.out.println("   1. Crear encuesta.");
        System.out.println("   2. Responder encuesta.");
        System.out.println("   3. Visualizar clustering encuesta");
        System.out.println("   4. Ver las respuestas de una encuesta");
        System.out.println("   5. Lista de las encuestas");
        System.out.println("   6. Modificar encuesta");
        System.out.println("   7. Borrar encuesta");
        System.out.println("   8. Iniciar sesion");
        System.out.println("   9. Cerrar sesion");
        System.out.println("   10. Crear nuevo usuario");
        //System.out.println("   11. Modificar respuesta a encuesta");
        System.out.println("   11. Borrar respuesta a encuesta");
        System.out.println("   12. Salir del programa");
    }

    public static void main(String[] args) {
        ControladorDominio cd = new ControladorDominio();

        Presentacion();

        Scanner sc = new Scanner(System.in);


        boolean continuar = true;
        while (continuar) {
            int option = sc.nextInt();
            switch (option){
                case 0:
                    Presentacion();
                    break;
                case 1:
                    cd.crearEncuesta();
                    break;
                case 2:
                    System.out.println("Escribe el nombre de la encuesta que quieres responder: ");
                    sc.nextLine();
                    cd.responderEncuesta(sc.nextLine());
                    break;
                case 3:
                    //cd.ClusteringKmeans()

                    Encuesta E = new Encuesta("test");
                    PregRespuestaLibre P = new PregRespuestaLibre("¿Cuál es vuestro oficio?");
                    E.add_question(P);

                    RespuestasEncuesta RE1 = new RespuestasEncuesta(E,"Alejandro");
                    RespLibre r1 = new RespLibre("Profesor de filosofía");
                    RE1.addRespuesta(r1);
                    E.responder(RE1);
                    RespuestasEncuesta RE2 = new RespuestasEncuesta(E,"Miguel");
                    RespLibre r2 = new RespLibre("Panadero");
                    RE2.addRespuesta(r2);
                    E.responder(RE2);
                    RespuestasEncuesta RE3 = new RespuestasEncuesta(E,"Aleix");
                    RespLibre r3 = new RespLibre("Profesor de Bellas Artes");
                    RE3.addRespuesta(r3);
                    E.responder(RE3);
                    /*
                    ArrayList<String> options = new ArrayList<>();
                    options.add("azul");options.add("rojo");options.add("amarillo");options.add("verde");
                    PregCualitativaNoOrdenadaMultiple PM = new PregCualitativaNoOrdenadaMultiple("colores que te molan",options);
                    E.add_question(PM);

                    RespuestasEncuesta RE1 = new RespuestasEncuesta(E,"Alejandro");
                    HashMap<Integer,String> sel = new HashMap<>();
                    sel.put(0,"azul");sel.put(1,"rojo");
                    RespCualitativaNoOrdenadaMultiple r1 = new RespCualitativaNoOrdenadaMultiple(sel);
                    RE1.addRespuesta(r1);
                    E.responder(RE1);

                    RespuestasEncuesta RE2 = new RespuestasEncuesta(E,"Miguel");
                    HashMap<Integer,String> sel2 = new HashMap<>();
                    sel2.put(0,"azul");sel2.put(1,"rojo");
                    RespCualitativaNoOrdenadaMultiple r2 = new RespCualitativaNoOrdenadaMultiple(sel2);
                    RE2.addRespuesta(r2);
                    E.responder(RE2);

                    RespuestasEncuesta RE3 = new RespuestasEncuesta(E,"Aleix");
                    HashMap<Integer,String> sel3 = new HashMap<>();
                    sel3.put(1,"rojo");sel3.put(2,"amarillo");
                    RespCualitativaNoOrdenadaMultiple r3 = new RespCualitativaNoOrdenadaMultiple(sel3);
                    RE3.addRespuesta(r3);
                    E.responder(RE3);*/
                    /*
                    ArrayList<String> x = new ArrayList<>();
                    x.add("mucho");x.add("meh");x.add("poco");x.add("nada");
                    PregCualitativaOrdenada P = new PregCualitativaOrdenada("Cuánto te gusta prop?",x);
                    E.add_question(P);
                    PregNumerica P2 = new PregNumerica("nota de prop?",0,10);
                    E.add_question(P2);

                    RespuestasEncuesta RE1 = new RespuestasEncuesta(E,"Manolo0");
                    RespCualitativaOrdenada r1 = new RespCualitativaOrdenada(0,4,"nada");
                    RE1.addRespuesta(r1);
                    RespNumerica r11 = new RespNumerica(4,0,10);
                    RE1.addRespuesta(r11);
                    E.responder(RE1);

                    RespuestasEncuesta RE2 = new RespuestasEncuesta(E,"Pepe1");
                    RespCualitativaOrdenada r2 = new RespCualitativaOrdenada(1,4,"nada");
                    RE2.addRespuesta(r2);
                    RespNumerica r22 = new RespNumerica(6,0,10);
                    RE2.addRespuesta(r22);
                    E.responder(RE2);

                    RespuestasEncuesta RE3 = new RespuestasEncuesta(E,"Juan2");
                    RespCualitativaOrdenada r3 = new RespCualitativaOrdenada(2,4,"meh");
                    RE3.addRespuesta(r3);
                    RespNumerica r33 = new RespNumerica(8,0,10);
                    RE3.addRespuesta(r33);
                    E.responder(RE3);

                    RespuestasEncuesta RE4 = new RespuestasEncuesta(E,"Rafa3");
                    RespCualitativaOrdenada r4 = new RespCualitativaOrdenada(3,4,"mucho");
                    RE4.addRespuesta(r4);
                    RespNumerica r44 = new RespNumerica(10,0,10);
                    RE4.addRespuesta(r44);
                    E.responder(RE4);   */

                    System.out.println("Introduce el número de clusters deseados");
                    int k = sc.nextInt();
                    Clustering c = new Clustering(E,k);
                    c.run();
                    break;
                case 4:
                    System.out.println("Escribe el nombre de la encuesta para mostrar sus respuestas: ");
                    sc.nextLine();
                    cd.verRespuestasEncuesta(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Introduce el criterio de búsqueda:");
                    sc.nextLine();
                    String criterio = sc.nextLine();
                    String[] titulos = cd.listaEncuestas(criterio);
                    for (String titulo : titulos) {
                        System.out.println(titulo);
                    }
                    break;
                case 6:
                    System.out.println("Encuestas disponibles:");
                    String[] t = cd.listaEncuestas("A-Z");
                    for (String i : t) {
                        System.out.println(i);
                    }
                    System.out.println("Escribe el nombre de la encuesta que quieres modificar");
                    sc.nextLine();
                    cd.modificarEncuesta(sc.nextLine());
                    break;
                case 7:
                    System.out.println("Encuestas disponibles:");
                    String[] ti = cd.listaEncuestas("A-Z");
                    for (String i : ti) {
                        System.out.println(i);
                    }
                    System.out.println("Escribe el nombre de la encuesta que quieres borrar");
                    sc.nextLine();
                    cd.borrarEncuesta(sc.nextLine());
                    break;
                case 8:
                    sc.nextLine();
                    System.out.println("Introduce el nombre de usuario");
                    String user = sc.nextLine();
                    System.out.println("Introduce la contraseña");
                    String pass = sc.nextLine();
                    cd.logIn(user,pass);
                    break;
                case 9:
                    cd.logOut();
                    System.out.println("Sesión cerrada");
                    break;
                case 10:
                    sc.nextLine();
                    System.out.println("Qué tipo de usuario eres? [Admin/Enc]");
                    String tU = sc.nextLine();
                    System.out.println("Introduce un nombre de usuario");
                    String u = sc.nextLine();
                    System.out.println("Introduce una contraseña");
                    String p = sc.nextLine();
                    cd.nuevoUsuario(tU, u, p);
                    break;
                case 11:
                    System.out.println("Escribe el nombre de la encuesta respondida que quieres borrar: ");
                    sc.nextLine();
                    cd.borrarRespuestasEncuesta(sc.nextLine());
                    break;
                case 12:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción invalida. Por favor, vuelve a intentar con un número en el rango [0..12].");
            }
        }
    }
}
