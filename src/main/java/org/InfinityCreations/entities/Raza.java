package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "razas")
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false, unique = true,name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    // Constructor predeterminado
    public Raza() {
    }

    // Constructor con argumentos
    public Raza(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
