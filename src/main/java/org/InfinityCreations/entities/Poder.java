package org.InfinityCreations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Poder {
    @Id
    private long id;
    private String nombre;
    private String descripcion;
    private int bonoFuerza;

    public Poder(int bonoFuerza, String nombre, String descripcion, long id) {
        this.bonoFuerza = bonoFuerza;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
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

    public int getBonoFuerza() {
        return bonoFuerza;
    }

    public void setBonoFuerza(int bonoFuerza) {
        this.bonoFuerza = bonoFuerza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
