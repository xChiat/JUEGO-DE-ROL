package org.InfinityCreations.logic;

import org.InfinityCreations.controller.ArmasController;
import org.InfinityCreations.entities.Armas;

import java.util.List;

public class ArmasLogic {
    private static List<Armas> armas;
    private static final ArmasController armasController = new ArmasController();

    public static int buscarArmas(String nombre) {
        return armasController.buscarArmas(nombre);
    }

    public static Armas getArmas(String nombre) {
        return armasController.getArmas(nombre);
    }

    public static boolean crearArmas(String nombre, String descripcion, int bonoFuerza, int precio) {
        return armasController.crearArmas(nombre, descripcion, bonoFuerza, precio);
    }

    public static boolean actualizarArmas(String nombre, String newNombre, String descripcion, int bonoFuerza, int precio) {
        return armasController.actualizarArmas(nombre, newNombre, descripcion, bonoFuerza, precio);
    }

    public static boolean eliminarArmas(String nombre) {
        return armasController.eliminarArmas(nombre);
    }

    public static List<Armas> obtenerArmas() {
        if (armas == null || armas.isEmpty()) {
            actualizarListaDeArmas();
        }
        return armas;
    }

    public static void actualizarListaDeArmas() {
        armas = ArmasController.obtenerTodasLasArmas();
    }
}