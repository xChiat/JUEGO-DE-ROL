package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Habilidad;
import org.InfinityCreations.logic.HabilidadLogic;
import org.InfinityCreations.logic.RazaLogic;

import java.util.List;
import java.util.Scanner;

public class GestionHabilidades {
    public static void gestionHabilidades() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------");
        System.out.println("Bienvenido al menú de gestión de Habilidades");
        System.out.println("--------------------------------------------");
        System.out.println("1. Crear Habilidades");
        System.out.println("2. Eliminar Habilidades");
        System.out.println("3. Modificar Habilidades");
        System.out.println("4. Buscar Habilidades por Raza");
        System.out.println("5. Mostrar todas las Habilidades");
        System.out.println("6. Regresar al menú del Game Master");

        int opcion = Main.validateOpcion(6);

        switch (opcion) {
            case 1:
                System.out.println("-------------------");
                System.out.println("| Crear Habilidad |");
                System.out.println("-------------------");
                System.out.println("¿A que raza desea crearle una nueva habilidad? - ingrese el nombre de la raza");
                String nombreRaza = scanner.nextLine();
                if (RazaLogic.buscarRaza(nombreRaza) == -1){
                    System.out.println("La raza no existe");
                    gestionHabilidades();
                }else{
                    int idRaza = RazaLogic.getRaza(nombreRaza).getId();
                    System.out.println("Ingrese el nombre de la Habilidad: ");
                    String nombreCrear = scanner.nextLine();
                    if (HabilidadLogic.buscarHabilidad(nombreCrear) !=-1){
                        System.out.println("El nombre de la habilidad ya existe");
                        gestionHabilidades();
                    }else{
                        System.out.print("Ingrese la descripción de la Habilidad: ");
                        String descripcionCrear = scanner.nextLine();
                        System.out.println("Asignele un valor al bono de destreza");
                        int bonoDestreza = scanner.nextInt();
                        System.out.println("Asignele un valor al bono de inteligencia");
                        int bonoInteligencia = scanner.nextInt();
                        if(HabilidadLogic.crearHabilidad(nombreCrear, descripcionCrear,idRaza, bonoDestreza, bonoInteligencia)){
                            System.out.println("Habilidad creada exitosamente");
                            HabilidadLogic.actualizarListaDeHabilidades();
                            Main.mostrarMenuGameMaster();
                        }else{
                            System.out.println("No se pudo crear la habilidad");
                        }
                    }
                }
            case 2:
                System.out.println("----------------------");
                System.out.println("| Eliminar Habilidad |");
                System.out.println("----------------------");
                System.out.print("Ingrese el nombre de la Habilidad a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                if (HabilidadLogic.buscarHabilidad(nombreEliminar) == -1){
                    System.out.println("El nombre de la habilidad no existe");
                    gestionHabilidades();
                }else{
                    HabilidadLogic.eliminarHabilidad(nombreEliminar);
                    System.out.println("Habilidad eliminada exitosamente");
                    HabilidadLogic.actualizarListaDeHabilidades();
                    Main.mostrarMenuGameMaster();
                }
            case 3:
                System.out.println("-----------------------");
                System.out.println("| Modificar Habilidad |");
                System.out.println("-----------------------");
                System.out.print("Ingrese el nombre de la Habilidad a modificar: ");
                String nombreModificar = scanner.nextLine();
                if(HabilidadLogic.buscarHabilidad(nombreModificar) == -1){
                    System.out.println("El nombre de la habilidad no existe");
                    gestionHabilidades();
                }else{
                    menuModificarHabilidad(nombreModificar);
                }
            case 4:
                System.out.println("-----------------------------");
                System.out.println("| Buscar Habilidad por Raza |");
                System.out.println("-----------------------------");
                System.out.print("Ingrese el nombre de la Raza: ");
                String nomRaza = scanner.nextLine();
                if(RazaLogic.buscarRaza(nomRaza) == -1){
                    System.out.println("La raza no existe");
                    gestionHabilidades();
                }else{
                    int idRaza = RazaLogic.getRaza(nomRaza).getId();
                    List<Habilidad> hXraza = HabilidadLogic.obtenerHabilidadesxRaza(idRaza);
                    for (Habilidad habilidad : hXraza) {
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Nombre : "+habilidad.getNombre());
                        System.out.println("Descripción : "+habilidad.getDescripcion());
                        System.out.println("Raza : "+habilidad.getRaza().getNombre());
                        System.out.println("Bono de destreza : "+habilidad.getBonoDestresa());
                        System.out.println("Bono de inteligencia : "+habilidad.getBonoInteligencia());
                        System.out.println("----------------------------------------------------------------");
                    }
                    Main.mostrarMenuGameMaster();
                }
            case 5:
                System.out.println("Mostrar todas las Habilidades");
                List<Habilidad> habilidades = HabilidadLogic.obtenerHabilidades();
                for (Habilidad habilidad : habilidades) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Nombre : "+habilidad.getNombre());
                    System.out.println("Descripción : "+habilidad.getDescripcion());
                    System.out.println("Raza : "+habilidad.getRaza().getNombre());
                    System.out.println("Bono de destreza : "+habilidad.getBonoDestresa());
                    System.out.println("Bono de inteligencia : "+habilidad.getBonoInteligencia());
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

    private static void menuModificarHabilidad(String nombre) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Que deseas modificar?");
        System.out.println("1. Descripción");
        System.out.println("2. Bono de destreza");
        System.out.println("3. Bono de inteligencia");
        System.out.println("4. Regresar al menú de gestión de Habilidades");
        int opcion = Main.validateOpcion(4);
        Habilidad habilidad = HabilidadLogic.getHabilidad(nombre);
        if (habilidad == null) {
            System.out.println("Habilidad no encontrada.");
            return;
        }
        switch (opcion) {
            case 1:
                System.out.print("Ingrese la nueva descripción: ");
                String descripcion = scanner.nextLine();
                if (HabilidadLogic.actualizarHabilidad(nombre, descripcion, habilidad.getBonoDestresa(), habilidad.getBonoInteligencia())) {
                    System.out.println("Descripcion modificada exitosamente");
                    HabilidadLogic.actualizarListaDeHabilidades();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion2 = Main.validateOpcion(2);
                    switch (opcion2){
                        case 1:
                            menuModificarHabilidad(nombre);
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
                    System.out.println("No se pudo modificar la habilidad");
                }
                break;
            case 2:
                System.out.print("Ingrese el nuevo bono de destreza: ");
                int bnDst2 = scanner.nextInt();
                if (HabilidadLogic.actualizarHabilidad(nombre, habilidad.getDescripcion(), bnDst2, habilidad.getBonoInteligencia())) {
                    System.out.println("Bono de destreza modificado exitosamente");
                    HabilidadLogic.actualizarListaDeHabilidades();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion2 = Main.validateOpcion(2);
                    switch (opcion2){
                        case 1:
                            menuModificarHabilidad(nombre);
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
                    System.out.println("No se pudo modificar la habilidad");
                }
                break;
            case 3:
                System.out.print("Ingrese el nuevo bono de inteligencia: ");
                int bnInt3 = scanner.nextInt();
                if (HabilidadLogic.actualizarHabilidad(nombre, habilidad.getDescripcion(), habilidad.getBonoDestresa(), bnInt3)) {
                    System.out.println("Bono de inteligencia modificado exitosamente");
                    HabilidadLogic.actualizarListaDeHabilidades();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion2 = Main.validateOpcion(2);
                    switch (opcion2){
                        case 1:
                            menuModificarHabilidad(nombre);
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
                    System.out.println("No se pudo modificar la habilidad");
                }
                break;
            case 4:
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
