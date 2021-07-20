package com.example.fmkid;

import java.util.ArrayList;

public class Materia {
    // Atributos

    private int cantidadNotas;
    private String nombreMateria;
    private String idMateria;
    private Nota peorNota;
    private Nota notaPromedio;
    private ArrayList<Nota> listaNotas;
    private static final int NOTAS_A_CONSIDERAR = 3;
    private static final int ID_DEFAULT = -1;

    // Constructores

    public Materia(String nombreMateria, String idMateria) {
        this.peorNota = new Nota(ID_DEFAULT, 100);
        this.listaNotas = new ArrayList<>();
        this.nombreMateria = nombreMateria.toUpperCase();
        this.idMateria = idMateria.toUpperCase();
    }

    // Métodos

    public void setNota(int notaEscala100) {
        Nota notaNueva = new Nota(this.cantidadNotas + 1, (double) notaEscala100);
        this.listaNotas.add(notaNueva);
        this.cantidadNotas = listaNotas.size();
        if ((notaEscala100 < this.peorNota.getNotaEscala100()) || (this.peorNota.getIdNota() == -1))
            this.peorNota = notaNueva;
    }

    private void calcularPromedioAjustado() {
        int sumatoria = 0;
        int peorNota = 0;
        double promedio;
        double notas = (double) this.cantidadNotas;

        for (Nota nota : this.listaNotas)
            sumatoria += nota.getNotaEscala100();

        if (this.cantidadNotas > NOTAS_A_CONSIDERAR) {
            peorNota = this.peorNota.getNotaEscala100();
            notas--;
        }

        promedio = (sumatoria - peorNota) / notas;
        notaPromedio = new Nota(ID_DEFAULT, promedio);
    }

    private String evaluarAprobacion() {
        if (this.notaPromedio.getNotaCuantitativa() == "INSUFICIENTE")
            return "REPROBADO";
        else
            return "APROBADO";
    }

    public void imprimirMateria() {
        String concepto = this.nombreMateria + " [" + this.idMateria + "]";

        System.out.println("\n==== Notas registradas para la asignatura " + concepto + " ====");

        if (this.cantidadNotas != 0) {
            for (Nota nota : this.listaNotas) {
                nota.imprimirNota();
                if ((nota.getIdNota() == this.peorNota.getIdNota()) && (this.cantidadNotas > NOTAS_A_CONSIDERAR))
                    System.out.println("(Esta es la peor nota registrada - No se tendrá en cuenta para el promedio)");
            }
            this.calcularPromedioAjustado();
            System.out.println("\nCantidad total de notas registradas: " + this.cantidadNotas);
            System.out.println("Promedio ajustado (en escala de 0 a 100): " + this.notaPromedio.getNotaEscala100());
            System.out.println("Promedio ajustado (en escala de 0.0 a 5.0): " + this.notaPromedio.getNotaEscala5());
            System.out
                    .println("Promedio ajustado (en escala cuantitativa): " + this.notaPromedio.getNotaCuantitativa());
            System.out.println("\n=== Ha " + this.evaluarAprobacion() + " la asignatura " + concepto + " ===");
        } else
            System.out.println("\n** En este momento no hay notas registradas ***");
        System.out.println();
    }
}
