package model.vo;

import java.sql.*;

/*  CREA UNA CLASE QUE DEFINE UN TIPO DE OBJETO EN EL QUE SE ALMACENA LOS DATOS DE LA CONSULTA 2
    2) Seleccione el nombre, salario, el impuesto sobre la renta del salario en una columna que se llame 
    “isr” y los dos apellidos concatenados y separados por un espacio en otra columna de nombre 
    “ape”. Solo seleccione los registros que tengan un salario mayor a 10000 */

public class Consulta2VO implements ConsultaVO {
    //Atributos

    private String nombre;
    private int salario;
    private double isr;
    private String ape;

    //Constructores

    public Consulta2VO() {
    }

    public Consulta2VO(ResultSet rs) throws SQLException {
        this.nombre = rs.getString("Nombre");
        this.salario = rs.getInt("Salario");
        this.isr = rs.getDouble("isr"); 
        this.ape = rs.getString("ape");
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
    public double getIsr() {
        return isr;
    }
    public void setIsr(double isr) {
        this.isr = isr;
    }
    public String getApe() {
        return ape;
    }
    public void setApe(String ape) {
        this.ape = ape;
    }

    @Override
    public Object[] getArrayDatos() {
        Object arrayDatos[] = new Object[4];
        arrayDatos[0] = nombre;
        arrayDatos[1] = ape;
        arrayDatos[2] = salario;
        arrayDatos[3] = isr;
        return arrayDatos;
    }  
}
