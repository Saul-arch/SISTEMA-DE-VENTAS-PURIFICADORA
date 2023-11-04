package Pruebas;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.io.File;

public class IconJoptionPane {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        // Crear un nuevo JOptionPane
        JOptionPane optionPane = new JOptionPane();

        // Crear un ImageIcon personalizado
        //ImageIcon iconoPersonalizado = new ImageIcon("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\pluss.png"); // Cambia la ruta al archivo de tu icono

        File ruta = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\Confirmacion.svg");
        FlatSVGIcon img = new FlatSVGIcon(ruta);

        // Mostrar el JOptionPane con el nuevo icono
        JOptionPane.showMessageDialog(null, "El producto se ha modificado correctamente. Recuerda que ahora la informacion del producto ha sido actualizada en el sistema", "Hecho", JOptionPane.INFORMATION_MESSAGE, img);
    }

}
