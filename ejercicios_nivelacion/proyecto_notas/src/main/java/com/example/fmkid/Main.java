package com.example.fmkid;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n====== BIENVENIDO AL SISTEMA DE REGISTRO DE NOTAS ======\n");
        System.out.print("Por favor ingrese el nombre de la asignatura: ");
        String nombreMateria = input.nextLine();
        System.out.print("Por favor ingrese el código de la asignatura: ");
        String codigoMateria = input.next();
        System.out.print("Ingrese la cantidad de notas a registrar: ");
        int cantNotas = input.nextInt();
        System.out.println();

        Materia asignatura = new Materia(nombreMateria, codigoMateria);

        for (int i = 1; i <= cantNotas; i++) {
            int notaActual;
            do {
                System.out.print("Digite el valor de la nota #" + i + " (en la escala entre 0 y 100) ==> ");
                notaActual = input.nextInt();
                if (notaActual < 0 || notaActual > 100)
                    System.out.println("*** ERROR: El valor digitado no es válido. Vuelva a intentarlo... ***\n");
                else
                    break;
            } while (true);
            asignatura.setNota(notaActual);
        }

        asignatura.imprimirMateria();
        input.close();
    }
}
