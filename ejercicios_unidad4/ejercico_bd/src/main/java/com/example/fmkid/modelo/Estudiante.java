package com.example.fmkid.modelo;

public class Estudiante {
    private long codigo;
    private long dni;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String correo;
    private String carrera;
    private long telefono;
    private long celular;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return String.format(
                "Datos del estudiante:\n=================\nCódigo: %d\n"
                        + "Nombre completo: %s %s\nDocumento de identidad: %d\n"
                        + "Fecha de nacimiento: %s\nCarrera: %s\nCorreo electrónico: %s\nTeléfono: %d\nCelular: %d\n",
                codigo, nombres, apellidos, dni, fechaNacimiento, carrera, correo, telefono, celular);
    }
}
