package com.example.fmkid;

import java.util.ArrayList;

public class Materia {
    //Atributos

    private int cantidadNotas;
    private int promedio100;
    private int peorNota100;
    private double promedio5;
    private String nombreMateria;
    private String idMateria;
    private ArrayList<Nota> listaNotas = new ArrayList<>();

    //Constructores

    public Materia(String nombreMateria, String idMateria) {
        this.nombreMateria = nombreMateria;
        this.idMateria = idMateria;
        this.peorNota100 = 100;
    }

    //Métodos
    
    private void calcularPromedioAjustado() {
        if (this.cantidadNotas > 1) {
            int sumatoria100 = 0;
            double sumatoria5 = 0;
            int n = this.cantidadNotas - 1;
            for (Nota nota : this.listaNotas) {
                sumatoria100 += nota.getNotaEscala100();
                sumatoria5 += nota.getNotaEscala5();
            }
            this.promedio100 = (sumatoria100 - this.peorNota100) / n;
            this.promedio5 = Nota.redondearNota((sumatoria5 - Nota.convertirDe100a5(this.peorNota100)) / n, 2);
        } else {
            this.promedio100 = this.listaNotas.get(0).getNotaEscala100();
            this.promedio5 = this.listaNotas.get(0).getNotaEscala5();
        }
    }

    public void imprimirMateria() {
        System.out.println("\n==== Notas registradas para " + this.nombreMateria.toUpperCase() + 
                           " [" + this.idMateria.toUpperCase() + "] ====");
        if (this.cantidadNotas != 0) {
            for (Nota nota : this.listaNotas) {
                nota.imprimirNota();
                if (nota.getNotaEscala100() == this.peorNota100)
                    System.out.println("<- Esta es la peor nota registrada -> ");
            }
            this.calcularPromedioAjustado();
            System.out.println("\nCantidad total de notas registradas: " + this.cantidadNotas);
            System.out.println("Promedio ajustado (en escala de 100): " + this.promedio100);
            System.out.println("Promedio ajustado (en escala de 5.0): " + this.promedio5);
        } else 
            System.out.println("\n** En este momento no hay notas registradas ***");
        System.out.println();
    }

    //Getters

    public int getCantidadNotas() {
        return cantidadNotas;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    //Setters

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public void setNota(int notaEscala100) {
        this.listaNotas.add(new Nota(this.cantidadNotas + 1, notaEscala100));
        this.cantidadNotas = listaNotas.size();
        if (notaEscala100 < this.peorNota100)
            this.peorNota100 = notaEscala100;
    }
}
