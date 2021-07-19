package com.example.fmkid;

import java.util.Scanner;

public class Main 
{
    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);

        System.out.println("\n====== BIENVENIDO AL SISTEMA DE REGISTRO DE NOTAS ======\n");
        System.out.print("Por favor ingrese el nombre de la materia: ");
        String nombreMateria = input.nextLine();
        System.out.print("\nPor favor ingrese el código de la materia: ");
        String codigoMateria = input.next();
        System.out.print("\nIngrese la cantidad de notas a registrar: ");
        int cantNotas = input.nextInt();
        Materia materia1 = new Materia(nombreMateria, codigoMateria);
        for (int i = 1; i <= cantNotas; i++) {
            int notaActual;
            do {
                System.out.print("\nDigite el valor de la nota #" + i + " (en la escala entre 0 y 100) ==> ");
                notaActual = input.nextInt();
                if (notaActual < 0 || notaActual > 100)
                    System.out.println("*** ERROR: El valor digitado no es válido. Vuelva a intentarlo... ***");
                else
                    break;
            } while (true);
            materia1.setNota(notaActual);
        }
        materia1.imprimirMateria();
        input.close();
    }
}
