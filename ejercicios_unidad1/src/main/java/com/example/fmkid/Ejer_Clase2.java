package com.example.fmkid;
import java.util.Scanner;

public class Ejer_Clase2 {
    public static void main(String[] args){
        ej_08();
    }

    public static void ej_01(){
    // Programa que pida por teclado la fecha de nacimiento de una persona 
    // (día, mes, año) y calcule su número de la suerte, sumando el día, mes y año de la fecha 
    // de nacimiento y a continuación sumando las cifras obtenidas en la suma.
        Scanner input =  new Scanner(System.in);
        System.out.println("== Programa que calcula el número de la suerte (sumando las cifras de su fecha de nacimiento) ==\n");
        System.out.print("Por favor ingrese su fecha de nacimiento (DD/MM/AAAA): ");
        String fechaNacimiento = input.nextLine();
        input.close();
        try {
            fechaNacimiento = fechaNacimiento.replaceAll("/", "");
            int sumaDigitos = 0;
            for (String digito: String.valueOf(fechaNacimiento).split(""))
                sumaDigitos += Integer.parseInt(digito);
                System.out.println("Su numero de la suerte es: " + sumaDigitos);
        } 
        catch (Exception e) {
            System.out.println("La fecha ingresada no es válida");
        }
    }

    public static void ej_02(){
    // Programa que lea dos variables enteras N y m y le quite a N sus m últimas cifras
        Scanner input =  new Scanner(System.in);
        System.out.print("Por favor ingrese un número entero grande: ");
        int N = input.nextInt();
        System.out.print("Ahora, por favor ingrese un número de cifras a quitarle al número " + N + ": ");
        int m = input.nextInt();
        input.close();
        if (String.valueOf(N).length() >= m) {
            int resultado = N / (int) Math.pow(10, m);
            System.out.println("El número " + N + " sin las " + m + " últimas cifras es: " + resultado);
        }
        else {
            System.out.println("El número de cifras digitado es mayor que la cantidad de cifras del número " + N);
        }
    }

    public static void ej_03() {
    // Programa que lea un carácter por teclado y compruebe si es una letra mayúscula.
        Scanner input =  new Scanner(System.in);
        System.out.print("Por favor ingrese una letra para determinar si es mayúscula: ");
        char letra = input.next().charAt(0);
        input.close();
        if (letra >= 'A'  && letra <= 'Z')
            System.out.println("La letra digitada ('" + letra + "') es mayúscula");
        else if (letra >= 'a'  && letra <= 'z')
            System.out.println("La letra digitada ('" + letra + "') NO es mayúscula");
        else
            System.out.println("El caracter ingresado ('" + letra + "') ni siquiera es una letra");
    }

    public static void ej_04() {
    // Programa que lea una variable entera mes y compruebe si el valor corresponde a un mes de 30 días,
    // de 31 o de 28. Supondremos que febrero tiene 28 días. Se mostrará además el nombre del mes.
    // Se debe comprobar que el valor introducido esté comprendido entre 1 y 12.
        String nombreMes = "";
        int diasMes = 31;
        Scanner input =  new Scanner(System.in);
        System.out.print("Por favor ingrese el número correspondiente al mes: ");
        int numeroMes = input.nextInt();
        input.close();
        switch (numeroMes) {
            case 1:
                nombreMes = "enero";
                break;
            case 2:
                nombreMes = "febrero";
                diasMes -= 3;
                break;
            case 3:
                nombreMes = "marzo";
                break;
            case 4:
                nombreMes = "abril";
                diasMes -= 1;
                break;
            case 5:
                nombreMes = "mayo";
                break;
            case 6:
                nombreMes = "junio";
                diasMes -= 1;
                break;
            case 7:
                nombreMes = "julio";
                break;
            case 8:
                nombreMes = "agosto";
                break;
            case 9:
                nombreMes = "septiembre";
                diasMes -= 1;
                break;
            case 10:
                nombreMes = "octubre";
                break;
            case 11:
                nombreMes = "noviembre";
                diasMes -= 1;
                break;
            case 12:
                nombreMes = "diciembre";
        }
        if (nombreMes == "")
            System.out.println("El valor digitado no corresponde a un mes del año");
        else
            System.out.println("El mes " + numeroMes + " corresponde a " + nombreMes + ", que tiene " + diasMes + " días");
    }

