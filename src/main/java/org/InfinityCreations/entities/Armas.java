package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Armas")
public class Armas extends Equipo {

    private int bonoFuerza;

    public Armas() {}

    public Armas(int id, String nombre, String descripcion, int bonoFuerza, int precio) {
        super(id, nombre, descripcion, precio);
        this.bonoFuerza = bonoFuerza;
    }

    // Getters y setters
    public int getBonoFuerza() { return bonoFuerza; }
    public void setBonoFuerza(int bonoFuerza) { this.bonoFuerza = bonoFuerza; }
}
