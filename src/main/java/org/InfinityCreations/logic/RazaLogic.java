package org.InfinityCreations.logic;

import org.InfinityCreations.controller.RazaController;
import org.InfinityCreations.entities.Raza;

public class RazaLogic {
    private static final RazaController razaController = new RazaController();
    public static int buscarRaza(String nombre) {
        return razaController.buscarRaza(nombre);
    }
    public static Raza getRaza(String nombre) {
        return razaController.getRaza(nombre);
    }
    public static Boolean crearRaza(String nombre, String descripcion) {
        return razaController.crearRaza(nombre, descripcion);
    }
    public static Boolean eliminarRaza(String nombre) {
        return razaController.eliminarRaza(nombre);
    }
    public static String ListarRazas() {
        return razaController.ListarRazas();
    }
}
