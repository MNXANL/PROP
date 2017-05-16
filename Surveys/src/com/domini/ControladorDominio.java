package com.domini;

import com.dades.ControladorDatos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Controlador de la capa de dominio
 */
public class ControladorDominio {
    private Encuesta e;
    private Usuario u;

    ControladorDatos contDatos;

    CjtEncuestas cjt;

    public ControladorDominio() throws ExcFormatoIncorrecto{
        contDatos = new ControladorDatos();
        cjt = new CjtEncuestas(contDatos.cargar());
        u = null;
    }

    public int logIn (String usuario, String pass) {
        int x = contDatos.logIn(usuario,pass);
        switch (x) {
            case 0:
                break;
            case 1:
                u = new Encuestado(usuario);
                break;
            case 2:
                u = new Administrador(usuario);
                break;
        }
        return x;
    }

    public String getUser() {
        return u.getUsername();
    }

    public void logOut () {
        u = null;
    }

    public void nuevoUsuario (String tipo, String nombre, String pass) throws ExcUsuarioExistente {
        contDatos.nuevoUsuario (tipo, nombre, pass);
    }

    public void crearEncuesta () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el título de la encuesta");
        String tituloE = sc.nextLine();
        e = new Encuesta(tituloE);
        System.out.println("Quieres añadir una nueva pregunta? [sí/no]");
        String r = sc.nextLine();
        while (r.toLowerCase().equals("sí") || r.toLowerCase().equals("si")){
            System.out.println("Introduce el título de la pregunta");
            String tituloP = sc.nextLine();
            System.out.println("Introduce el tipo de pregunta [PCO, PCNOU, PCNOM, PN, PRL]");
            String tipo = sc.nextLine();

            if (tipo.toUpperCase().equals("PCO")) {
                System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                int n = sc.nextInt();
                sc.nextLine();
                ArrayList<String> opciones = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce la opción número "+ (i+1));
                    opciones.add(i,sc.nextLine());
                }
                PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP,opciones);
                e.add_question(preg);
            }
            else if (tipo.toUpperCase().equals("PCNOU")) {
                System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                int n = sc.nextInt();
                sc.nextLine();
                ArrayList<String> opciones = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce la opción número "+ (i+1));
                    opciones.add(i,sc.nextLine());
                }
                PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP,opciones);
                e.add_question(preg);
            }
            else if (tipo.toUpperCase().equals("PCNOM")) {
                System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                int n = sc.nextInt();
                sc.nextLine();
                System.out.println("Cuántas opciones se pueden seleccionar a la vez como máximo?");
                int max = sc.nextInt();
                sc.nextLine();
                ArrayList<String> opciones = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce la opción número "+ (i+1));
                    opciones.add(i,sc.nextLine());
                }
                PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP,opciones,max);
                e.add_question(preg);
            }
            else if (tipo.toUpperCase().equals("PN")) {
                System.out.println("Cuál es el valor mínimo que admite ésta pregunta?");
                int min = sc.nextInt();
                sc.nextLine();
                System.out.println("Cuál es el valor máximo que admite ésta pregunta?");
                int max = sc.nextInt();
                sc.nextLine();
                PregNumerica preg = null;
                preg = new PregNumerica(tituloP,min,max);

                e.add_question(preg);
            }
            else if (tipo.toUpperCase().equals("PRL")) {
                PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                e.add_question(preg);
            }
            else {
                System.out.println("Tipo de pregunta inválido.");
            }
            System.out.println("Quieres añadir una nueva pregunta? [sí/no]");
            r = sc.nextLine();
        }
        cjt.addEncuesta(e);
        contDatos.guardarEncuesta(e);
        e.leer();
    }

    public void importarEncuesta (String path) {
        Encuesta e = null;
        try {
            e = Encuesta.importar(path);
        } catch (ExcFormatoIncorrecto excFormatoIncorrecto) {
            String linea = excFormatoIncorrecto.getMessage();
            System.out.println("Error en la importación de la encuesta "+path+". Error en la linea "+linea+".");
        }
        contDatos.guardarEncuesta(e);
    }

    public String[] listaEncuestas (String criterio) {
        return cjt.getTitulosEncuestas(criterio);
    }

    public String[] listaEncuestasPalabras (String palabras) {
        return  cjt.getTitulosEncuestasPalabras(palabras);
    }

    public String[] listaEncuestaFecha (String f1, String f2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha1 = sdf.parse(f1);
            Date fecha2 = sdf.parse(f2);
            fecha2.setHours(23);
            fecha2.setMinutes(59);
            fecha2.setSeconds(59);
            return cjt.getTitulosEncuestasFecha(fecha1, fecha2);
        }
        catch (ParseException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public void modificarEncuesta(String tituloE) {
        Encuesta e = cjt.getEncuesta(tituloE);
        System.out.println("Contenido actual de la encuesta:");
        e.leer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Qué parte de la encuesta quieres modificar? [título, pregunta, nada]");
        String parte = sc.nextLine();
        while (!parte.equals("nada")) {
            if (parte.equals("título")) {
                System.out.println("Introduce el nuevo título:");
                String viejoTitulo = e.getTitulo();
                String nuevoTitulo = sc.nextLine();
                e.setTitulo(nuevoTitulo);
                cjt.cambiarTitulo(nuevoTitulo, viejoTitulo);
            } else if (parte.equals("pregunta")) {
                System.out.println("Introduce la acción a realizar [modificar, añadir, borrar]");
                String accion = sc.nextLine();
                if (accion.equals("modificar")) {
                    System.out.println("Introduce el número de la pregunta que quieres cambiar:");
                    int index = sc.nextInt();
                    index -= 1;
                    sc.nextLine();
                    Pregunta p = e.getPregunta(index);
                    String tipo = p.tipo();
                    System.out.println("Introduce el nuevo contenido:");
                    System.out.println("Introduce el título de la pregunta");
                    String tituloP = sc.nextLine();
                    if (tipo.equals("PCO")) {
                        System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                        int n = sc.nextInt();
                        sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Introduce la opción número " + (i + 1));
                            opciones.add(i, sc.nextLine());
                        }
                        PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP, opciones);
                        e.add_question(index, preg);
                    } else if (tipo.equals("PCNOU")) {
                        System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                        int n = sc.nextInt();
                        sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Introduce la opción número " + (i + 1));
                            opciones.add(i, sc.nextLine());
                        }
                        PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP, opciones);
                        e.add_question(index, preg);
                    } else if (tipo.equals("PCNOM")) {
                        System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                        int n = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Cuántas opciones se pueden seleccionar a la vez como máximo?");
                        int max = sc.nextInt();
                        sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Introduce la opción número " + (i + 1));
                            opciones.add(i, sc.nextLine());
                        }
                        PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP, opciones, max);
                        e.add_question(index, preg);
                    } else if (tipo.equals("PN")) {
                        System.out.println("Cuál es el valor mínimo que admite ésta pregunta?");
                        int min = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Cuál es el valor máximo que admite ésta pregunta?");
                        int max = sc.nextInt();
                        sc.nextLine();
                        PregNumerica preg = null;
                            preg = new PregNumerica(tituloP, min, max);

                        e.add_question(index, preg);
                    } else if (tipo.equals("PRL")) {
                        PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                        e.add_question(index, preg);
                    }
                }
                else if (accion.equals("borrar")) {
                    System.out.println("Introduce el número de la pregunta que quieres borrar:");
                    int index = sc.nextInt();
                    index -= 1;
                    sc.nextLine();
                    e.delete_question(index);
                }
                else if (accion.equals("añadir")) {
                    System.out.println("Introduce el título de la pregunta");
                    String tituloP = sc.nextLine();
                    System.out.println("Introduce el tipo de pregunta [PCO, PCNOU, PCNOM, PN, PRL]");
                    String tipo = sc.nextLine();
                    if (tipo.equals("PCO")) {
                        System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                        int n = sc.nextInt();
                        sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Introduce la opción número " + (i + 1));
                            opciones.add(i, sc.nextLine());
                        }
                        PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP, opciones);
                        e.add_question(preg);
                    } else if (tipo.equals("PCNOU")) {
                        System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                        int n = sc.nextInt();
                        sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Introduce la opción número " + (i + 1));
                            opciones.add(i, sc.nextLine());
                        }
                        PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP, opciones);
                        e.add_question(preg);
                    } else if (tipo.equals("PCNOM")) {
                        System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                        int n = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Cuántas opciones se pueden seleccionar a la vez como máximo?");
                        int max = sc.nextInt();
                        sc.nextLine();
                        ArrayList<String> opciones = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Introduce la opción número " + (i + 1));
                            opciones.add(i, sc.nextLine());
                        }
                        PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP, opciones, max);
                        e.add_question(preg);
                    } else if (tipo.equals("PN")) {
                        System.out.println("Cuál es el valor mínimo que admite ésta pregunta?");
                        int min = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Cuál es el valor máximo que admite ésta pregunta?");
                        int max = sc.nextInt();
                        sc.nextLine();
                        PregNumerica preg = null;
                            preg = new PregNumerica(tituloP, min, max);

                        e.add_question(preg);
                    } else if (tipo.equals("PRL")) {
                        PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                        e.add_question(preg);
                    }
                }
            }
            System.out.println("Quieres modificar algo más? [título, pregunta, nada]");
            parte = sc.nextLine();
        }

        e.leer();
        contDatos.actualizarEncuesta(tituloE, e);
    }

    public void borrarEncuesta (String titulo) {
        cjt.borrarEncuesta(titulo);
        contDatos.borrarEncuesta(titulo);
    }


    public void responderEncuesta(String tituloE) {
        Encuesta e = cjt.getEncuesta(tituloE);

        ArrayList<Respuesta> resp = e.responderEncuesta(u.getUsername());
        RespuestasEncuesta re = new RespuestasEncuesta(e, u.getUsername(), resp);

        e.responder(re);
        System.out.println("Guardando encuesta...");
        contDatos.guardarRespuestasEncuesta(re);
        System.out.println("Guardada.");
    }

    public void verRespuestasEncuesta(String tituloE) {
        Encuesta e = cjt.getEncuesta(tituloE);
        e.printarRespuestasDeEncuesta();
    }


    public void borrarRespuestasEncuesta (String titulo) {
        cjt.borrarRespuesta(titulo, u.getUsername());
        contDatos.borrarRespuestasEncuesta(titulo + "_" + u.getUsername());
    }
}
