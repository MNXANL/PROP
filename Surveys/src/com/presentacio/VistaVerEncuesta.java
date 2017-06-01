package com.presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by aleixballetbo on 31/5/17.
 */
public class VistaVerEncuesta {
    private ControladorPresentacio ctrlPres;
    private ArrayList<ArrayList<String>> pregs;

    private JFrame frame = new JFrame("Ver encuestas");

    private JPanel panelList;
    private JLabel tituloEnc;
    private JPanel panelBotones;
    private JPanel panel1;
    private JPanel panelRespLibre;
    private JPanel panelRespNum;
    private JPanel panelRespCual;
    private JList listaPreguntas;
    private JList listaOpciones;
    private JButton verPreguntaButton;
    private JButton cerrarButton;
    private DefaultListModel<String> modelOpts;
    private DefaultListModel<String> modelEnc;
    private JLabel minMax;
    private JLabel maxOpts;
    private JLabel contPRL;
    private JLabel contPN;
    private JLabel contPC;
    private JLabel tipoPC;

    /**
     * Constructora vista ver encuesta
     *
     * @param ctrlPres Controlador de presentacion
     * @param enc      Titulo de la encuesta
     * @param pregs    Preguntas de la encuesta
     */
    public VistaVerEncuesta(ControladorPresentacio ctrlPres, String enc, ArrayList<ArrayList<String>> pregs) {
        this.ctrlPres = ctrlPres;
        this.pregs = pregs;
        asignarListeners();
        listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelRespNum.setVisible(false);
        panelRespLibre.setVisible(false);
        panelRespNum.setVisible(false);
        panelRespCual.setVisible(false);

        modelEnc = new DefaultListModel<>();
        modelOpts = new DefaultListModel<>();
        listaPreguntas.setModel(modelEnc);
        listaOpciones.setModel(modelOpts);

        cerrarButton.setEnabled(true);
        tituloEnc.setText(enc);
        for (ArrayList<String> p : pregs) modelEnc.addElement(p.get(1));
    }

