package org.InfinityCreations;

import org.InfinityCreations.entities.Usuario;
import org.InfinityCreations.logic.PasswordToHash;
import org.InfinityCreations.logic.UsuarioLogic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prototipo juego de rol");
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
            } catch (NumberFormatException opcion) {
                System.out.println("Solo se pueden ingresar numeros");
                scanner.next();  // Consumir el valor incorrecto
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
                int perfil_id = opcion;
                System.out.println("Ingrese su nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                if (UsuarioLogic.crearUsuario(nombre, correo, perfil_id, password, nacionalidad)) {
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
        System.out.println("Bienvenido al menú del GameMaster");
        salir();
    }
}
