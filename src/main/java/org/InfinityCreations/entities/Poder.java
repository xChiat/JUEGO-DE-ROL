package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "poderes")
public class Poder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "raza_id", referencedColumnName = "id")
    private Raza raza;

    @Column(name = "bono_fuerza")
    private int bonoFuerza;

    public Poder() {
    }

    public Poder(int id, String nombre, String descripcion, Raza raza, int bonoFuerza) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raza = raza;
        this.bonoFuerza = bonoFuerza;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public int getBonoFuerza() {
        return bonoFuerza;
    }

    public void setBonoFuerza(int bonoFuerza) {
        this.bonoFuerza = bonoFuerza;
    }
}