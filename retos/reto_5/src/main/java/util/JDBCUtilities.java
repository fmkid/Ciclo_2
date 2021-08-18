package util;

import java.sql.*;

public class JDBCUtilities {
    //Atributos de clase para gestión de conexión con la base de datos    
    private static String rutaDB = "./resources";    

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + rutaDB;        
        return DriverManager.getConnection(url);
    }

    public static void setRouteDB(String rutaNueva) {
        rutaDB = rutaNueva;
    }

    public static String getRouteDB() {
        return rutaDB;
    }
}