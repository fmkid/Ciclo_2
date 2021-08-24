package controller;

//Estructuras de datos (colecciones)
import java.util.ArrayList;

//Modelos (acceso y objetos contenedores)
import model.dao.*;
import model.vo.*;

//Librerías para bases de datos
import java.sql.SQLException;

/*  CREA UNA CLASE QUE DEFINE OBJETOS DE TIPO CONTROLADOR, 
    DEVOLVIENDO EL ARRAYLIST GENERADO POR LA DAO DEL MODELO
    EN DONDE SE ALMACENAN LOS DATOS DE LAS DISTINTAS CONSULTAS */

public class Controlador {

    private static final String CONSULTA_1 = "SELECT Nombre, Salario FROM Lider WHERE Ciudad_Residencia = 'Bogota'";

    private static final String CONSULTA_2 = "SELECT Nombre , Salario, (Salario * 0.16) AS isr, "
            + "(Primer_Apellido || ' ' || Segundo_Apellido) AS ape FROM Lider WHERE Salario > 10000";

    private static final String CONSULTA_3 = "SELECT Constructora, Numero_Banos, Nombre "
            + "FROM Proyecto p JOIN Lider l ON p.ID_Lider = l.ID_Lider WHERE p.ID_Proyecto BETWEEN 5 AND 17";

    private ConsultaDAO listaConsulta;

    public Controlador() {
        listaConsulta = new ConsultaDAO();
    }

    public ArrayList<ConsultaVO> realizarConsulta(int idConsulta) throws SQLException {
        switch (idConsulta) {
            case 1:
                return listaConsulta.getQuery(1, CONSULTA_1);
            case 2:
                return listaConsulta.getQuery(2, CONSULTA_2);
            case 3:
                return listaConsulta.getQuery(3, CONSULTA_3);
            default:
                throw new SQLException();
        }
    }

    public Object[][] crearDatosTabla(int idConsulta) throws SQLException {
        ArrayList<ConsultaVO> lista = null;
        switch (idConsulta) {
            case 1:
                lista = realizarConsulta(1);
                break;
            case 2:
                lista = realizarConsulta(2);
                break;
            case 3:
                lista = realizarConsulta(3);
        }

        Object[][] tabla = new Object[lista.size()][lista.get(0).getArrayDatos().length];

        for (int i = 0; i < tabla.length; i++)
            tabla[i] = lista.get(i).getArrayDatos();

        return tabla;
    }
}
