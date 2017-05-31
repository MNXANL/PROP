package com.domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.presentacio.*;

import javax.swing.*;

/**
 * Clase main del programa
 */
public class Main {

    /**
     * Metodo main del programa
     * @param args argumentos
     */
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
    }
}
