package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NamePanelApp extends JFrame {

    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel contentPanel;
    private List<String> namesList;

    public NamePanelApp() {
        namesList = new ArrayList<>();

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.CYAN);
        scrollPane = new JScrollPane();
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.GRAY);
        contentPanel.setSize(100, 30);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));

        scrollPane.setViewportView(contentPanel);
        mainPanel.add(scrollPane, BorderLayout.LINE_START);

        JButton addButton = new JButton("Agregar Nombre");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNameInputDialog();
            }
        });

        mainPanel.add(addButton, BorderLayout.SOUTH);

        setTitle("Nombres App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(800, 400);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showNameInputDialog() {
        String name = JOptionPane.showInputDialog(this, "Ingrese un nombre:", "Agregar Nombre", JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            namesList.add(name);
            updateContentPanel();
        }
    }

    private void updateContentPanel() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Set vertical BoxLayout

        for (String name : namesList) {
            JPanel namePanel = new JPanel(null); // Use null layout
            namePanel.setBackground(Color.PINK);
            namePanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 50)); // Set width to contentPanel width
            namePanel.setMaximumSize(new Dimension(contentPanel.getWidth(), 50));
            namePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel nameLabel = new JLabel(name);
            nameLabel.setBounds(0, 0, namePanel.getWidth(), 50); // Set the position and size of the JLabel within the namePanel
            namePanel.add(nameLabel); // Add the JLabel to the namePanel

            contentPanel.add(namePanel);

            contentPanel.add(Box.createVerticalStrut(10)); // Adjust the space (10 pixels) as needed
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NamePanelApp();
            }
        });
    }
}