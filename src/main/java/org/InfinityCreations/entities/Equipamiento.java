package org.InfinityCreations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Equipamiento {
    @Id
    private long id;
    private String nombre;
    private String descripcion;
    private String tipo; // armas o armaduras
    private int bonoFuerza;
    private int bonoDestreza;
    private int bonoInteligencia; // proximamente objetos magicos
    private int proteccion; //bono de Vida
    private String imagen;
    private int precio;

    public Equipamiento(long id, String nombre, int precio, String imagen, int proteccion, int bonoDestreza, int bonoFuerza, String tipo, String descripcion, int bonoInteligencia) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.proteccion = proteccion;
        this.bonoDestreza = bonoDestreza;
        this.bonoFuerza = bonoFuerza;
        this.tipo = tipo;
        this.descripcion = descripcion;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getBonoFuerza() {
        return bonoFuerza;
    }

    public void setBonoFuerza(int bonoFuerza) {
        this.bonoFuerza = bonoFuerza;
    }

    public int getBonoDestreza() {
        return bonoDestreza;
    }

    public void setBonoDestreza(int bonoDestreza) {
        this.bonoDestreza = bonoDestreza;
    }

    public int getBonoInteligencia() {
        return bonoInteligencia;
    }

    public void setBonoInteligencia(int bonoInteligencia) {
        this.bonoInteligencia = bonoInteligencia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getProteccion() {
        return proteccion;
    }

    public void setProteccion(int proteccion) {
        this.proteccion = proteccion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
