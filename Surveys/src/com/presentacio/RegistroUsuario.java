package com.presentacio;

import com.domini.ExcUsuarioExistente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase de vista de registro de usuario
 */
public class RegistroUsuario {
    private ControladorPresentacio ctrlPres;

    private JFrame frame = new JFrame("Registro usuario");
    private JPanel panel1;
    private JTextField nombreTextField;
    private JPasswordField pass1TextField;
    private JPasswordField pass2TextField;
    private JButton crearUsuarioButton;

    /**
     * Constructora de clase
     * @param ctrlPres Referencia al controlador de presentacion
     */
    public RegistroUsuario(ControladorPresentacio ctrlPres) {
        this.ctrlPres = ctrlPres;
        asignarListeners();
    }

    /**
     * Método para asignar los listeners de la vista.
     */
    public void asignarListeners() {
        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!nombreTextField.getText().equals("") && !pass1TextField.getText().equals("") && !pass2TextField.getText().equals("") && pass1TextField.getText().equals(pass2TextField.getText())) {
                        ctrlPres.registrarUsuario(nombreTextField.getText(), pass1TextField.getText());
                    } else if (!pass1TextField.getText().equals(pass2TextField.getText())) {
                        lanzarAviso("Las contraseñas introducidas no son iguales.");
                    }
                }
                catch (ExcUsuarioExistente exc) {
                    lanzarAviso(exc.getMessage());
                }
            }
        });
    }

    /**
     * Aviso de la vista
     * @param contenido Mensaje que sale en el aviso
     */
    private void lanzarAviso(String contenido) {
        JOptionPane optionPane = new JOptionPane(contenido, JOptionPane.ERROR_MESSAGE);
        String[] opciones = {"Aceptar"};
        optionPane.setOptions(opciones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(), "AVISO");
        dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(5, 5, 5, 5), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Nombre de usuario");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Contraseña");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Repite la contraseña");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nombreTextField = new JTextField();
        panel1.add(nombreTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pass1TextField = new JPasswordField();
        panel1.add(pass1TextField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pass2TextField = new JPasswordField();
        panel1.add(pass2TextField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        crearUsuarioButton = new JButton();
        crearUsuarioButton.setText("Crear usuario");
        panel1.add(crearUsuarioButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
