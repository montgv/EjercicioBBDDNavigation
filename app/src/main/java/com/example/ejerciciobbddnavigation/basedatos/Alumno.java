package com.example.ejerciciobbddnavigation.basedatos;

//Definimos la clase Alumno
public class Alumno {
    //Declaramos los diferentes atributos que tiene esta clase
    private String dni;
    private String nombre;
    private String apellidos;
    private String sexo;

    //Declaramos un constructor personas con los diferentes atributos
    public Alumno(String dni, String nombre, String apellidos, String sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    //Declaramos los getter y los setter de los diferentes atributos
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
