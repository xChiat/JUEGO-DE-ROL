package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Estado;
import org.InfinityCreations.logic.EstadoLogic;
import org.InfinityCreations.utils.DatabaseUtils;

import java.util.List;
import java.util.Scanner;

public class GestionEstados {
    public static void gestionEstados() {
        int maxNombreLength = DatabaseUtils.getColumnMaxLength("estado", "nombre");
        int maxDescripcionLength = DatabaseUtils.getColumnMaxLength("estado", "descripcion");

        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("Bienvenido al menú de gestión de Estados");
        System.out.println("----------------------------------------");
        System.out.println("1. Crear Estado");
        System.out.println("2. Eliminar Estado");
        System.out.println("3. Modificar Estado");
        System.out.println("4. Mostrar todas las Estado");
        System.out.println("5. Regresar al menú del Game Master");

        int opcion = Main.validateOpcion(5);

        switch (opcion) {
            case 1:
                System.out.println("Crear estados");
                System.out.println("Ingrese el nombre del estado: ");
                String nombreCrear = readInputWithMaxLength(scanner, maxNombreLength);
                if (EstadoLogic.buscarEstado(nombreCrear) !=-1){
                    System.out.println("El nombre del estado ya existe");
                    gestionEstados();
                }else{
                    System.out.print("Ingrese la descripción del estado: ");
                    String descripcionCrear = readInputWithMaxLength(scanner, maxDescripcionLength);
                    EstadoLogic.crearEstado(nombreCrear, descripcionCrear);
                    System.out.println("Estado creado exitosamente");
                    Main.mostrarMenuGameMaster();
                }
            case 2:
                System.out.println("Eliminar estado");
                System.out.print("Ingrese el nombre del estado a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                if (EstadoLogic.buscarEstado(nombreEliminar) == -1){
                    System.out.println("El nombre del estado no existe");
                    gestionEstados();
                }else{
                    EstadoLogic.eliminarEstado(nombreEliminar);
                    System.out.println("Estado eliminada exitosamente");
                    Main.mostrarMenuGameMaster();
                }
            case 3:
                System.out.println("Modificar estado");
                System.out.print("Ingrese el nombre del estado a modificar: ");
                String nombreModificar = scanner.nextLine();
                if (EstadoLogic.buscarEstado(nombreModificar) == -1){
                    System.out.println("El nombre del estado no existe");
                    gestionEstados();
                }else{
                    System.out.print("Ingrese la nueva descripción del estado: ");
                    String descripcionModificar = readInputWithMaxLength(scanner, maxDescripcionLength);
                    EstadoLogic.actualizarEstado(nombreModificar, descripcionModificar);
                    System.out.println("Estado modificado exitosamente");
                    Main.mostrarMenuGameMaster();
                }
            case 4:
                System.out.println("Mostrar todos los Estados");
                List<Estado> estados = EstadoLogic.obtenerEstados();
                for (Estado estado : estados) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Estado: "+estado.getNombre());
                    System.out.println("Descripción: "+estado.getDescripcion());
                    System.out.println("----------------------------------------------------------------");
                }
                Main.mostrarMenuGameMaster();
            case 5:
                System.out.println("Regresar al menú del Game Master");
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    private static String readInputWithMaxLength(Scanner scanner, int maxLength) {
        String input = scanner.nextLine();
        while (input.length() > maxLength) {
            System.out.println("La entrada es demasiado larga. El máximo permitido es " + maxLength + " caracteres. Inténtelo de nuevo:");
            input = scanner.nextLine();
        }
        return input;
    }
}
