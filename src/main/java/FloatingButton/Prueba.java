package FloatingButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Prueba {
    public static void main(String[] args) {
        createAndShowGUI();
    }
    private static void createAndShowGUI() {
        // Crear una ventana
        JFrame frame = new JFrame("JLabel Auto Size Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Texto largo que queremos mostrar en el JLabel
        String textoLargo = "Recuerda que Java 8 y versiones posteriores xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx incluyen las clases del paquete java.time, que proporcionan una API más moderna y robusta para trabajar con fechas y tiempos. Si estás utilizando una versión anterior de Java, puedes usar la antigua clase.";

        // Crear el JLabel con el texto
        JLabel label = new JLabel(textoLargo);

        // Establecer el ajuste de texto para que el JLabel se adapte al contenido
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.TOP);

        // Establecer el tamaño preferido para el JLabel
        label.setPreferredSize(new Dimension(300, label.getPreferredSize().height));

        // Agregar el JLabel al JFrame
        frame.add(label);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}
