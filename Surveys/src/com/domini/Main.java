package com.domini;
import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Encuesta e = new Encuesta();
        e.importar("src/com/domini/Encuesta.txt");
        e.leer();
    }
}
