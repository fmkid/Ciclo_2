package model.vo;

import java.sql.*;

/*  CREA UNA CLASE QUE DEFINE UN TIPO DE OBJETO EN EL QUE SE ALMACENA LOS DATOS DE LA CONSULTA 1
    1) Seleccione el nombre y el salario de los Lideres que se encuentra en la ciudad de “Bogota”. */

public class Consulta1VO implements ConsultaVO {
    //Atributos
    private String nombre;
    private int salario;

    //Constructores
    
    public Consulta1VO() {
    }

    public Consulta1VO(ResultSet rs) throws SQLException {
        this.nombre = rs.getString("Nombre");
        this.salario = rs.getInt("Salario");
    }

    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public Object[] getArrayDatos() {
        Object arrayDatos[] = new Object[2];
        arrayDatos[0] = nombre;
        arrayDatos[1] = salario;
        return arrayDatos;
    }
}
