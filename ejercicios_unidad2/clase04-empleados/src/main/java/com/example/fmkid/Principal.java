package com.example.fmkid;

public class Principal {
    public static void main(String[] args) {
        final String linea = "---------------------------------------------------------------------";
        //Creación de objeto tipo Empresa
            Empresa miEmpresa = new Empresa("SkyNet Inc.");
        //Creación de objetos (instancias) tipo Persona usando constructores específicos (miPrimer*)        
            Cliente miPrimerCliente = new Cliente("Carlos Contreras", 45, "321 456 78 02");
            Empleado miPrimerEmpleado = new Empleado ("Mario Acosta", 33, 1200000);
            Directivo miPrimerDirectivo = new Directivo("Juan Pablo Cassini", 51, 30000000, 1);
        //Creación de objetos (instancias) tipo Persona usando constructores por defecto (miOtro*)
            Cliente miOtroCliente = new Cliente();
            Empleado miOtroEmpleado = new Empleado ();
            Directivo miOtroDirectivo = new Directivo();
        //Uso del método mostrar para ver datos de personas (clientes, empleados y directivos) 
        //correspondientes a grupo de objetos miPrimer*
            System.out.println(linea);
            System.out.println("DATOS CORRESPONDIENTE A LA EMPRESA - " + miEmpresa.getNombre());
            System.out.println(linea);
            miPrimerCliente.mostrar();
            miPrimerEmpleado.mostrar();
            miPrimerDirectivo.mostrar();
        //Uso del método mostrar para ver datos de personas (clientes, empleados y directivos) 
        //correspondientes a grupo de objeto miOtro*
            System.out.println(linea);
            miOtroCliente.mostrar();
            miOtroEmpleado.mostrar();
            miOtroDirectivo.mostrar();
        //Modificando algunos datos de personas correspondientes a grupo de objeto miOtro* mediante setters
            miOtroCliente.setNombre("Armando Barrera");
            miOtroCliente.setEdad(28);
            miOtroCliente.setTelefono("311 445 55 69");
            miOtroEmpleado.setNombre("Marta Lucia Mendoza");
            miOtroEmpleado.setEdad(19);
            miOtroEmpleado.setSueldoBruto(1000000);
            miOtroDirectivo.setNombre("Laura Pineda");
            miOtroDirectivo.setEdad(41);
            miOtroDirectivo.setSueldoBruto(4000000);
            miOtroDirectivo.setCategoria(2);
        //Volver a mostrar datos de personas correspondientes a grupo de objeto miOtro* para ver cambios
            System.out.println(linea);
            miOtroCliente.mostrar();
            miOtroEmpleado.mostrar();
            miOtroDirectivo.mostrar();
    }
}
