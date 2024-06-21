package org.InfinityCreations.logic;

import org.InfinityCreations.controller.HabilidadController;
import org.InfinityCreations.entities.Habilidad;

import java.util.List;

public class HabilidadLogic {
    private static List<Habilidad> habilidades;
    private static final HabilidadController habilidadController = new HabilidadController();
    public static int buscarHabilidad(String nombre) {
        return habilidadController.buscarHabilidad(nombre);
    }
    public static Habilidad getHabilidad(String nombre) {
        return habilidadController.getHabilidad(nombre);
    }
    public static boolean crearHabilidad(String nombre, String descripcion, int id_raza, int bonoDestresa, int bonoInteligencia) {
        return habilidadController.crearHabilidad(nombre, descripcion, id_raza, bonoDestresa, bonoInteligencia);
    }
    public static boolean actualizarHabilidad(String nombre, String descripcion, int bonoDestresa, int bonoInteligencia) {
        return habilidadController.actualizarHabilidad(nombre, descripcion, bonoDestresa, bonoInteligencia);
    }
    public static boolean eliminarHabilidad(String nombre) {
        return habilidadController.eliminarHabilidad(nombre);
    }
    public static List<Habilidad> obtenerHabilidades(){
        if(habilidades == null){
            actualizarListaDeHabilidades();
        }
        return habilidades;
    }
    private static void actualizarListaDeHabilidades(){
        habilidades = HabilidadController.obtenerTodasLasHabilidades();
    }
}
