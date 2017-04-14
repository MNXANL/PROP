package com.domini;

import com.dades.ControladorDatos;

import java.util.*;

/**
 * Controlador de la capa de dominio
 */
public class ControladorDominio {
    private Encuesta e;
    private RespuestasEncuesta re;

    ControladorDatos contDatos;

    CjtEncuestas cjt;

    public ControladorDominio() {
        contDatos = new ControladorDatos();
        cjt = new CjtEncuestas(contDatos.cargar());
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
                PregNumerica preg = new PregNumerica(tituloP,min,max);
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
        Encuesta e = Encuesta.importar(path);
        contDatos.guardarEncuesta(e);
    }

    public String[] listaEncuestas (String criterio) {
        return cjt.getTitulosEncuestas(criterio);
    }

    public Set<String> listaEncuestasPorPalabras (String criterio) {
        String [] palabras = criterio.split("\\s+");
        return null;
    }

    public Set<String> listaEncuestasPorFecha (Date fecha1, Date fecha2) {
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
                        PregNumerica preg = new PregNumerica(tituloP, min, max);
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
                        PregNumerica preg = new PregNumerica(tituloP, min, max);
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

    //Por ahora, tal y como está hecho el controlador, responde a la ultima encuesta creada
    //No hace falta comentar que deberíamos cambiarlo
    public void responderEncuesta() {
        re = new RespuestasEncuesta(e, "John Doe"); //John Doe is a placeholder!
        re.ResponderPreguntasDeUnaEncuesta();

    }

    public void verRespuestasEncuesta() {
        re.printarRespuestas();
    }
}
