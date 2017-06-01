package com.presentacio;

import com.domini.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.EventObject;
import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * Clase de la vista del clustering
 */
public class VistaClustering {
    private JFrame frame;
    private JPanel CPanel;
    private JButton recalc;
    private JTabbedPane tabs;
    private JScrollPane scrollable1, scrollable2, scrollable3;
    private JTable clusterTable, centroidTable, rTable;
    private ControladorPresentacio cp;

    /**
     * Constructor de la vista de clustering
     *
     * @param cp        Referencia al controlador de presentacion
     * @param clusts    Diccionario de clusters de la encuesta
     * @param centroids Lista de los centroides de la encuesta
     * @param name      Nombre de la respuesta
     * @param resps     Lista de las respuestas a la encuesta
     */
    public VistaClustering(ControladorPresentacio cp, HashMap<Integer, List<String>> clusts, ArrayList<RespuestasEncuesta> centroids,
                           String name, ArrayList<RespuestasEncuesta> resps) {
        frame = new JFrame("Encuesta '" + name + "'");
        Initialize(clusts, centroids, resps);
        this.cp = cp;
        recalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cp.Clusters(name);
                frame.setVisible(false);
            }
        });
    }

    /**
     * Metodo para inicializar el clustering
     *
     * @param clusts    Diccionario de clusters de la encuesta
     * @param centroids Lista de los centroides de la encuesta
     * @param resps     Lista de las respuestas a la encuesta
     */
    public void Initialize(HashMap<Integer, List<String>> clusts, ArrayList<RespuestasEncuesta> centroids, ArrayList<RespuestasEncuesta> resps) {
        ini_clTable(clusts);
        ini_ceTable(centroids);
        ini_rTable(resps);
        scrollable1 = new JScrollPane(clusterTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollable2 = new JScrollPane(centroidTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollable3 = new JScrollPane(rTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //clusterTable.setFillsViewportHeight(true);
        clusterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        centroidTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        rTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tabs.addTab("Clusters", scrollable1);
        tabs.addTab("Centroides", scrollable2);
        tabs.addTab("Respuestas", scrollable3);
        // frame.add(CPanel);
        CPanel.setPreferredSize(clusterTable.getSize());
        CPanel.setSize(clusterTable.getSize());


        frame.setContentPane(CPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 600);
        frame.setVisible(true);
        frame.setVisible(true);


    }

    /**
     * Metodo para inicializar la pestanya de clusters
     *
     * @param clusts Diccionario de los clusters de la encuesta
     */
    public void ini_clTable(HashMap<Integer, List<String>> clusts) {
        int maxAssig = 0;
        for (Map.Entry<Integer, List<String>> entry : clusts.entrySet()) {
            if (entry.getValue().size() > maxAssig)
                maxAssig = entry.getValue().size();
        }
        String[] columnNames = new String[clusts.size()];
        Object[][] data = new Object[maxAssig][clusts.size()];
        for (Integer i = 0; i != clusts.size(); ++i) {
            columnNames[i] = "CLUSTER " + (i + 1);
        }

        for (Map.Entry<Integer, List<String>> entry : clusts.entrySet()) {
            for (String name : entry.getValue()) {
                int i = 0;
                while (data[i][entry.getKey()] != null) ++i;
                data[i][entry.getKey()] = name;
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        clusterTable = new JTable(tableModel) {
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                Object value = getValueAt(row, column);
                return value == null ? null : value.toString();
            }
        };
        for (Object[] os : data) {
            tableModel.addRow(os);
        }
        TableColumnModel tcm = clusterTable.getColumnModel();
        for (int i = 0; i != clusts.size(); ++i) {
            tcm.getColumn(i).setPreferredWidth(100);
        }
    }

    /**
     * Metodo para inicializar la pestanya de centroides
     *
     * @param centroids Lista de los centroides de la encuesta
     */
    public void ini_ceTable(ArrayList<RespuestasEncuesta> centroids) {
        int nResps = centroids.get(0).getResps().size();
        String[] columnNames = new String[centroids.size()];
        Object[][] data = new Object[nResps][centroids.size()];
        for (Integer i = 0; i != centroids.size(); ++i) {
            columnNames[i] = "Centroid " + (i + 1);
        }

        for (int i = 0; i != centroids.size(); ++i) {
            ArrayList<Respuesta> rs = centroids.get(i).getResps();
            for (int j = 0; j != rs.size(); ++j) {
                Respuesta r = rs.get(j);
                data[j][i] = rs.get(j);
                if (r instanceof RespNumerica) {
                    data[j][i] = ((RespNumerica) r).get();
                }
                if (r instanceof RespCualitativaNoOrdenadaUnica) {
                    data[j][i] = ((RespCualitativaNoOrdenadaUnica) r).getText();
                }
                if (r instanceof RespCualitativaOrdenada) {
                    data[j][i] = ((RespCualitativaOrdenada) r).getText();
                }
                if (r instanceof RespCualitativaNoOrdenadaMultiple) {
                    data[j][i] = ((RespCualitativaNoOrdenadaMultiple) r).getMap().values();
                }
                if (r instanceof RespLibre) {
                    data[j][i] = ((RespLibre) r).get();
                }
                if (r instanceof RespVacia) {
                    data[j][i] = "No contestada";
                }

            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        centroidTable = new JTable(tableModel) {
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                Object value = getValueAt(row, column);
                return value == null ? null : value.toString();
            }
        };
        for (Object[] os : data) {
            tableModel.addRow(os);
        }
        TableColumnModel tcm = centroidTable.getColumnModel();
        for (int i = 0; i != centroids.size(); ++i) {
            tcm.getColumn(i).setPreferredWidth(100);
        }
    }

    /**
     * Metodo para inicializar la pestanya de respuestas
     *
     * @param resps Lista de respuestas de la encuesta
     */
    public void ini_rTable(ArrayList<RespuestasEncuesta> resps) {
        int nResps = resps.get(0).getResps().size();
        String[] columnNames = new String[nResps + 1];
        Object[][] data = new Object[resps.size()][nResps + 1];
        columnNames[0] = "";
        for (Integer i = 0; i != nResps; ++i) {
            columnNames[i + 1] = "Pregunta " + (i + 1);
        }
        for (int i = 0; i != resps.size(); ++i) {
            data[i][0] = resps.get(i).getUser();
        }
        for (int i = 0; i != resps.size(); ++i) {
            ArrayList<Respuesta> rs = resps.get(i).getResps();
            for (int j = 0; j != nResps; ++j) {
                Respuesta r = rs.get(j);
                if (r instanceof RespNumerica) {
                    data[i][j + 1] = ((RespNumerica) r).get();
                }
                if (r instanceof RespCualitativaNoOrdenadaUnica) {
                    data[i][j + 1] = ((RespCualitativaNoOrdenadaUnica) r).getText();
                }
                if (r instanceof RespCualitativaOrdenada) {
                    data[i][j + 1] = ((RespCualitativaOrdenada) r).getText();
                }
                if (r instanceof RespCualitativaNoOrdenadaMultiple) {
                    data[i][j + 1] = ((RespCualitativaNoOrdenadaMultiple) r).getMap().values();
                }
                if (r instanceof RespLibre) {
                    data[i][j + 1] = ((RespLibre) r).get();
                }
                if (r instanceof RespVacia) {
                    data[i][j + 1] = "No contestada";
                }

            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        rTable = new JTable(tableModel) {
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                Object value = getValueAt(row, column);
                return value == null ? null : value.toString();
            }
        };
        for (Object[] os : data) {
            tableModel.addRow(os);
        }
        TableColumnModel tcm = rTable.getColumnModel();
        for (int i = 0; i != nResps + 1; ++i) {
            tcm.getColumn(i).setPreferredWidth(100);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     */
    private void $$$setupUI$$$() {
        CPanel = new JPanel();
        CPanel.setLayout(new BorderLayout(0, 0));
        CPanel.setPreferredSize(new Dimension(200, 200));
        recalc = new JButton();
        recalc.setLabel("Seleccionar otra K");
        recalc.setText("Seleccionar otra K");
        CPanel.add(recalc, BorderLayout.SOUTH);
        tabs = new JTabbedPane();
        CPanel.add(tabs, BorderLayout.CENTER);
    }

    /**
     */
    public JComponent $$$getRootComponent$$$() {
        return CPanel;
    }
}
