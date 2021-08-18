package model.vo;

/*  Interfaz sencilla para agrupar los VOs de las consultas en un
    solo tipo (esto facilita la reduccíon del código original y
    permite su escalabilidad en el futuro (para implementar más 
    consultas en el programa) */

public interface ConsultaVO {
    public Object[] getArrayDatos();
}