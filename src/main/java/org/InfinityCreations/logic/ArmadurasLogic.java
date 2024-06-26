package org.InfinityCreations.logic;

import org.InfinityCreations.controller.ArmadurasController;
import org.InfinityCreations.entities.Armaduras;

import java.util.List;

public class ArmadurasLogic {
    private static List<Armaduras> armaduras;
    private static final ArmadurasController armadurasController = new ArmadurasController();

    public static int buscarArmaduras(String nombre) {
        return armadurasController.buscarArmaduras(nombre);
    }

    public static Armaduras getArmaduras(String nombre) {
        return armadurasController.getArmadura(nombre);
    }

    public static boolean crearArmaduras(String nombre, String descripcion, int bonoVida, int precio) {
        return armadurasController.crearArmaduras(nombre, descripcion, bonoVida, precio);
    }

    public static boolean actualizarArmaduras(String nombre, String newNombre, String descripcion, int bonoVida, int precio) {
        return armadurasController.actualizarArmaduras(nombre, newNombre, descripcion, bonoVida, precio);
    }

    public static boolean eliminarArmaduras(String nombre) {
        return armadurasController.eliminarArmaduras(nombre);
    }

    public static List<Armaduras> obtenerArmaduras() {
        if (armaduras == null) {
            actualizarListaDeArmaduras();
        }
        return armaduras;
    }

    public static void actualizarListaDeArmaduras() {
        armaduras = ArmadurasController.obtenerTodasLasArmaduras();
    }
}