package com.presentacio;


import com.domini.ExcEncuestaExistente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VistaRespInteractiva {
    private ControladorPresentacio ctrlPres;
    private ArrayList<ArrayList<String>> enc;
    private JFrame frame = new JFrame("Responder encuestas");

    private JPanel panelList;
    private JLabel userLabel;
    private JList listaPreguntas;
    private JButton nuevaPreguntaButton;
    private JButton borrarPreguntaButton;
    private JButton modificarPreguntaButton;
    private JPanel panelBotones;
    private JPanel panel1;
    private JPanel panelPreg;
    private JPanel PregNum;
    private JPanel PregCual;
    private JList listaOpciones;
    private DefaultListModel<String> modelPregs;
    private DefaultListModel<String> modelEnc;
    private JButton guardarPreguntaButton;
    private JSpinner spinner1;
    private JTextField optField;
    private JButton guardarEncuestaButton;
    private JTextArea textArea1;

    private boolean esModificado;
    private int idxMod;

    /**
     * Constructora vista de respuesta interactiva
     *
     * @param ctrlPres Controlador de presentacion
     */
    public VistaRespInteractiva(ControladorPresentacio ctrlPres, ArrayList<ArrayList<String>> enc) {
        this.ctrlPres = ctrlPres;
        this.enc = enc;
        asignarListeners();
        listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PregNum.setVisible(false);
        panelPreg.setVisible(false);
        PregNum.setVisible(false);
        PregCual.setVisible(false);
        modelEnc = new DefaultListModel<>();
        modelPregs = new DefaultListModel<>();
        listaPreguntas.setModel(modelEnc);
        listaOpciones.setModel(modelPregs);

        spinner1.setValue(2);

        esModificado = false;
        idxMod = -1;
    }

    private void panelVisibility(int idx) {
        if (idx == 0) {
            PregNum.setVisible(true);
            PregCual.setVisible(false);
            guardarPreguntaButton.setEnabled(true);
        } else if (idx == 1) {
            PregNum.setVisible(false);
            PregCual.setVisible(false);
            guardarPreguntaButton.setEnabled(true);
        } else if (idx == 2) {
            PregNum.setVisible(false);
            PregCual.setVisible(true);
            if (modelPregs.size() == 0) guardarPreguntaButton.setEnabled(false);
            else guardarPreguntaButton.setEnabled(true);
        } else if (idx == 3) {
            PregNum.setVisible(false);
            PregCual.setVisible(true);
            if (modelPregs.size() == 0) guardarPreguntaButton.setEnabled(false);
            else guardarPreguntaButton.setEnabled(true);
        } else if (idx == 4) {
            PregNum.setVisible(false);
            PregCual.setVisible(true);
            if (modelPregs.size() == 0) guardarPreguntaButton.setEnabled(false);
            else guardarPreguntaButton.setEnabled(true);
        }
    }

    /**
     * Método para asignar los listeners de la vista.
     */
    private void asignarListeners() {
        nuevaPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPreg.setVisible(true);
                esModificado = false;
            }
        });


        modificarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        borrarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        guardarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        guardarEncuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        listaPreguntas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modificarPreguntaButton.setEnabled(true);
                borrarPreguntaButton.setEnabled(true);
            }
        });

        listaOpciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //...
            }
        });

        listaOpciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modificarPreguntaButton.setEnabled(true);
                borrarPreguntaButton.setEnabled(true);
            }
        });


    }

    /**
     * Método que lanza un popup para borrar encuesta o no
     *
     * @return identificador de borrado de encuesta: 0 = Sí; 1 = No
     */
    private int AvisoBorrarPregunta() {
        JOptionPane optionPane = new JOptionPane("Quieres borrar esta pregunta?", 3);
        String[] strBotones = {"Sí", "No"};
        optionPane.setOptions(strBotones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(), "AVISO");
        dialogOptionPane.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);

        // Captura la opcion elegida
        String vsel = (String) optionPane.getValue();
        int isel;
        for (isel = 0; isel < strBotones.length && !strBotones[isel].equals(vsel); isel++) ;
        return isel;
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
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setMinimumSize(new Dimension(965, 300));
        panel1.setPreferredSize(new Dimension(965, 300));
        panelBotones = new JPanel();
        panelBotones.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelBotones, gbc);
        nuevaPreguntaButton = new JButton();
        nuevaPreguntaButton.setText("Responder pregunta");
        panelBotones.add(nuevaPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        borrarPreguntaButton = new JButton();
        borrarPreguntaButton.setEnabled(false);
        borrarPreguntaButton.setText("Borrar respuesta");
        panelBotones.add(borrarPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        modificarPreguntaButton = new JButton();
        modificarPreguntaButton.setEnabled(false);
        modificarPreguntaButton.setText("Modificar respuesta");
        panelBotones.add(modificarPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guardarEncuestaButton = new JButton();
        guardarEncuestaButton.setEnabled(false);
        guardarEncuestaButton.setText("Guardar respuestas de encuesta");
        panelBotones.add(guardarEncuestaButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelList = new JPanel();
        panelList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 6, new Insets(5, 5, 5, 5), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(panelList, gbc);
        userLabel = new JLabel();
        userLabel.setText("Responder encuesta");
        panelList.add(userLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelList.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 2, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listaPreguntas = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        listaPreguntas.setModel(defaultListModel1);
        scrollPane1.setViewportView(listaPreguntas);
        final JLabel label1 = new JLabel();
        label1.setText("<Título pregunta>");
        panelList.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelPreg = new JPanel();
        panelPreg.setLayout(new GridBagLayout());
        panelPreg.setEnabled(true);
        panelPreg.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelPreg, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPreg.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer7, gbc);
        guardarPreguntaButton = new JButton();
        guardarPreguntaButton.setEnabled(false);
        guardarPreguntaButton.setText("Guardar pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPreg.add(guardarPreguntaButton, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer8, gbc);
        textArea1 = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelPreg.add(textArea1, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer9, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Escribe tu respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelPreg.add(label2, gbc);
        PregNum = new JPanel();
        PregNum.setLayout(new GridBagLayout());
        PregNum.setEnabled(true);
        PregNum.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(PregNum, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregNum.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer12, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Valores mínimo y máximo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PregNum.add(label3, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer14, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Resultado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        PregNum.add(label4, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer15, gbc);
        spinner1 = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregNum.add(spinner1, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("[0 .. 10]");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        PregNum.add(label5, gbc);
        final JButton button1 = new JButton();
        button1.setEnabled(false);
        button1.setText("Guardar pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregNum.add(button1, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer16, gbc);
        PregCual = new JPanel();
        PregCual.setLayout(new GridBagLayout());
        PregCual.setEnabled(true);
        PregCual.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(PregCual, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer17, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer18, gbc);
        optField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregCual.add(optField, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer19, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer20, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer21, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Opciones de respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PregCual.add(label6, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setPreferredSize(new Dimension(190, 64));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        PregCual.add(scrollPane2, gbc);
        listaOpciones = new JList();
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        listaOpciones.setModel(defaultListModel2);
        scrollPane2.setViewportView(listaOpciones);
        final JLabel label7 = new JLabel();
        label7.setText("# maximo opciones:  <2>");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        PregCual.add(label7, gbc);
        final JButton button2 = new JButton();
        button2.setEnabled(false);
        button2.setText("Guardar pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregCual.add(button2, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer22, gbc);
        final JPanel spacer23 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer23, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
