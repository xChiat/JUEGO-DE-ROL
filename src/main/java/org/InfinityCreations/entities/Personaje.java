package org.InfinityCreations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Personaje {
    @Id
    private long id;
    private String nombre;
    private int inteligencia;
    private int fuerza;
    private int destreza;
    private int vida;
    private Usuario jugador;
    private Raza raza;
    private Habilidad[] habilidades;
    private Poder[] poderes;
    private int nivel;
    private int experiencia;
    private String imagen;
    private int slotsInventario;
    private Equipamiento[] equipo;

    public Personaje(long id, String nombre, int inteligencia, int fuerza, int destreza, int vida, Usuario jugador,
                     Raza raza, Habilidad[] habilidades, Poder[] poderes, int nivel, int experiencia, String imagen,
                     int slotsInventario, Equipamiento[] equipo) {
        this.id = id;
        this.nombre = nombre;
        this.inteligencia = inteligencia;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.vida = vida;
        this.jugador = jugador;
        this.raza = raza;
        this.habilidades = habilidades;
        this.poderes = poderes;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.imagen = imagen;
        this.slotsInventario = slotsInventario;
        this.equipo = equipo;
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

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Usuario getJugador() {
        return jugador;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Habilidad[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidad[] habilidades) {
        this.habilidades = habilidades;
    }

    public Poder[] getPoderes() {
        return poderes;
    }

    public void setPoderes(Poder[] poderes) {
        this.poderes = poderes;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getSlotsInventario() {
        return slotsInventario;
    }

    public void setSlotsInventario(int slotsInventario) {
        this.slotsInventario = slotsInventario;
    }

    public Equipamiento[] getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipamiento[] equipo) {
        this.equipo = equipo;
    }
}
