package Sistema;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
public class Consultas {
    public ResultSet rs;
    public int CantRegistros = 0;
    public int ultimo_Id = 0;
    public int getUltimo_Id_compras = 0;
    public List<String> ListColors = new ArrayList<>();

    // Obtener la fecha actual
    LocalDate currentDate = LocalDate.now();

    // Definir el formato deseado para día en texto y mes en texto
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM");

    // Formatear la fecha en el formato deseado
    String formattedDate = currentDate.format(formatter);


    public void refresh(Statement st, JTable lista, Vector<String> columnas, String consulta, String tipoTabla) {
        System.out.println(consulta);
        CantRegistros = 0;
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        DefaultTableModel model;

        try {

            rs = st.executeQuery(consulta);

            if (tipoTabla.equals("ventas")){
                while (rs.next()){
                    Vector<Object> row = new Vector<Object>();
                    // Vector<Object> columnNames = new Vector<Object>();
                    row.add(rs.getString("id_ventas"));
                    row.add(rs.getString("nombre_venta"));
                    row.add(rs.getString("precio"));
                    row.add(rs.getString("nombre_vend"));
                    row.add(rs.getString("fecha_vent"));
                    data.add(row);

                    ultimo_Id = Integer.parseInt(rs.getString("id_ventas"));
                    CantRegistros = Integer.parseInt(rs.getString("id_ventas")) + 1;

                }
            }
            if (tipoTabla.equals("compras")){
                while (rs.next()){
                    Vector<Object> row = new Vector<Object>();
                    // Vector<Object> columnNames = new Vector<Object>();
                    row.add(rs.getString("Id"));
                    row.add(rs.getString("Producto"));
                    row.add(rs.getString("Precio"));
                    row.add(rs.getString("Cantidad"));

                    data.add(row);

                    getUltimo_Id_compras = Integer.parseInt(rs.getString("Id")) + 1;
                    CantRegistros = Integer.parseInt(rs.getString("id")) + 1;
                }

            }


            model = new DefaultTableModel(data, columnas);
            lista.setModel(model);

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Exception ");
        }
    }

