package tests.domini.RespCualitativaNoOrdenadaMultiple;

import org.junit.Test;
import tests.domini.RespCualitativaNoOrdenadaMultiple.RespCualitativaNoOrdenadaMultiple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests unitarios PregRespuestaLibre
 */
public class RespCualitativaNoOrdenadaMultipleTest {
    @Test
    public void getMap () {
        HashMap<Integer,String> hm = new HashMap<Integer,String>();
        hm.put(1, "");
        RespCualitativaNoOrdenadaMultiple r = new RespCualitativaNoOrdenadaMultiple(hm);

        assertEquals(r.getMap(), hm);
    }

    @Test
    public void getResp() {
        HashMap<Integer,String> hm = new HashMap<Integer,String>();
        hm.put(1, "Resp");
        RespCualitativaNoOrdenadaMultiple r = new RespCualitativaNoOrdenadaMultiple(hm);
        ArrayList<String> t = new ArrayList<>(r.getMap().values());
        assertEquals("Resp", t.get(0));
    }

    @Test
    public void set1 () {

        HashMap<Integer,String> hm = new HashMap<Integer,String>();
        hm.put(1, "Resp");
        RespCualitativaNoOrdenadaMultiple r = new RespCualitativaNoOrdenadaMultiple(hm);

        HashMap<Integer,String> hm2 = new HashMap<Integer,String>();
        hm2.put(2, "Mod");
        r.set(hm2);
        ArrayList<String> t = new ArrayList<>(r.getMap().values());
        assertEquals("Mod", t.get(0));
    }



}
