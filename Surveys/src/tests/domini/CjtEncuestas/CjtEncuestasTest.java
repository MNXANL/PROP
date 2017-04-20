package tests.domini.CjtEncuestas;

import com.domini.Encuesta;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit tests de CjtEncuestas
 */
public class CjtEncuestasTest {
    @Test
    public void addEnquesta () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("título");
        cjt.addEncuesta(e);
        assertEquals("título", cjt.getEncuesta("título").getTitulo());
    }

    @Test
    public void getTitulosEncuestasAZ () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("A");
        Encuesta e1 = new Encuesta("B");
        Encuesta e2 = new Encuesta("C");
        cjt.addEncuesta(e);
        cjt.addEncuesta(e1);
        cjt.addEncuesta(e2);
        String[] t = {"A","B","C"};
        assertEquals(t,cjt.getTitulosEncuestas("A-Z"));
    }

    @Test
    public void getTitulosEncuestasZA () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("A");
        Encuesta e1 = new Encuesta("B");
        Encuesta e2 = new Encuesta("C");
        cjt.addEncuesta(e);
        cjt.addEncuesta(e1);
        cjt.addEncuesta(e2);
        String[] t = {"C","B","A"};
        assertEquals(t,cjt.getTitulosEncuestas("Z-A"));
    }

    @Test
    public void getTitulosEncuestasNuevas () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("A");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Encuesta enc = new Encuesta("B");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Encuesta encu = new Encuesta("C");
        cjt.addEncuesta(e);
        cjt.addEncuesta(enc);
        cjt.addEncuesta(encu);
        String[] t = {"C","B","A"};
        assertEquals(t,cjt.getTitulosEncuestas("nuevas"));
    }

    @Test
    public void getTitulosEncuestasAntiguas () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("A");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Encuesta enc = new Encuesta("B");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Encuesta encu = new Encuesta("C");
        cjt.addEncuesta(e);
        cjt.addEncuesta(enc);
        cjt.addEncuesta(encu);
        String[] t = {"A","B","C"};
        assertEquals(t,cjt.getTitulosEncuestas("antiguas"));
    }

    @Test
    public void getTitulosEncuestasFecha () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("A");
        Encuesta e1 = new Encuesta("B");
        Encuesta e2 = new Encuesta("C");
        cjt.addEncuesta(e);
        cjt.addEncuesta(e1);
        cjt.addEncuesta(e2);
        String[] t = {"A","B","C"};
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha1 = null;
        Date fecha2 = null;
        try {
            fecha1 = sdf.parse("01/01/2017");
            fecha2 = sdf.parse("31/12/2017");
        } catch (ParseException e3) {
            e3.printStackTrace();
        }
        assertEquals(t,cjt.getTitulosEncuestasFecha(fecha1,fecha2));
    }

    @Test
    public void getTitulosEncuestaPalabras () {
        CjtEncuestas cjt = new CjtEncuestas();
        Encuesta e = new Encuesta("A");
        Encuesta e1 = new Encuesta("B");
        Encuesta e2 = new Encuesta("C");
        cjt.addEncuesta(e);
        cjt.addEncuesta(e1);
        cjt.addEncuesta(e2);
        String[] t = {"A"};
        assertEquals(t,cjt.getTitulosEncuestasPalabras("A"));
    }
}
