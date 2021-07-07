package com.example.fmkid;
public class Cliente extends Persona{
    //Atributo
    private String telefono = "000 000 00 00";

    //Constructores
    public Cliente(){
        //Constructor por defecto
    }

    public Cliente(String nombre, Integer edad, String telefono) {
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setTelefono(telefono);
    }

    //Setter
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    //Getter
    public String getTelefono(){
        return this.telefono;
    }

    //Método heredado de la clase abstracta Persona
    @Override
    public void mostrar(){
        System.out.println("El número telefónico del cliente " + this.getNombre()  + " (" + this.getEdad() +
        " años)" + " es: " + this.getTelefono());
    }
}
