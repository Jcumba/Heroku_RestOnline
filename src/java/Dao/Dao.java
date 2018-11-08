package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Dao {

    private Connection cn;

    public void Conexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@35.237.73.4:1521:XE", "RESTONLINE", "vallegrande2018");
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void Cerrar() throws SQLException {      //Cerrar la coneccion
        if (cn != null) {
            if (cn.isClosed() == false) {
                cn.close();
            }
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public static void main(String[] args) {
        Dao dao = new Dao();
        dao.Conexion();
    }

  
}
