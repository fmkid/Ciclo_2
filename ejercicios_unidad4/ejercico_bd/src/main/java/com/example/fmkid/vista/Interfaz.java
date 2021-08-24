package com.example.fmkid.vista;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.example.fmkid.controlador.*;
import com.example.fmkid.modelo.Estudiante;

public class Interfaz extends JOptionPane {
    private static final String MSJ_ERROR = "El valor ingresado no es válido...\n\nIntente de nuevo";
    private static final String ERROR_DB = "\n\nDetalle del error:\n*** %s ***";
    private static Interaccion control = new Interaccion();
    private static boolean inicio = false;

    public static void menuPrincipal() {
        final String TITULO = "Instituto La Floresta - Menú Principal";
        final String MENU = "Seleccione tarea a realizar:\n\n" + "1. Ingresar estudiante\n"
                + "2. Modificar estudiante\n" + "3. Eliminar Estudiante\n" + "4. Consulta de estudiantes\n"
                + "5. Salir del programa\n\n" + "Digite su opción:";
        int opcion;

        if (!inicio)
            try {
                control.conectarConDB();
                mensajeStandard("¡Bienvenido al sistema de registro académico del Instituto La Floresta!"
                        + "\n\nPresione OK para continuar...", "Ingreso al sistema");
                inicio = true;
            } catch (Exception e) {
                mensajeError("¡No se ha podido conectar con la base de datos! Por favor, contacte a soporte técnico"
                        + String.format(ERROR_DB, e.getMessage()));
                System.exit(0);
            }

        while (inicio)
            try {
                opcion = Integer.parseInt(entradaStandard(MENU, TITULO, Validador.NO_VALIDAR));
                switch (opcion) {
                    case 1:
                        IngresarEstudiante();
                        break;
                    case 2:
                        ModificarEstudiante();
                        break;
                    case 3:
                        EliminarEstudiante();
                        break;
                    case 4:
                        ConsultarEstudiante();
                        break;
                    case 5:
                        mensajeStandard("Gracias por usar el sistema de registro\n\n¡Hasta pronto!",
                                "Salir del sistema");
                        control.desconectarBD();
                        System.exit(0);
                        break;
                    default:
                        if (opcion >= 0 && opcion <= 9)
                            mensajeError("La opción " + opcion + " no existe");
                        else
                            mensajeError();
                }
            } catch (Exception e) {
                mensajeError();
            }
    }

    public static void IngresarEstudiante() {
        final String TITULO = "Ingresar Estudiante";

        try {
            Estudiante estNuevo = new Estudiante();
            estNuevo.setDni(control.comprobarDni(Long
                    .parseLong(entradaStandard("Ingrese el documento de identidad:", TITULO, Validador.VALIDAR_DNI))));
            estNuevo.setApellidos(entradaStandard("Ingrese el/los apellidos:", TITULO, Validador.VALIDAR_ALFA));
            estNuevo.setNombres(entradaStandard("Ingrese el/los nombres:", TITULO, Validador.VALIDAR_ALFA));
            estNuevo.setFechaNacimiento(
                    entradaStandard("Ingrese la fecha de nacimiento (AAAA-MM-DD):", TITULO, Validador.VALIDAR_FECHA));
            estNuevo.setCarrera(entradaStandard("Ingrese la carrera:", TITULO, Validador.VALIDAR_ALFA));
            estNuevo.setCorreo(entradaStandard("Ingrese el correo electrónico", TITULO, Validador.VALIDAR_CORREO));
            estNuevo.setTelefono(
                    Long.parseLong(entradaStandard("Ingrese el numero telefónico:", TITULO, Validador.VALIDAR_TLF)));
            estNuevo.setCelular(
                    Long.parseLong(entradaStandard("Ingrese el numero celular:", TITULO, Validador.VALIDAR_CEL)));
            estNuevo.setCodigo(control.generarCodigo());
            control.agregarEstudiante(estNuevo);
            mensajeStandard("Se ha ingresado al estudiante en la base de datos correctamente\n\nCódigo del estudiante: "
                    + estNuevo.getCodigo(), TITULO);
        } catch (SQLWarning e) {
            mensajeError("El documento de identidad ingresado ya se encuentra registrado\n\n"
                    + "¡No se puede agregar un estudiante con el mismo documento de identidad!");
        } catch (SQLException e) {
            mensajeError("¡Ha ocurrido un error al momento de realizar el registro del estudiante en la base de datos!"
                    + String.format(ERROR_DB, e.getMessage()));
        } catch (Exception e) {
            mensajeError();
        }
    }

    public static void ModificarEstudiante() {

    }

    public static void EliminarEstudiante() {

    }

