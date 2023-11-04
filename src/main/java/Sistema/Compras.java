package Sistema;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Vector;

public class Compras {
    public JFrame frmPrincipal;
    public Statement st;
    public JPanel pnlCompras;

    public JButton btnAGregar;
    public JButton btnBorrar;
    public JButton btnModificar;
    public JButton btnDetalles;
    public JButton btnBuscar;
    public int indicador = -1;
    public int id = 0;
    public String producto = "";
    public float precio = 0.0f;
    public int cantidad = 0;
    public String fecha = "";
    public String comprador = "";


    public int ultimoRegistro;
    public Compras(JFrame frmPrincipal, Statement st){
        this.frmPrincipal = frmPrincipal;
        this.st = st;
        initUiCompras();

    }

    private void initUiCompras() {
        Consultas consultasCompras = new Consultas();

        btnAGregar = new JButton();
        btnModificar = new JButton();
        btnDetalles = new JButton();
        btnBorrar = new JButton();
        btnBuscar = new JButton();

        JTable tblTablaCompras = new JTable();
        JScrollPane scpscroll = new JScrollPane();
        String readData = "select * from compras";
        Vector<String> columnNamesCompras = new Vector<String>();

        JDialog dialogAgregarCompra = new JDialog();
        JLabel lblId = new JLabel();
        JLabel jblNomVenta = new JLabel();
        JLabel jblPrecio = new JLabel();
        JLabel jblNomVend = new JLabel();
        JLabel jblFecha = new JLabel();
        JLabel jblCantidad = new JLabel();

        JTextField txtId = new JTextField();
        JTextField txtNombreCompra = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtNomComprador = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtCantidad = new JTextField();
        JTextField txtBuscar = new JTextField();

        JButton btnGuardar = new JButton();
        JButton btnCancelar = new JButton();

        File FileImg = new File("C:\\Users\\pc\\IdeaProjects\\Prueba_Pur\\Imagenes\\img_mod_compra.svg");
        FlatSVGIcon imgModCompra = new FlatSVGIcon(FileImg);
        JLabel jlbImageMod = new JLabel();
        jlbImageMod.setSize(650,600);
        jlbImageMod.setLocation(0, 0);
        jlbImageMod.setIcon(imgModCompra);

        btnGuardar.setSize(150, 35);
        btnGuardar.setText("Guardar");
        btnGuardar.setLocation(390, 450);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btnGuardar.getText().equals("Guardar")){

                    LocalTime horaActual = LocalTime.now();
                    System.out.println("Hora actual: " + horaActual);
                    String Consulta = "Insert into compras(producto, precio, fecha, hora, nombre_comprador, cantidad) values ('"+txtNombreCompra.getText().toString()+"', '"+txtPrecio.getText().toString()+"', '"+txtFecha.getText().toString()+"', '"+horaActual+"', '"+txtNomComprador.getText().toString()+"', '"+txtCantidad.getText().toString()+"')";
                    System.out.println("Consulta prueba: "+Consulta);
                    consultasCompras.NuevoRegistro(Consulta, st, "Compras");
                    consultasCompras.refresh(st, tblTablaCompras, columnNamesCompras, readData, "compras");
                    ultimoRegistro = consultasCompras.MaxRegistro();
                    txtNombreCompra.setText("");
                    txtCantidad.setText("");
                    txtFecha.setText("");
                    txtPrecio.setText("");
                    txtNomComprador.setText("");
                    txtId.setText("");
                    dialogAgregarCompra.dispose();

                } else if (btnGuardar.getText().equals("Modificar")) {
                    //UPDATE compras SET Producto = "Cloro", Precio = 90, Cantidad = 2 WHERE Id = 16;
                    String consulta = "update compras set Producto = '"+txtNombreCompra.getText().toString()+"', Precio = "+txtPrecio.getText().toString()+", Cantidad = "+txtCantidad.getText().toString()+" where Id = "+txtId.getText().toString();
                    consultasCompras.Actualizar(st, consulta);
                    consultasCompras.refresh(st, tblTablaCompras, columnNamesCompras, readData, "compras");

                    txtNombreCompra.setText("");
                    txtCantidad.setText("");
                    txtFecha.setText("");
                    txtPrecio.setText("");
                    txtNomComprador.setText("");
                    txtId.setText("");

                    lblId.setLocation(120,50);
                    jblNomVenta.setLocation(120, 110);
                    jblPrecio.setLocation(120, 170);
                    jblCantidad.setLocation(120, 230);
                    jblNomVend.setLocation(120, 290);
                    jblNomVend.setVisible(true);
                    jblFecha.setLocation(120, 350);
                    jblFecha.setVisible(true);

                    txtId.setLocation(260, 50);
                    txtNombreCompra.setLocation(260, 110);
                    txtPrecio.setLocation(260, 170);
                    txtCantidad.setLocation(260, 230);
                    txtNomComprador.setLocation(260, 290);
                    txtNomComprador.setVisible(true);
                    txtFecha.setLocation(260, 350);
                    txtFecha.setVisible(true);

                    indicador = -1;
                    dialogAgregarCompra.dispose();

                }
            }
        });

        btnCancelar.setSize(150, 35);
        btnCancelar.setText("Cancelar");
        btnCancelar.setLocation(140, 450);


        lblId.setSize(150, 25);
        lblId.setText("Id");
        lblId.setLocation(120,50);

        jblNomVenta.setSize(150, 25);
        jblNomVenta.setLocation(120, 110);
        jblNomVenta.setText("Producto o servicio");

        jblPrecio.setSize(150, 25);
        jblPrecio.setLocation(120, 170);
        jblPrecio.setText("Precio");

        jblCantidad.setText("Cantidad");
        jblCantidad.setSize(150, 25);
        jblCantidad.setLocation(120, 230);

        jblNomVend.setSize(150, 25);
        jblNomVend.setLocation(120, 290);
        jblNomVend.setText("Nombre del comprador");

        jblFecha.setSize(150, 25);
        jblFecha.setLocation(120, 350);
        jblFecha.setText("Fecha");

        txtId.setSize(150, 25);
        txtId.setLocation(260, 50);
        txtId.setEditable(false);

        txtNombreCompra.setSize(250, 25);
        txtNombreCompra.setLocation(260, 110);

        txtPrecio.setSize(150, 25);
        txtPrecio.setLocation(260, 170);

        txtCantidad.setSize(150, 25);
        txtCantidad.setLocation(260, 230);

        txtNomComprador.setSize(250, 25);
        txtNomComprador.setLocation(260, 290);

        txtFecha.setSize(250, 25);
        txtFecha.setLocation(260, 350);

        txtBuscar.setSize(680, 35);
        txtBuscar.setLocation(0, 30);
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

        dialogAgregarCompra.setSize(650, 600);
        dialogAgregarCompra.setVisible(false);
        dialogAgregarCompra.setLayout(null);
        dialogAgregarCompra.setTitle("Nueva compra");
        dialogAgregarCompra.setModal(true);
        dialogAgregarCompra.setResizable(false);
        dialogAgregarCompra.add(jlbImageMod);
        dialogAgregarCompra.add(lblId);
        dialogAgregarCompra.add(txtId);
        dialogAgregarCompra.add(jblNomVenta);
        dialogAgregarCompra.add(txtNombreCompra);
        dialogAgregarCompra.add(jblPrecio);
        dialogAgregarCompra.add(txtPrecio);
        dialogAgregarCompra.add(jblCantidad);
        dialogAgregarCompra.add(txtCantidad);
        dialogAgregarCompra.add(jblNomVend);
        dialogAgregarCompra.add(txtNomComprador);
        dialogAgregarCompra.add(txtFecha);
        dialogAgregarCompra.add(jblFecha);
        dialogAgregarCompra.add(btnGuardar);
        dialogAgregarCompra.add(btnCancelar);


        btnDetalles.setSize(80, 35);
        btnDetalles.setText("Detalle");
        btnDetalles.setLocation(820,30);
        btnDetalles.setBackground(Color.decode("#b0bec5"));
        btnDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        btnAGregar.setText("Agregar");
        btnAGregar.setSize(130, 35);
        btnAGregar.setLocation(750, 683);
        btnAGregar.setBackground(Color.decode("#0288d1"));
        btnAGregar.setForeground(Color.WHITE);

        btnAGregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fecha = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

                String Fecha = formato.format(fecha);
                txtFecha.setText(Fecha);
                txtId.setText(""+ultimoRegistro);

                int posX = frmPrincipal.getX() + 340;
                int posY = frmPrincipal.getY() + 125;
                dialogAgregarCompra.setLocation(posX, posY);
                btnGuardar.setText("Guardar");
                dialogAgregarCompra.setTitle("Nueva compra");
                dialogAgregarCompra.setVisible(true);
            }
        });

        btnBorrar.setSize(130, 35);
        btnBorrar.setText("Borrar");
        btnBorrar.setLocation(600, 683);
        btnBorrar.setBackground(Color.decode("#0288d1"));
        btnBorrar.setForeground(Color.WHITE);
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indicador != -1){
                    String ConsultaBorrarCompra = "delete from compras where Id = "+id;
                    consultasCompras.BorraRegistro(st, ConsultaBorrarCompra);
                    consultasCompras.refresh(st, tblTablaCompras, columnNamesCompras, readData, "compras");
                    indicador = -1;
                    JOptionPane.showMessageDialog(null, "Eliminacion Exitosa");
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione un registro de compra.", "Algo ah salido mal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setSize(130, 35);
        btnModificar.setLocation(450, 683);
        btnModificar.setBackground(Color.decode("#0288d1"));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indicador != -1){

                    try{
                        jblFecha.setVisible(false);
                        txtFecha.setVisible(false);
                        txtId.setText(""+tblTablaCompras.getValueAt(indicador, 0));
                        txtNombreCompra.setText(""+tblTablaCompras.getValueAt(indicador, 1));
                        txtPrecio.setText(""+tblTablaCompras.getValueAt(indicador, 2));
                        txtCantidad.setText(""+tblTablaCompras.getValueAt(indicador, 3));
                        txtNomComprador.setVisible(false);
                        jblNomVend.setVisible(false);
                        dialogAgregarCompra.setTitle("Modificacion -> compra");

                        lblId.setLocation(120,150);
                        jblNomVenta.setLocation(120, 210);
                        jblPrecio.setLocation(120, 270);
                        jblCantidad.setLocation(120, 330);
                        txtId.setLocation(260, 150);
                        txtNombreCompra.setLocation(260, 210);
                        txtPrecio.setLocation(260, 270);
                        txtCantidad.setLocation(260, 330);

                        int posX = frmPrincipal.getX() + 340;
                        int posY = frmPrincipal.getY() + 125;
                        dialogAgregarCompra.setLocation(posX, posY);
                        btnGuardar.setText("Modificar");
                        dialogAgregarCompra.setVisible(true);



                    }catch (Exception i){
                        System.out.println(i);
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningun registro", "Ha ocurrido un problema", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBuscar.setSize(110, 35);
        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(Color.decode("#4db6ac"));
        btnBuscar.setLocation(700,30);
        btnBuscar.setForeground(Color.white);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buscar = txtBuscar.getText();

                if (isNumeric(buscar)){
                    String ConsultaBuscar = "select * from compras where Id = "+buscar +" or "+ "precio = "+buscar + " or Cantidad = "+buscar;
                    consultasCompras.Buscar(st, tblTablaCompras, columnNamesCompras, ConsultaBuscar, "compras");

                }else{
                    String ConsultaBuscar = "select * from compras where Producto = '"+buscar+"'";
                    System.out.println(ConsultaBuscar);
                    consultasCompras.Buscar(st, tblTablaCompras, columnNamesCompras, ConsultaBuscar, "compras");

                }

                txtBuscar.setText("Ingrese la palabra clave del registro a buscar");
                txtBuscar.setForeground(Color.GRAY);
            }
        });

        columnNamesCompras.add("Id");
        columnNamesCompras.add("Producto");
        columnNamesCompras.add("Precio");
        columnNamesCompras.add("Cantidad");

        consultasCompras.refresh(st, tblTablaCompras, columnNamesCompras, readData, "compras");
        ultimoRegistro = consultasCompras.MaxRegistro();

        tblTablaCompras.setSize(905, 610);
        tblTablaCompras.setLocation(0,70);
        tblTablaCompras.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indicador = tblTablaCompras.getSelectedRow();

                if (indicador != -1){
                    id = Integer.parseInt(tblTablaCompras.getValueAt(indicador, 0).toString());
                    producto = tblTablaCompras.getValueAt(indicador, 1).toString();
                    precio = Float.parseFloat(tblTablaCompras.getValueAt(indicador, 2).toString());
                    cantidad = Integer.parseInt(tblTablaCompras.getValueAt(indicador, 3).toString());
                }else {
                    System.out.println("Sin seleccionar");
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

        scpscroll.setSize(910, 610);
        scpscroll.setLocation(0,70);
        scpscroll.setViewportView(tblTablaCompras);

        pnlCompras = new JPanel();
        pnlCompras.setSize(910, 723);
        pnlCompras.setLocation(220, 40);
        pnlCompras.setLayout(null);
        pnlCompras.add(btnAGregar);
        pnlCompras.add(btnBorrar);
        pnlCompras.add(btnModificar);
        pnlCompras.add(btnDetalles);
        pnlCompras.add(btnBuscar);
        pnlCompras.add(scpscroll);
        pnlCompras.add(txtBuscar);
        pnlCompras.setVisible(false);

        frmPrincipal.add(pnlCompras);

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