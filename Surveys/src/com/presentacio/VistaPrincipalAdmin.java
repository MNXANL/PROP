package com.presentacio;

import com.domini.ExcFormatoIncorrecto;
import com.domini.ExcUsuarioRespuestaIncorrecto;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;

/**
 * Created by aleixballetbo on 12/5/17.
 */
public class VistaPrincipalAdmin {
    private ControladorPresentacio ctrlPres;

    private JFrame frame = new JFrame("Vista Principal");
    private JPanel panel1;
    private JLabel userLabel;
    private JList list1;
    private JTextField palClave;
    private JTextField fechaIni;
    private JTextField fechaFin;
    private JButton buscarButton;
    private JButton nuevaEncuestaButton;
    private JButton borrarEncuestaButton;
    private JButton modificarEncuestaButton;
    private JButton clusteringButton;
    private JButton cerrarSesiónButton;
    private JButton exportarButton;
    private JButton importarRespuestasButton;
    private JComboBox comboBox1;

    /**
     * Constructora vista principal del programa
     *
     * @param ctrlPres Controlador de presentacion
     * @param user     Usuario
     */
    public VistaPrincipalAdmin(ControladorPresentacio ctrlPres, String user) {
        this.ctrlPres = ctrlPres;
        userLabel.setText("Bienvenido " + user);
        asignarListeners();
    }

