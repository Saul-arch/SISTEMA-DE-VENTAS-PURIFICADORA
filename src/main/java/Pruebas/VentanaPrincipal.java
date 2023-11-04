package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        super("Navegador a Google");

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);

        // Crear un botón
        JButton botonIr = new JButton("Ir a Google");

        String destinatario = "destinatario@example.com";
        String asunto = "Saludo desde Java";
        String cuerpo = "Hola, Este es un correo electrónico enviado desde una aplicación Java.";

        try {
            destinatario = URLEncoder.encode(destinatario, "UTF-8");
            asunto = URLEncoder.encode(asunto, StandardCharsets.UTF_8);
            cuerpo = URLEncoder.encode(cuerpo, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uriString = "mailto:" + destinatario + "?subject=" + asunto + "&body=" + cuerpo;

        // Agregar un ActionListener al botón para manejar clics
        botonIr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir Google.com en un navegador web predeterminado
                try {
                    Desktop desktop = Desktop.getDesktop();
                    //Desktop.getDesktop().browse(new URI("https://itcostagrande.mindbox.app/alumnos"));
                    // Verificar si la acción de correo electrónico es compatible en el sistema
                    if (desktop.isSupported(Desktop.Action.MAIL)) {
                        // Crear una URI a partir de la cadena
                        URI mailtoURI = new URI(uriString);

                        // Abrir el cliente de correo electrónico predeterminado
                        desktop.mail(mailtoURI);

                        System.out.println("Se abrió el cliente de correo electrónico.");
                    } else {
                        System.out.println("La acción de correo electrónico no es compatible en este sistema.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Agregar el botón a la ventana
        getContentPane().add(botonIr);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}