package Pruebas;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JLabelDynamicSizeExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Crear una ventana
        JFrame frame = new JFrame("JTextArea Dynamic Size Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); // Tamaño fijo del JFrame
        frame.setLayout(new FlowLayout());

        // Crear el JTextField
        JTextField textField = new JTextField(20);
        frame.add(textField);

        // Crear el JButton
        JButton button = new JButton("Agregar al JTextArea");
        frame.add(button);

        // Crear el JTextArea con el ancho de 200 y sin altura inicial definida
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true); // Hacer que el texto se ajuste automáticamente en líneas
        textArea.setWrapStyleWord(true); // Hacer que el texto se ajuste respetando las palabras completas
        textArea.setEditable(false); // Deshabilitar edición del JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(200, 1)); // Tamaño fijo del JScrollPane, altura inicial de 1 píxel
        frame.add(scrollPane);

        // Agregar ActionListener al JButton para actualizar el JTextArea
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = textField.getText();

                if (texto.length() > 100) {
                    // Truncar el texto y agregar los tres puntos suspensivos "..." al final
                    texto = texto.substring(0, 100) + "...";
                }

                textArea.setText(texto); // Actualizar el contenido del JTextArea con el texto del JTextField
                textArea.setCaretPosition(0); // Hacer que el JTextArea siempre muestre el inicio del texto
                // Ajustar la altura del JTextArea según el contenido del texto
                int textAreaHeight = textArea.getPreferredSize().height + 10;

                System.out.println("HEIGH: "+textAreaHeight);

                scrollPane.setPreferredSize(new Dimension(200, textAreaHeight));
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                frame.pack(); // Ajustar el tamaño del JFrame para adaptarse al contenido del JTextArea
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }
}