    /**
     * Método para asignar los listeners de la vista.
     */
    private void asignarListeners() {
        verPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = listaPreguntas.getSelectedIndex();
                ArrayList<String> preg = pregs.get(i);
                if (preg.get(0).equals("PRL")) {
                    panelRespLibre.setVisible(true);
                    panelRespNum.setVisible(false);
                    panelRespCual.setVisible(false);
                    contPRL.setText(preg.get(1));
                } else if (preg.get(0).equals("PN")) {
                    panelRespNum.setVisible(true);
                    panelRespLibre.setVisible(false);
                    panelRespCual.setVisible(false);
                    contPN.setText(preg.get(1));
                    minMax.setText(preg.get(2) + " .. " + preg.get(3));
                } else if (preg.get(0).equals("PCO") || preg.get(0).equals("PCNOU")) {
                    panelRespCual.setVisible(true);
                    panelRespLibre.setVisible(false);
                    panelRespNum.setVisible(false);
                    contPC.setText(preg.get(1));
                    if (preg.get(0).equals("PCO")) tipoPC.setText("Cualitativa ordenada");
                    else tipoPC.setText("Cualitativa no ordenada única");
                    modelOpts = new DefaultListModel<>();
                    for (int j = 2; j < preg.size(); j++) {
                        modelOpts.addElement(preg.get(j));
                    }
                    listaOpciones.setModel(modelOpts);
                    listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    maxOpts.setText("# máximo de opciones: 1");
                } else if (preg.get(0).equals("PCNOM")) {
                    panelRespCual.setVisible(true);
                    panelRespNum.setVisible(false);
                    panelRespLibre.setVisible(false);
                    contPC.setText(preg.get(1));
                    tipoPC.setText("Cualitativa no ordenada múltiple");
                    modelOpts = new DefaultListModel<>();
                    for (int j = 3; j < preg.size(); j++) {
                        modelOpts.addElement(preg.get(j));
                    }
                    listaOpciones.setModel(modelOpts);
                    listaOpciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    maxOpts.setText("# máximo de opciones: " + preg.get(2));
                }
            }
        });

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });


        listaPreguntas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listaPreguntas.isSelectionEmpty()) {
                    verPreguntaButton.setEnabled(true);
                } else {
                    verPreguntaButton.setEnabled(false);
                }
            }
        });

    }

    /**
     * Método para visualizar la vista
     */
    public void show() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Método para cerrar la vista
     */
    public void close() {
        frame.setVisible(false);
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
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setMinimumSize(new Dimension(965, 300));
        panel1.setPreferredSize(new Dimension(965, 300));
        panelBotones = new JPanel();
        panelBotones.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelBotones, gbc);
        verPreguntaButton = new JButton();
        verPreguntaButton.setEnabled(false);
        verPreguntaButton.setText("Ver pregunta");
        panelBotones.add(verPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        cerrarButton = new JButton();
        cerrarButton.setEnabled(true);
        cerrarButton.setText("Cerrar");
        panelBotones.add(cerrarButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelList = new JPanel();
        panelList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 6, new Insets(5, 5, 5, 5), -1, -1));
        panelList.setMinimumSize(new Dimension(414, 153));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(panelList, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        panelList.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listaPreguntas = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        listaPreguntas.setModel(defaultListModel1);
        scrollPane1.setViewportView(listaPreguntas);
        panelRespLibre = new JPanel();
        panelRespLibre.setLayout(new GridBagLayout());
        panelRespLibre.setEnabled(true);
        panelRespLibre.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespLibre, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(spacer7, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Pregunta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespLibre.add(label1, gbc);
        contPRL = new JLabel();
        contPRL.setText("Contenido de la pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespLibre.add(contPRL, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Tipo de pregunta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespLibre.add(label2, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer8, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Respuesta libre");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespLibre.add(label3, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer9, gbc);
        panelRespNum = new JPanel();
        panelRespNum.setLayout(new GridBagLayout());
        panelRespNum.setEnabled(true);
        panelRespNum.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespNum, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer12, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Valores mínimo y máximo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label4, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer13, gbc);
        minMax = new JLabel();
        minMax.setText("[0 .. 10]");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        panelRespNum.add(minMax, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spacer14, gbc);
        contPN = new JLabel();
        contPN.setText("Contenido de la pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(contPN, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer15, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Pregunta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label5, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Tipo de pregunta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label6, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer16, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Numérica");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label7, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer17, gbc);
        panelRespCual = new JPanel();
        panelRespCual.setLayout(new GridBagLayout());
        panelRespCual.setEnabled(true);
        panelRespCual.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespCual, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer18, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer19, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer20, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer21, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("Opciones de respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(label8, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setPreferredSize(new Dimension(190, 64));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelRespCual.add(scrollPane2, gbc);
        listaOpciones = new JList();
        listaOpciones.setMaximumSize(new Dimension(200, 1000));
        listaOpciones.setMinimumSize(new Dimension(200, 200));
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        listaOpciones.setModel(defaultListModel2);
        scrollPane2.setViewportView(listaOpciones);
        maxOpts = new JLabel();
        maxOpts.setText("# maximo opciones:  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(maxOpts, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespCual.add(spacer22, gbc);
        contPC = new JLabel();
        contPC.setText("Contenido de la pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(contPC, gbc);
        final JPanel spacer23 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer23, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Pregunta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(label9, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("Tipo de pregunta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(label10, gbc);
        final JPanel spacer24 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer24, gbc);
        tipoPC = new JLabel();
        tipoPC.setText("Tipo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(tipoPC, gbc);
        final JPanel spacer25 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer25, gbc);
        final JPanel spacer26 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer26, gbc);
        tituloEnc = new JLabel();
        tituloEnc.setText("Titulo encuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.NORTH;
        panel1.add(tituloEnc, gbc);
        final JPanel spacer27 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer27, gbc);
    }

    /**
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
