package Sistema;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection_bd {

    Connection cn;
    String Driver = "com.mysql.cj.jdbc.Driver";
    String usuario = "root";
    String password = "123456789";
    String url = "jdbc:mysql://localhost:3306/agua_vida";

    public Conection_bd(){

        try {

            Class.forName(Driver);
            cn = DriverManager.getConnection(url, usuario, password);


        }catch(Exception e){
            System.out.println("Ocurrio un error en la coneccion: "+e);
        }

    }

}
