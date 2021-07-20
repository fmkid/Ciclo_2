package com.example.fmkid;

public class Nota {
    // Atributos

    private int idNota;
    private int notaEscala100;
    private double notaEscala5;
    private String notaCualitativa;
    //private static final int CANT_DECIMALES = 2;

    // Constructores

    public Nota(int idNota, double notaEscala100) {
        this.idNota = idNota;
        this.notaEscala100 = (int) notaEscala100;
        this.notaEscala5 = redondearNota(notaEscala100 / 20, 2);
        this.notaCualitativa = cualificarNota(this.notaEscala5);
    }

    // Métodos

    public void imprimirNota(String tipoNota) {
        if (this.idNota > Materia.ID_DEFAULT) {
            System.out.println("\n** Nota #" + this.idNota + " **");
        }
        System.out.println(tipoNota + " (en escala de 0 a 100): " + this.notaEscala100);
        System.out.println(tipoNota + " (en escala de 0.0 a 5.0): " + this.notaEscala5);
        System.out.println(tipoNota + " (en escala cualitativa): " + this.notaCualitativa);
    }

    public static double redondearNota(double nota, int decimales) {
        double potencia = Math.pow(10, (double) decimales);
        return Math.round(nota * potencia) / potencia;
    }

    private static String cualificarNota(double notaEscala5) {
        if (notaEscala5 < 2.95)
            return "INSUFICIENTE";
        else if (notaEscala5 < 3.55)
            return "ACEPTABLE";
        else if (notaEscala5 < 4.15)
            return "BUENO";
        else if (notaEscala5 < 4.75)
            return "SOBRESALIENTE";
        else
            return "EXCELENTE";
    }

    // Getters

    public int getNotaEscala100() {
        return notaEscala100;
    }

    public double getNotaEscala5() {
        return notaEscala5;
    }

    public String getNotaCualitativa() {
        return notaCualitativa;
    }

    public int getIdNota() {
        return idNota;
    }
}