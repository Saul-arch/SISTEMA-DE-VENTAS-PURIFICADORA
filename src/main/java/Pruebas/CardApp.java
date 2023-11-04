package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CardApp {

    public static void main(String[] args) {
        // Crear una ventana
        JFrame frame = new JFrame("JLabel Multiline Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setBackground(Color.cyan);

        // Texto largo en una sola línea
        String textoLargo = "Recuerda que Java 8 y versiones posteriores incluyen las clases del paquete java.time, que proporcionan una API más moderna y robusta para trabajar con fechas y tiempos. Si estás utilizando una versión anterior de Java, puedes usar la antigua clase.";

        // Crear el JLabel con el texto largo utilizando HTML para el formato
        JLabel label = new JLabel("<html><div style='width: 300px;'>" + textoLargo + "</div></html>");
        label.setBackground(Color.YELLOW);
        // Agregar el JLabel al JFrame
        frame.add(label);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}