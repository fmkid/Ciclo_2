package com.example.fmkid.Classes;

public abstract class Persona {
    //Atributos de clase
    static Integer cuentaPersona = 0; //Contador de objetos tipo Persona

    //Atributos de instancia
    private String nombre = "N.N.";
    private Integer edad = 0;
    private Integer id = 0;

    //Método abstracto
    public abstract void mostrar();
    
    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEdad(Integer edad){
        this.edad = edad;
    }

    protected void setId(Integer id){
        this.id = id;
    }

    //Getters
    public String getNombre(){
        return this.nombre;
    }

    public Integer getEdad(){
        return this.edad;
    }

    public Integer getId(){
        return this.id;
    }
}