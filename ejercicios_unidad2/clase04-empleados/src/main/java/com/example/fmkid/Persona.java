package com.example.fmkid;
public abstract class Persona {
    //Atributos
    private String nombre = "N.N.";
    private Integer edad = 0;

    //Método abstracto
    public abstract void mostrar();
    
    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEdad(Integer edad){
        this.edad = edad;
    }

    //Getters
    public String getNombre(){
        return this.nombre;
    }

    public Integer getEdad(){
        return this.edad;
    }
}