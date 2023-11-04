package Pruebas;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JButton openButton = new JButton("Abrir");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(MainFrame.this, "Ventana Modal", Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setSize(300, 300);
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(MainFrame.this);

                JButton closeButton = new JButton("Cerrar");
                closeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose(); // Cierra el JDialog
                    }
                });
                dialog.add(closeButton);

                dialog.setVisible(true);
            }
        });
        add(openButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}