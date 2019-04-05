package com.example.edu.clickimage;

public class Usuario {

    private int id;
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private String correo;
    private Perfil_usuario perfil;

    public Usuario(){

        this.nombre = "";
        this.apellido_m = "";
        this.apellido_p = "";
        this.correo = "";
        this.id = 0;
        this.perfil = null;

    }

    public Usuario(Usuario user) {

        this.nombre = user.nombre;
        this.id = user.id;
        this.apellido_p = user.apellido_p;
        this.apellido_m = user.apellido_m;
        this.correo = user.correo;
        this.perfil = user.perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
