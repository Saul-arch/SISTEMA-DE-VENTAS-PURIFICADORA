package Pruebas;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDisplayExample extends JFrame {

    public ImageDisplayExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Image Display Example");

        // Cargar la imagen desde un archivo
        ImageIcon imageIcon = new ImageIcon("Imagenes/logo.png");
        Image originalImage = imageIcon.getImage();

        // Crear una imagen de alta resolución para mostrar en el componente
        int width = originalImage.getWidth(null) * 2; // Duplicar el ancho
        int height = originalImage.getHeight(null) * 2; // Duplicar la altura
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Crear un componente de etiqueta para mostrar la imagen
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        // Agregar el componente de etiqueta al marco principal
        getContentPane().add(imageLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageDisplayExample example = new ImageDisplayExample();
            example.setVisible(true);
        });
    }
}