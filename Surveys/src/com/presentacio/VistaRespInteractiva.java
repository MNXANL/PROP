package com.presentacio;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * Clase de vista de respuesta interactiva
 */
public class VistaRespInteractiva {
    private ControladorPresentacio ctrlPres;
    private ArrayList<ArrayList<String>> pregs;
    private JFrame frame = new JFrame("Responder encuestas");

    private JPanel panelList;
    private JLabel tituloEnc;
    private JPanel panelBotones;
    private JPanel panel1;
    private JPanel panelRespLibre;
    private JPanel panelRespNum;
    private JPanel panelRespCual;
    private JList listaPreguntas;
    private JList listaOpciones;
    private JButton responderPreguntaButton;
    private JButton borrarRespuestaButton;
    private JButton modificarRespuestaButton;
    private JButton NSNCButtonRL;
    private JButton NSNCButtonRN;
    private JButton NSNCButtonRC;
    private JButton guardarRespuestaNumericaButton;
    private JButton guardarRespuestaCualitativaButton;
    private JButton guardarRespuestaLibreButton;
    private JButton guardarRespuestasButton;
    private DefaultListModel<String> modelOpts;
    private DefaultListModel<String> modelEnc;
    private JLabel minMax;
    private JLabel maxOpts;
    private JTextArea textArea1;
    private JSpinner spinner1;

    private ArrayList<ArrayList<String>> respuestas;

