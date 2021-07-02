package com.example.fmkid;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Ejercicios {
    public static void main(String[] args) {
        ej_06();
    }

    public static void ej_01(){
    // Realizar la suma, la resta, la división y la multiplicación de dos números
    // leídos por teclado y mostrar en pantalla “La <operación> de <número 1>
    // y <número 2> es igual a <resultado> ”.
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el primer número entero: ");
        int num1 = entrada.nextInt();
        System.out.print("Digite el segundo número entero: ");
        int num2 = entrada.nextInt();
        System.out.println("La suma de los números " + num1 + " y " + num2 + " es: " +  (num1 + num2));
        System.out.println("La resta de los números " + num1 + " y " + num2 + " es: " +  (num1 - num2));
        System.out.println("El producto de los números " + num1 + " y " + num2 + " es: " +  (num1 * num2));
        System.out.println("La división de los números " + num1 + " y " + num2 + " es: " + ((double) num1 / num2));
        entrada.close();
    }

    public static void ej_02(){
    // Realizar un programa que realice el promedio de las notas de un alumno,
    // para ello el programa va a tener que solicitar el nombre del alumno y las
    // notas de las 3 evaluaciones. Si el alumno tiene un promedio mayor o igual a
    // 3.0 también debe imprimir “Aprobado”, si no alcanzó esa nota debe imprimir
    // “Reprobado” . Requisitos: Las notas que se ingresan pueden tener decimales.
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el nombre del alumno: ");
        String nombre = entrada.nextLine();
        float suma_nota = 0.0f;
        for (int i = 1; i <= 3; i++){
            System.out.print("Digite la nota #1: ");
            suma_nota += entrada.nextDouble();
        }
        entrada.close();
        float promedio = suma_nota / 3;
        promedio = Math.round(promedio * 10) / 10.0f;
        System.out.print("El promedio del alumno " + nombre + " es: " +  promedio + " -> ");
        if (promedio >= 3.0f){
            System.out.println("APROBADO");
        } 
        else{
            System.out.println("REPROBADO");
        } 
    }

    public static void ej_03(){
    // Solicitar un número al usuario y mostrar la tabla de multiplicar de ese número,
    // desde el 0 hasta el 10. Truco: Usa un bucle for para recorrer la tabla y mostrar los datos.
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite un número entero para mostrar su tabla de multiplicar: ");
        int num = entrada.nextInt();
        entrada.close();
        for (int i = 0; i <= 10; i++){
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }

    public static void ej_04(){
    // Generar un número aleatorio entre el 1 y el 100, el usuario lo tiene que
    // adivinar introduciendo el número por teclado. En el caso que el número a
    // adivinar sea mayor al ingresado, decirle al usuario “El número que busca es
    // mayor”, de lo contrario, “El número que busca es menor”. El programa
    // finalizará cuando se introduzca el número correcto. Nota: usar la clase
    // Random para generar el número aleatorio.
        Scanner entrada = new Scanner(System.in);
        int numero, aleatorio, cont = 0;
        aleatorio = (int)(Math.random() * 100 + 1);
        System.out.println("Estoy pensando un número entre 1 y 100... ¿Adivina cuál es?\n");
        do {
            System.out.print("Digita tu respuesta ==> ");
            numero = entrada.nextInt();
            cont++;
            if(aleatorio > numero){
                System.out.println("¡Te equivocaste!... El numero a adivinar es mayor\n");
            } else if (aleatorio < numero){
                System.out.println("¡Qué mal!... El numero a adivinar es menor\n");
            } else {
                System.out.println("FELICIDADES. ¡Has acertado el número en " + cont + " intentos!");
                break;
            }
        } while (true);
        entrada.close();
    }

    public static void ej_05() {
    //Pedir un número, comprobar si es primo y preguntar si quiere introducir más (S/N) y volver a pensar.
        boolean esPrimo;
        char opcion;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.print("Digite un número entero (mayor o igual a 2) para comprobar si es primo o no: ");
            int numero = entrada.nextInt();
            if (numero < 2)
                System.out.println("El número " + numero + " no es una entrada válida");
            else {
                esPrimo = true;
                if ((numero % 2) == 0)
                    esPrimo = false;
                else
                    for(int i = 3; i <= (int) Math.sqrt((double) numero); i += 2)
                        if (numero % i == 0){
                            esPrimo = false;
                            break;
                        }
                if (esPrimo)
                    System.out.println("El numero " + numero + " es primo");
                else
                    System.out.println("El numero " + numero + " no es primo");
            }
            System.out.print("\n¿Desea volver a intentar con otro número (S/N)?: ");
            opcion = entrada.next().toUpperCase().charAt(0); 
            System.out.println();  
        } while (opcion != 'N');
        entrada.close();
    }

    public static void ej_06() {
    // Pide por ventana emergente el nombre, edad y salario y muestra el salario
    // * Si es menor de 16 no tiene edad para trabajar
    // * Entre 19 y 50 años el salario es un 5 por ciento más
    // * Entre 51 y 60 años el salario es un 10 por ciento más
    // * Si es mayor de 60 el salario es un 15 por ciento más
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad de la persona:"));
        if (edad < 0)
            JOptionPane.showMessageDialog(null, "¡La edad ingresada no es correcta!");
        else if (edad < 16)
            JOptionPane.showMessageDialog(null, nombre  + " aún no tiene la edad para trabajar");
        else {
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario de " + nombre + ":"));
            if (edad >= 19 && edad <= 50)
                salario *= 1.05;
            else if (edad >= 51 && edad <= 60)
                salario *= 1.1;
            else if (edad > 60)
                salario *= 1.15;
            JOptionPane.showMessageDialog(null, "El salario total de " + nombre  + " es: $" + (int) salario);
        }
    }
}
