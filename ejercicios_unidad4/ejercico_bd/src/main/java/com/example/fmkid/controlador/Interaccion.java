package com.example.fmkid.controlador;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.*;

import com.example.fmkid.modelo.*;
import com.example.fmkid.vista.Validador;

public class Interaccion {
    private static DBManager bd = new DBManager();

    public void conectarConDB() throws SQLException {
        bd.iniciarConexion();
    }

    public void desconectarBD() throws SQLException {
        bd.finalizarConexion();
    }

    // public void comprobarConexionBD() throws SQLException {
    //     bd.getConn();
    // }

    public void agregarEstudiante(Estudiante est) throws SQLException {
        bd.insertarRegistro(est);
    }

    public ArrayList<Estudiante> buscarCorreo(String correo) throws SQLException {
        return bd.realizarConsulta("email = '" + correo + "'");
    }

    public ArrayList<Estudiante> buscarCodigo(long codigo) throws SQLException {
        return bd.realizarConsulta("codigo = " + codigo);
    }

    public ArrayList<Estudiante> buscarDni(long dni) throws SQLException {
        return bd.realizarConsulta("dni = " + dni);
    }

    public ArrayList<Estudiante> buscarNombres(String nombres) throws SQLException {
        return bd.realizarConsulta("nombres = '" + nombres + "'");
    }

    public ArrayList<Estudiante> buscarApellidos(String apellidos) throws SQLException {
        return bd.realizarConsulta("apellidos = '" + apellidos + "'");
    }

    public ArrayList<Estudiante> buscarCarrera(String carrera) throws SQLException {
        return bd.realizarConsulta("programa = '" + carrera + "'");
    }

    public ArrayList<Estudiante> buscarFecha(String fecha) throws SQLException {
        return bd.realizarConsulta("fecha_nac = '" + fecha + "'");
    }

    public ArrayList<Estudiante> buscarTelCel(long telcel) throws SQLException {
        ArrayList<Estudiante> consultaTel = bd.realizarConsulta("telefono = " + telcel);
        if (consultaTel.size() != 0)
            return consultaTel;
        return bd.realizarConsulta("celular = " + telcel);
    }

    public String contarPorCarrera(String carrera) throws SQLException {
        int cantidad = bd.realizarConteo("programa", carrera);
        if (cantidad > 0)
            return "La cantidad de estudiantes de '" + carrera + "' es " + cantidad;
        return "No se han encontrado estudiantes para la carrera '" + carrera + "' o la carrera no existe";
    }

    public ArrayList<Estudiante> totalRegistros() throws SQLException {
        return bd.realizarConsulta(null);
    }

    public Long generarCodigo() throws SQLException {
        long codigoNuevo;
        int cantAleatorios = (int) (Validador.MAX_COD - Validador.MIN_COD); 
        do
            codigoNuevo = (new Random()).nextInt(cantAleatorios) +  Validador.MIN_COD;
        while(bd.realizarConsulta("codigo = " + codigoNuevo).size() > 0);
        return codigoNuevo;
    }

    public long comprobarDni(long dni) throws SQLException {
        if (bd.realizarConsulta("dni = " + dni).size() == 0)
            return dni;
        else
            throw new SQLWarning();
    }
} 