    public Boolean NuevoRegistro(String consulta, Statement st, String tipo_opcion){
        ultimo_Id = ultimo_Id+1;
        String consulta2 = "";
        if (tipo_opcion.equals("Venta")){
            consulta2 = "insert into recientes(id, tipo_object) values ("+ultimo_Id+", '"+tipo_opcion+"')";
        } else if (tipo_opcion.equals("Compras")) {
            consulta2 = "insert into recientes(id, tipo_object) values ("+getUltimo_Id_compras+", '"+tipo_opcion+"')";
        }

        try {
            st.executeUpdate(consulta);
            st.executeUpdate(consulta2);
            return  true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int MaxRegistro(){
        return CantRegistros;
    }
    public int Buscar(Statement st, JTable lista, Vector<String> columnas, String consulta, String tipo){
        System.out.println(consulta);
        CantRegistros = 0;
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        DefaultTableModel model;
        int result = -1;

        try {

            rs = st.executeQuery(consulta);

            if (tipo.equals("ventas")){

                while (rs.next()){
                    Vector<Object> row = new Vector<Object>();
                    // Vector<Object> columnNames = new Vector<Object>();
                    row.add(rs.getString("id_ventas"));
                    row.add(rs.getString("nombre_venta"));
                    row.add(rs.getString("precio"));
                    row.add(rs.getString("nombre_vend"));
                    row.add(rs.getString("fecha_vent"));
                    data.add(row);
                    result++;
                }

            }
            if (tipo.equals("compras")) {

                while (rs.next()){
                    Vector<Object> row = new Vector<Object>();
                    // Vector<Object> columnNames = new Vector<Object>();
                    row.add(rs.getString("Id"));
                    row.add(rs.getString("Producto"));
                    row.add(rs.getString("Precio"));
                    row.add(rs.getString("Cantidad"));

                    data.add(row);

                }

            }

            model = new DefaultTableModel(data, columnas);
            lista.setModel(model);

        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
    public void Actualizar(Statement st, String consulta){
        System.out.println(consulta);
        try {
            st.executeUpdate(consulta);
            JOptionPane.showMessageDialog(null, "Modificacion Exitosa");

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Algo a salido mal intentelo de nuevo.", "Ocurrio un error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void BorraRegistro(Statement st, String cosulta){
        try {
            System.out.println(cosulta);
            st.executeUpdate(cosulta);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Algo a salido mal", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void refreshNotas(JPanel pnlNotas, Statement st){

        ListColors.add("#FADBD8");
        ListColors.add("#EBDEF0");
        ListColors.add("#D4E6F1");
        ListColors.add("#D6EAF8");
        ListColors.add("#D1F2EB");
        ListColors.add("#FCF3CF");
        ListColors.add("#F6DDCC");

        String consulta = "select * from notas";
        List<String> Notas = new ArrayList<>();
        List<String> fechasNotas = new ArrayList<>();

        pnlNotas.setBorder(BorderFactory.createEmptyBorder());

        try {
            rs = st.executeQuery(consulta);

            while (rs.next()){

                Notas.add(rs.getString("nota"));
                fechasNotas.add(rs.getString("fecha"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pnlNotas.removeAll();
        pnlNotas.setLayout(new BoxLayout(pnlNotas, BoxLayout.Y_AXIS));

        for (int i = 0; i < Notas.size(); i++) {

            String randomColor = randomColor();
            Border border = BorderFactory.createMatteBorder(0, 110, 0, 0, pnlNotas.getBackground());

            String nameExtrai = Notas.get(i);

            JPanel namePanel = new JPanel();

            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS)); // Usar BoxLayout para el namePanel
            namePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));


            JTextArea textArea = new JTextArea();
            JLabel lblFecha = new JLabel();

            textArea.setLineWrap(true); // Hacer que el texto se ajuste automáticamente en líneas
            textArea.setWrapStyleWord(true); // Hacer que el texto se ajuste respetando las palabras completas
            textArea.setEditable(false); // Deshabilitar edición del JTextArea
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(180, 100)); // Tamaño fijo del JScrollPane, altura de 100 píxeles

            String texto = nameExtrai;

            if (texto.length() > 150) {
                // Truncar el texto y agregar los tres puntos suspensivos "..." al final
                texto = texto.substring(0, 150) + "...";
            }

            textArea.setText(texto);// Actualizar el contenido del JTextArea con el texto del nombre
            lblFecha.setText(formattedDate);
            lblFecha.setBackground(Color.decode(randomColor));
            lblFecha.setOpaque(true);
            lblFecha.setBorder(border);
            lblFecha.setSize(210, 20);
            lblFecha.setForeground(Color.decode("#B2BABB"));

            textArea.setBackground(Color.decode(randomColor));
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            //namePanel.add(lblFecha);
            namePanel.add(scrollPane);

            pnlNotas.add(namePanel);
            pnlNotas.add(Box.createVerticalStrut(10));
        }

        pnlNotas.revalidate();
        pnlNotas.repaint();

    }

    private String randomColor() {
        Random random = new Random();
        int randomNumber = random.nextInt(7);

        return ListColors.get(randomNumber);

    }

    public Boolean saveNota(JTextArea jtaNota, Statement st){

        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String Fecha = formato.format(fecha);

        System.out.println(Fecha);

        String textoNota = jtaNota.getText().toString();
        String consulta = "INSERT INTO notas(nota, fecha) values('"+textoNota+ "', '"+Fecha+"')";
        System.out.println(consulta);

        try {
            st.executeUpdate(consulta);
            return  true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveListRecent(Statement st, List<String> listaId, List<String> listaObject){
        listaId.clear();
        listaObject.clear();

        String consulta = "select * from recientes";
        try {
            rs = st.executeQuery(consulta);

            while (rs.next()){

                listaId.add(rs.getString("id"));
                listaObject.add(rs.getString("tipo_object"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void infoRecent(){
        System.out.println("INFO");
        JDialog dlgInfo = new JDialog();
        JLabel lblProdServ = new JLabel("Producto;");
        JLabel lblPrecio = new JLabel("Precio");
        JLabel lblHora = new JLabel("Hora");
        JLabel lblComprador = new JLabel("Comprador");

        lblProdServ.setSize(150, 20);
        lblProdServ.setLocation(0,0);
        lblPrecio.setSize(150, 20);
        lblPrecio.setLocation(0,30);
        lblHora.setSize(150, 20);
        lblHora.setLocation(0,60);
        lblComprador.setSize(150, 20);
        lblComprador.setLocation(0,90);

        dlgInfo.setSize(650, 600);
        dlgInfo.setVisible(true);
        dlgInfo.setLayout(null);
        dlgInfo.setTitle("Detalles");
        dlgInfo.setModal(true);
        dlgInfo.setResizable(false);
        dlgInfo.add(lblProdServ);
        dlgInfo.add(lblPrecio);
        dlgInfo.add(lblHora);
        dlgInfo.add(lblComprador);
    }



}
