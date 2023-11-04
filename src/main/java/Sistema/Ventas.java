package Sistema;


import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

public class Ventas {
    public JPanel pnlVentas;
    public JDialog dlgAgregar;
    public JLabel lblId = new JLabel();
    public JLabel jblNomVenta = new JLabel();
    public JLabel jblPrecio = new JLabel();
    public JLabel jblNomVend = new JLabel();
    public JLabel jblFecha = new JLabel();
    public JLabel lblNotFound = new JLabel();
    public JLabel lblSinResult = new JLabel();

    public JTextField txtId = new JTextField();
    public JTextField txtNombVenta = new JTextField();
    public JTextField txtPrecio = new JTextField();
    public JTextField txtNomVend = new JTextField();
    public JTextField txtFecha = new JTextField();

    public JButton btnGuardar;
    public JButton btnCancelar;
    public int Verificador = -1;
    public String id;
    public String Nomb_venta;
    public int precio;
    public String nombr_vendedor;
    public String fecha_venta;


    public JFrame jfmAgregar;
    static public JFrame frmPrincipal;
    public Statement st;

    public int ultimoRegistro = 0;

    public Ventas(JFrame jrmPrincipal, Statement st){
        frmPrincipal = jrmPrincipal;
        this.st = st;
        initWindow();

    }

