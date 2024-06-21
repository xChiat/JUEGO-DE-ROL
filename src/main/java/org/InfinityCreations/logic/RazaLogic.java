package org.InfinityCreations.logic;

import org.InfinityCreations.controller.RazaController;
import org.InfinityCreations.entities.Raza;
import java.util.List;

public class RazaLogic {
    private static List<Raza> razas;
    private static final RazaController razaController = new RazaController();

    public static int buscarRaza(String nombre) {
        return razaController.buscarRaza(nombre);
    }

    public static Raza getRaza(String nombre) {
        return razaController.getRaza(nombre);
    }
    public static boolean crearRaza(String nombre, String descripcion) {
        boolean resultado = RazaController.crearRaza(nombre, descripcion);
        if (resultado) {
            actualizarListaDeRazas();
        }
        return resultado;
    }

    public static boolean eliminarRaza(String nombre) {
        boolean resultado = RazaController.eliminarRaza(nombre);
        if (resultado) {
            actualizarListaDeRazas();
        }
        return resultado;
    }

    public static List<Raza> obtenerRazas() {
        if (razas == null) {
            actualizarListaDeRazas();
        }
        return razas;
    }

    private static void actualizarListaDeRazas() {
        razas = RazaController.obtenerTodasLasRazas();
    }
}