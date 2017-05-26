package com.presentacio;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VistaRespInteractiva {
    private ControladorPresentacio ctrlPres;
    private ArrayList<ArrayList<String>> pregs;
    private JFrame frame = new JFrame("Responder encuestas");

    private JPanel panelList;
    private JLabel tituloEnc;
    private JList listaPreguntas;
    private JButton responderPreguntaButton;
    private JButton borrarRespuestaButton;
    private JButton modificarRespuestaButton;
    private JPanel panelBotones;
    private JPanel panel1;
    private JPanel panelRespLibre;
    private JPanel panelRespNum;
    private JPanel panelRespCual;
    private JList listaOpciones;
    private DefaultListModel<String> modelPregs;
    private DefaultListModel<String> modelEnc;
    private JButton guardarRespuestaLibreButton;
    private JSpinner spinner1;
    private JButton guardarRespuestasButton;
    private JTextArea textArea1;
    private JButton guardarRespuestaNumericaButton;
    private JButton guardarRespuestaCualitativaButton;

    private boolean esModificado;
    private int idxMod;

    /**
     * Constructora vista de respuesta interactiva
     *
     * @param ctrlPres Controlador de presentacion
     */
    public VistaRespInteractiva(ControladorPresentacio ctrlPres, String enc, ArrayList<ArrayList<String>> pregs) {
        this.ctrlPres = ctrlPres;
        this.pregs = pregs;
        asignarListeners();
        listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelRespNum.setVisible(false);
        panelRespLibre.setVisible(false);
        panelRespNum.setVisible(false);
        panelRespCual.setVisible(false);
        modelEnc = new DefaultListModel<>();
        modelPregs = new DefaultListModel<>();
        listaPreguntas.setModel(modelEnc);
        listaOpciones.setModel(modelPregs);

        tituloEnc.setText(enc);
        for (ArrayList<String> p : pregs) modelEnc.addElement(p.get(1));

        spinner1.setValue(0);

        esModificado = false;
        idxMod = -1;
    }

    private void panelVisibility(int idx) {
        if (idx == 0) {
            panelRespNum.setVisible(true);
            panelRespCual.setVisible(false);
            guardarRespuestaLibreButton.setEnabled(true);
        } else if (idx == 1) {
            panelRespNum.setVisible(false);
            panelRespCual.setVisible(false);
            guardarRespuestaLibreButton.setEnabled(true);
        } else if (idx == 2) {
            panelRespNum.setVisible(false);
            panelRespCual.setVisible(true);
            if (modelPregs.size() == 0) guardarRespuestaLibreButton.setEnabled(false);
            else guardarRespuestaLibreButton.setEnabled(true);
        } else if (idx == 3) {
            panelRespNum.setVisible(false);
            panelRespCual.setVisible(true);
            if (modelPregs.size() == 0) guardarRespuestaLibreButton.setEnabled(false);
            else guardarRespuestaLibreButton.setEnabled(true);
        } else if (idx == 4) {
            panelRespNum.setVisible(false);
            panelRespCual.setVisible(true);
            if (modelPregs.size() == 0) guardarRespuestaLibreButton.setEnabled(false);
            else guardarRespuestaLibreButton.setEnabled(true);
        }
    }

    /**
     * Método para asignar los listeners de la vista.
     */
    private void asignarListeners() {
        responderPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(false);
                int i = listaPreguntas.getSelectedIndex();
                ArrayList<String> preg = pregs.get(i);
                if (preg.get(0).equals("PRL")) {
                    panelRespLibre.setVisible(true);
                    guardarRespuestaLibreButton.setEnabled(true);
                } else if (preg.get(0).equals("PN")) {
                    panelRespNum.setVisible(true);
                    guardarRespuestaNumericaButton.setEnabled(true);
                } else if (preg.get(0).equals("PCO")) {
                    panelRespCual.setVisible(true);
                    guardarRespuestaCualitativaButton.setEnabled(true);
                } else if (preg.get(0).equals("PCNOU")) {
                    panelRespCual.setVisible(true);
                    guardarRespuestaCualitativaButton.setEnabled(true);
                } else if (preg.get(0).equals("PCNOM")) {
                    panelRespCual.setVisible(true);
                    guardarRespuestaCualitativaButton.setEnabled(true);
                }
            }
        });


        modificarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        borrarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        guardarRespuestaLibreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                panelRespLibre.setVisible(false);
            }
        });

        guardarRespuestaNumericaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                panelRespNum.setVisible(false);
            }
        });

        guardarRespuestaCualitativaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                panelRespCual.setVisible(false);
            }
        });

        guardarRespuestasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        listaPreguntas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listaPreguntas.isSelectionEmpty()) {
                    responderPreguntaButton.setEnabled(true);
                    modificarRespuestaButton.setEnabled(true);
                    borrarRespuestaButton.setEnabled(true);
                } else {
                    responderPreguntaButton.setEnabled(false);
                    modificarRespuestaButton.setEnabled(false);
                    borrarRespuestaButton.setEnabled(false);
                }
            }
        });

        listaOpciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //...
            }
        });


    }

    /**
     * Método que lanza un popup para borrar encuesta o no
     *
     * @return identificador de borrado de encuesta: 0 = Sí; 1 = No
     */
    private int AvisoBorrarRespuesta() {
        JOptionPane optionPane = new JOptionPane("Quieres borrar la respuesta a esta pregunta?", 3);
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
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelBotones, gbc);
        responderPreguntaButton = new JButton();
        responderPreguntaButton.setText("Responder pregunta");
        panelBotones.add(responderPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        borrarRespuestaButton = new JButton();
        borrarRespuestaButton.setEnabled(false);
        borrarRespuestaButton.setText("Borrar respuesta");
        panelBotones.add(borrarRespuestaButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        modificarRespuestaButton = new JButton();
        modificarRespuestaButton.setEnabled(false);
        modificarRespuestaButton.setText("Modificar respuesta");
        panelBotones.add(modificarRespuestaButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guardarRespuestasButton = new JButton();
        guardarRespuestasButton.setEnabled(false);
        guardarRespuestasButton.setText("Guardar respuestas");
        panelBotones.add(guardarRespuestasButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelList = new JPanel();
        panelList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 6, new Insets(5, 5, 5, 5), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
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
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespLibre, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer7, gbc);
        guardarRespuestaLibreButton = new JButton();
        guardarRespuestaLibreButton.setEnabled(false);
        guardarRespuestaLibreButton.setText("Guardar respuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(guardarRespuestaLibreButton, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer8, gbc);
        textArea1 = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelRespLibre.add(textArea1, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer9, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Escribe tu respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespLibre.add(label1, gbc);
        panelRespNum = new JPanel();
        panelRespNum.setLayout(new GridBagLayout());
        panelRespNum.setEnabled(true);
        panelRespNum.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespNum, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer12, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Valores mínimo y máximo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label2, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer14, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Resultado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label3, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer15, gbc);
        spinner1 = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spinner1, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("[0 .. 10]");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRespNum.add(label4, gbc);
        guardarRespuestaNumericaButton = new JButton();
        guardarRespuestaNumericaButton.setEnabled(false);
        guardarRespuestaNumericaButton.setText("Guardar respuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(guardarRespuestaNumericaButton, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer16, gbc);
        panelRespCual = new JPanel();
        panelRespCual.setLayout(new GridBagLayout());
        panelRespCual.setEnabled(true);
        panelRespCual.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespCual, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer17, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer18, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer19, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer20, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Opciones de respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(label5, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setPreferredSize(new Dimension(190, 64));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panelRespCual.add(scrollPane2, gbc);
        listaOpciones = new JList();
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        listaOpciones.setModel(defaultListModel2);
        scrollPane2.setViewportView(listaOpciones);
        final JLabel label6 = new JLabel();
        label6.setText("# maximo opciones:  <2>");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(label6, gbc);
        guardarRespuestaCualitativaButton = new JButton();
        guardarRespuestaCualitativaButton.setEnabled(false);
        guardarRespuestaCualitativaButton.setText("Guardar respuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespCual.add(guardarRespuestaCualitativaButton, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer21, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer22, gbc);
        tituloEnc = new JLabel();
        tituloEnc.setText("Título encuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.NORTH;
        panel1.add(tituloEnc, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
