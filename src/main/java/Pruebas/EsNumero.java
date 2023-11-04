package Pruebas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EsNumero extends JFrame {
    private JTextField textField;
    private JButton button;

    public EsNumero() {
        setTitle("Verificar si es numérico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        textField = new JTextField(20);
        button = new JButton("Verificar");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                if (isNumeric(input)) {
                    JOptionPane.showMessageDialog(EsNumero.this, "El valor es numérico.");
                } else {
                    JOptionPane.showMessageDialog(EsNumero.this, "El valor no es numérico.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(button);

        add(panel);
    }

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EsNumero().setVisible(true);
            }
        });
    }
}