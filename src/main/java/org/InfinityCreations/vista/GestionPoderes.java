package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Poder;
import org.InfinityCreations.logic.PoderLogic;
import org.InfinityCreations.logic.RazaLogic;

import java.util.List;
import java.util.Scanner;

public class GestionPoderes {
    public static void gestionPoderes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("Bienvenido al menú de gestión de Poderes");
        System.out.println("----------------------------------------");
        System.out.println("1. Crear Poderes");
        System.out.println("2. Eliminar Poderes");
        System.out.println("3. Modificar Poderes");
        System.out.println("4. Buscar Poderes por Raza");
        System.out.println("5. Mostrar todos los Poderes");
        System.out.println("6. Regresar al menú del Game Master");

        int opcion = Main.validateOpcion(6);

        switch (opcion) {
            case 1:
                System.out.println("-------------------");
                System.out.println("|  Crear Poderes  |");
                System.out.println("-------------------");
                System.out.println("¿A que raza desea crearle un nuevo Poder? - ingrese el nombre de la raza");
                String nombreRaza = scanner.nextLine();
                if (RazaLogic.buscarRaza(nombreRaza) == -1){
                    System.out.println("La raza no existe");
                    gestionPoderes();
                }else{
                    int idRaza = RazaLogic.getRaza(nombreRaza).getId();
                    System.out.println("Ingrese el nombre del poder: ");
                    String nombreCrear = scanner.nextLine();
                    if (PoderLogic.buscarPoder(nombreCrear) !=-1){
                        System.out.println("El nombre del poder ya existe");
                        gestionPoderes();
                    }else{
                        System.out.print("Ingrese la descripción del poder: ");
                        String descripcionCrear = scanner.nextLine();
                        System.out.println("Asignele un valor al bono de fuerza");
                        int bonoFuerza = scanner.nextInt();
                        if(PoderLogic.crearPoder(nombreCrear,descripcionCrear,idRaza,bonoFuerza)){
                            System.out.println("Poder creado exitosamente");
                            PoderLogic.actualizarListaDePoderes();
                            Main.mostrarMenuGameMaster();
                        }else{
                            System.out.println("No se pudo crear el Poder");
                        }
                    }
                }
            case 2:
                System.out.println("----------------------");
                System.out.println("|  Eliminar Poderes  |");
                System.out.println("----------------------");
                System.out.print("Ingrese el nombre del poder a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                if (PoderLogic.buscarPoder(nombreEliminar) == -1){
                    System.out.println("El nombre del poder no existe");
                    gestionPoderes();
                }else{
                    PoderLogic.eliminarPoder(nombreEliminar);
                    System.out.println("Poder eliminado exitosamente");
                    PoderLogic.actualizarListaDePoderes();
                    Main.mostrarMenuGameMaster();
                }
            case 3:
                System.out.println("---------------------");
                System.out.println("| Modificar Poderes |");
                System.out.println("---------------------");
                System.out.print("Ingrese el nombre del Poder a modificar: ");
                String nombreModificar = scanner.nextLine();
                if(PoderLogic.buscarPoder(nombreModificar) == -1){
                    System.out.println("El nombre del Poder no existe");
                    gestionPoderes();
                }else{
                    menuModificarPoderes(nombreModificar);
                }
            case 4:
                System.out.println("---------------------------");
                System.out.println("|  Buscar Poder por Raza  |");
                System.out.println("---------------------------");
                System.out.print("Ingrese el nombre de la Raza: ");
                String nomRaza = scanner.nextLine();
                if(RazaLogic.buscarRaza(nomRaza) == -1){
                    System.out.println("La raza no existe");
                    gestionPoderes();
                }else{
                    int idRaza = RazaLogic.getRaza(nomRaza).getId();
                    List<Poder> pXraza = PoderLogic.obtenerPoderesxRaza(idRaza);
                    for (Poder poder : pXraza) {
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Nombre : "+poder.getNombre());
                        System.out.println("Descripción : "+poder.getDescripcion());
                        System.out.println("Raza : "+poder.getRaza().getNombre());
                        System.out.println("Bono de Fuerza : "+poder.getBonoFuerza());
                        System.out.println("----------------------------------------------------------------");
                    }
                    Main.mostrarMenuGameMaster();
                }
            case 5:
                System.out.println("Mostrar todos los poderes");
                List<Poder> poderes = PoderLogic.obtenerPoderes();
                for (Poder poder : poderes) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Nombre : "+poder.getNombre());
                    System.out.println("Descripción : "+poder.getDescripcion());
                    System.out.println("Raza : "+poder.getRaza().getNombre());
                    System.out.println("Bono de Fuerza : "+poder.getBonoFuerza());
                    System.out.println("----------------------------------------------------------------");
                }
                Main.mostrarMenuGameMaster();
            case 6:
                System.out.println("Regresar al menú del Game Master");
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void menuModificarPoderes(String nombre) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Que deseas modificar?");
        System.out.println("1. Descripción");
        System.out.println("2. Bono de Fuerza");
        System.out.println("3. Regresar al menú de gestión de Poderes");
        int opcion = Main.validateOpcion(3);
        Poder poder = PoderLogic.getPoder(nombre);
        if (poder == null) {
            System.out.println("Poder no encontrado.");
            return;
        }
        switch (opcion) {
            case 1:
                System.out.print("Ingrese la nueva descripción: ");
                String descripcion = scanner.nextLine();
                if (PoderLogic.actualizarPoder(nombre, descripcion, poder.getBonoFuerza())) {
                    System.out.println("Descripcion modificada exitosamente");
                    PoderLogic.actualizarListaDePoderes();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion2 = Main.validateOpcion(2);
                    switch (opcion2){
                        case 1:
                            menuModificarPoderes(nombre);
                            break;
                        case 2:
                            Main.mostrarMenuGameMaster();
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    Main.mostrarMenuGameMaster();

                } else {
                    System.out.println("No se pudo modificar el poder");
                }
                break;
            case 2:
                System.out.print("Ingrese el nuevo bono de Fuerza: ");
                int bnFZ = scanner.nextInt();
                if (PoderLogic.actualizarPoder(nombre,poder.getDescripcion(),bnFZ)) {
                    System.out.println("Bono de fuerza modificado exitosamente");
                    PoderLogic.actualizarListaDePoderes();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando el poder "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion2 = Main.validateOpcion(2);
                    switch (opcion2){
                        case 1:
                            menuModificarPoderes(nombre);
                            break;
                        case 2:
                            Main.mostrarMenuGameMaster();
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    Main.mostrarMenuGameMaster();

                } else {
                    System.out.println("No se pudo modificar el poder");
                }
                break;
            case 3:
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
