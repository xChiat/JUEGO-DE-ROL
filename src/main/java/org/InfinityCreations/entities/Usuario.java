package org.InfinityCreations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Usuario {
    @Id
    private long id;
    private String nombre;
    private String correo;
    private String perfil;
    private Timestamp fechaRegistro;
    private String password;
    private String nacionalidad;
    public Usuario(long id, String nombre, String correo, String perfil, Timestamp fechaRegistro, String password, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
        this.fechaRegistro = fechaRegistro;
        this.password = password;
        this.nacionalidad = nacionalidad;

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
