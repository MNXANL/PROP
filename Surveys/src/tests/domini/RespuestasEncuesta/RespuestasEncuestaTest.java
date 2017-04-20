package tests.domini.RespuestasEncuesta;

import com.domini.Encuesta;
import org.junit.Test;
import tests.domini.RespNumerica.RespNumerica;

import static org.junit.Assert.assertEquals;

/**
 * Unit test de la clase RespLibre
 */
public class RespuestasEncuestaTest {
    @Test
    public void getRespEnc () {
        Encuesta e = new Encuesta("Enc");
        RespuestasEncuesta re = new RespuestasEncuesta(e, "user");
        int t = re.getResps().size();
        assertEquals(0, t);
    }

    @Test
    public void getRespEnc2 () {
        Encuesta e = new Encuesta("Enc");
        RespuestasEncuesta re = new RespuestasEncuesta(e, "user");
        Encuesta e1 = re.getEncuesta();
        boolean b = e.equals(e1);
        assertEquals(true, b);
    }

    @Test
    public void getRespEnc3 () {
        Encuesta e = new Encuesta("Enc");
        RespuestasEncuesta re = new RespuestasEncuesta(e, "user");
        String t = re.getNombreFichero();
        assertEquals("Enc_user", t);
    }

    @Test
    public void setRespEnc () {
        Encuesta e = new Encuesta("Enc");
        RespuestasEncuesta re = new RespuestasEncuesta(e, "user");
        assertEquals("user", re.getUser());
    }
}