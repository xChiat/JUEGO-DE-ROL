package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int bonoDestreza;

    @Column(name = "bono_inteligencia")
    private int bonoInteligencia;

    public Habilidad() {}

    public Habilidad(long id, String nombre, String descripcion, Raza raza, int bonoDestreza, int bonoInteligencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raza = raza;
        this.bonoDestreza = bonoDestreza;
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
        return bonoDestreza;
    }

    public void setBonoDestresa(int bonoDestresa) {
        this.bonoDestreza = bonoDestresa;
    }

    public int getBonoInteligencia() {
        return bonoInteligencia;
    }

    public void setBonoInteligencia(int bonoInteligencia) {
        this.bonoInteligencia = bonoInteligencia;
    }
}
