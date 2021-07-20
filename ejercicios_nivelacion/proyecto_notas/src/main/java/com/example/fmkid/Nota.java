package com.example.fmkid;

public class Nota {
    // Atributos

    private int notaEscala100;
    private int idNota;
    private double notaEscala5;
    private String notaCuantitativa;
    //private static final int CANT_DECIMALES = 2;

    // Constructores

    public Nota(int idNota, double notaEscala100) {
        this.idNota = idNota;
        this.notaEscala100 = (int) notaEscala100;
        this.notaEscala5 = redondearNota(notaEscala100 / 20, 2);
        this.notaCuantitativa = cuantificarNota(this.notaEscala5);
    }

    // Métodos

    public void imprimirNota() {
        System.out.println();
        System.out.println("** Nota #" + this.idNota + " **");
        System.out.println("Nota en escala de 0 a 100: " + this.notaEscala100);
        System.out.println("Nota en escala de 0.0 a 5.0: " + this.notaEscala5);
        System.out.println("Nota en escala cuantitativa: " + this.notaCuantitativa);
    }

    public static double redondearNota(double nota, int decimales) {
        double potencia = Math.pow(10, (double) decimales);
        return Math.round(nota * potencia) / potencia;
    }

    private static String cuantificarNota(double notaEscala5) {
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

    public String getNotaCuantitativa() {
        return notaCuantitativa;
    }

    public int getIdNota() {
        return idNota;
    }
}
