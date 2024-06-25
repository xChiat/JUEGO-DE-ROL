package org.InfinityCreations.logic;

import org.InfinityCreations.controller.EstadoController;
import org.InfinityCreations.entities.Estado;

import java.util.List;

public class EstadoLogic {
    private static List<Estado> estados;
    private static final EstadoController estadoController = new EstadoController();
    public static int buscarEstado(String nombre) {
        return estadoController.buscarEstado(nombre);
    }
    public static Estado getEstado(String nombre) {
        return estadoController.getEstado(nombre);
    }
    public static boolean crearEstado(String nombre, String descripcion) {
        return EstadoController.crearEstado(nombre, descripcion);
    }
    public static boolean actualizarEstado(String nombre, String descripcion) {
        return EstadoController.actualizarEstado(nombre, descripcion);
    }
    public static boolean eliminarEstado(String nombre) {
        return EstadoController.eliminarEstado(nombre);
    }
    public static List<Estado> obtenerEstados() {
        if (estados == null){
            actualizarListaDeEstados();
        } else if (estados.isEmpty()) {
            return estados;
        }
        return estados;
    }

    private static void actualizarListaDeEstados() {
        estados = EstadoController.obtenerTodosLosEstados();
    }
}
