package org.InfinityCreations.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que la BD genere automáticamente el ID
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "perfil_id") // Asume que hay una columna perfil_id que es una FK a la tabla Perfil
    private Perfil perfil;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "password")
    private String password;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    // Constructor vacío necesario para JPA
    public Usuario() {}

    // Constructor completo
    public Usuario(long id, String nombre, String correo, Perfil perfil, LocalDateTime fechaRegistro, String password, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
        this.fechaRegistro = fechaRegistro;
        this.password = password;
        this.nacionalidad = nacionalidad;
    }

    // Getters y setters

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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
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
