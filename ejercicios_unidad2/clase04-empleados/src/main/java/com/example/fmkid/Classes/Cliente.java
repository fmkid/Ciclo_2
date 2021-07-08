package com.example.fmkid.Classes;

public class Cliente extends Persona{
    //Atributo
    private String telefono = "000 000 00 00";

    //Constructores
    public Cliente(){
        //Constructor por defecto
        Persona.cuentaPersona++;
        this.setId(Persona.cuentaPersona);
    }

    public Cliente(String nombre, Integer edad, String telefono) {
        this();
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
        System.out.println("El número telefónico del cliente " + this.getNombre()  + " (ID: " + this.getId() + " - Edad: " +
            this.getEdad() + " años)" + " es: " + this.getTelefono());
    }
}
