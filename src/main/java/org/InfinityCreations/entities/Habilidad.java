package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que la BD genere automáticamente el ID
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "raza_id")
    private Raza raza;

    @Column(name = "bono_destreza")
    private int bonoDestresa;

    @Column(name = "bono_inteligencia")
    private int bonoInteligencia;

    public Habilidad() {}

    public Habilidad(long id, String nombre, String descripcion, Raza raza, int bonoDestresa, int bonoInteligencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raza = raza;
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

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
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
