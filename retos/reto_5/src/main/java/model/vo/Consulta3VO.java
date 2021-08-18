package model.vo;
import java.sql.*;

/*  CREA UNA CLASE QUE DEFINE UN TIPO DE OBJETO EN EL QUE SE ALMACENA LOS DATOS DE LA CONSULTA 3
    3) Seleccione el constructor, el número de baños y el nombre del Lider de las Construcciones que 
    tengan un id entre 5 y 17 incluyendo los extremos. */

public class Consulta3VO implements ConsultaVO {
    //Atributos
    private String constructora;
    private int nBanios;
    private String nombre;

    //Constructores

    public Consulta3VO() {
    }

    public Consulta3VO(ResultSet rs) throws SQLException {
        this.constructora = rs.getString("Constructora");
        this.nBanios = rs.getInt("Numero_Banos");
        this.nombre = rs.getString("Nombre");
    }

    //Getters y Setters

    public String getConstructora() {
        return constructora;
    }

    public void setConstructora(String constructora) {
        this.constructora = constructora;
    }
    public int getnBanios() {
        return nBanios;
    }
    public void setnBanios(int nBanios) {
        this.nBanios = nBanios;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Object[] getArrayDatos() {
        Object arrayDatos[] = new Object[3];
        arrayDatos[0] = nombre;
        arrayDatos[1] = constructora;
        arrayDatos[2] = nBanios;
        return arrayDatos;
    }
}