    public static void ConsultarEstudiante() {
        final String TITULO = "Consulta de estudiantes";
        final String MENU = "Seleccione consulta a realizar:\n\n" + "1. Buscar por código estudiantil\n"
                + "2. Buscar por documento de identidad\n" + "3. Buscar por nombre(s)\n" + "4. Buscar por apellido(s)\n"
                + "5. Buscar por correo electrónico\n" + "6. Buscar por fecha de nacimiento\n"
                + "7. Buscar por número de teléfono o celular\n" + "8. Buscar por carrera\n"
                + "9. Consultar cantidad de estudiantes por carrera\n" + "10. Ver todos los estudiantes registrados\n"
                + "11. Volver al menú principal\n\n" + "Digite su opción:";
        int opcion;

        while (true)
            try {
                ArrayList<Estudiante> resultado = null;
                opcion = Integer.parseInt(entradaStandard(MENU, TITULO, Validador.NO_VALIDAR));
                switch (opcion) {
                    case 1:
                        resultado = control
                                .buscarCodigo(Long.parseLong(entradaStandard("Ingrese el código estudiantil:",
                                        "Búsqueda por código", Validador.VALIDAR_COD)));
                        break;
                    case 2:
                        resultado = control
                                .buscarDni(Long.parseLong(entradaStandard("Ingrese el documento de identidad:",
                                        "Búsqueda por documento de identidad", Validador.VALIDAR_DNI)));
                        break;
                    case 3:
                        resultado = control.buscarNombres(entradaStandard("Ingrese el/los nombres:",
                                "Búsqueda por nombres", Validador.VALIDAR_ALFA));
                        break;
                    case 4:
                        resultado = control.buscarApellidos(entradaStandard("Ingrese los apellidos:",
                                "Búsqueda por apellidos", Validador.VALIDAR_ALFA));
                        break;
                    case 5:
                        resultado = control.buscarCorreo(entradaStandard("Ingrese el correo electrónico:",
                                "Búsqueda por correo", Validador.VALIDAR_CORREO));
                        break;
                    case 6:
                        resultado = control.buscarFecha(entradaStandard("Ingrese la fecha (AAAA-MM-DD):",
                                "Búsqueda por fecha de nacimiento", Validador.VALIDAR_FECHA));
                        break;
                    case 7:
                        resultado = control.buscarTelCel(
                                Long.parseLong(entradaStandard("Ingrese el número telefónico o el celular:",
                                        "Búsqueda por teléfono", Validador.VALIDAR_TELCEL)));
                        break;
                    case 8:
                        resultado = control.buscarCarrera(
                                entradaStandard("Ingrese la carrera:", "Búsqueda por carrera", Validador.VALIDAR_ALFA));
                        break;
                    case 9:
                        mensajeStandard(control.contarPorCarrera(entradaStandard("Ingrese la carrera:",
                                "Número de estudiantes por carrera", Validador.VALIDAR_ALFA)), TITULO);
                        break;
                    case 10:
                        resultado = control.totalRegistros();
                        break;
                    case 11:
                        menuPrincipal();
                        break;
                    default:
                        if (opcion >= 0 && opcion <= 15)
                            mensajeError("La opción " + opcion + " no existe");
                        else
                            mensajeError();
                }

                if (resultado != null)
                    mostrarRegistros(resultado, TITULO);
            } catch (SQLException e) {
                mensajeError("¡Ha ocurrido un error al momento de realizar la consulta en la base de datos!"
                        + String.format(ERROR_DB, e.getMessage()));
            } catch (Exception e) {
                mensajeError();
            }
    }

    private static void mensajeError() {
        mensajeError(MSJ_ERROR);
    }

    private static void mensajeError(String msjError) {
        showMessageDialog(null, msjError, "Error", ERROR_MESSAGE);
    }

    private static void mensajeStandard(String msj, String titulo) {
        showMessageDialog(null, msj, titulo, NO_OPTION);
    }

    private static void mostrarRegistros(ArrayList<Estudiante> lista, String titulo) {
        int i = lista.size();
        if (i > 0) {
            mensajeStandard("Se ha encontrado un total de " + i + " registros de estudiantes", titulo);
            i = 0;
            for (Estudiante estudiante : lista)
                mensajeStandard(estudiante.toString(), "Resultado de la consulta #" + (i++ + 1));
        } else
            mensajeStandard("No se han encontrado registros asociados a la búsqueda", titulo);
    }

    private static String entradaStandard(String msgEntrada, String titulo, int tipoValidacion) throws Exception {
        String entrada;
        boolean validacion;

        do {
            entrada = showInputDialog(null, msgEntrada, titulo, NO_OPTION);
            switch (tipoValidacion) {
                case Validador.VALIDAR_ALFA:
                    validacion = Validador.validarAlfa(entrada);
                    break;
                case Validador.VALIDAR_FECHA:
                    validacion = Validador.validarFecha(entrada);
                    break;
                case Validador.VALIDAR_CORREO:
                    validacion = Validador.validarCorreo(entrada);
                    break;
                case Validador.VALIDAR_COD:
                    validacion = Validador.validarNum(entrada, Validador.MAX_COD, Validador.MIN_COD);
                    break;
                case Validador.VALIDAR_TLF:
                    validacion = Validador.validarNum(entrada, Validador.MAX_TLF, Validador.MIN_TLF);
                    break;
                case Validador.VALIDAR_CEL:
                    validacion = Validador.validarNum(entrada, Validador.MAX_CEL, Validador.MIN_CEL);
                    break;
                case Validador.VALIDAR_DNI:
                    validacion = Validador.validarNum(entrada, Integer.MAX_VALUE, 0);
                    break;
                case Validador.VALIDAR_TELCEL:
                    validacion = Validador.validarTelCel(entrada);
                    break;
                default:
                    validacion = true;
            }
            if (!validacion)
                mensajeError();
        } while (!validacion);
        return entrada;
    }
}