package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Armaduras")
public class Armaduras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "bono_Vida")
    private int bonoVida;

    @Column(name = "precio")
    private int precio;

    public Armaduras() {}

    public Armaduras(int id, String nombre, String descripcion, int bonoVida, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.bonoVida = bonoVida;
        this.precio = precio;
    }

    public int getId() {
        return id;
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

    public int getBonoVida() {
        return bonoVida;
    }

    public void setBonoVida(int bonoVida) {
        this.bonoVida = bonoVida;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