    private void initWindow() {

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");

        int dia = fechaActual.getDayOfMonth();
        String diaTexto = fechaActual.format(formatter);
        int mes = fechaActual.getMonthValue();
        int ano = fechaActual.getYear();

        jfmAgregar = new JFrame();
        dlgAgregar = new JDialog();
        pnlVentas = new JPanel();
        btnCancelar = new JButton();
        btnGuardar = new JButton();
        Consultas consultaVentas = new Consultas();
        String readData = "select * from ventas";
        Vector<String> columnNamesVentas = new Vector<String>();
        JTable tblTabla = new JTable();
        JScrollPane scpscroll = new JScrollPane();

        File FileImg = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\img_mod_venta.svg");
        FlatSVGIcon imgModVenta = new FlatSVGIcon(FileImg);
        JLabel jlbImageModVenta = new JLabel();
        jlbImageModVenta.setSize(650, 600);
        jlbImageModVenta.setLocation(0,0);
        jlbImageModVenta.setIcon(imgModVenta);

        lblId.setSize(150, 25);
        lblId.setText("Id");
        lblId.setLocation(120,50);

        jblNomVenta.setSize(150, 25);
        jblNomVenta.setLocation(120, 110);
        jblNomVenta.setText("Nombre de venta");

        jblPrecio.setSize(150, 25);
        jblPrecio.setLocation(120, 170);
        jblPrecio.setText("Precio");

        jblNomVend.setSize(150, 25);
        jblNomVend.setLocation(120, 230);
        jblNomVend.setText("Nombre de vendedor");

        jblFecha.setSize(150, 25);
        jblFecha.setLocation(120, 290);
        jblFecha.setText("Fecha");

        txtId.setSize(150, 25);
        txtId.setLocation(260, 50);
        txtId.setEditable(false);

        txtNombVenta.setSize(250, 25);
        txtNombVenta.setLocation(260, 100);

        txtPrecio.setSize(150, 25);
        txtPrecio.setLocation(260, 170);

        txtNomVend.setSize(250, 25);
        txtNomVend.setLocation(260, 230);

        txtFecha.setSize(250, 25);
        txtFecha.setLocation(260, 290);

        btnCancelar.setSize(150, 35);
        btnCancelar.setText("Cancelar");
        btnCancelar.setLocation(140, 450);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jfmAgregar.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            }
        });

        btnGuardar.setSize(150, 35);
        btnGuardar.setText("Guardar");
        btnGuardar.setLocation(390, 450);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //INSERT INTO clientes(CC, NOMBRE, CIUDAD) VALUES ("C7", "FRANCISCO", "MEXICO")

                if (btnGuardar.getText().toString().equals("Guardar")){
                    String Consulta = "insert into ventas(nombre_venta, precio, nombre_vend, fecha_vent) values ('"+txtNombVenta.getText().toString()+"', '"+txtPrecio.getText().toString()+"', '"+txtNomVend.getText().toString()+"', '"+txtFecha.getText().toString()+"')";
                    System.out.println("Consulta: "+Consulta);
                    consultaVentas.NuevoRegistro(Consulta, st, "Venta");
                    consultaVentas.refresh(st, tblTabla, columnNamesVentas, readData, "ventas");
                    ultimoRegistro = consultaVentas.MaxRegistro();
                    txtId.setText("");
                    txtNombVenta.setText("");
                    txtPrecio.setText("");
                    txtNomVend.setText("");
                    txtFecha.setText("");
                    dlgAgregar.dispose();
                } else if (btnGuardar.getText().toString().equals("Actualizar")) {
                    String ConsultaActualizarVenta = "update ventas set id_ventas = "+ txtId.getText() + ", nombre_venta = '" + txtNombVenta.getText().toString()  + "', precio = " + txtPrecio.getText() + ", nombre_vend = '" + txtNomVend.getText() + "', fecha_vent = '" + txtFecha.getText() + "' where id_ventas = "+txtId.getText();

                    consultaVentas.Actualizar(st, ConsultaActualizarVenta);
                    consultaVentas.refresh(st, tblTabla, columnNamesVentas, readData, "ventas");
                    Verificador = -1;
                    txtId.setText("");
                    txtNombVenta.setText("");
                    txtPrecio.setText("");
                    txtNomVend.setText("");
                    txtFecha.setText("");
                    dlgAgregar.dispose();

                }


            }
        });

        dlgAgregar.setSize(650, 600);
        dlgAgregar.setVisible(false);
        dlgAgregar.setLayout(null);
        //dlgAgregar.setLocation(530,160);
        dlgAgregar.setModal(true);
        dlgAgregar.setResizable(false);
        dlgAgregar.setTitle("Nueva venta");
        dlgAgregar.add(jlbImageModVenta);
        dlgAgregar.add(lblId);
        dlgAgregar.add(txtId);
        dlgAgregar.add(jblNomVenta);
        dlgAgregar.add(txtNombVenta);
        dlgAgregar.add(jblPrecio);
        dlgAgregar.add(txtPrecio);
        dlgAgregar.add(jblNomVend);
        dlgAgregar.add(txtNomVend);
        dlgAgregar.add(txtFecha);
        dlgAgregar.add(jblFecha);
        dlgAgregar.add(btnCancelar);
        dlgAgregar.add(btnGuardar);

        /*jfmAgregar.setSize(650, 600);
        jfmAgregar.setResizable(false);
        jfmAgregar.setTitle("Nuevo");
        jfmAgregar.setLocationRelativeTo(null);*/


        JLabel lblFecha = new JLabel();
        JButton btnAgregar = new JButton();
        JButton btnBorrar = new JButton();
        JButton btnActualizar = new JButton();
        JButton btnBuscar = new JButton();
        JTextField txtBuscar = new JTextField();

        columnNamesVentas.add("Id");
        columnNamesVentas.add("Venta");
        columnNamesVentas.add("Precio");
        columnNamesVentas.add("Vendedor");
        columnNamesVentas.add("Fecha");


        consultaVentas.refresh(st, tblTabla, columnNamesVentas, readData, "ventas");
        ultimoRegistro = consultaVentas.MaxRegistro();

        tblTabla.setSize(910, 590);
        tblTabla.setLocation(0,70);
        tblTabla.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Verificador = tblTabla.getSelectedRow();

                if(Verificador != -1 ){

                    id = tblTabla.getValueAt(Verificador, 0).toString();
                    Nomb_venta = tblTabla.getValueAt(Verificador, 1).toString();
                    precio = Integer.parseInt(tblTabla.getValueAt(Verificador, 2).toString());
                    nombr_vendedor = tblTabla.getValueAt(Verificador, 3).toString();
                    fecha_venta = tblTabla.getValueAt(Verificador, 4).toString();


                }else{
                    System.out.println("Sin seleccion");
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

        scpscroll.setSize(910, 590);
        scpscroll.setLocation(0,70);
        scpscroll.setViewportView(tblTabla);

        lblFecha.setSize(300, 35);
        lblFecha.setText("Hoy es "+diaTexto+ " "+ dia +" de "+mes(mes)+ " de "+ano);
        lblFecha.setLocation(10, 0);
        lblFecha.setFont(new Font("Mono", Font.BOLD, 17));
        lblFecha.setForeground(Color.decode("#0277bd"));

        txtBuscar.setSize(725, 35);
        txtBuscar.setLocation(10, 30);
        txtBuscar.setText("Ingrese la palabra clave del registro a buscar");
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtBuscar.setText("");
                txtBuscar.setForeground(Color.BLACK);
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

        btnBuscar.setSize(130, 35);
        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(Color.decode("#4db6ac"));
        btnBuscar.setLocation(750,30);
        btnBuscar.setForeground(Color.white);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buscar = txtBuscar.getText();

                File rutaImg = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\not_result.svg");
                FlatSVGIcon img404 = new FlatSVGIcon(rutaImg);
                lblNotFound.setSize(850, 500);
                lblNotFound.setLocation(240, 60);
                lblNotFound.setIcon(img404);
                lblNotFound.setVisible(false);

                lblSinResult.setText("SIN RESULTADOS");
                lblSinResult.setSize(200, 20);
                lblSinResult.setLocation(390, 530);
                lblSinResult.setFont(new Font("Arial", Font.BOLD, 18));
                lblSinResult.setForeground(Color.decode("#757575"));
                lblSinResult.setVisible(false);

                if (isNumeric(buscar)){
                    String ConsultaBuscar = "select * from ventas where id_ventas = "+buscar +" or "+ "precio = "+buscar;
                    if (consultaVentas.Buscar(st, tblTabla, columnNamesVentas, ConsultaBuscar, "ventas") == -1){
                        scpscroll.setVisible(false);
                        lblNotFound.setVisible(true);
                        lblSinResult.setVisible(true);

                    }else{
                        scpscroll.setVisible(true);
                        lblNotFound.setVisible(false);
                        lblSinResult.setVisible(false);
                    }

                }else{
                    String ConsultaBuscar = "select * from ventas where nombre_venta = '"+buscar +"' or "+ "nombre_vend = '"+buscar +"' or fecha_vent = '"+buscar+"'";
                    System.out.println(ConsultaBuscar);
                    consultaVentas.Buscar(st, tblTabla, columnNamesVentas, ConsultaBuscar, "ventas");

                    if (consultaVentas.Buscar(st, tblTabla, columnNamesVentas, ConsultaBuscar, "ventas") == -1){
                        scpscroll.setVisible(false);
                        lblNotFound.setVisible(true);
                        lblSinResult.setVisible(true);

                    }else{
                        scpscroll.setVisible(true);
                        lblNotFound.setVisible(false);
                        lblSinResult.setVisible(false);
                    }

                }

                txtBuscar.setText("Ingrese la palabra clave del registro a buscar");
                txtBuscar.setForeground(Color.GRAY);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.setSize(120, 35);
        btnAgregar.setLocation(750, 670);
        btnAgregar.setForeground(Color.white);
        btnAgregar.setBackground(Color.decode("#0288d1"));


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fecha = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

                String Fecha = formato.format(fecha);
                txtFecha.setText(Fecha);
                txtId.setText(""+ultimoRegistro);

                int posX = frmPrincipal.getX() + 340;
                int posY = frmPrincipal.getY() + 125;
                dlgAgregar.setLocation(posX, posY);
                dlgAgregar.setTitle("Nueva Venta");
                btnGuardar.setText("Guardar");
                dlgAgregar.setVisible(true);

            }
        });

        btnBorrar.setSize(130, 35);
        btnBorrar.setText("Borrar");
        btnBorrar.setBackground(Color.decode("#0288d1"));
        btnBorrar.setLocation(450, 670);
        btnBorrar.setForeground(Color.white);
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Verificador != -1){
                    int IdEliminar = Integer.parseInt(tblTabla.getValueAt(Verificador, 0).toString());
                    String ConsultaEliminarVenta = "delete from ventas where id_ventas = "+IdEliminar;
                    consultaVentas.BorraRegistro(st, ConsultaEliminarVenta);
                    consultaVentas.refresh(st, tblTabla, columnNamesVentas, readData, "ventas");
                    Verificador = -1;
                    JOptionPane.showMessageDialog(null, "Eliminacion Exitosa");

                }else{
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro a elimar","Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        btnActualizar.setText("Modificar");
        btnActualizar.setSize(130, 35);
        btnActualizar.setLocation(600, 670);
        btnActualizar.setForeground(Color.white);
        btnActualizar.setBackground(Color.decode("#0288d1"));
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Verificador != -1){

                    dlgAgregar.setTitle("Modificar Registro -> Ventas");
                    int posX = frmPrincipal.getX() + 340;
                    int posY = frmPrincipal.getY() + 125;
                    dlgAgregar.setLocation(posX, posY);


                    txtId.setText(id);
                    txtNombVenta.setText(Nomb_venta);
                    txtPrecio.setText(""+precio);
                    txtNomVend.setText(nombr_vendedor);
                    txtFecha.setText(fecha_venta);
                    dlgAgregar.dispose();
                    btnGuardar.setText("Actualizar");
                    dlgAgregar.setVisible(true);

                }else{

                    JOptionPane.showMessageDialog(null, "Debe de seleccionar una venta a modificar.", "Error", JOptionPane.ERROR_MESSAGE);


                }



            }
        });



        pnlVentas = new JPanel();
        pnlVentas.setSize(915, 723);
        pnlVentas.setLocation(220, 40);
        pnlVentas.setLayout(null);
        pnlVentas.add(scpscroll);
        pnlVentas.add(lblNotFound);
        pnlVentas.add(lblSinResult);
        pnlVentas.add(lblFecha);
        pnlVentas.add(btnBuscar);
        pnlVentas.add(txtBuscar);
        pnlVentas.add(btnAgregar);
        pnlVentas.add(btnBorrar);
        pnlVentas.add(btnActualizar);
        pnlVentas.setVisible(false);


        frmPrincipal.add(pnlVentas);

    }

    private String mes(int mess) {
        String mes = "";
        int pos1 = 0;
        int meses[] = new int[12];
        String mesesNombre[] = new String[12];

        meses[0] = 1;
        meses[1] = 2;
        meses[2] = 3;
        meses[3] = 4;
        meses[4] = 5;
        meses[5] = 6;
        meses[6] = 7;
        meses[7] = 8;
        meses[8] = 9;
        meses[9] = 10;
        meses[10] = 11;
        meses[11] = 12;

        mesesNombre[0] = "Enero";
        mesesNombre[1] = "Febrero";
        mesesNombre[2] = "Marzo";
        mesesNombre[3] = "Abril";
        mesesNombre[4] = "Mayo";
        mesesNombre[5] = "Junio";
        mesesNombre[6] = "Julio";
        mesesNombre[7] = "Agosto";
        mesesNombre[8] = "Septiembre";
        mesesNombre[9] = "Octubre";
        mesesNombre[10] = "Noviembre";
        mesesNombre[11] = "Diciembre";


        for (int i = 0; i < 12; i++){
            if (mess == meses[i]){
                pos1 = meses[i];
                break;
            }

        }
        mes = mesesNombre[pos1-1];


        return mes;
    }
    private boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
