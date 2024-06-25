package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Habilidad;
import org.InfinityCreations.entities.Poder;
import org.InfinityCreations.entities.Raza;
import org.InfinityCreations.logic.HabilidadLogic;
import org.InfinityCreations.logic.PoderLogic;
import org.InfinityCreations.logic.RazaLogic;
import org.InfinityCreations.utils.DatabaseUtils;

import java.util.List;
import java.util.Scanner;

public class GestionRazas {
    public static void gestionRazas() {
        int maxNombreLength = DatabaseUtils.getColumnMaxLength("razas", "nombre");
        int maxDescripcionLength = DatabaseUtils.getColumnMaxLength("razas", "descripcion");

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
                String nombreCrear = readInputWithMaxLength(scanner, maxNombreLength);
                if (RazaLogic.buscarRaza(nombreCrear) !=-1){
                    System.out.println("El nombre de la raza ya existe");
                    gestionRazas();
                }else{
                    System.out.print("Ingrese la descripción de la raza: ");
                    String descripcionCrear = readInputWithMaxLength(scanner, maxDescripcionLength);
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
                    int idRaza = RazaLogic.getRaza(nombreEliminar).getId();
                    List<Poder> pXraza = PoderLogic.obtenerPoderesxRaza(idRaza);
                    List<Habilidad> hXraza = HabilidadLogic.obtenerHabilidadesxRaza(idRaza);
                    if(pXraza.isEmpty() && hXraza.isEmpty()){
                        RazaLogic.eliminarRaza(nombreEliminar);
                        System.out.println("Raza eliminada exitosamente");
                        Main.mostrarMenuGameMaster();
                    }else{
                        System.out.println("No se puede eliminar la raza porque tiene poderes o habilidades asociados");
                        Main.mostrarMenuGameMaster();
                    }
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
    private static String readInputWithMaxLength(Scanner scanner, int maxLength) {
        String input = scanner.nextLine();
        while (input.length() > maxLength) {
            System.out.println("La entrada es demasiado larga. El máximo permitido es " + maxLength + " caracteres. Inténtelo de nuevo:");
            input = scanner.nextLine();
        }
        return input;
    }
}