    /**
     * Método para dar asignaciones y acciones a los listeners
     */
    public void asignarListeners() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.isEnabled())
                    ctrlPres.buscarEncuestas(comboBox1.getSelectedItem().toString());
                else if (palClave.isEnabled() && !palClave.getText().equals(""))
                    ctrlPres.buscarEncuestasPalabras(palClave.getText());
                else if (fechaIni.isEnabled() && fechaFin.isEnabled() && !fechaIni.getText().equals("") && !fechaFin.getText().equals("")) {
                    try {
                        if (fechaIni.getText().length() != 10 || fechaFin.getText().length() != 10) aviso("Formato de fechas incorrecto. El formato debe ser dd/mm/aaaa");
                        else ctrlPres.buscarEncuestaFecha(fechaIni.getText(), fechaFin.getText());
                    }
                    catch (ParseException e1) {
                        aviso("Formato de fechas incorrecto. El formato debe ser dd/mm/aaaa");
                    }
                }

            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list1.isSelectionEmpty())
                    NOseleccionadaEncuesta();
                else if (ctrlPres.encuestaRespondida(list1.getSelectedValue().toString())) {
                    seleccionadaEncuestaRespondida();
                }
                else {
                    seleccionadaEncuestaSinResponder();
                }
            }
        });

        nuevaEncuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPres.crearEncuesta();
            }
        });

        clusteringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPres.Clusters(list1.getSelectedValue().toString());
            }
        });

        modificarEncuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPres.modificarEncuesta(list1.getSelectedValue().toString());
            }
        });


        borrarEncuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrando: " + list1.getSelectedValue().toString());
                if (AvisoBorrarEncuesta() == 0) ctrlPres.borrarEncuesta(list1.getSelectedValue().toString());
            }
        });

        palClave.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                palabrasClaveChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                palabrasClaveChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                palabrasClaveChanged();
            }
        });

        fechaIni.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fechaChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fechaChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fechaChanged();
            }
        });

        fechaFin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fechaChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fechaChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fechaChanged();
            }
        });

        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPres.logOut();
            }
        });

        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona donde quieres guardar la encuesta");
                fileChooser.setSelectedFile(new File(list1.getSelectedValue().toString() + ".txt"));

                int userSelection = fileChooser.showSaveDialog(parentFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    ctrlPres.exportarEncuesta(list1.getSelectedValue().toString(), fileToSave.getAbsolutePath());
                }
            }
        });

        importarRespuestasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarRespuesta();
            }
        });
    }

    /**
     * Método que lanza un popup para borrar encuesta o no
     *
     * @return identificador de borrado de encuesta: 0 = Sí; 1 = No
     */
    private int AvisoBorrarEncuesta() {
        JOptionPane optionPane = new JOptionPane("Quieres borrar esta encuesta?", 3);
        String[] strBotones = {"Sí", "No"};
        optionPane.setOptions(strBotones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(), "AVISO");
        dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);

        // Captura la opcion elegida
        String vsel = (String) optionPane.getValue();
        int isel;
        for (isel = 0; isel < strBotones.length && !strBotones[isel].equals(vsel); isel++) ;
        return isel;
    }

    /**
     * Metodo que modifica los paneles al cambiar las palabras clave
     */
    private void palabrasClaveChanged() {
        if (palClave.getText().equals("")) {
            comboBox1.setEnabled(true);
            fechaIni.setEnabled(true);
            fechaFin.setEnabled(true);
        } else {
            comboBox1.setEnabled(false);
            fechaIni.setEnabled(false);
            fechaFin.setEnabled(false);
        }
    }

    /**
     * Metodo que modifica los paneles al cambiar la fecha
     */
    private void fechaChanged() {
        if (fechaIni.getText().equals("") && fechaFin.getText().equals("")) {
            comboBox1.setEnabled(true);
            palClave.setEnabled(true);
        } else {
            comboBox1.setEnabled(false);
            palClave.setEnabled(false);
        }
    }

    /**
     * Método para visualizar la vista
     */
    public void show() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
     * Metodo que infla la lista de encuestas
     * @param lista Lista con los titulos de las encuestas a inflar
     */
    public void llenarLista(String[] lista) {

        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < lista.length; i++) {
            model.addElement(lista[i]);
        }
        list1.setModel(model);
    }

    /**
     * Metodo para configurar los botones cuando no se selecciona ninguna encuesta de la lista
     */
    public void NOseleccionadaEncuesta() {
        borrarEncuestaButton.setEnabled(false);
        modificarEncuestaButton.setEnabled(false);
        clusteringButton.setEnabled(false);
        exportarButton.setEnabled(false);
        importarRespuestasButton.setEnabled(false);
    }

    /**
     * Metodo para configurar los botones cuando se selecciona una encuesta sin responder
     */
    public void seleccionadaEncuestaSinResponder() {
        borrarEncuestaButton.setEnabled(true);
        modificarEncuestaButton.setEnabled(true);
        clusteringButton.setEnabled(false);
        exportarButton.setEnabled(true);
        importarRespuestasButton.setEnabled(true);
    }

    /**
     * Metodo para configurar los botones cuando se selecciona una encuesta respondida
     */
    public void seleccionadaEncuestaRespondida() {
        clusteringButton.setEnabled(true);
        borrarEncuestaButton.setEnabled(true);
        modificarEncuestaButton.setEnabled(false);
        exportarButton.setEnabled(true);
        importarRespuestasButton.setEnabled(true);
    }

    /**
     * Metodo para importar una respuesta al sistema
     */
    public void importarRespuesta() {
        JFrame parentFrame = new JFrame("Importar respuesta");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona la(s) respuesta(s) que quieres importar");
        FileFilter txt = new FileNameExtensionFilter("txt", "txt");
        fileChooser.setFileFilter(txt);
        fileChooser.setMultiSelectionEnabled(true);

        int userSelection = fileChooser.showOpenDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File[] filesToSave = fileChooser.getSelectedFiles();
            try {
                for (int i = 0; i < filesToSave.length; i++) {
                    String enc = list1.getSelectedValue().toString();
                    String path = filesToSave[i].getAbsolutePath();
                    ctrlPres.importarRespuestaEncuesta(enc, path);
                }
            } catch (ExcFormatoIncorrecto e1) {
                aviso(e1.getMessage());
            } catch (ExcUsuarioRespuestaIncorrecto e2) {

            }
        }
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 6, new Insets(5, 5, 5, 5), -1, -1));
        panel1.setMinimumSize(new Dimension(800, 600));
        panel1.setOpaque(true);
        panel1.setPreferredSize(new Dimension(1024, 768));
        userLabel = new JLabel();
        userLabel.setText("Hola usuario");
        panel1.add(userLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        palClave = new JTextField();
        panel1.add(palClave, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 24), null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("A-Z");
        defaultComboBoxModel1.addElement("Z-A");
        defaultComboBoxModel1.addElement("Nuevas");
        defaultComboBoxModel1.addElement("Antiguas");
        comboBox1.setModel(defaultComboBoxModel1);
        panel1.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        panel1.add(buscarButton, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Palabras clave");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Criterio");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fechaIni = new JTextField();
        panel1.add(fechaIni, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fechaFin = new JTextField();
        panel1.add(fechaFin, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Fecha Inicial");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Fecha Final");
        panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(11, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nuevaEncuestaButton = new JButton();
        nuevaEncuestaButton.setText("Nueva encuesta");
        panel2.add(nuevaEncuestaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        borrarEncuestaButton = new JButton();
        borrarEncuestaButton.setEnabled(false);
        borrarEncuestaButton.setText("Borrar encuesta");
        panel2.add(borrarEncuestaButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        clusteringButton = new JButton();
        clusteringButton.setEnabled(false);
        clusteringButton.setText("Clustering");
        panel2.add(clusteringButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modificarEncuestaButton = new JButton();
        modificarEncuestaButton.setEnabled(false);
        modificarEncuestaButton.setText("Modificar encuesta");
        panel2.add(modificarEncuestaButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exportarButton = new JButton();
        exportarButton.setEnabled(false);
        exportarButton.setText("Exportar encuesta");
        panel2.add(exportarButton, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        importarRespuestasButton = new JButton();
        importarRespuestasButton.setEnabled(false);
        importarRespuestasButton.setText("Importar respuestas");
        panel2.add(importarRespuestasButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cerrarSesiónButton = new JButton();
        cerrarSesiónButton.setText("Cerrar sesión");
        panel1.add(cerrarSesiónButton, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list1 = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        list1.setModel(defaultListModel1);
        scrollPane1.setViewportView(list1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
