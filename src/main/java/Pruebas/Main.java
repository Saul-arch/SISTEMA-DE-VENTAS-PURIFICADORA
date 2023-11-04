package Pruebas;

import com.formdev.flatlaf.FlatLightLaf;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        // Crear un conjunto de datos XY
        DefaultXYDataset dataset = new DefaultXYDataset();

        double[][] data = new double[][] {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, // Valores x del polígono 1
                {1, 3, 2, 4, 2, 3, 2, 6, 8, 9, 10, 4, 7, 8}  // Valores y del polígono 1
        };
        dataset.addSeries("Polígono 1", data);

        // Crear el gráfico de polígonos
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Gráfico de Polígonos", // Título del gráfico
                "X", // Etiqueta del eje x
                "Y", // Etiqueta del eje y
                dataset, // Datos
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Incluir leyenda
                true, // Incluir tooltips
                false // Incluir URLs
        );

        // Crear una ventana para mostrar el gráfico
        JFrame ventana = new JFrame();
        JPanel pnlPrincipal = new JPanel();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(700, 450);
        chartPanel.setLocation(0, 0);

        pnlPrincipal.setSize(900, 450);
        pnlPrincipal.setLayout(null);
        pnlPrincipal.setVisible(true);
        pnlPrincipal.add(chartPanel);

        ventana.setSize(900, 450);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setTitle("Ventana");
        ventana.add(pnlPrincipal);

        ventana.setVisible(true);



    }

}
