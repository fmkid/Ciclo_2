package com.example.fmkid.Classes;

public class Directivo extends Empleado{
    //Atributos
    private Integer categoria = 0;

    //Constructores
    public Directivo(){
        //Constructor por defecto
        super();
    }

    public Directivo(String nombre, Integer edad, Integer sueldo, Integer categoria) {
        super(nombre, edad, sueldo); //Constructor heredado de la clase Empleado
        this.setCategoria(categoria);
    }

    //Setter
    public void setCategoria(Integer categoria){
        this.categoria = categoria;
    }

    //Getter
    public Integer getCategoria(){
        return this.categoria;
    }

    //Métodos heredados de las clases Persona y Empleado
    @Override
    public void mostrar(){
        System.out.println("El directivo " + this.getNombre() + " (ID: " + this.getId() + " - Edad: " +
            this.getEdad() + " años)" + " está en la categoría " + this.getCategoria() + 
            " y tiene un sueldo neto de $" + this.calcularSalarioNeto());
    }

    @Override
    protected Integer calcularSalarioNeto() {
        return (int) (this.getSueldoBruto() * (1 + this.getCategoria() * 0.4));
    }
}