package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Armaduras;
import org.InfinityCreations.entities.Armas;
import org.InfinityCreations.logic.ArmadurasLogic;
import org.InfinityCreations.logic.ArmasLogic;
import org.InfinityCreations.utils.DatabaseUtils;

import java.util.List;
import java.util.Scanner;

public class GestionEquipo {
    public static void gestionEquipo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("Bienvenido al menú de gestión de Poderes");
        System.out.println("----------------------------------------");
        System.out.println("1. CRUD ARMAS");
        System.out.println("2. CRUD Armaduras");
        System.out.println("3. Regresar al menu del Game Master");

        int opcion = Main.validateOpcion(3);
        switch (opcion) {
            case 1:
                gestionArmas();
                break;
            case 2:
                gestionArmaduras();
                break;
            case 3:
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                gestionEquipo();
                break;
        }
    }

    private static void gestionArmaduras() {
        int maxNombreLength = DatabaseUtils.getColumnMaxLength("armaduras", "nombre");
        int maxDescripcionLength = DatabaseUtils.getColumnMaxLength("armaduras", "descripcion");

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("|  CRUD ARMADURAS  |");
        System.out.println("--------------------");
        System.out.println("1. Crear Armadura");
        System.out.println("2. Eliminar Armadura");
        System.out.println("3. Modificar Armadura");
        System.out.println("4. Mostrar todas las Armaduras");
        System.out.println("5. Regresar a Gestion de Equipo");

        int opcion = Main.validateOpcion(5);

        switch (opcion) {
            case 1:
                System.out.println("---------------------");
                System.out.println("|  Crear armaduras  |");
                System.out.println("---------------------");
                System.out.println("Ingrese el nombre de la armadura: ");
                String nombreCrear = readInputWithMaxLength(scanner, maxNombreLength);
                if (ArmadurasLogic.buscarArmaduras(nombreCrear) != -1) {
                    System.out.println("El nombre del estado ya existe");
                    gestionArmaduras();
                } else {
                    System.out.print("Ingrese la descripción de la armadura: ");
                    String descripcionCrear = readInputWithMaxLength(scanner, maxDescripcionLength);
                    System.out.println("Ingresa la protección de la armadura: ");
                    System.out.println("/ La protección que brinda la armadura se suma a la vida total /");
                    int bonoVida = scanner.nextInt();
                    System.out.println("Ingresa el precio de la armadura: ");
                    int precio = scanner.nextInt();
                    if (ArmadurasLogic.crearArmaduras(nombreCrear, descripcionCrear, bonoVida, precio)) {
                        System.out.println("Armadura creada exitosamente");
                        ArmadurasLogic.actualizarListaDeArmaduras();
                        Main.mostrarMenuGameMaster();
                    } else {
                        System.out.println("No se pudo crear la armadura");
                        Main.mostrarMenuGameMaster();
                    }
                }
                break;
            case 2:
                System.out.println("-----------------------");
                System.out.println("|  Eliminar armadura  |");
                System.out.println("-----------------------");
                System.out.print("Ingrese el nombre de la armadura a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                if (ArmadurasLogic.buscarArmaduras(nombreEliminar) == -1) {
                    System.out.println("El nombre de la armadura no existe");
                    gestionArmaduras();
                } else {
                    if (ArmadurasLogic.eliminarArmaduras(nombreEliminar)) {
                        System.out.println("Armadura eliminada exitosamente");
                        ArmadurasLogic.actualizarListaDeArmaduras();
                        Main.mostrarMenuGameMaster();
                    } else {
                        System.out.println("No se pudo eliminar la armadura");
                        Main.mostrarMenuGameMaster();
                    }
                }
                break;
            case 3:
                System.out.println("----------------------");
                System.out.println("| Modificar Armadura |");
                System.out.println("----------------------");
                System.out.print("Ingrese el nombre de la armadura a modificar:");
                String nombreModificar = scanner.nextLine();
                if (ArmadurasLogic.buscarArmaduras(nombreModificar) == -1) {
                    System.out.println("El nombre de la armadura no existe");
                    gestionArmaduras();
                } else {
                    menuModificarArmaduras(nombreModificar, maxDescripcionLength);
                }
                break;
            case 4:
                System.out.println("-- Mostrar todas las armaduras --");
                List<Armaduras> armaduras = ArmadurasLogic.obtenerArmaduras();
                for (Armaduras armadura : armaduras) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Nombre: " + armadura.getNombre());
                    System.out.println("Descripción: " + armadura.getDescripcion());
                    System.out.println("Protección: " + armadura.getBonoVida() + " puntos");
                    System.out.println("Precio: $" + armadura.getPrecio());
                    System.out.println("----------------------------------------------------------------");
                }
                Main.mostrarMenuGameMaster();
                break;
            case 5:
                System.out.println("Regresar al menú del Game Master");
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                gestionArmaduras();
                break;
        }
    }

    private static void menuModificarArmaduras(String nombreModificar, int maxDescripcionLength) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("| Modificar Armaduras |");
        System.out.println("---------------------");
        System.out.println("1. Modificar nombre");
        System.out.println("2. Modificar descripción");
        System.out.println("3. Modificar protección");
        System.out.println("4. Modificar precio");
        System.out.println("5. Regresar a Gestion de Equipo");

        int opcion = Main.validateOpcion(5);
        switch (opcion) {
            case 1:
                System.out.println("Modificar nombre");
                System.out.print("Ingrese el nuevo nombre: ");
                String nombre = readInputWithMaxLength(scanner, maxDescripcionLength);
                if (ArmadurasLogic.buscarArmaduras(nombre) != -1) {
                    System.out.println("El nombre de la armadura ya existe");
                    menuModificarArmaduras(nombreModificar, maxDescripcionLength);
                } else {
                    String desc = ArmadurasLogic.getArmaduras(nombreModificar).getDescripcion();
                    int bonoVida = ArmadurasLogic.getArmaduras(nombreModificar).getBonoVida();
                    int precio = ArmadurasLogic.getArmaduras(nombreModificar).getPrecio();
                    if (ArmadurasLogic.actualizarArmaduras(nombreModificar, nombre, desc, bonoVida, precio)) {
                        System.out.println("Nombre modificado exitosamente");
                        ArmadurasLogic.actualizarListaDeArmaduras();
                        Main.mostrarMenuGameMaster();
                    } else {
                        System.out.println("No se pudo modificar el nombre");
                        Main.mostrarMenuGameMaster();
                    }
                }
                break;
            case 2:
                System.out.println("Modificar descripción");
                System.out.print("Ingrese la nueva descripción: ");
                String descripcion = readInputWithMaxLength(scanner, maxDescripcionLength);
                int bonoVida = ArmadurasLogic.getArmaduras(nombreModificar).getBonoVida();
                int precio = ArmadurasLogic.getArmaduras(nombreModificar).getPrecio();
                if (ArmadurasLogic.actualizarArmaduras(nombreModificar, nombreModificar, descripcion, bonoVida, precio)) {
                    System.out.println("Descripción modificada exitosamente");
                    ArmadurasLogic.actualizarListaDeArmaduras();
                    Main.mostrarMenuGameMaster();
                } else {
                    System.out.println("No se pudo modificar la descripción");
                    Main.mostrarMenuGameMaster();
                }
                break;
            case 3:
                System.out.println("Modificar protección");
                System.out.print("Ingrese la nueva protección: ");
                int bnVida = scanner.nextInt();
                int p = ArmadurasLogic.getArmaduras(nombreModificar).getPrecio();
                String descrip = ArmadurasLogic.getArmaduras(nombreModificar).getDescripcion();
                if (ArmadurasLogic.actualizarArmaduras(nombreModificar, nombreModificar, descrip, bnVida, p)) {
                    System.out.println("Protección modificada exitosamente");
                    ArmadurasLogic.actualizarListaDeArmaduras();
                    Main.mostrarMenuGameMaster();
                } else {
                    System.out.println("No se pudo modificar la protección");
                    Main.mostrarMenuGameMaster();
                }
                break;
            case 4:
                System.out.println("Modificar precio");
                System.out.print("Ingrese el nuevo precio: ");
                int pr = scanner.nextInt();
                int bVida = ArmadurasLogic.getArmaduras(nombreModificar).getBonoVida();
                String desc = ArmadurasLogic.getArmaduras(nombreModificar).getDescripcion();
                if (ArmadurasLogic.actualizarArmaduras(nombreModificar, nombreModificar, desc, bVida, pr)) {
                    System.out.println("Precio modificado exitosamente");
                    ArmadurasLogic.actualizarListaDeArmaduras();
                    Main.mostrarMenuGameMaster();
                } else {
                    System.out.println("No se pudo modificar el precio");
                    Main.mostrarMenuGameMaster();
                }
                break;
            case 5:
                System.out.println("Regresar a Gestion de Equipo");
                gestionArmaduras();
                break;
            default:
                System.out.println("Opción no válida");
                menuModificarArmaduras(nombreModificar, maxDescripcionLength);
                break;
        }
    }

    private static void gestionArmas() {
        int maxNombreLength = DatabaseUtils.getColumnMaxLength("armas", "nombre");
        int maxDescripcionLength = DatabaseUtils.getColumnMaxLength("armas", "descripcion");

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("|    CRUD ARMAS    |");
        System.out.println("--------------------");
        System.out.println("1. Crear arma");
        System.out.println("2. Eliminar arma");
        System.out.println("3. Modificar arma");
        System.out.println("4. Mostrar todas las armas");
        System.out.println("5. Regresar a Gestión de Equipo");

        int opcion = Main.validateOpcion(5);

        switch (opcion) {
            case 1:
                System.out.println("-------------------");
                System.out.println("|   Crear arma    |");
                System.out.println("-------------------");
                System.out.println("Ingrese el nombre del arma: ");
                String nombreCrear = readInputWithMaxLength(scanner, maxNombreLength);
                if (ArmasLogic.buscarArmas(nombreCrear) != -1) {
                    System.out.println("El nombre del arma ya existe");
                    gestionArmas();
                } else {
                    System.out.print("Ingrese la descripción del arma: ");
                    String descripcionCrear = readInputWithMaxLength(scanner, maxDescripcionLength);
                    System.out.println("Ingresa el daño del arma: ");
                    System.out.println("/ El daño que realiza el arma a los enemigos /");
                    int bonoDmg = scanner.nextInt();
                    System.out.println("Ingresa el precio del arma: ");
                    int precio = scanner.nextInt();
                    if (ArmasLogic.crearArmas(nombreCrear, descripcionCrear, bonoDmg, precio)) {
                        System.out.println("Arma creada exitosamente");
                        ArmasLogic.actualizarListaDeArmas();
                        Main.mostrarMenuGameMaster();
                    } else {
                        System.out.println("No se pudo crear el arma");
                        Main.mostrarMenuGameMaster();
                    }
                }
                break;
            case 2:
                System.out.println("---------------------");
                System.out.println("|   Eliminar arma   |");
                System.out.println("---------------------");
                System.out.print("Ingrese el nombre del arma a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                if (ArmasLogic.buscarArmas(nombreEliminar) == -1) {
                    System.out.println("El nombre del arma no existe");
                    gestionArmas();
                } else {
                    if (ArmasLogic.eliminarArmas(nombreEliminar)) {
                        System.out.println("Arma eliminada exitosamente");
                        ArmasLogic.actualizarListaDeArmas();
                        Main.mostrarMenuGameMaster();
                    } else {
                        System.out.println("No se pudo eliminar el arma");
                        Main.mostrarMenuGameMaster();
                    }
                }
                break;
            case 3:
                System.out.println("-------------------");
                System.out.println("| Modificar Arma  |");
                System.out.println("-------------------");
                System.out.print("Ingrese el nombre del arma a modificar:");
                String nombreModificar = scanner.nextLine();
                if (ArmasLogic.buscarArmas(nombreModificar) == -1) {
                    System.out.println("El nombre del arma no existe");
                    gestionArmas();
                } else {
                    menuModificarArmas(nombreModificar, maxDescripcionLength);
                }
                break;
            case 4:
                System.out.println("-- Mostrar todas las armas --");
                List<Armas> armas = ArmasLogic.obtenerArmas();
                for (Armas arma : armas) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Nombre: " + arma.getNombre());
                    System.out.println("Descripción: " + arma.getDescripcion());
                    System.out.println("Daño: " + arma.getBonoFuerza() + " puntos");
                    System.out.println("Precio: $" + arma.getPrecio());
                    System.out.println("----------------------------------------------------------------");
                }
                Main.mostrarMenuGameMaster();
                break;
            case 5:
                System.out.println("Regresar al menú del Game Master");
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                gestionArmas();
                break;
        }
    }

    private static void menuModificarArmas(String nombreModificar, int maxDescripcionLength) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------");
        System.out.println("| Modificar Arma  |");
        System.out.println("-------------------");
        System.out.println("1. Modificar nombre");
        System.out.println("2. Modificar descripción");
        System.out.println("3. Modificar daño");
        System.out.println("4. Modificar precio");
        System.out.println("5. Regresar a Gestion de Equipo");

        int opcion = Main.validateOpcion(5);
        switch (opcion) {
            case 1:
                System.out.println("Modificar nombre");
                System.out.print("Ingrese el nuevo nombre: ");
                String nombre = readInputWithMaxLength(scanner, maxDescripcionLength);
                if (ArmasLogic.buscarArmas(nombre) != -1) {
                    System.out.println("El nombre del arma ya existe");
                    menuModificarArmas(nombreModificar, maxDescripcionLength);
                } else {
                    String desc = ArmasLogic.getArmas(nombreModificar).getDescripcion();
                    int bonoDmg = ArmasLogic.getArmas(nombreModificar).getBonoFuerza();
                    int precio = ArmasLogic.getArmas(nombreModificar).getPrecio();
                    if (ArmasLogic.actualizarArmas(nombreModificar, nombre, desc, bonoDmg, precio)) {
                        System.out.println("Nombre modificado exitosamente");
                        ArmasLogic.actualizarListaDeArmas();
                        Main.mostrarMenuGameMaster();
                    } else {
                        System.out.println("No se pudo modificar el nombre");
                        Main.mostrarMenuGameMaster();
                    }
                }
                break;
            case 2:
                System.out.println("Modificar descripción");
                System.out.print("Ingrese la nueva descripción: ");
                String descripcion = readInputWithMaxLength(scanner, maxDescripcionLength);
                int bonoDmg = ArmasLogic.getArmas(nombreModificar).getBonoFuerza();
                int precio = ArmasLogic.getArmas(nombreModificar).getPrecio();
                if (ArmasLogic.actualizarArmas(nombreModificar, nombreModificar, descripcion, bonoDmg, precio)) {
                    System.out.println("Descripción modificada exitosamente");
                    ArmasLogic.actualizarListaDeArmas();
                    Main.mostrarMenuGameMaster();
                } else {
                    System.out.println("No se pudo modificar la descripción");
                    Main.mostrarMenuGameMaster();
                }
                break;
            case 3:
                System.out.println("Modificar daño");
                System.out.print("Ingrese el nuevo daño: ");
                int bnDmg = scanner.nextInt();
                int p = ArmasLogic.getArmas(nombreModificar).getPrecio();
                String descrip = ArmasLogic.getArmas(nombreModificar).getDescripcion();
                if (ArmasLogic.actualizarArmas(nombreModificar, nombreModificar, descrip, bnDmg, p)) {
                    System.out.println("Daño modificado exitosamente");
                    ArmasLogic.actualizarListaDeArmas();
                    Main.mostrarMenuGameMaster();
                } else {
                    System.out.println("No se pudo modificar el daño");
                    Main.mostrarMenuGameMaster();
                }
                break;
            case 4:
                System.out.println("Modificar precio");
                System.out.print("Ingrese el nuevo precio: ");
                int pr = scanner.nextInt();
                int bDmg = ArmasLogic.getArmas(nombreModificar).getBonoFuerza();
                String desc = ArmasLogic.getArmas(nombreModificar).getDescripcion();
                if (ArmasLogic.actualizarArmas(nombreModificar, nombreModificar, desc, bDmg, pr)) {
                    System.out.println("Precio modificado exitosamente");
                    ArmasLogic.actualizarListaDeArmas();
                    Main.mostrarMenuGameMaster();
                } else {
                    System.out.println("No se pudo modificar el precio");
                    Main.mostrarMenuGameMaster();
                }
                break;
            case 5:
                System.out.println("Regresar a Gestion de Equipo");
                gestionArmas();
                break;
            default:
                System.out.println("Opción no válida");
                menuModificarArmas(nombreModificar, maxDescripcionLength);
                break;
        }
    }

    private static String readInputWithMaxLength(Scanner scanner, int maxLength) {
        String input = scanner.nextLine();
        while (input.length() > maxLength) {
            System.out.println("Input exceeds maximum length of " + maxLength + ". Please enter again:");
            input = scanner.nextLine();
        }
        return input;
    }
}
