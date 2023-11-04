package Sistema;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class initComponentesHome {
    public static List<String> listId = new ArrayList<>();
    public static List<String> listObject = new ArrayList<>();
    public static JPanel contentPanel = new JPanel();
    static JPanel pnlHome;
    JLabel lblLogo;
    JButton btnHome;
    JButton btnVentas;
    JButton btnCompras;
    JButton btnClientes;
    JButton btnInventario;
    JButton btnReportes;
    JButton btnColaboradores;
    JButton btnSalir;

    JPanel pnlNotas  = new JPanel();
    JPanel pnlHederNotas;
    JPanel pnlBodyNotas;
    JLabel lblSettings;
    JLabel agregarReciente = new JLabel();
    JDialog jdlNuevaNota;
    JScrollPane scrNotas = new JScrollPane();
    JTextArea txtNotaArea;
    JPanel pnlBusqCancel;
    JPanel pnlNotResults;
    JPanel pnlGrafBarras = new JPanel();
    JPanel pnlGrafPoli = new JPanel();
    JPanel pnlActiRecientes = new JPanel();
    JScrollPane tblTablaActivity = new JScrollPane();

    static JMenuBar jmbBarra;

    public static JFrame jfrPrincipal;
    public Consultas consultNotas = new Consultas();
    public static Consultas consultInit = new Consultas();
    public static Statement st;
    public initComponentesHome(JFrame jfrPrincipal, Statement st){
        this.jfrPrincipal = jfrPrincipal;
        this.st = st;
        Home();
    }

    private void Home() {
        Ventas WindowVentas = new Ventas(jfrPrincipal, st);
        Compras WindowCompras = new Compras(jfrPrincipal, st);

        pnlHome = new JPanel();
        lblLogo = new JLabel();
        btnHome = new JButton();
        btnVentas = new JButton();
        btnCompras = new JButton();
        btnClientes = new JButton();
        btnInventario = new JButton();
        btnColaboradores = new JButton();
        btnReportes =  new JButton();
        btnSalir = new JButton();

        jmbBarra = new JMenuBar();


        // Crear un conjunto de datos XY
        DefaultXYDataset dataset = new DefaultXYDataset();
        DefaultCategoryDataset datasetBarras = new DefaultCategoryDataset();

        double[][] data = new double[][] {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 ,19, 20, 21, 22, 23, 24, 25}, // Valores x del polígono 1
                {1, 3, 2, 4, 2, 3, 2, 6, 8, 9, 10, 4, 7, 8, 9, 12, 10, 10, 8, 10, 10, 7, 9, 13, 17}  // Valores y del polígono 1
        };

        double[][] data2 = new double[][] {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 ,19, 20, 21, 22, 23, 24, 25}, // Valores x del polígono 1
                {1, 5, 2, 4, 2, 2, 2, 10, 8, 9, 10, 5, 3, 8, 9, 12, 17, 10, 4, 10, 8, 15 , 9, 7, 10}  // Valores y del polígono 1
        };

        datasetBarras.addValue(1, "Martin", "");
        datasetBarras.addValue(10, "Sergio", "");
        datasetBarras.addValue(3, "Saul", "");
        datasetBarras.addValue(20, "Perez", "");
        datasetBarras.addValue(17, "Teodoro", "");
        datasetBarras.addValue(25, "Daniel", "");
        datasetBarras.addValue(21, "Juan", "");
        dataset.addSeries("ventas", data);
        dataset.addSeries("cant.agua", data2);

        // Crear el gráfico de polígonos
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", // Título del gráfico
                "", // Etiqueta del eje x
                "", // Etiqueta del eje y
                dataset, // Datos
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Incluir leyenda
                true, // Incluir tooltips
                false // Incluir URLs
        );
        TextTitle tituloPoligonos = chart.getTitle();
        tituloPoligonos.setFont(new Font("Arial", Font.PLAIN, 15));
        tituloPoligonos.setPaint(Color.decode("#4db6ac"));
        chart.setBackgroundPaint(Color.decode("#eceff1"));
        chart.getLegend().setBackgroundPaint(Color.decode("#eceff1"));

        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "",
                "",
                datasetBarras,
                PlotOrientation.VERTICAL,
                true, // No incluir leyenda para eliminar los recuadros de colores
                true,
                false
        );
        barChart.setBackgroundPaint(Color.decode("#eceff1"));
        TextTitle tituloBarras = barChart.getTitle();
        tituloBarras.setFont(new Font("Arial", Font.PLAIN, 15));
        tituloBarras.setPaint(Color.decode("#4db6ac"));
        barChart.getLegend().setBackgroundPaint(Color.decode("#eceff1"));
       /* XYPlot plotB = (XYPlot) barChart.getPlot();
        XYItemRenderer rendererB = plotB.getRenderer();
        Stroke strokeB = new BasicStroke(2.0f);
        plotB.setOutlineVisible(false);
        plotB.setBackgroundPaint(Color.white);

        rendererB.setSeriesPaint(0, Color.decode("#ec407a"));
        rendererB.setSeriesPaint(1, Color.decode("#64b5f6"));
        rendererB.setSeriesStroke(0, strokeB);
        rendererB.setSeriesStroke(1, strokeB);*/
        CategoryPlot plotB = barChart.getCategoryPlot();
        plotB.setBackgroundPaint(Color.decode("#eceff1"));
        plotB.setOutlineVisible(false);

        BarRenderer renderB = (BarRenderer) plotB.getRenderer();
        renderB.setSeriesPaint(0, Color.decode("#80cbc4"));
        renderB.setSeriesPaint(1, Color.decode("#ce93d8"));
        renderB.setSeriesPaint(2, Color.decode("#f8bbd0"));
        renderB.setSeriesPaint(3, Color.decode("#52BE80"));
        renderB.setSeriesPaint(4, Color.decode("#F1C40F"));
        renderB.setSeriesPaint(5, Color.decode("#EB984E"));
        renderB.setSeriesPaint(6, Color.decode("#9B59B6"));

        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        Stroke stroke = new BasicStroke(2.0f);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(Color.decode("#eceff1"));

        renderer.setSeriesPaint(0, Color.decode("#bbdefb"));
        renderer.setSeriesPaint(1, Color.decode("#ffccbc"));
        renderer.setSeriesStroke(0, stroke);
        renderer.setSeriesStroke(1, stroke);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(250, 120);
        chartPanel.setLocation(20, 50);
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                System.out.println("Picaste al grafico");
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {

            }
        });
        ChartPanel chartPanelBarras = new ChartPanel(barChart);
        chartPanelBarras.setSize(250, 120);
        chartPanelBarras.setLocation(20, 50);
        chartPanelBarras.repaint();


        JMenu jmConfiguracion = new JMenu("Configuracion");
        jmConfiguracion.setSize(90, 40);

        JMenu jmAyuda = new JMenu("Ayuda");
        jmAyuda.setSize(50, 40);
        jmAyuda.setLocation(95, 0);

        JMenu jmBuscar = new JMenu("Buscar");
        jmBuscar.setSize(60, 40);
        jmBuscar.setLocation(150,0);

        JMenu jmHistorial = new JMenu("Historial");
        jmHistorial.setSize(70, 40);
        jmHistorial.setLocation(215, 0);

        JMenu jmSoporte = new JMenu("Soporte");
        jmSoporte.setSize(60, 40);
        jmSoporte.setLocation(290, 0);



        jmbBarra.setSize(910,40);
        jmbBarra.setLocation(220, 0);
        jmbBarra.add(jmConfiguracion);
        jmbBarra.add(jmHistorial);
        jmbBarra.add(jmSoporte);
        jmbBarra.add(jmAyuda);
        jmbBarra.add(jmBuscar);


        File rutaImgHome = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\home.svg");
        FlatSVGIcon imgHome = new FlatSVGIcon(rutaImgHome);
        btnHome.setSize(150, 42);
        btnHome.setLocation(25,140);
        btnHome.setText("HOME");
        btnHome.setIcon(imgHome);
        btnHome.setContentAreaFilled(false);
        btnHome.setOpaque(false);
       // btnHome.setFont(new Font("Arial", Font.BOLD, 12));
        btnHome.requestFocus(null);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnHome.setContentAreaFilled(true);
                btnHome.setBackground(Color.decode("#eeeeee"));
                btnHome.setForeground(Color.gray);
                btnVentas.setContentAreaFilled(false);
                btnVentas.setForeground(Color.BLACK);
                btnCompras.setContentAreaFilled(false);
                btnCompras.setForeground(Color.BLACK);
                btnClientes.setContentAreaFilled(false);
                btnClientes.setForeground(Color.BLACK);
                btnInventario.setContentAreaFilled(false);
                btnInventario.setForeground(Color.BLACK);
                pnlGrafBarras.setVisible(true);
                WindowVentas.pnlVentas.setVisible(false);
                chartPanel.setVisible(true);
                chartPanelBarras.setVisible(true);
                WindowCompras.pnlCompras.setVisible(false);
                pnlActiRecientes.setVisible(true);
                AgregarCard();
                refreshRecent();
            }
        });

        File rutaImgVentas = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\ventas.svg");
        FlatSVGIcon imgVentas = new FlatSVGIcon(rutaImgVentas);
        btnVentas.setSize(150, 42);
        btnVentas.setLocation(25,200);
        btnVentas.setText("VENTAS");
        btnVentas.setIcon(imgVentas);
        btnVentas.setContentAreaFilled(false);
        btnVentas.setOpaque(false);
       // btnVentas.setFont(new Font("Arial", Font.BOLD, 12));
        btnVentas.requestFocus(null);

        btnVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVentas.setContentAreaFilled(true);
                btnVentas.setBackground(Color.decode("#eeeeee"));
                btnVentas.setForeground(Color.gray);
                btnHome.setContentAreaFilled(false);
                btnHome.setForeground(Color.BLACK);
                btnCompras.setContentAreaFilled(false);
                btnCompras.setForeground(Color.BLACK);
                btnClientes.setContentAreaFilled(false);
                btnClientes.setForeground(Color.BLACK);
                btnInventario.setContentAreaFilled(false);
                btnInventario.setForeground(Color.BLACK);
                pnlGrafBarras.setVisible(false);
                chartPanel.setVisible(false);
                chartPanelBarras.setVisible(false);
                WindowVentas.pnlVentas.setVisible(true);
                WindowCompras.pnlCompras.setVisible(false);
                pnlActiRecientes.setVisible(false);
            }
        });

        File rutaImgCompras = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\compras.svg");
        FlatSVGIcon imgCompras = new FlatSVGIcon(rutaImgCompras);
        btnCompras.setSize(150, 42);
        btnCompras.setLocation(25,260);
        btnCompras.setText("COMPRAS");
        btnCompras.setIcon(imgCompras);
        btnCompras.setContentAreaFilled(false);
        btnCompras.setOpaque(false);
       // btnCompras.setFont(new Font("Arial", Font.BOLD, 12));
        btnCompras.requestFocus(null);
        btnCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCompras.setContentAreaFilled(true);
                btnCompras.setBackground(Color.decode("#eeeeee"));
                btnCompras.setForeground(Color.gray);
                btnVentas.setContentAreaFilled(false);
                btnVentas.setForeground(Color.BLACK);
                btnHome.setContentAreaFilled(false);
                btnHome.setForeground(Color.BLACK);
                btnClientes.setContentAreaFilled(false);
                btnClientes.setForeground(Color.BLACK);
                btnInventario.setContentAreaFilled(false);
                btnInventario.setForeground(Color.BLACK);
                WindowCompras.pnlCompras.setVisible(true);
                WindowVentas.pnlVentas.setVisible(false);
                chartPanel.setVisible(false);
                chartPanelBarras.setVisible(false);
                pnlActiRecientes.setVisible(false);
            }
        });

        File rutaImgClientes = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\clientes.svg");
        FlatSVGIcon imgClientes = new FlatSVGIcon(rutaImgClientes);
        btnClientes.setSize(150, 42);
        btnClientes.setLocation(25,320);
        btnClientes.setText("CLIENTES");
        btnClientes.setIcon(imgClientes);
        btnClientes.setContentAreaFilled(false);
        btnClientes.setOpaque(false);
        //btnClientes.setFont(new Font("Arial", Font.BOLD, 12));
        btnClientes.requestFocus(null);
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClientes.setContentAreaFilled(true);
                btnClientes.setBackground(Color.decode("#eeeeee"));
                btnClientes.setForeground(Color.gray);
                btnVentas.setContentAreaFilled(false);
                btnVentas.setForeground(Color.BLACK);
                btnCompras.setContentAreaFilled(false);
                btnCompras.setForeground(Color.BLACK);
                btnHome.setContentAreaFilled(false);
                btnHome.setForeground(Color.BLACK);
                btnInventario.setContentAreaFilled(false);
                btnInventario.setForeground(Color.BLACK);
                pnlActiRecientes.setVisible(false);

                JOptionPane.showMessageDialog(null, "CLIENYES");

            

            }
        });
        File rutaImgColaboradores = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\Colabo.svg");
        FlatSVGIcon imgColaboradores = new FlatSVGIcon(rutaImgColaboradores);
        btnColaboradores.setSize(150, 42);
        btnColaboradores.setLocation(25,380);
        btnColaboradores.setText("COLABORADORES");
        btnColaboradores.setIcon(imgColaboradores);
        btnColaboradores.setContentAreaFilled(false);
        btnColaboradores.setOpaque(false);
        btnColaboradores.requestFocus(null);

        File rutaImgReportes = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\report.svg");
        FlatSVGIcon imgReportes = new FlatSVGIcon(rutaImgReportes);
        btnReportes.setSize(150, 42);
        btnReportes.setLocation(25,440);
        btnReportes.setText("REPORTES");
        btnReportes.setIcon(imgReportes);
        btnReportes.setContentAreaFilled(false);
        btnReportes.setOpaque(false);
        btnReportes.requestFocus(null);

        File rutaImgInventario = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\inventario.svg");
        FlatSVGIcon imgInventario = new FlatSVGIcon(rutaImgInventario);
        btnInventario.setSize(150, 42);
        btnInventario.setLocation(25,500);
        btnInventario.setText("INVENTARIO");
        btnInventario.setIcon(imgInventario);
        btnInventario.setContentAreaFilled(false);
        btnInventario.setOpaque(false);
        btnInventario.requestFocus(null);
        btnInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                btnInventario.setContentAreaFilled(true);
                btnInventario.setBackground(Color.decode("#eeeeee"));
                btnInventario.setForeground(Color.gray);
                btnVentas.setContentAreaFilled(false);
                btnVentas.setForeground(Color.BLACK);
                btnCompras.setContentAreaFilled(false);
                btnCompras.setForeground(Color.BLACK);
                btnClientes.setContentAreaFilled(false);
                btnClientes.setForeground(Color.BLACK);
                btnHome.setContentAreaFilled(false);
                btnHome.setForeground(Color.BLACK);
                pnlActiRecientes.setVisible(false);

            }
        });

        File rutaImgExit = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\exit.svg");
        FlatSVGIcon imgExit = new FlatSVGIcon(rutaImgExit);
        btnSalir.setSize(220, 70);
        btnSalir.setLocation(0,670);
        btnSalir.setText("SALIR");
        btnSalir.setIcon(imgExit);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setOpaque(false);
        btnSalir.setForeground(Color.decode("#ec407a"));
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.requestFocus(null);

        JLabel lblIngresosBrut = new JLabel("INGRESOS BRUTOS");
        JLabel lblCantBrut = new JLabel("$9500.00");
        JLabel lblIngresosNeto = new JLabel("Ingresos Neto");
        JLabel lblCantidadNeta = new JLabel("$6255.00");
        JLabel lblPerdidas = new JLabel("Perdidas");
        JLabel lblCantidadPerdidas = new JLabel("+-$825.00");


        lblIngresosBrut.setSize(200, 25);
        lblIngresosBrut.setForeground(Color.black);
        lblIngresosBrut.setLocation(200, 60);
        lblIngresosBrut.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));

        lblIngresosNeto.setSize(200, 25);
        lblIngresosNeto.setForeground(Color.black);
        lblIngresosNeto.setLocation(450, 60);
        lblIngresosNeto.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));

        lblCantBrut.setSize(200, 30);
        lblCantBrut.setForeground(Color.black);
        lblCantBrut.setLocation(200, 85);
        lblCantBrut.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));
        lblCantidadNeta.setSize(200, 30);
        lblCantidadNeta.setForeground(Color.black);
        lblCantidadNeta.setLocation(450, 85);
        lblCantidadNeta.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));

        lblPerdidas.setSize(200, 25);
        lblPerdidas.setForeground(Color.black);
        lblPerdidas.setLocation(700, 60);
        lblPerdidas.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));

        lblCantidadPerdidas.setSize(200, 30);
        lblCantidadPerdidas.setForeground(Color.black);
        lblCantidadPerdidas.setLocation(700, 85);
        lblCantidadPerdidas.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));

        File ruta = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\logo_home.svg");
        FlatSVGIcon Splash = new FlatSVGIcon(ruta);

        lblLogo.setSize(900, 130);
        lblLogo.setLocation(0,0);
        lblLogo.setBackground(Color.GRAY);
        lblLogo.setIcon(Splash);

        JLabel TitleNotas = new JLabel();
        JTextField txtBuscarNota = new JTextField();

        TitleNotas.setText("Notas rapidas");
        TitleNotas.setLocation(10, 33);
        TitleNotas.setSize(110, 15);
        TitleNotas.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        TitleNotas.setForeground(Color.decode("#455a64"));

        JLabel lblBuscar = new JLabel();
        JLabel lblCancelar = new JLabel();
        File rutaImgBuscar = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\search.svg");
        FlatSVGIcon imgBuscar = new FlatSVGIcon(rutaImgBuscar);

        lblBuscar.setSize(13, 13);
        lblBuscar.setLocation(20, 5);
        lblBuscar.setIcon(imgBuscar);
        lblBuscar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scrNotas.setVisible(false);
                pnlNotResults.setVisible(true);
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

        File rutaImgCancel = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\cancel.svg");
        FlatSVGIcon imgCancel = new FlatSVGIcon(rutaImgCancel);
        lblCancelar.setSize(12, 12);
        lblCancelar.setLocation(0,5);
        lblCancelar.setIcon(imgCancel);

        pnlBusqCancel = new JPanel();
        pnlBusqCancel.setSize(42, 20);
        pnlBusqCancel.setLocation(148, 2);
        pnlBusqCancel.setBackground(Color.white);
        pnlBusqCancel.setLayout(null);
        pnlBusqCancel.add(lblBuscar);
        pnlBusqCancel.add(lblCancelar);

        txtBuscarNota.setSize(195, 25);
        txtBuscarNota.setText("Buscar...");
        txtBuscarNota.setFont(new Font("Arial", Font.PLAIN, 13));
        txtBuscarNota.setForeground(Color.decode("#455a64"));
        txtBuscarNota.setLocation(8, 60);
        txtBuscarNota.add(pnlBusqCancel);

        File rutaPlus = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\plus.svg");
        FlatSVGIcon imgAgregarNota = new FlatSVGIcon(rutaPlus);

        JLabel lblPlus = new JLabel();
        lblPlus.setSize(50, 50);
        lblPlus.setLocation(150,620);
        lblPlus.setIcon(imgAgregarNota);
        lblPlus.setLayout(null);
        lblPlus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                NuevaNota();

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
        File rutaImgSettings = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\settings.svg");
        FlatSVGIcon imgSettings = new FlatSVGIcon(rutaImgSettings);

        lblSettings = new JLabel();
        lblSettings.setSize(25, 25);
        lblSettings.setLocation(175, 5);
        lblSettings.setIcon(imgSettings);
        lblSettings.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Ajustes");
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
        JLabel lblImgNotResult = new JLabel();
        File rutaImgNotResult = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\no-results.svg");
        FlatSVGIcon img404 = new FlatSVGIcon(rutaImgNotResult);
        JLabel lblnoResult = new JLabel();

        lblImgNotResult.setSize(250, 200);
        lblImgNotResult.setLocation(0,0);
        lblImgNotResult.setIcon(img404);

        lblnoResult.setText("Sin resultados");
        lblnoResult.setSize(150, 20);
        lblnoResult.setLocation(20, 20);
        lblnoResult.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        lblnoResult.setForeground(Color.decode("#919C9F"));

        JLabel lblTitleColaboradores = new JLabel();
        JLabel lblTitleVentas = new JLabel();
        JLabel lblActiRes = new JLabel();
        JLabel imgIr = new JLabel();
        JLabel imgIrPoli = new JLabel();
        File rutaImgIr = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\more_info.svg");
        FlatSVGIcon imgMoreInfoGrafBarras = new FlatSVGIcon(rutaImgIr);
        JComboBox<String> cbbOrder = new JComboBox();
        JComboBox<String> cbbOrderPoli = new JComboBox<>();

        cbbOrder.addItem("Asendente");
        cbbOrder.addItem("Desendente");
        cbbOrder.setSize(18, 15);
        cbbOrder.setLocation(300,5);
        cbbOrder.setBackground(Color.decode("#eceff1"));
        cbbOrder.setFocusable(false);

        cbbOrderPoli.addItem("Asendente");
        cbbOrderPoli.addItem("Desendente");
        cbbOrderPoli.setSize(18, 15);
        cbbOrderPoli.setLocation(300,5);
        cbbOrderPoli.setBackground(Color.decode("#eceff1"));
        cbbOrderPoli.setFocusable(false);

        imgIr.setSize(50, 50);
        imgIr.setLocation(290, 160);
        imgIr.setIcon(imgMoreInfoGrafBarras);

        imgIrPoli.setSize(50, 50);
        imgIrPoli.setLocation(290, 160);
        imgIrPoli.setIcon(imgMoreInfoGrafBarras);

        lblTitleColaboradores.setText("Colaboradores");
        lblTitleColaboradores.setSize(150, 20);
        lblTitleColaboradores.setLocation(5,5);
        lblTitleColaboradores.setForeground(Color.decode("#212121"));
        lblTitleColaboradores.setFont(new Font("", Font.PLAIN, 12));

        lblTitleVentas.setText("Ventas");
        lblTitleVentas.setSize(150, 20);
        lblTitleVentas.setLocation(5, 5);
        lblTitleVentas.setForeground(Color.decode("#212121"));
        lblTitleVentas.setFont(new Font("", Font.PLAIN, 12));

        lblActiRes.setText("ACTIVIDADES RECIENTES");
        lblActiRes.setSize(180, 20);
        lblActiRes.setLocation(10, 10);
        lblActiRes.setForeground(Color.decode("#212121"));
        lblActiRes.setFont(new Font("", Font.PLAIN, 12));

        agregarReciente.setSize(150, 20);
        agregarReciente.setLocation(600, 0);
        agregarReciente.setText("AGREGAR");
        agregarReciente.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AgregarCard();
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

        contentPanel.setSize(700, 300);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        tblTablaActivity.setSize(700, 300);
        tblTablaActivity.setLocation(0,35);
        tblTablaActivity.setBorder(new EmptyBorder(0, 0, 0, 0));
        tblTablaActivity.setBackground(Color.decode("#eceff1"));
        tblTablaActivity.setViewportView(contentPanel);

        pnlActiRecientes.setSize(700, 320);
        pnlActiRecientes.setLocation(200, 430);
        pnlActiRecientes.setLayout(null);
        pnlActiRecientes.setBackground(Color.decode("#eceff1"));
        pnlActiRecientes.add(lblActiRes);
        pnlActiRecientes.add(agregarReciente);
        pnlActiRecientes.add(tblTablaActivity, BorderLayout.CENTER);

        pnlGrafBarras.setSize(330, 200);
        pnlGrafBarras.setLocation(200,215);
        pnlGrafBarras.setBackground(Color.decode("#eceff1"));
        pnlGrafBarras.setLayout(null);
        pnlGrafBarras.add(imgIr);
        pnlGrafBarras.add(cbbOrder);
        pnlGrafBarras.add(lblTitleColaboradores);
        pnlGrafBarras.add(chartPanelBarras);

        pnlGrafPoli.setSize(330, 200);
        pnlGrafPoli.setLocation(570,215);
        pnlGrafPoli.setBackground(Color.decode("#eceff1"));
        pnlGrafPoli.setLayout(null);
        pnlGrafPoli.add(lblTitleVentas);
        pnlGrafPoli.add(cbbOrderPoli);
        pnlGrafPoli.add(imgIrPoli);
        pnlGrafPoli.add(chartPanel);


        pnlNotResults = new JPanel();
        pnlNotResults.setSize(210, 400);
        pnlNotResults.setLocation(0, 225);
        pnlNotResults.setBackground(Color.decode("#eeeeee"));
        pnlNotResults.add(lblImgNotResult);
        pnlNotResults.add(lblnoResult);
        pnlNotResults.setVisible(false);


        pnlHederNotas = new JPanel();
        pnlHederNotas.setSize(210, 100);
        pnlHederNotas.setLocation(0,0);
        pnlHederNotas.setLayout(null);
        pnlHederNotas.add(TitleNotas);
        pnlHederNotas.add(txtBuscarNota);
        pnlHederNotas.add(lblSettings);

        pnlBodyNotas = new JPanel();
        pnlBodyNotas.setSize(210, 650);
        pnlBodyNotas.setBorder(BorderFactory.createEmptyBorder());
        pnlBodyNotas.setLayout(new BoxLayout(pnlBodyNotas, BoxLayout.Y_AXIS));

        scrNotas.setSize(210, 650);
        scrNotas.setLocation(0, 100);
        scrNotas.setBorder(BorderFactory.createEmptyBorder());
        scrNotas.setViewportView(pnlBodyNotas);

        pnlNotas.setSize(210, 700);
        pnlNotas.setLocation(915, 50);
        pnlNotas.setLayout(null);
        pnlNotas.setBorder(BorderFactory.createEmptyBorder());
        pnlNotas.add(pnlHederNotas);
        pnlNotas.add(lblPlus);
        pnlNotas.add(scrNotas);
        pnlNotas.add(pnlNotResults);
        pnlNotas.setBackground(Color.decode("#eeeeee"));


        pnlHome.setSize(1150, 800);
        // pnlHome.setBackground(Color.decode("#81d4fa"));
        pnlHome.setVisible(true);
        pnlHome.setLayout(null);
        pnlHome.setLocation(0,0);
        pnlHome.setBackground(Color.white);
        pnlHome.add(lblLogo);
        pnlHome.add(btnHome);
        pnlHome.add(btnVentas);
        pnlHome.add(btnCompras);
        pnlHome.add(btnClientes);
        pnlHome.add(btnColaboradores);
        pnlHome.add(btnInventario);
        pnlHome.add(btnReportes);
        pnlHome.add(btnSalir);
        pnlHome.add(lblIngresosBrut);
        pnlHome.add(lblCantBrut);
        pnlHome.add(lblIngresosNeto);
        pnlHome.add(lblCantidadNeta);
        pnlHome.add(lblPerdidas);
        pnlHome.add(lblCantidadPerdidas);
        pnlHome.add(jmbBarra);
        pnlHome.add(pnlNotas);
        pnlHome.add(pnlGrafBarras);
        pnlHome.add(pnlGrafPoli);
        pnlHome.add(pnlActiRecientes);

        jfrPrincipal.add(pnlHome);
        jfrPrincipal.repaint();

        consultNotas.refreshNotas(pnlBodyNotas, st);
        AgregarCard();

    }

    private void refreshRecent() {


    }

    private static void AgregarCard() {

        //Te quedaste en que hivas a hallar la forma de obetener el id y el tipo de objeto
        // para asi concatenarlo en un String y agregar ese estring talvez a la listaNombres

       // cdfvdfvdfvdfvdfvdfvdfvdf
        consultInit.saveListRecent(st, listId, listObject);

        contentPanel.removeAll();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < listId.size(); i++) {
            String idExtra = listId.get(i);
            String tipoObjectExtra = listObject.get(i);

            JPanel namePanel = new JPanel();
            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS)); // Usar BoxLayout para el namePanel
            namePanel.setBackground(Color.PINK);
            namePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

            JLabel lbltext = new JLabel("Agregaste la "+tipoObjectExtra+ " "+idExtra);

            // Panel para el JLabel con FlowLayout alineado a la izquierda
            JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 13, 7));
            labelPanel.setBackground(Color.decode("#eceff1"));
            labelPanel.add(lbltext);
            namePanel.add(labelPanel);

            // Panel para los botones con FlowLayout alineado a la derecha
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 7));
            buttonPanel.setBackground(Color.decode("#eceff1"));
            File rutaImgMoreInfoRes = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\more_info_res.svg");
            File rutaImgOcultar = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\ocultar.svg");

            FlatSVGIcon imgOcular = new FlatSVGIcon(rutaImgOcultar);
            FlatSVGIcon imgMoreInfo = new FlatSVGIcon(rutaImgMoreInfoRes);

           // JButton btnBorrar = new JButton("Borrar");
            JButton btnOcultar = new JButton();
            JButton MoreInfo = new JButton();


            btnOcultar.setIcon(imgOcular);
            btnOcultar.setBorder(null);
            btnOcultar.setContentAreaFilled(false);
            btnOcultar.setOpaque(false);
            btnOcultar.requestFocus(null);

            MoreInfo.setIcon(imgMoreInfo);
            MoreInfo.setBorder(null);
            MoreInfo.setContentAreaFilled(false);
            MoreInfo.setOpaque(false);
            MoreInfo.requestFocus(null);

            //buttonPanel.add(btnBorrar);
            buttonPanel.add(MoreInfo);
            buttonPanel.add(btnOcultar);
            namePanel.add(buttonPanel);

            MoreInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //consultInit.infoRecent();

                }
            });

            btnOcultar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener el nombre asociado al botón "Borrar"
                    String nameToDelete = lbltext.getText();
                    // Llamar al método para borrar el nombre
                    VerNombreBorrar(nameToDelete);
                }

                private void VerNombreBorrar(String nameToDelete) {
                    System.out.println("Vas a borrar: "+nameToDelete);
                }
            });

            contentPanel.add(namePanel);
            contentPanel.add(Box.createVerticalStrut(10));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void NuevaNota() {

        consultNotas.refreshNotas(pnlBodyNotas, st);

        jdlNuevaNota = new JDialog();
        txtNotaArea = new JTextArea();
        JPanel pnlBarraNav = new JPanel();
        JScrollPane scrollNota;

        File rutaImagePalomita = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\Palomita.svg");
        FlatSVGIcon imgPalomita = new FlatSVGIcon(rutaImagePalomita);
        File rutaImageClose= new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\close.svg");
        FlatSVGIcon imgClose = new FlatSVGIcon(rutaImageClose);
        File rutaImageOptions = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\options.svg");
        FlatSVGIcon imgOptions = new FlatSVGIcon(rutaImageOptions);

        JLabel lblCerrar = new JLabel("");
        JLabel lblHecho = new JLabel("");
        JLabel lblEdicion = new JLabel("");

        lblCerrar.setBounds(328, 1, 35, 30);
        lblCerrar.setFont(new Font("Arial", Font.PLAIN, 17));
        lblCerrar.setIcon(imgClose);
        lblCerrar.setBackground(Color.red);
        lblCerrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jdlNuevaNota.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblCerrar.setBackground(Color.red);
                lblCerrar.repaint();
                System.out.println("SALOR");
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        lblHecho.setBounds(2, 1, 35, 30);
        lblHecho.setFont(new Font("Arial", Font.PLAIN, 17));
        lblHecho.setIcon(imgPalomita);
        lblHecho.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (consultNotas.saveNota(txtNotaArea, st)){
                    jdlNuevaNota.dispose();
                    consultNotas.refreshNotas(pnlBodyNotas, st);
                }

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

        lblEdicion.setBounds(280, 1, 35, 30);
        lblEdicion.setFont(new Font("Arial", Font.PLAIN, 17));
        lblEdicion.setIcon(imgOptions);

        pnlBarraNav.setSize(350, 30);
        pnlBarraNav.setLocation(0,0);
        pnlBarraNav.add(lblCerrar);
        pnlBarraNav.add(lblHecho);
        pnlBarraNav.add(lblEdicion);
        pnlBarraNav.setLayout(null);
        pnlBarraNav.setBackground(Color.decode("#42a5f5"));

        int posX = jfrPrincipal.getX() + 520;
        int posY = jfrPrincipal.getY() + 100;

        txtNotaArea.setSize(350, 410);
        txtNotaArea.setLocation(0, 0);
        txtNotaArea.setText("Toma una nota");
        txtNotaArea.setBackground(Color.decode("#e3f2fd"));
        txtNotaArea.setForeground(Color.decode("#bdbdbd"));

        scrollNota = new JScrollPane(txtNotaArea);
        scrollNota.setSize(350, 410);
        scrollNota.setLocation(0, 30);

        jdlNuevaNota.setSize(350, 450);
        jdlNuevaNota.setLocation(posX, posY);
        jdlNuevaNota.add(scrollNota);
        jdlNuevaNota.setLayout(null);
        jdlNuevaNota.setTitle("Nueva Nota");
        jdlNuevaNota.setModal(true);
        jdlNuevaNota.setUndecorated(true);
        jdlNuevaNota.add(pnlBarraNav);
        jdlNuevaNota.setVisible(true);

    }

}
    class WhiteComboBoxRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Llamar al método original para que los elementos se muestren correctamente
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Cambiar el color de fondo del JComboBox a blanco
        setBackground(Color.YELLOW);

        return this;
    }

}
