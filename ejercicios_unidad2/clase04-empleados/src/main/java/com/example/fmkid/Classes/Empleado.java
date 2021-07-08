package com.example.fmkid.Classes;

public class Empleado extends Persona{
    //Atributos
    private Integer sueldoBruto = 0;

    //Constructores
    public Empleado(){
        //Constructor por defecto
        Persona.cuentaPersona++;
        this.setId(Persona.cuentaPersona);
    }

    public Empleado(String nombre, Integer edad, Integer sueldo) {
        this();
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setSueldoBruto(sueldo);
    }

    //Métodos propios
    protected Integer calcularSalarioNeto() {
        return (int) (this.sueldoBruto * 0.8);
    }

    //Setter
    public void setSueldoBruto(Integer sueldo){
        this.sueldoBruto = sueldo;
    }

    //Getter
    public Integer getSueldoBruto(){
        return this.sueldoBruto;
    }

    //Método heredado de la clase abstracta Persona
    @Override
    public void mostrar(){
        System.out.println("El empleado " + this.getNombre() + " (ID: " + this.getId() + " - Edad: " +
            this.getEdad() + " años)" + " tiene un sueldo neto de $" + this.calcularSalarioNeto());
    }
}
