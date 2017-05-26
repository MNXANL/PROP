package com.presentacio;

import com.domini.Encuesta;
import com.domini.ExcEncuestaExistente;
import com.domini.ExcFormatoIncorrecto;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
        Vector rowData = new Vector();
        for (int i = 0; i < 1; i++) {
            Vector colData = new Vector(Arrays.asList("qq"));
            rowData.add(colData);
        }

        String[] columnNames = {"a"};

        Vector columnNamesV = new Vector(Arrays.asList(columnNames));

        JTable table = new JTable(rowData, columnNamesV);
        JFrame f = new JFrame();
        f.setSize(300, 300);
        f.add(new JScrollPane(table));
        f.setVisible(true);
        //clusterTable=new JTable(2,clusts.size());
    }
}
