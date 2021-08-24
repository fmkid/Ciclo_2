package com.example.fmkid.modelo;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static final String RUTA_DB = "C:/Users/electro/Documents/Mao/Programacion/MisionTIC/Ciclo_2/"
            + "ejercicios_unidad4/ejercico_bd/db/la_floresta.db";
    private Connection conn = null;

    public void iniciarConexion() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + RUTA_DB);
    }

    public void finalizarConexion() throws SQLException {
        if (conn != null)
            conn.close();
    }

    public void insertarRegistro(Estudiante est) throws SQLException {
        String insercion = "INSERT INTO estudiantes VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statmnt = conn.prepareStatement(insercion);
        statmnt.setLong(1, est.getCodigo());
        statmnt.setString(2, est.getNombres());
        statmnt.setString(3, est.getApellidos());
        statmnt.setLong(4, est.getDni());
        statmnt.setString(5, est.getFechaNacimiento());
        statmnt.setString(6, est.getCarrera());
        statmnt.setString(7, est.getCorreo());    
        statmnt.setLong(8, est.getTelefono());
        statmnt.setLong(9, est.getCelular());
        statmnt.executeUpdate();
        if (statmnt != null)
            statmnt.close();
    }

    public int realizarConteo(String campoAContar, String datoConteo) throws SQLException {
        String consulta = String.format("SELECT COUNT(%s) AS conteo FROM estudiantes GROUP BY %s HAVING %s = '%s';",
                campoAContar, campoAContar, campoAContar, datoConteo);
        int resConteo = 0;

        Statement statmnt = conn.createStatement();
        ResultSet res = statmnt.executeQuery(consulta);
        while (res.next())
            resConteo = res.getInt("conteo");
        res.close();
        if (statmnt != null)
            statmnt.close();
        return resConteo;
    }

    public ArrayList<Estudiante> realizarConsulta(String criterio) throws SQLException {
        ArrayList<Estudiante> lista = new ArrayList<>();
        String consulta = "SELECT * FROM estudiantes";
        if (criterio != null)
            consulta += " WHERE " + criterio;
        PreparedStatement statmnt = conn.prepareStatement(consulta + " ORDER BY codigo;");
        ResultSet res = statmnt.executeQuery();
        while (res.next()) {
            Estudiante est = new Estudiante();
            est.setCodigo(res.getLong("codigo"));
            est.setDni(res.getLong("dni"));
            est.setNombres(res.getString("nombres"));
            est.setApellidos(res.getString("apellidos"));
            est.setFechaNacimiento(res.getString("fecha_nac"));
            est.setCorreo(res.getString("email"));
            est.setCarrera(res.getString("programa"));
            est.setTelefono(res.getLong("telefono"));
            est.setCelular(res.getLong("celular"));
            lista.add(est);
        }
        res.close();
        if (statmnt != null)
            statmnt.close();
        return lista;
    }

    public Connection getConn() {
        return conn;
    }
}
