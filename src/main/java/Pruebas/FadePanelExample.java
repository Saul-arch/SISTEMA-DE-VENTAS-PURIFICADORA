package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadePanelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de Fade Panel");
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        panel1.setBackground(Color.RED);
        panel2.setBackground(Color.GREEN);
        panel2.setOpaque(false); // Establecer panel2 como transparente inicialmente
        panel2.setVisible(false); // Ocultar panel2 inicialmente

        JButton button = new JButton("Mostrar Panel");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fadePanel(panel2);
            }
        });

        mainPanel.add(panel1, BorderLayout.CENTER);
        mainPanel.add(panel2, BorderLayout.CENTER);
        mainPanel.add(button, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void fadePanel(JPanel panel) {
        final int duration = 1050; // Duración de la animación en milisegundos
        final int steps = 60; // Número de pasos de la animación
        final int delay = duration / steps;

        panel.setVisible(true); // Hacer visible el panel antes de comenzar la animación

        Timer timer = new Timer(delay, new ActionListener() {
            int currentStep = 0;
            float initialAlpha = 0.0f;
            float finalAlpha = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentStep++;

                // Calcular el valor intermedio de opacidad
                float alpha = initialAlpha + (finalAlpha - initialAlpha) * currentStep / steps;
                panel.setOpaque(alpha < 1.0f); // Establecer si el panel debe ser opaco o transparente
                panel.setBackground(new Color(panel.getBackground().getRed(),
                        panel.getBackground().getGreen(), panel.getBackground().getBlue(), (int) (alpha * 255)));

                panel.repaint();

                if (currentStep == steps) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }
}
