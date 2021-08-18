package model.dao;

//Estructura de datos
import java.util.ArrayList;

//Librería para SQL y Base de Datos
import java.sql.*;

//Clase para conexión
import util.JDBCUtilities;

//Encapsulamiento de los datos
import model.vo.*;

/*  GENERA LAS CONSULTA TIPO SQL DE LOS REQUERIMIENTOS EN LA DB 
    Y RETORNA EL RESULTADO EN UN ARRAY CON LOS OBJETOS TIPO VO */

public class ConsultaDAO {
    public ArrayList<ConsultaVO> getQuery(int idConsulta, String consulta) throws SQLException {
        // Contenedor de la respuesta
        ArrayList<ConsultaVO> respuesta = new ArrayList<>();
        Connection conexion = null;

        // Crear los objetos relacionados con la consulta y la conexión BD
        conexion = JDBCUtilities.getConnection();
        PreparedStatement statement = conexion.prepareStatement(consulta);
        ResultSet resultSet = statement.executeQuery();

        // Moviendo apuntador por cada registro, cuando no hay más, retorna falso y sale
        while (resultSet.next()) {
            // Cargar el registro actual en un Value Object, 
            // dependiendo del tipo de consulta (1, 2 o 3)
            switch(idConsulta) {
                case 1:
                    respuesta.add(new Consulta1VO(resultSet));
                    break;
                case 2:
                    respuesta.add(new Consulta2VO(resultSet));
                    break;
                case 3:
                    respuesta.add(new Consulta3VO(resultSet));
            }
        }

        // Cerrar los objetos relacionados con la consulta y la conexión BD
        resultSet.close();
        statement.close();
        if (conexion != null) {
            conexion.close();
        }

        // Retorna la colección de VO's obtenida de la BD (Vacía, con un VO o muchos)
        return respuesta;
    }
}
