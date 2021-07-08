package com.example.fmkid.Classes;

public class Empresa{
    //Atributo
    private String nombre = "N.N Ltda.";

    //Constructores
    public Empresa(){
        //Constructor por defecto
    }

    public Empresa(String nombre) {
        this.setNombre(nombre);
    }

    //Setter
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //Getter
    public String getNombre(){
        return this.nombre;
    }
}
