package org.InfinityCreations;

import org.InfinityCreations.entities.Habilidad;
import org.InfinityCreations.entities.Raza;
import org.InfinityCreations.entities.Usuario;
import org.InfinityCreations.logic.HabilidadLogic;
import org.InfinityCreations.logic.RazaLogic;
import org.InfinityCreations.logic.UsuarioLogic;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("|    Prototipo juego de rol   |");
        System.out.println("-------------------------------");
        System.out.println("Bienvenido a Infinity Creations");
        menuPrincipal();
    }

    private static int validateOpcion(int menu) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= menu) {
                    return opcion;
                } else {
                    System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Solo se pueden ingresar números");
                scanner.next();
            }
        }
    }

    private static void salir() {
        System.out.println("Gracias por usar Infinity Creations");
    }

    private static void ingresar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        if (UsuarioLogic.buscarUsuario(nombre) == -1) {
            System.out.println("El nombre de usuario no existe");
            ingresar();
        } else {
            System.out.println("1. para ingresar su contraseña: ");
            System.out.println("2. para recuperar la contraseña: ");
            int opc = validateOpcion(2);
            if (opc == 1) {
                System.out.println("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                boolean fndUser = UsuarioLogic.iniciarSesion(nombre, password);
                if (fndUser) {
                    Usuario usuario = UsuarioLogic.getUsuario(nombre);
                    System.out.println("Inicio de sesión exitoso");
                    if (usuario.getPerfil().getId() == 1) {
                        mostrarMenuJugador();
                    } else if (usuario.getPerfil().getId() == 2) {
                        mostrarMenuGameMaster();
                    }
                } else {
                    System.out.println("Nombre de usuario o contraseña incorrectos");
                }
            } else {
                recuperarPassword(nombre);
            }
        }
    }

    private static void registrarse() {
        System.out.println("Registrarse");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        if (UsuarioLogic.buscarUsuario(nombre) != -1) {
            System.out.println("El nombre de usuario ya existe");
            registrarse();
        } else {
            System.out.println("Ingrese su correo: ");
            String correo = scanner.nextLine();
            System.out.println("Elija su perfil de usuario");
            System.out.println("1. Jugador");
            System.out.println("2. Game Master");
            int opcion = validateOpcion(2);
            if (opcion == 1 || opcion == 2) {
                System.out.println("Ingrese su nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                if (UsuarioLogic.crearUsuario(nombre, correo, opcion, password, nacionalidad)) {
                    System.out.println("Usuario creado exitosamente");
                    menuPrincipal();
                } else {
                    System.out.println("Error al crear el usuario");
                }
            } else {
                System.out.println("Opción inválida");
                registrarse();
            }
        }
    }

    private static void recuperarPassword(String nombre) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nueva contraseña:");
        String password = scanner.nextLine();
        if (UsuarioLogic.cambiarPassword(nombre, password)) {
            System.out.println("Contraseña cambiada exitosamente");
            ingresar();
        } else {
            System.out.println("Error al cambiar la contraseña");
        }
    }

    private static void menuPrincipal() {
        int opcion = 0;
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Registrarse");
        System.out.println("2. Ingresar");
        System.out.println("3. Salir");
        System.out.println("Ingrese una opción: ");
        opcion = validateOpcion(3);
        if (opcion == 1) {
            registrarse();
        } else if (opcion == 2) {
            ingresar();
        } else if (opcion == 3) {
            salir();
        } else {
            System.out.println("Opción inválida");
        }
    }

    private static void mostrarMenuJugador() {
        System.out.println("Bienvenido al juego");
        salir();
    }

    private static void mostrarMenuGameMaster() {
        System.out.println("----------------------------------");
        System.out.println("Bienvenido al menú del Game Master");
        System.out.println("----------------------------------");
        System.out.println("1. Gestión de personajes");
        System.out.println("2. Gestión de misiones");
        System.out.println("3. Gestión de razas");
        System.out.println("4. Gestión de habilidades");
        System.out.println("5. Gestión de poderes");
        System.out.println("6. Gestión de equipo");
        System.out.println("7. Salir");
        int opcion = validateOpcion(7);
        if (opcion == 1) {
            gestionPersonajes();
        } else if (opcion == 2) {
            gestionMisiones();
        } else if (opcion == 3) {
            gestionRazas();
        } else if (opcion == 4) {
            gestionHabilidades();
        } else if (opcion == 5) {
            gestionPoderes();
        } else if (opcion == 6) {
            gestionEquipo();
        } else if (opcion == 7) {
            salir();
        }
    }

    private static void gestionPersonajes() {
        // Implementar lógica de gestión de personajes
    }

    private static void gestionMisiones() {
        // Implementar lógica de gestión de misiones
    }

    public static void gestionRazas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------");
        System.out.println("Bienvenido al menú de gestión de razas");
        System.out.println("--------------------------------------");
        System.out.println("1. Crear raza");
        System.out.println("2. Eliminar raza");
        System.out.println("3. Mostrar todas las razas");
        System.out.println("4. Regresar al menú del Game Master");

        int opcion = validateOpcion(4);
        scanner.nextLine(); // Consume newline

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
                    mostrarMenuGameMaster();
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
                    mostrarMenuGameMaster();
                }
            case 3:
                System.out.println("Mostrar todas las razas");
                List<Raza> razas = RazaLogic.obtenerRazas();
                for (Raza raza : razas) {
                    System.out.println(raza.getNombre() + ": " + raza.getDescripcion());
                }
                mostrarMenuGameMaster();
            case 4:
                System.out.println("Regresar al menú del Game Master");
                mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void gestionHabilidades() {
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

        int opcion = validateOpcion(6);
        scanner.nextLine(); // Consume newline

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
                        System.out.println("El nombre de la raza ya existe");
                        gestionHabilidades();
                    }else{
                        System.out.print("Ingrese la descripción de la Habilidad: ");
                        String descripcionCrear = scanner.nextLine();
                        System.out.println("Asignele un valor al bono de destreza");
                        int bonoDestreza = scanner.nextInt();
                        System.out.println("Asignele un valor al bono de inteligencia");
                        int bonoInteligencia = scanner.nextInt();
                        if(HabilidadLogic.crearHabilidad(nombreCrear, descripcionCrear,idRaza, bonoDestreza, bonoInteligencia)){
                            System.out.println("Raza creada exitosamente");
                            mostrarMenuGameMaster();
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
                    mostrarMenuGameMaster();
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
                mostrarMenuGameMaster();
            case 6:
                System.out.println("Regresar al menú del Game Master");
                mostrarMenuGameMaster();
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
        int opcion = validateOpcion(4);
        scanner.nextLine(); // Consume newline
        switch (opcion) {
            case 1:
                System.out.print("Ingrese la nueva descripción: ");
                String descripcion = scanner.nextLine();
                int bnDst = HabilidadLogic.getHabilidad(nombre).getBonoDestresa();
                int bnInt = HabilidadLogic.getHabilidad(nombre).getBonoInteligencia();
                if(HabilidadLogic.actualizarHabilidad(nombre, descripcion, bnDst, bnInt)){
                    System.out.println("Descripcion modificada exitosamente");
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion2 = validateOpcion(2);
                    switch (opcion2){
                        case 1:
                            menuModificarHabilidad(nombre);
                            break;
                        case 2:
                            mostrarMenuGameMaster();
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    mostrarMenuGameMaster();
                }else{
                    System.out.println("No se pudo modificar la habilidad");
                }
                break;
            case 2:
                System.out.print("Ingrese el nuevo bono de destreza: ");
                int bnDst2 = scanner.nextInt();
                String desc = HabilidadLogic.getHabilidad(nombre).getDescripcion();
                int bnInt2 = HabilidadLogic.getHabilidad(nombre).getBonoInteligencia();
                if(HabilidadLogic.actualizarHabilidad(nombre, desc,bnDst2,bnInt2)){
                    System.out.println("Bono de destreza modificado exitosamente");
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion3 = validateOpcion(2);
                    switch (opcion3){
                        case 1:
                            menuModificarHabilidad(nombre);
                            break;
                        case 2:
                            mostrarMenuGameMaster();
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    mostrarMenuGameMaster();
                }
            case 3:
                System.out.print("Ingrese el nuevo bono de inteligencia: ");
                int bnInt3 = scanner.nextInt();
                String desc2 = HabilidadLogic.getHabilidad(nombre).getDescripcion();
                int bnDst3 = HabilidadLogic.getHabilidad(nombre).getBonoDestresa();
                if(HabilidadLogic.actualizarHabilidad(nombre, desc2,bnDst3,bnInt3)){
                    System.out.println("Bono de inteligencia modificado exitosamente");
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("¿Desea seguir modificando la habilidad "+nombre+"?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    int opcion4 = validateOpcion(2);
                    switch (opcion4){
                        case 1:
                            menuModificarHabilidad(nombre);
                            break;
                        case 2:
                            mostrarMenuGameMaster();
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    mostrarMenuGameMaster();
                }
            case 4:
                mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void gestionPoderes() {
        // Implementar lógica de gestión de poderes
    }

    private static void gestionEquipo() {
        // Implementar lógica de gestión de equipo
    }
}