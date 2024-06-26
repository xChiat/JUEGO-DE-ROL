package org.InfinityCreations.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Personaje")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "inteligencia")
    private int inteligencia;

    @Column(name = "destreza")
    private int destreza;

    @Column(name = "fuerza")
    private int fuerza;

    @Column(name = "puntos_vida")
    private int puntosVida;

    @Column(name = "nivel")
    private int nivel;

    @Column(name = "experiencia")
    private int experiencia;

    @Column(name = "estado_id")
    private int estadoId;

    @Column(name = "usuario_id")
    private int usuarioId;

    @Column(name = "raza_id")
    private int razaId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Poder> poderes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Habilidad> habilidades;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Equipo> equipamiento;

    public Personaje() {}

    public Personaje(int id, String nombre, int inteligencia, int destreza, int fuerza, int puntosVida, int nivel, int experiencia, int estadoId, int usuarioId, int razaId) {
        this.id = id;
        this.nombre = nombre;
        this.inteligencia = inteligencia;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.puntosVida = puntosVida;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.estadoId = estadoId;
        this.usuarioId = usuarioId;
        this.razaId = razaId;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

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

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getRazaId() {
        return razaId;
    }

    public void setRazaId(int razaId) {
        this.razaId = razaId;
    }

    public List<Poder> getPoderes() { return poderes; }
    public void setPoderes(List<Poder> poderes) { this.poderes = poderes; }

    public List<Habilidad> getHabilidades() { return habilidades; }
    public void setHabilidades(List<Habilidad> habilidades) { this.habilidades = habilidades; }

    public List<Equipo> getEquipamiento() { return equipamiento; }
    public void setEquipamiento(List<Equipo> equipamiento) { this.equipamiento = equipamiento; }
}
