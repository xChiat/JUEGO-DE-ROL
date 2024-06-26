package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Armas")
public class Armas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "bono_fuerza")
    private int bonoFuerza;

    @Column(name = "precio")
    private int precio;
    public Armas() {}

    public int getId() {
        return id;
    }

    public Armas(int id, String nombre, String descripcion, int bonoFuerza, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.bonoFuerza = bonoFuerza;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBonoFuerza() {
        return bonoFuerza;
    }

    public void setBonoFuerza(int bonoFuerza) {
        this.bonoFuerza = bonoFuerza;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
