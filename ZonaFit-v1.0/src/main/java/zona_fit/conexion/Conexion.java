package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexion {

    public static Connection getConexion() {
        Connection conexion = null;
        var baseDatos = "zona_fit_db";
        var usuario = "root";
        var password = "admin";
        var urlServidor = "jdbc:mysql://localhost:3306/";
        var urlCompleta = urlServidor + baseDatos + "?useSSL=false";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear la base de datos si no existe
            Connection conexionTemporal = DriverManager.getConnection(urlServidor, usuario, password);
            Statement stmtTemp = conexionTemporal.createStatement();
            stmtTemp.executeUpdate("CREATE DATABASE IF NOT EXISTS " + baseDatos);
            conexionTemporal.close();

            // Conectarse a la base de datos ya existente o recién creada
            conexion = DriverManager.getConnection(urlCompleta, usuario, password);

            // Crear tabla 'clientes' si no existe
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "apellido VARCHAR(100) NOT NULL, " +
                    "membresia INT NOT NULL)");

        } catch (Exception e) {
            System.out.println("Error al conectarse o crear la BD/tabla: " + e.getMessage());
            e.printStackTrace();
        }

        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null)
            System.out.println("¡Conexión exitosa y estructura lista para uso!");
        else
            System.out.println("Error al conectarse.");
    }
}

