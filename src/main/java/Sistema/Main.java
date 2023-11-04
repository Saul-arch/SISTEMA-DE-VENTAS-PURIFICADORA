package Sistema;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    static JFrame jfVentanaPrincipal;
    static JPanel pnlPrincipal;
    static JLabel lblLogo;
    static Statement st;

    public static void main(String[] args) {
        FlatLightLaf.setup();
        Conection_bd conection = new Conection_bd();

        try{
            st = conection.cn.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }

        initUi();

    }

    private static void initUi() {
        File ruta = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\logo_splash.svg");
        FlatSVGIcon Splash = new FlatSVGIcon(ruta);

        jfVentanaPrincipal = new JFrame();
        pnlPrincipal = new JPanel();
        lblLogo = new JLabel();
        ProcessHilos hilo1 = new ProcessHilos();

        lblLogo.setSize(1450, 800);
        lblLogo.setLocation(0,0);
        lblLogo.setIcon(Splash);
        lblLogo.setVisible(true);

        pnlPrincipal.setSize(1000, 500);
        pnlPrincipal.setLocation(0,0);
        pnlPrincipal.setLayout(null);
        pnlPrincipal.setVisible(true);
        pnlPrincipal.add(lblLogo);

        ImageIcon icono = new ImageIcon("imagenes/miniatura.jpg");


        jfVentanaPrincipal.setSize(1150, 800);
        jfVentanaPrincipal.setResizable(false);
        jfVentanaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfVentanaPrincipal.setLocationRelativeTo(null);
        jfVentanaPrincipal.setVisible(true);
        jfVentanaPrincipal.setTitle("SISTEMA DE CONTROL");
        jfVentanaPrincipal.setIconImage(icono.getImage());
        jfVentanaPrincipal.add(pnlPrincipal);

        hilo1.start();
        try{

            hilo1.join();

        }catch (Exception e){
            System.out.println(e);
        }

        pnlPrincipal.setVisible(false);

        initComponentesHome iniciar = new initComponentesHome(jfVentanaPrincipal, st);

    }
}
