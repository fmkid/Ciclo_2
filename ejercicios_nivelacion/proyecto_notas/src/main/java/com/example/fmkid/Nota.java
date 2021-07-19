package com.example.fmkid;

public class Nota {
    //Atributos

    private int notaEscala100;
    private double notaEscala5;
    private int idNota;

    //Constructores

    public Nota() {
        this.idNota = -1;
        this.notaEscala100 = 0;
        this.notaEscala5 = 0.0;
    }

    public Nota(int idNota, int notaEscala100){
        this.idNota = idNota;
        this.notaEscala100 = notaEscala100;
        this.notaEscala5 = Nota.convertirDe100a5(notaEscala100);
    }

    //Métodos

    public void imprimirNota() {
        System.out.println();
        System.out.println("** Nota #" + this.idNota + " **");
        System.out.println("Nota en escala de 100: " + this.notaEscala100);
        System.out.println("Nota en escala de 5.0: " + this.notaEscala5);
    }

    public static double redondearNota(double nota, int decimales) {
        double potencia = Math.pow(10, (double)decimales);
        return Math.round(nota * potencia) / potencia; 
    }

    public static double convertirDe100a5(int notaEscala100) {
        return Nota.redondearNota(notaEscala100 / 20.0, 2);
    }

    //Getters
    
    public int getNotaEscala100() {
        return notaEscala100;
    }

    public double getNotaEscala5() {
        return notaEscala5;
    }

    public int getIdNota() {
        return idNota;
    }
}
