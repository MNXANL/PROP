package com.presentacio;


import com.domini.ExcEncuestaExistente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VistaCrearEncuesta {
    private ControladorPresentacio ctrlPres;
    private JFrame frame = new JFrame("Creadora de encuesta");

    private JPanel panelList;
    private JLabel userLabel;
    private JTextField tituloEnc;
    private JList list1;
    private JButton nuevaPreguntaButton;
    private JButton borrarPreguntaButton;
    private JButton modificarPreguntaButton;
    private JPanel panelBotones;
    private JPanel panel1;
    private JTextField preguntaField;
    private JComboBox comboBox1;
    private JPanel panelPreg;
    private JPanel PregNum;
    private JPanel PregCual;
    private JList listOption;
    private DefaultListModel<String> modelPregs;
    private DefaultListModel<String> modelEnc;
    private JButton guardarPreguntaButton;
    private JButton plusButton;
    private JButton minusButton;
    private JSpinner spinner1;
    private JPanel buttonPanel;
    private JPanel PCNOMpanel;
    private JTextField optField;
    private JSpinner minSpinner;
    private JSpinner maxSpinner;
    private JButton guardarEncuestaButton;
    private ArrayList<ArrayList<String>> PreguntasGuardadas;

    private boolean esModificado;
    private boolean modEnc;
    private int idxMod;

    /**
     * Constructora vista de creación de encuesta
     *
     * @param ctrlPres Controlador de presentacion
     */
    public VistaCrearEncuesta(ControladorPresentacio ctrlPres) {
        this.ctrlPres = ctrlPres;
        asignarListeners();
        String tipoResp[] = {"Libre", "Numérica", "Cualitativa ordenada", "Cualitativa no ordenada unica", "Cualitativa no ordenada multiple"};
        for (String tr : tipoResp) {
            comboBox1.addItem(tr);
        }
        listOption.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PregNum.setVisible(false);
        panelPreg.setVisible(false);
        PregNum.setVisible(false);
        PregCual.setVisible(false);
        PCNOMpanel.setVisible(false);
        modelEnc = new DefaultListModel<>();
        modelPregs = new DefaultListModel<>();
        list1.setModel(modelEnc);
        listOption.setModel(modelPregs);

        minSpinner.setValue(0);
        maxSpinner.setValue(10);
        spinner1.setValue(2);
        minusButton.setEnabled(false);

        PreguntasGuardadas = new ArrayList<ArrayList<String>>();
        esModificado = false;
        idxMod = -1;
        modEnc = false;
    }

    /**
     * Constructora vista de creación de encuesta
     *
     * @param ctrlPres Controlador de presentacion
     */
    public VistaCrearEncuesta(ControladorPresentacio ctrlPres, String titulo, ArrayList<ArrayList<String>> pregs) {
        this.ctrlPres = ctrlPres;
        asignarListeners();
        String tipoResp[] = {"Libre", "Numérica", "Cualitativa ordenada", "Cualitativa no ordenada unica", "Cualitativa no ordenada multiple"};
        for (String tr : tipoResp) {
            comboBox1.addItem(tr);
        }
        listOption.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PregNum.setVisible(false);
        panelPreg.setVisible(false);
        PregNum.setVisible(false);
        PregCual.setVisible(false);
        PCNOMpanel.setVisible(false);
        modelEnc = new DefaultListModel<>();
        modelPregs = new DefaultListModel<>();
        list1.setModel(modelEnc);
        listOption.setModel(modelPregs);

        minSpinner.setValue(0);
        maxSpinner.setValue(10);
        spinner1.setValue(2);
        minusButton.setEnabled(false);
        esModificado = false;
        idxMod = -1;

        PreguntasGuardadas = pregs;
        tituloEnc.setText(titulo);
        for (ArrayList<String> p : pregs) modelEnc.addElement(p.get(1)); //Añadir titulos al modelo
        frame.setTitle("Modificadora de encuesta");
        modEnc = true;
    }

    private void panelVisibility(int idx) {
        if (idx == 0) {
            PregNum.setVisible(true);
            PregCual.setVisible(false);
            PCNOMpanel.setVisible(false);
            guardarPreguntaButton.setEnabled(true);
        } else if (idx == 1) {
            PregNum.setVisible(false);
            PregCual.setVisible(false);
            PCNOMpanel.setVisible(false);
            guardarPreguntaButton.setEnabled(true);
        } else if (idx == 2) {
            PregNum.setVisible(false);
            PregCual.setVisible(true);
            PCNOMpanel.setVisible(false);
            if (modelPregs.size() == 0) guardarPreguntaButton.setEnabled(false);
            else guardarPreguntaButton.setEnabled(true);
        } else if (idx == 3) {
            PregNum.setVisible(false);
            PregCual.setVisible(true);
            PCNOMpanel.setVisible(false);
            if (modelPregs.size() == 0) guardarPreguntaButton.setEnabled(false);
            else guardarPreguntaButton.setEnabled(true);
        } else if (idx == 4) {
            PregNum.setVisible(false);
            PregCual.setVisible(true);
            PCNOMpanel.setVisible(true);
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
                idxMod = list1.getSelectedIndex();
                ArrayList<String> PregMod = PreguntasGuardadas.get(idxMod);
                if (PregMod.get(0).equals("PN")) {
                    panelVisibility(0);
                    preguntaField.setText(PregMod.get(1));
                    minSpinner.setValue(Integer.parseInt(PregMod.get(2)));
                    maxSpinner.setValue(Integer.parseInt(PregMod.get(3)));
                    comboBox1.setSelectedIndex(1);
                } else if (PregMod.get(0).equals("PRL")) {
                    panelVisibility(1);
                    preguntaField.setText(PregMod.get(1));
                    comboBox1.setSelectedIndex(0);
                } else if (PregMod.get(0).equals("PCO")) {
                    panelVisibility(2);
                    preguntaField.setText(PregMod.get(1));
                    modelPregs.clear();
                    for (int i = 2; i < PregMod.size(); i++) {
                        modelPregs.addElement(PregMod.get(i));
                    }
                    comboBox1.setSelectedIndex(2);
                } else if (PregMod.get(0).equals("PCNOU")) {
                    panelVisibility(3);
                    preguntaField.setText(PregMod.get(1));
                    modelPregs.clear();
                    for (int i = 2; i < PregMod.size(); i++) {
                        modelPregs.addElement(PregMod.get(i));
                    }
                    comboBox1.setSelectedIndex(3);
                } else if (PregMod.get(0).equals("PCNOM")) {
                    panelVisibility(4);
                    preguntaField.setText(PregMod.get(1));
                    spinner1.setValue(Integer.parseInt(PregMod.get(2)));
                    modelPregs.clear();
                    for (int i = 3; i < PregMod.size(); i++) {
                        modelPregs.addElement(PregMod.get(i));
                    }
                    comboBox1.setSelectedIndex(4);
                }
                panelPreg.setVisible(true);
                esModificado = true;
            }
        });


        borrarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrando: " + list1.getSelectedValue().toString());
                if (AvisoBorrarPregunta() == 0) {
                    PreguntasGuardadas.remove(list1.getSelectedIndex());
                    modelEnc.remove(list1.getSelectedIndex());
                }
            }
        });

        guardarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!preguntaField.getText().equals("")) {
                    String NomPreg = preguntaField.getText();
                    ArrayList<String> preg = new ArrayList<>();
                    preguntaField.setText("");
                    if (comboBox1.getSelectedItem().toString().equals("Numérica")) {
                        if (Integer.parseInt(minSpinner.getValue().toString()) <= Integer.parseInt(maxSpinner.getValue().toString())) {
                            preg.add("PN");
                            preg.add(NomPreg);
                            preg.add(minSpinner.getValue().toString()); //Min
                            preg.add(maxSpinner.getValue().toString()); //Max
                        } else System.out.println("ERROR: min < max");
                    } else if (comboBox1.getSelectedItem().toString().equals("Libre")) {
                        preg.add("PRL");
                        preg.add(NomPreg);
                    } else if (comboBox1.getSelectedItem().toString().equals("Cualitativa ordenada")) {
                        preg.add("PCO");
                        preg.add(NomPreg);
                        for (int i = 0; i != modelPregs.size(); ++i) {
                            preg.add(modelPregs.getElementAt(i));
                        }
                    } else if (comboBox1.getSelectedItem().toString().equals("Cualitativa no ordenada unica")) {
                        preg.add("PCNOU");
                        preg.add(NomPreg);
                        for (int i = 0; i != modelPregs.size(); ++i) {
                            preg.add(modelPregs.getElementAt(i));
                        }
                    } else if (comboBox1.getSelectedItem().toString().equals("Cualitativa no ordenada multiple")) {
                        if (Integer.parseInt(spinner1.getValue().toString()) > 1) {
                            preg.add("PCNOM");
                            preg.add(NomPreg);
                            preg.add(spinner1.getValue().toString());
                            for (int i = 0; i != modelPregs.size(); ++i) {
                                preg.add(modelPregs.getElementAt(i));
                            }
                        } else System.out.println("ERROR: at least 2 options");
                    }
                    if (!guardarEncuestaButton.isEnabled()) {
                        guardarEncuestaButton.setEnabled(true);
                        modificarPreguntaButton.setEnabled(true);
                        borrarPreguntaButton.setEnabled(true);
                    }
                    if (!modelPregs.isEmpty()) modelPregs.clear();
                    if (esModificado) {
                        esModificado = false;
                        PreguntasGuardadas.set(idxMod, preg);
                        modelEnc.set(idxMod, NomPreg);
                    } else {
                        modelEnc.addElement(NomPreg);
                        PreguntasGuardadas.add(preg);
                    }
                }
            }
        });

        guardarEncuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!modEnc) ctrlPres.actualizarEncuestaArgs(tituloEnc.getText(), PreguntasGuardadas);
                    else ctrlPres.crearEncuestaArgs(tituloEnc.getText(), PreguntasGuardadas);
                    close();
                } catch (ExcEncuestaExistente excEncuestaExistente) {
                    excEncuestaExistente.printStackTrace();
                }
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedItem().toString().equals("Numérica")) {
                    panelVisibility(0);
                } else if (comboBox1.getSelectedItem().toString().equals("Libre")) {
                    panelVisibility(1);
                } else if (comboBox1.getSelectedItem().toString().equals("Cualitativa ordenada")) {
                    panelVisibility(2);
                } else if (comboBox1.getSelectedItem().toString().equals("Cualitativa no ordenada unica")) {
                    panelVisibility(3);
                } else if (comboBox1.getSelectedItem().toString().equals("Cualitativa no ordenada multiple")) {
                    panelVisibility(4);
                }
            }
        });

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!optField.getText().equals("")) {
                    modelPregs.addElement(optField.getText());
                    optField.setText("");
                }
                if (!guardarPreguntaButton.isEnabled()) guardarPreguntaButton.setEnabled(true);
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listOption.getSelectedIndex() >= 0 || listOption.getSelectedIndex() < modelPregs.size()) {
                    modelPregs.remove(listOption.getSelectedIndex());
                    listOption.setModel(modelPregs);
                }
                minusButton.setEnabled(false);
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modificarPreguntaButton.setEnabled(true);
                borrarPreguntaButton.setEnabled(true);
            }
        });

        listOption.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                minusButton.setEnabled(true);
            }
        });

        listOption.addListSelectionListener(new ListSelectionListener() {
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


    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        nuevaPreguntaButton.setText("Nueva pregunta");
        panelBotones.add(nuevaPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        borrarPreguntaButton = new JButton();
        borrarPreguntaButton.setEnabled(false);
        borrarPreguntaButton.setText("Borrar pregunta");
        panelBotones.add(borrarPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panelBotones.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        modificarPreguntaButton = new JButton();
        modificarPreguntaButton.setEnabled(false);
        modificarPreguntaButton.setText("Modificar pregunta");
        panelBotones.add(modificarPreguntaButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guardarEncuestaButton = new JButton();
        guardarEncuestaButton.setEnabled(false);
        guardarEncuestaButton.setText("Guardar encuesta");
        panelBotones.add(guardarEncuestaButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelList = new JPanel();
        panelList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 6, new Insets(5, 5, 5, 5), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(panelList, gbc);
        userLabel = new JLabel();
        userLabel.setText("Creadora de encuestas");
        panelList.add(userLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tituloEnc = new JTextField();
        panelList.add(tituloEnc, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, 24), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelList.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list1 = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        list1.setModel(defaultListModel1);
        scrollPane1.setViewportView(list1);
        final JLabel label1 = new JLabel();
        label1.setText("Título");
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
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPreg.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer7, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Nombre pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelPreg.add(label2, gbc);
        preguntaField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPreg.add(preguntaField, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer9, gbc);
        comboBox1 = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPreg.add(comboBox1, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer10, gbc);
        guardarPreguntaButton = new JButton();
        guardarPreguntaButton.setEnabled(false);
        guardarPreguntaButton.setText("Guardar pregunta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPreg.add(guardarPreguntaButton, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelPreg.add(spacer12, gbc);
        PregNum = new JPanel();
        PregNum.setLayout(new GridBagLayout());
        PregNum.setEnabled(true);
        PregNum.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(PregNum, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregNum.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer14, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer15, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Valor mínimo");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PregNum.add(label3, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer16, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer17, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Valor máximo");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        PregNum.add(label4, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregNum.add(spacer18, gbc);
        maxSpinner = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregNum.add(maxSpinner, gbc);
        minSpinner = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregNum.add(minSpinner, gbc);
        PregCual = new JPanel();
        PregCual.setLayout(new GridBagLayout());
        PregCual.setEnabled(true);
        PregCual.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(PregCual, gbc);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.BOTH;
        PregCual.add(buttonPanel, gbc);
        plusButton = new JButton();
        plusButton.setText("+");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(plusButton, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer19, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        buttonPanel.add(spacer20, gbc);
        minusButton = new JButton();
        minusButton.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(minusButton, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer21, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer22, gbc);
        final JPanel spacer23 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        buttonPanel.add(spacer23, gbc);
        PCNOMpanel = new JPanel();
        PCNOMpanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.BOTH;
        PregCual.add(PCNOMpanel, gbc);
        spinner1 = new JSpinner();
        spinner1.setDebugGraphicsOptions(5);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PCNOMpanel.add(spinner1, gbc);
        final JPanel spacer24 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PCNOMpanel.add(spacer24, gbc);
        final JPanel spacer25 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        PCNOMpanel.add(spacer25, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("# maximo opciones:  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        PCNOMpanel.add(label5, gbc);
        final JPanel spacer26 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer26, gbc);
        final JPanel spacer27 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer27, gbc);
        optField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PregCual.add(optField, gbc);
        final JPanel spacer28 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer28, gbc);
        final JPanel spacer29 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer29, gbc);
        final JPanel spacer30 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        PregCual.add(spacer30, gbc);
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
        listOption = new JList();
        scrollPane2.setViewportView(listOption);
        final JPanel spacer31 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer31, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
