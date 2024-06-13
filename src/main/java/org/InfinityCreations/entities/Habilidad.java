package org.InfinityCreations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Habilidad {
    @Id
    private long id;
    private String nombre;
    private String descripcion;
    private int bonoDestresa;
    private int bonoInteligencia;

    public Habilidad(long id, String nombre, String descripcion, int bonoDestresa, int bonoInteligencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.bonoDestresa = bonoDestresa;
        this.bonoInteligencia = bonoInteligencia;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBonoDestresa() {
        return bonoDestresa;
    }

    public void setBonoDestresa(int bonoDestresa) {
        this.bonoDestresa = bonoDestresa;
    }

    public int getBonoInteligencia() {
        return bonoInteligencia;
    }

    public void setBonoInteligencia(int bonoInteligencia) {
        this.bonoInteligencia = bonoInteligencia;
    }
}
