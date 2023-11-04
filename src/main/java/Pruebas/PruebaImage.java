package Pruebas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.geometry.size.FloatSize;
import com.github.weisj.jsvg.parser.SVGLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;


public class PruebaImage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Floating Action Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Agregar otros componentes a la interfaz.
        JLabel label = new JLabel("Este es un ejemplo con un botón flotante.");
        frame.add(label, BorderLayout.NORTH);

        JButton otherButton = new JButton("Otro botón");
        frame.add(otherButton, BorderLayout.CENTER);

        File rutaPlus = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\plus.svg");
        FlatSVGIcon imgAgregarNota = new FlatSVGIcon(rutaPlus);

///////////////////////////////////////////////////////////////////////////////////
        JButton floatingButton = new JButton();
        floatingButton.setSize(50, 50);
        floatingButton.setLocation(150,620);
        floatingButton.setIcon(imgAgregarNota);

        floatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes realizar alguna acción cuando se haga clic en el botón flotante.
                JOptionPane.showMessageDialog(null, "¡Botón flotante presionado!");
            }
        });

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        layeredPane.add(floatingButton, BorderLayout.CENTER);
        layeredPane.setLayer(floatingButton, JLayeredPane.POPUP_LAYER); // Capa superior

        ///////////////////////////////////////////////////////////////////////////////////////////7

        frame.add(layeredPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}