package com.domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.presentacio.*;

import javax.swing.*;

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
        System.out.println("   8. Cambiar sesion de usuario");
        System.out.println("   9. Cerrar sesion");
        System.out.println("   10. Crear nuevo usuario");
        //System.out.println("   11. Modificar respuesta a encuesta");
        System.out.println("   11. Borrar respuesta a encuesta");
        System.out.println("   12. Salir del programa");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        ControladorPresentacio ctrlPres = new ControladorPresentacio();
                        ctrlPres.inicializarPresentacion();
                    }
                }
        );
/*
        try {
            ControladorDominio cd = new ControladorDominio();

            int hasLogIn = 0;

            Scanner sc = new Scanner(System.in);
            while (hasLogIn == 0) {
                System.out.println("Introduce el nombre de usuario");
                String user = sc.nextLine();
                System.out.println("Introduce la contraseña");
                String pass = sc.nextLine();
                hasLogIn = cd.logIn(user, pass);
            }

            Presentacion();

            boolean continuar = true;
            while (continuar) {
                if (hasLogIn == 0) {
                    sc.nextLine();
                    System.out.println("Introduce el nombre de usuario");
                    String user = sc.nextLine();
                    System.out.println("Introduce la contraseña");
                    String pass = sc.nextLine();
                    hasLogIn = cd.logIn(user, pass);
                } else {
                    int option = sc.nextInt();
                    switch (option) {
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

                            ArrayList<String> x = new ArrayList<>();
                            x.add("mucho");
                            x.add("meh");
                            x.add("poco");
                            x.add("nada");
                            PregCualitativaOrdenada P = new PregCualitativaOrdenada("Cuánto te gusta prop?", x);
                            E.add_question(P);
                            PregNumerica P2 = new PregNumerica("nota de prop?", 0, 10);
                            E.add_question(P2);

                            RespuestasEncuesta RE1 = new RespuestasEncuesta(E.getTitulo(), "Manolo0");
                            RespCualitativaOrdenada r1 = new RespCualitativaOrdenada(0, 4, "nada");
                            RE1.addRespuesta(r1);
                            RespVacia r11 = new RespVacia();
                            RE1.addRespuesta(r11);
                            E.responder(RE1);

                            RespuestasEncuesta RE2 = new RespuestasEncuesta(E.getTitulo(), "Pepe1");
                            RespVacia r2 = new RespVacia();
                            RE2.addRespuesta(r2);
                            RespVacia r22 = new RespVacia();
                            RE2.addRespuesta(r22);
                            E.responder(RE2);

                            RespuestasEncuesta RE3 = new RespuestasEncuesta(E.getTitulo(), "Juan2");
                            RespCualitativaOrdenada r3 = new RespCualitativaOrdenada(2, 4, "meh");
                            RE3.addRespuesta(r3);
                            RespVacia r33 = new RespVacia();
                            RE3.addRespuesta(r33);
                            E.responder(RE3);

                            RespuestasEncuesta RE4 = new RespuestasEncuesta(E.getTitulo(), "Rafa3");
                            RespCualitativaOrdenada r4 = new RespCualitativaOrdenada(3, 4, "mucho");
                            RE4.addRespuesta(r4);
                            RespNumerica r44 = new RespNumerica(100,0,1000);
                            RE4.addRespuesta(r44);
                            E.responder(RE4);

                            System.out.println("Introduce el número de clusters deseados");
                            int k = sc.nextInt();
                            Clustering c = new Clustering(E, k);
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
                            cd.logIn(user, pass);
                            break;
                        case 9:
                            cd.logOut();
                            System.out.println("Sesión cerrada");
                            hasLogIn = 0;
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
        catch(ExcFormatoIncorrecto e){
            System.out.println("YOLO");
        }
        catch(ExcUsuarioExistente u){
            System.out.println("YOLO");
        }*/

    }
}