    public static void ej_05() {
    // Realizar programa que muestre los números del 1 al 100 que no sean múltiplos de 3,
    // utilizando cada una las instrucciones repetitivas (while, do while, for)
        System.out.println("== Programa que muestra los números del 1 al 100 que no son múltiplos de 3 ==\n");

        int cont = 1;
        System.out.println("Usando el ciclo 'while':\n");
        while(cont <= 100) {
            if ((cont % 3) != 0) {
                System.out.print(cont);
                if (cont != 100)
                    System.out.print(" - ");
            }
            cont++;
        }
        
        cont = 1;
        System.out.println("\n\nUsando el ciclo 'do - while':\n");
        do {
            if ((cont % 3) != 0) {
                System.out.print(cont);
                if (cont != 100)
                    System.out.print(" - ");
            }
            cont++;
        } while(cont <= 100);

        System.out.println("\n\nUsando el ciclo 'for':\n");
        for (cont = 1; cont <= 100; cont++) {
            if ((cont % 3) != 0) {
                System.out.print(cont);
                if (cont != 100)
                    System.out.print(" - ");
            }
        }
    }

    public static void ej_06() {
    //Mostrar los N primeros términos de la serie de Fibonacci
        System.out.println("== Programa que muestra los N primeros números de la serie de Fibonacci ==\n");
        Scanner input =  new Scanner(System.in);
        System.out.print("Por favor ingrese la cantidad de números que quiere mostrar: ");
        int N = input.nextInt();
        input.close();
        if (N < 1 || N > 93)
            System.out.print("El número ingresado no es válido");
        else {
            System.out.print("\nLos " + N + " primeros números de la serie de Fibonacci son: ");
            long anterior = 0, siguiente = 1;
            for (int i = 1; i < N; i++){
                System.out.print(anterior + ", ");
                long temp = anterior;
                anterior = siguiente;
                siguiente += temp;
            }
            System.out.print(anterior);
        }
    }
    
    public static void ej_07() {
    // Programa que muestre en líneas separadas lo siguiente: 
    // ZYWXVUTSRQPONMLKJIHGFEDCBA
    // YWXVUTSRQPONMLKJIHGFEDCBA
    // WXVUTSRQPONMLKJIHGFEDCBA
    // ....
    // DCBA
    // CBA
    // BA
    // A"
        String letras = "ZYWXVUTSRQPONMLKJIHGFEDCBA";
        while(letras.length() > 0){
            System.out.println(letras);
            letras = letras.replace(String.valueOf(letras.charAt(0)), "");
        }
    }

    public static void ej_08() {
    //Leer un número N y calcular la suma de los factoriales de los números desde 0 hasta N
        System.out.println("== Programa que suma los factoriales de los N primeros números ==\n");
        Scanner input =  new Scanner(System.in);
        System.out.print("Por favor ingrese la cantidad de números para sumar sus factoriales: ");
        int N = input.nextInt();
        input.close();
        if (N < 1 || N > 20)
            System.out.print("El número ingresado no es válido");
        else {
            long suma = 0, factorial = 1;
            for (int i = 1; i <= N; i++) {
                factorial *= i; 
                suma += factorial;
            }
            System.out.print("\nLa suma de los factoriales de los " + N + " primeros números es: " + suma);
        }
    }

    /* public static long factorial(int valor) {
        if (valor == 0)
            return 1;
        else
            return valor * factorial(valor - 1);
    } */
}