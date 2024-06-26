package org.InfinityCreations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Armaduras")
public class Armaduras extends Equipo {

    private int bonoVida;

    public Armaduras() {}

    public Armaduras(int id, String nombre, String descripcion, int bonoVida, int precio) {
        super(id, nombre, descripcion, precio);
        this.bonoVida = bonoVida;
    }

    // Getters y setters
    public int getBonoVida() { return bonoVida; }
    public void setBonoVida(int bonoVida) { this.bonoVida = bonoVida; }
}
