package org.InfinityCreations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Raza {
    @Id
    private long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Habilidad[] skillsByRaza;
    private Poder[] powersByRaza;
    //Constructor
    public Raza(long id, String nombre, String descripcion, String imagen, Habilidad[] skillsByRaza, Poder[] powersByRaza) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.skillsByRaza = skillsByRaza;
        this.powersByRaza = powersByRaza;
    }
    //Getters and setters
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Habilidad[] getSkillsByRaza() {
        return skillsByRaza;
    }

    public void setSkillsByRaza(Habilidad[] skillsByRaza) {
        this.skillsByRaza = skillsByRaza;
    }

    public Poder[] getPowersByRaza() {
        return powersByRaza;
    }

    public void setPowersByRaza(Poder[] powersByRaza) {
        this.powersByRaza = powersByRaza;
    }
}