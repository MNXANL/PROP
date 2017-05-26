package com.presentacio;

import com.domini.Encuesta;
import com.domini.ExcEncuestaExistente;
import com.domini.ExcFormatoIncorrecto;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alejandro on 26/05/2017.
 */
public class VistaClustering {
    private JPanel CPanel;
    private JTable clusterTable;
    private JScrollPane scrollable;

    public VistaClustering(HashMap<Integer,List<String>> clusts){
        Initialize(clusts);
    }
    public void Initialize(HashMap<Integer,List<String>> clusts){

        clusterTable=new JTable(2,clusts.size());
    }
}
