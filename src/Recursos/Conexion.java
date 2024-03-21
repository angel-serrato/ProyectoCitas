//package Recursos;
//
//import java.sql.*;
//import javax.swing.JOptionPane;
//
//public class Conexion {
//
//    private String driver, url, bd, usr, pass;
//    public Connection conexion;
//
//    public Conexion(String ip, String bd, String usr, String pass) {
//        // driver = "oracle.jdbc.driver.OracleDriver";
//        driver = "com.mysql.cj.jdbc.Driver";
//        this.bd = "db_citas";
//        this.usr = "root";
//        this.pass = "51697453";
//        url = "jdbc:mysql://localhost:3306/" + bd;
//        
//        // jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL
//        try {
//            Class.forName(driver);
//            conexion = DriverManager.getConnection(url, usr, pass);
//        } 
//        catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Error de conexion a la base de datos");
//        }
//    }
//
//    public Connection getConexion() {
//        return conexion;
//    }
//
//    public Connection CerrarConexion() throws SQLException {
//        conexion.close();
//        conexion = null;
//        return conexion;
//    }
//}

//package Recursos;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import javax.swing.JOptionPane;
//
//public class Conexion {
//    
//    public Connection conexion;
//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_citas";
//    // jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL
//    private static final String JDBC_USER = "root";
//    private static final String JDBC_PASSWORD = "51697453";
//
//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//            if (connection != null) {
//                System.out.println("Conexión exitosa a la base de datos MySQL");
//            } else {
//                JOptionPane.showMessageDialog(null, "Error de conexion a la base de datos");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al conectar a la base de datos MySQL: " + e.getMessage());
//            JOptionPane.showMessageDialog(null, "Error de conexion a la base de datos");
//        } catch (ClassNotFoundException e) {
//            System.out.println("No se encontró la clase del driver JDBC: " + e.getMessage());
//            JOptionPane.showMessageDialog(null, "Error de conexion a la base de datos");
//        }
//    }
//    
//    public Connection getConexion() {
//        return conexion;
//    }
//
//    public Connection CerrarConexion() throws SQLException {
//        conexion.close();
//        conexion = null;
//        return conexion;
//    }
//}

package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_citas";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "51697453";

    public Connection conexion;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
