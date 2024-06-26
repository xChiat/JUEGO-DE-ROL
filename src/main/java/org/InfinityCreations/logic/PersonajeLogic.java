package org.InfinityCreations.logic;

import org.InfinityCreations.controller.PersonajeController;
import org.InfinityCreations.entities.Personaje;

import java.util.List;

public class PersonajeLogic {
    public static List<Personaje> personajes;
    private static final PersonajeController personajeController = new PersonajeController();

    public static int buscarPersonaje(String nombre) {
        return personajeController.buscarPersonaje(nombre);
    }
    public static Personaje getPersonaje(String nombre) {
        return personajeController.getPersonaje(nombre);
    }
    public static int buscarPersonajePorUsuario(String nombre, int usuario){
        return personajeController.buscarPersonajePorUsuario( usuario,nombre);
    }
    public static Personaje getPersonajePorUsuario(int usuario, String nombre) {
        return personajeController.getPersonajePorUsuario(usuario, nombre);
    }
    public static boolean crearPersonaje(String nombre, int i, int dez, int fz, int pv, int niv, int exp, int estadoID, int usuarioID, int razaID) {
        return personajeController.crearPersonaje(nombre, i, dez, fz, pv, niv, exp, estadoID, usuarioID, razaID);
    }
    public static boolean eliminarPersonaje(String nombre, int usuario) {
        return personajeController.eliminarPersonaje(nombre, usuario);
    }
    public static boolean actualizarPersonaje(String nombre,int i, int dez, int fz, int pv, int niv, int exp, int estadoID, int usuario) {
        return personajeController.actualizarPersonaje(nombre, i, dez, fz, pv, niv, exp, estadoID, usuario);
    }
    public static List<Personaje> obtenerPersonajes(){
        if(personajes == null){
            actualizarListaDePersonajes();
        }
        return personajes;
    }

    private static void actualizarListaDePersonajes() {
        personajes = PersonajeController.obtenerTodosLosPersonajes();
    }
}