    /**
     * Constructora vista de respuesta interactiva
     * @param ctrlPres Controlador de presentacion
     * @param enc Titulo de la encuesta
     * @param pregs Preguntas de la encuesta
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
        modelOpts = new DefaultListModel<>();
        listaPreguntas.setModel(modelEnc);
        listaOpciones.setModel(modelOpts);

        respuestas = new ArrayList<>();
        for (int i = 0; i < pregs.size(); i++) {
            ArrayList<String> r = new ArrayList<>();
            r.add("RV");
            respuestas.add(r);
        }
        guardarRespuestasButton.setEnabled(true);
        tituloEnc.setText(enc);
        for (ArrayList<String> p : pregs) modelEnc.addElement(p.get(1));

        spinner1.setValue(0);

    }

    /**
     * Método para asignar los listeners de la vista.
     */
    private void asignarListeners() {
        responderPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPreguntas.setEnabled(false);
                responderPreguntaButton.setEnabled(false);
                int i = listaPreguntas.getSelectedIndex();
                ArrayList<String> preg = pregs.get(i);
                if (preg.get(0).equals("PRL")) {
                    panelRespLibre.setVisible(true);
                    guardarRespuestaLibreButton.setEnabled(true);
                    textArea1.setText("");
                } else if (preg.get(0).equals("PN")) {
                    panelRespNum.setVisible(true);
                    guardarRespuestaNumericaButton.setEnabled(true);
                    minMax.setText(preg.get(2) + " .. " + preg.get(3));
                    SpinnerModel sm = new SpinnerNumberModel(Double.parseDouble(preg.get(2)), Double.parseDouble(preg.get(2)), Double.parseDouble(preg.get(3)), 0.01);
                    spinner1.setModel(sm);
                } else if (preg.get(0).equals("PCO") || preg.get(0).equals("PCNOU")) {
                    panelRespCual.setVisible(true);
                    modelOpts = new DefaultListModel<>();
                    for (int j = 2; j < preg.size(); j++) {
                        modelOpts.addElement(preg.get(j));
                    }
                    listaOpciones.setModel(modelOpts);
                    listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    maxOpts.setText("# máximo de opciones: 1");
                } else if (preg.get(0).equals("PCNOM")) {
                    panelRespCual.setVisible(true);
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


        modificarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaPreguntas.setEnabled(false);
                responderPreguntaButton.setEnabled(false);
                int i = listaPreguntas.getSelectedIndex();
                ArrayList<String> preg = pregs.get(i);
                if (preg.get(0).equals("PRL")) {
                    panelRespLibre.setVisible(true);
                    guardarRespuestaLibreButton.setEnabled(true);
                    textArea1.setText(respuestas.get(i).get(1));
                } else if (preg.get(0).equals("PN")) {
                    panelRespNum.setVisible(true);
                    guardarRespuestaNumericaButton.setEnabled(true);
                    minMax.setText(preg.get(2) + " .. " + preg.get(3));
                    SpinnerModel sm = new SpinnerNumberModel(Double.parseDouble(preg.get(2)), Double.parseDouble(preg.get(2)), Double.parseDouble(preg.get(3)), 0.01);
                    spinner1.setModel(sm);
                    Double d = Double.parseDouble(respuestas.get(i).get(1));
                    spinner1.setValue(d); //yolo
                } else if (preg.get(0).equals("PCO")) {
                    panelRespCual.setVisible(true);
                    //guardarRespuestaCualitativaButton.setEnabled(true);
                    modelOpts = new DefaultListModel<>();
                    for (int j = 2; j < preg.size(); j++) {
                        modelOpts.addElement(preg.get(j));

                    }
                    listaOpciones.setModel(modelOpts);
                    listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listaOpciones.setSelectedIndex(modelOpts.indexOf(respuestas.get(i).get(3)));
                    maxOpts.setText("# máximo de opciones: 1");
                } else if (preg.get(0).equals("PCNOU")) {
                    panelRespCual.setVisible(true);
                    //guardarRespuestaCualitativaButton.setEnabled(true);
                    modelOpts = new DefaultListModel<>();
                    for (int j = 2; j < preg.size(); j++) {
                        modelOpts.addElement(preg.get(j));
                    }
                    listaOpciones.setModel(modelOpts);
                    listaOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listaOpciones.setSelectedIndex(modelOpts.indexOf(respuestas.get(i).get(2)));
                    maxOpts.setText("# máximo de opciones: 1");
                } else if (preg.get(0).equals("PCNOM")) {
                    panelRespCual.setVisible(true);
                    //guardarRespuestaCualitativaButton.setEnabled(true);
                    modelOpts = new DefaultListModel<>();
                    for (int j = 3; j < preg.size(); j++) {
                        modelOpts.addElement(preg.get(j));
                    }
                    listaOpciones.setModel(modelOpts);
                    listaOpciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    int[] opts = new int[respuestas.get(i).size() - 2];
                    for (int k = 2; k < respuestas.get(i).size(); k++) {
                        opts[k - 2] = modelOpts.indexOf(respuestas.get(i).get(k));
                    }
                    listaOpciones.setSelectedIndices(opts);
                    maxOpts.setText("# máximo de opciones: " + preg.get(2));
                }
            }
        });


        borrarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AvisoBorrarRespuesta() == 0) {
                    ArrayList<String> r = new ArrayList<>();
                    r.add("RV");
                    respuestas.set(listaPreguntas.getSelectedIndex(), r);
                    responderPreguntaButton.setEnabled(true);
                    modificarRespuestaButton.setEnabled(false);
                    borrarRespuestaButton.setEnabled(false);
                }
            }
        });

        guardarRespuestaLibreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                panelRespLibre.setVisible(false);
                ArrayList<String> resp = new ArrayList<>();
                resp.add("RL");
                resp.add(textArea1.getText());
                respuestas.remove(listaPreguntas.getSelectedIndex());
                respuestas.add(listaPreguntas.getSelectedIndex(), resp);
                listaPreguntas.setEnabled(true);
                responderPreguntaButton.setEnabled(false);
                modificarRespuestaButton.setEnabled(true);
                borrarRespuestaButton.setEnabled(true);
            }
        });

        NSNCButtonRL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                modificarRespuestaButton.setEnabled(false);
                borrarRespuestaButton.setEnabled(false);
                panelRespLibre.setVisible(false);
                ArrayList<String> resp = new ArrayList<>();
                resp.add("RV");
                respuestas.remove(listaPreguntas.getSelectedIndex());
                respuestas.add(listaPreguntas.getSelectedIndex(), resp);
                listaPreguntas.setEnabled(true);
            }
        });

        guardarRespuestaNumericaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(false);
                modificarRespuestaButton.setEnabled(true);
                borrarRespuestaButton.setEnabled(true);
                panelRespNum.setVisible(false);
                ArrayList<String> resp = new ArrayList<>();
                resp.add("RN");
                resp.add(spinner1.getValue().toString());
                resp.add(pregs.get(listaPreguntas.getSelectedIndex()).get(2));
                resp.add(pregs.get(listaPreguntas.getSelectedIndex()).get(3));
                respuestas.remove(listaPreguntas.getSelectedIndex());
                respuestas.add(listaPreguntas.getSelectedIndex(), resp);
                listaPreguntas.setEnabled(true);
            }
        });

        NSNCButtonRN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                modificarRespuestaButton.setEnabled(false);
                borrarRespuestaButton.setEnabled(false);
                panelRespNum.setVisible(false);
                ArrayList<String> resp = new ArrayList<>();
                resp.add("RV");
                respuestas.remove(listaPreguntas.getSelectedIndex());
                respuestas.add(listaPreguntas.getSelectedIndex(), resp);
                listaPreguntas.setEnabled(true);
            }
        });

        guardarRespuestaCualitativaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> resp = new ArrayList<>();
                if (pregs.get(listaPreguntas.getSelectedIndex()).get(0).equals("PCO")) {
                    resp.add("RCO");
                    resp.add(Integer.toString(listaOpciones.getSelectedIndex()));
                    resp.add(Integer.toString(listaOpciones.getModel().getSize()));
                    resp.add(listaOpciones.getSelectedValue().toString());
                } else if (pregs.get(listaPreguntas.getSelectedIndex()).get(0).equals("PCNOU")) {
                    resp.add("RCNOU");
                    resp.add(Integer.toString(listaOpciones.getSelectedIndex()));
                    resp.add(listaOpciones.getSelectedValue().toString());
                } else if (pregs.get(listaPreguntas.getSelectedIndex()).get(0).equals("PCNOM")) {
                    int index = listaPreguntas.getSelectedIndex();
                    ArrayList<String> preg = pregs.get(index);
                    if (listaOpciones.getSelectedValuesList().size() > Integer.parseInt(preg.get(2))) {
                        aviso("No se pueden seleccionar más de " + preg.get(2) + " respuestas.");
                        return;
                    }
                    resp.add("RCNOM");
                    for (int i = 0; i < listaOpciones.getSelectedValuesList().size(); i++) {
                        resp.add(Integer.toString(listaOpciones.getSelectedIndices()[i]));
                        resp.add(listaOpciones.getSelectedValuesList().get(i).toString());
                    }
                }
                responderPreguntaButton.setEnabled(false);
                modificarRespuestaButton.setEnabled(true);
                borrarRespuestaButton.setEnabled(true);
                panelRespCual.setVisible(false);

                respuestas.remove(listaPreguntas.getSelectedIndex());
                respuestas.add(listaPreguntas.getSelectedIndex(), resp);
                listaPreguntas.setEnabled(true);
            }
        });

        NSNCButtonRC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntaButton.setEnabled(true);
                modificarRespuestaButton.setEnabled(false);
                borrarRespuestaButton.setEnabled(false);
                panelRespCual.setVisible(false);
                ArrayList<String> resp = new ArrayList<>();
                resp.add("RV");
                respuestas.remove(listaPreguntas.getSelectedIndex());
                respuestas.add(listaPreguntas.getSelectedIndex(), resp);
                listaPreguntas.setEnabled(true);
            }
        });

        guardarRespuestasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPres.guardarRespEnc(tituloEnc.getText(), respuestas);
                close();
            }
        });


        listaPreguntas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listaPreguntas.isSelectionEmpty()) {
                    if (respuestas.get(listaPreguntas.getSelectedIndex()).get(0).equals("RV")) {
                        responderPreguntaButton.setEnabled(true);
                        modificarRespuestaButton.setEnabled(false);
                        borrarRespuestaButton.setEnabled(false);
                    } else {
                        responderPreguntaButton.setEnabled(false);
                        modificarRespuestaButton.setEnabled(true);
                        borrarRespuestaButton.setEnabled(true);
                    }
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
                if (listaOpciones.isSelectionEmpty()) {
                    guardarRespuestaCualitativaButton.setEnabled(false);
                } else {
                    guardarRespuestaCualitativaButton.setEnabled(true);
                }
            }
        });


    }

    /**
     * Método que lanza un popup para borrar encuesta o no
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

    /**
     * Aviso de la vista
     * @param mensaje Mensaje que sale en el aviso
     */
    public void aviso(String mensaje) {
        JOptionPane optionPane = new JOptionPane(mensaje, JOptionPane.ERROR_MESSAGE);
        String[] strBotones = {"Aceptar"};
        optionPane.setOptions(strBotones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(), "AVISO");
        dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);
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
        panelList.setMinimumSize(new Dimension(414, 153));
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
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer7, gbc);
        guardarRespuestaLibreButton = new JButton();
        guardarRespuestaLibreButton.setEnabled(false);
        guardarRespuestaLibreButton.setText("Guardar respuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(guardarRespuestaLibreButton, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespLibre.add(spacer9, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Escribe tu respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespLibre.add(label1, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(spacer10, gbc);
        textArea1 = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelRespLibre.add(textArea1, gbc);
        NSNCButtonRL = new JButton();
        NSNCButtonRL.setText("NS / NC");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespLibre.add(NSNCButtonRL, gbc);
        panelRespNum = new JPanel();
        panelRespNum.setLayout(new GridBagLayout());
        panelRespNum.setEnabled(true);
        panelRespNum.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespNum, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer12, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer13, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Valores mínimo y máximo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label2, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer14, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer15, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Resultado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespNum.add(label3, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer16, gbc);
        spinner1 = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spinner1, gbc);
        minMax = new JLabel();
        minMax.setText("[0 .. 10]");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelRespNum.add(minMax, gbc);
        guardarRespuestaNumericaButton = new JButton();
        guardarRespuestaNumericaButton.setEnabled(false);
        guardarRespuestaNumericaButton.setText("Guardar respuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(guardarRespuestaNumericaButton, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespNum.add(spacer17, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(spacer18, gbc);
        NSNCButtonRN = new JButton();
        NSNCButtonRN.setText("NS / NC");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespNum.add(NSNCButtonRN, gbc);
        panelRespCual = new JPanel();
        panelRespCual.setLayout(new GridBagLayout());
        panelRespCual.setEnabled(true);
        panelRespCual.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelRespCual, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer19, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer20, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer21, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer22, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Opciones de respuesta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(label4, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setPreferredSize(new Dimension(190, 64));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
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
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panelRespCual.add(maxOpts, gbc);
        guardarRespuestaCualitativaButton = new JButton();
        guardarRespuestaCualitativaButton.setEnabled(false);
        guardarRespuestaCualitativaButton.setText("Guardar respuesta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespCual.add(guardarRespuestaCualitativaButton, gbc);
        final JPanel spacer23 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelRespCual.add(spacer23, gbc);
        final JPanel spacer24 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespCual.add(spacer24, gbc);
        NSNCButtonRC = new JButton();
        NSNCButtonRC.setText("NS / NC");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRespCual.add(NSNCButtonRC, gbc);
        final JPanel spacer25 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer25, gbc);
        tituloEnc = new JLabel();
        tituloEnc.setText("Titulo encuesta");
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
