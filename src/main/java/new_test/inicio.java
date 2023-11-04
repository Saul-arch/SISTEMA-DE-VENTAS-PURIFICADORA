package new_test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class inicio {

    public static JTextArea textArea = new JTextArea();
    public static JScrollPane scrollPane = new JScrollPane(textArea);
    public static List<String> listaNombres = new ArrayList<>();
    public static JPanel contentPanel = new JPanel();
    public static JScrollPane tblTablaActivity = new JScrollPane();
    public static JLabel agregarReciente = new JLabel();

    public static void main(String[] args) {

        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();

        // Definir el formato deseado para día en texto y mes en texto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM");

        // Formatear la fecha en el formato deseado
        String formattedDate = currentDate.format(formatter);

        // Imprimir la fecha formateada
        System.out.println("Fecha actual en formato día y mes: " + formattedDate);

        JFrame frmVentana = new JFrame();
        JPanel pnlActiRecientes = new JPanel();

        contentPanel.setSize(800, 440);

        tblTablaActivity.setSize(800, 440);
        tblTablaActivity.setLocation(0,35);
        tblTablaActivity.setBorder(new EmptyBorder(0, 0, 0, 0));
        tblTablaActivity.setBackground(Color.decode("#eceff1"));
        tblTablaActivity.setViewportView(contentPanel);


        textArea.setLineWrap(true); // Hacer que el texto se ajuste automáticamente en líneas
        textArea.setWrapStyleWord(true); // Hacer que el texto se ajuste respetando las palabras completas
        textArea.setEditable(false); // Deshabilitar edición del JTextArea
        scrollPane.setPreferredSize(new Dimension(200, 1)); // Tamaño fijo del JScrollPane, altura inicial de 1 píxel

        agregarReciente.setSize(150, 20);
        agregarReciente.setLocation(600, 0);
        agregarReciente.setText("AGREGAR");
        agregarReciente.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AgregarCard(tblTablaActivity, pnlActiRecientes, formattedDate);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        pnlActiRecientes.setSize(800, 480);
        pnlActiRecientes.setLocation(200, 270);
        pnlActiRecientes.setLayout(null);
        pnlActiRecientes.setBackground(Color.decode("#eceff1"));
        pnlActiRecientes.add(agregarReciente);
        pnlActiRecientes.add(tblTablaActivity, BorderLayout.CENTER);

        frmVentana.setSize(800, 500);
        frmVentana.setVisible(true);
        frmVentana.add(pnlActiRecientes);


    }
    private static void AgregarCard(JScrollPane panelScroll, JPanel pnlPruebaa, String fecha) {
        String name = JOptionPane.showInputDialog(null, "Ingrese un nombre:", "Agregar Nombre", JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            listaNombres.add(name);
        }

        contentPanel.removeAll();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < listaNombres.size(); i++) {
            String nameExtrai = listaNombres.get(i);

            JPanel namePanel = new JPanel();

            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS)); // Usar BoxLayout para el namePanel
            namePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

            JTextArea textArea = new JTextArea();
            JLabel lblFecha = new JLabel();

            lblFecha.setText(fecha);
            lblFecha.setBackground(Color.YELLOW);
            lblFecha.setOpaque(true);

            textArea.setLineWrap(true); // Hacer que el texto se ajuste automáticamente en líneas
            textArea.setWrapStyleWord(true); // Hacer que el texto se ajuste respetando las palabras completas
            textArea.setEditable(false); // Deshabilitar edición del JTextArea
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(200, 100)); // Tamaño fijo del JScrollPane, altura de 100 píxeles

            String texto = nameExtrai;

            if (texto.length() > 300) {
                // Truncar el texto y agregar los tres puntos suspensivos "..." al final
                texto = texto.substring(0, 300) + "...";
            }

            textArea.setText(texto); // Actualizar el contenido del JTextArea con el texto del nombre
            scrollPane.setBorder(BorderFactory.createEmptyBorder());


            namePanel.add(lblFecha);
            namePanel.add(Box.createHorizontalGlue());
            namePanel.add(scrollPane);

            contentPanel.add(namePanel);
            contentPanel.add(Box.createVerticalStrut(10));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }


}
