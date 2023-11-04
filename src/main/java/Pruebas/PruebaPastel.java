package Pruebas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PruebaPastel {

    public static void main(String[] args) {
        // Crear datos aleatorios para las frutas y los porcentajes
        List<String> frutas = new ArrayList<>();
        List<Double> porcentajes = new ArrayList<>();
        Random rand = new Random();
        double totalPorcentaje = 0.0;

        for (int i = 0; i < 10; i++) {
            // Generar nombres de frutas aleatorios (cambia esto con tu lista de frutas real)
            String fruta = "Fruta " + (i + 1);
            frutas.add(fruta);

            // Generar porcentajes aleatorios (asegúrate de que sumen 100%)
            double porcentaje = rand.nextDouble() * 20; // Porcentajes entre 0 y 20
            porcentajes.add(porcentaje);
            totalPorcentaje += porcentaje;
        }

        // Ajustar el último porcentaje para que sume 100%
        porcentajes.set(9, 100.0 - totalPorcentaje);

        // Crear un conjunto de datos para la gráfica de pastel
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < frutas.size(); i++) {
            dataset.setValue(frutas.get(i), porcentajes.get(i));
        }

        // Crear la gráfica de pastel
        JFreeChart chart = ChartFactory.createPieChart(
                "Gráfica de Pastel de Frutas",
                dataset,
                false, // Leyenda
                false,
                false);

        // Desactivar las etiquetas de sección
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);

        // Crear el panel de la gráfica y agregarlo a un JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Gráfica de Pastel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
