package com.example.fmkid.vista;

public class Validador {
    static final int NO_VALIDAR = -1;
    static final int VALIDAR_ALFA = 0;
    static final int VALIDAR_FECHA = 1;
    static final int VALIDAR_CORREO = 2;
    static final int VALIDAR_COD = 3;
    static final int VALIDAR_TLF = 4;
    static final int VALIDAR_CEL = 5;
    static final int VALIDAR_TELCEL = 6;
    static final int VALIDAR_DNI = 7;
    public static final long MAX_TLF = 9999999L;
    public static final long MIN_TLF = 1000000L;
    public static final long MAX_CEL = 3599999999L;
    public static final long MIN_CEL = 3000000000L;
    public static final long MAX_COD = 219999L;
    public static final long MIN_COD = 210000L;

    public static boolean validarAlfa(String cadena) {
        if (cadena.length() == 0)
            return false;

        for (char letra : cadena.toCharArray()) {
            if (cadena.indexOf(letra) == 0 && !Character.isLetter(letra))
                return false;
            if (!Character.isLetter(letra) && letra != ' ')
                return false;
        }
        return true;
    }

    public static boolean validarCorreo(String correo) {
        if (correo.length() == 0 || !correo.contains("@"))
            return false;

        String[] correoSplit = correo.split("@");
        if (correoSplit.length <= 0 || correoSplit.length > 2)
            return false;

        String usuario = correoSplit[0];
        for (char letra : usuario.toCharArray()) {
            if (usuario.indexOf(letra) == 0 && !Character.isLowerCase(letra))
                return false;
            else if (!Character.isDigit(letra) && !Character.isLowerCase(letra) && !isOnString(letra, ".-_"))
                return false;
            else if (isOnString(letra, "áéíóúäëïöüàèìòùâêîôûñ"))
                return false;
        }

        String dominio = correoSplit[1];
        if (!dominio.contains(".")) {
            return false;
        }

        for (char letra : dominio.toCharArray()) {
            if (!Character.isLowerCase(letra) && letra != '.')
                return false;
            else if (isOnString(letra, "áéíóúäëïöüàèìòùâêîôûñ"))
                return false;
        }

        return true;
    }

    public static boolean validarFecha(String fecha) {
        final int MIN_ANIO = 1920;
        final int MAX_ANIO = 2020;
        int maxDiaMes;

        if (fecha.length() != 10 || !fecha.contains("-"))
            return false;

        String[] camposFecha = fecha.split("-");
        if (camposFecha[0].length() != 4 || camposFecha[1].length() != 2 || camposFecha[2].length() != 2)
            return false;

        int anio = Integer.parseInt(camposFecha[0]);
        int mes = Integer.parseInt(camposFecha[1]);
        int dia = Integer.parseInt(camposFecha[2]);

        if (anio < MIN_ANIO || anio > MAX_ANIO)
            return false;

        switch (mes) {
            case 2:
                if (esBisiesto(anio))
                    maxDiaMes = 29;
                else
                    maxDiaMes = 28;
                break;
            case 4, 6, 9, 11:
                maxDiaMes = 30;
                break;
            default:
                if (mes > 0 && mes < 13)
                    maxDiaMes = 31;
                else
                    return false;
        }

        if (dia < 1 || dia > maxDiaMes)
            return false;

        return true;
    }

    public static boolean validarNum(String numero, long max_val, long min_val) throws Exception {
            long num = Long.parseLong(numero);
            if (num < min_val || num > max_val)
                throw new Exception();
            return true;
    }

    public static boolean validarTelCel(String numero) throws Exception {
        long num = Long.parseLong(numero);
        if ((num >= MIN_TLF && num <= MAX_TLF) || (num >= MIN_CEL && num <= MAX_CEL))
            return true;
        throw new Exception();
    }

    private static boolean isOnString(char letra, String cadena) {
        for (char c : cadena.toCharArray())
            if (letra == c)
                return true;
        return false;
    }

    private static boolean esBisiesto(int anio) {
        if (anio % 100 == 0)
            return anio % 400 == 0;
        else
            return anio % 4 == 0;
    }
}
