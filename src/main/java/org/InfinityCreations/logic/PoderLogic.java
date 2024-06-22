package org.InfinityCreations.logic;

import org.InfinityCreations.controller.PoderController;
import org.InfinityCreations.entities.Poder;

import java.util.List;

public class PoderLogic {
    private static List<Poder> poderes;
    private static final PoderController poderController = new PoderController();
    public static int buscarPoder(String nombre){
        return poderController.buscarPoder(nombre);
    }
    public static Poder getPoder(String nombre){
        return poderController.getPoder(nombre);
    }
    public static boolean crearPoder(String nombre, String descripcion, int id_raza, int bonoFuerza){
        return poderController.crearPoder(nombre, descripcion, id_raza, bonoFuerza);
    }
    public static boolean actualizarPoder(String nombre, String descripcion, int bonoFuerza){
        return poderController.actualizarPoder(nombre, descripcion, bonoFuerza);
    }
    public static boolean eliminarPoder(String nombre){
        return poderController.eliminarPoder(nombre);
    }
    public static List<Poder> obtenerPoderes(){
        if(poderes == null){
            actualizarListaDePoderes();
        }
        return poderes;
    }
    public static List<Poder> obtenerPoderesxRaza(int idRaza){
        return PoderController.obtenerPoderesxRaza(idRaza);
    }
    public static void actualizarListaDePoderes(){
        poderes = PoderController.obtenerTodosLosPoderes();
    }
}
