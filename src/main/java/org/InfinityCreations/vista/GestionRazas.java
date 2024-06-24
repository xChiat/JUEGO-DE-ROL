package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Raza;
import org.InfinityCreations.logic.RazaLogic;

import java.util.List;
import java.util.Scanner;

public class GestionRazas {
    public static void gestionRazas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------");
        System.out.println("Bienvenido al menú de gestión de razas");
        System.out.println("--------------------------------------");
        System.out.println("1. Crear raza");
        System.out.println("2. Eliminar raza");
        System.out.println("3. Mostrar todas las razas");
        System.out.println("4. Regresar al menú del Game Master");

        int opcion = Main.validateOpcion(4);

        switch (opcion) {
            case 1:
                System.out.println("Crear raza");
                System.out.print("Ingrese el nombre de la raza: ");
                String nombreCrear = scanner.nextLine();
                if (RazaLogic.buscarRaza(nombreCrear) !=-1){
                    System.out.println("El nombre de la raza ya existe");
                    gestionRazas();
                }else{
                    System.out.print("Ingrese la descripción de la raza: ");
                    String descripcionCrear = scanner.nextLine();
                    RazaLogic.crearRaza(nombreCrear, descripcionCrear);
                    System.out.println("Raza creada exitosamente");
                    Main.mostrarMenuGameMaster();
                }
            case 2:
                System.out.println("Eliminar raza");
                System.out.print("Ingrese el nombre de la raza a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                if (RazaLogic.buscarRaza(nombreEliminar) == -1){
                    System.out.println("El nombre de la raza no existe");
                    gestionRazas();
                }else{
                    RazaLogic.eliminarRaza(nombreEliminar);
                    System.out.println("Raza eliminada exitosamente");
                    Main.mostrarMenuGameMaster();
                }
            case 3:
                System.out.println("Mostrar todas las razas");
                List<Raza> razas = RazaLogic.obtenerRazas();
                for (Raza raza : razas) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Raza: "+raza.getNombre());
                    System.out.println("Descripción: "+raza.getDescripcion());
                    System.out.println("----------------------------------------------------------------");
                }
                Main.mostrarMenuGameMaster();
            case 4:
                System.out.println("Regresar al menú del Game Master");
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
