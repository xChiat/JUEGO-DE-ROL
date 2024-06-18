package org.InfinityCreations;
import org.InfinityCreations.logic.PasswordToHash;
import org.InfinityCreations.logic.UsuarioLogic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prototipo juego de rol");
        System.out.println("Bienvenido a Infinity Creations");
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Registrarse");
        System.out.println("2. Ingresar");
        System.out.println("3. Salir");
        System.out.print("Ingrese una opción: ");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        if (opcion == 1) {
            registrarse(scanner);
        } else if (opcion == 2) {
            ingresar(scanner);
        } else if (opcion == 3) {
            salir();
        } else {
            System.out.println("Opción inválida");
        }
    }

    private static void salir() {
        System.out.println("Gracias por usar Infinity Creations");
    }

    private static void ingresar(Scanner scanner) {
        // Implementación pendiente
    }

    private static void registrarse(Scanner scanner) {
        System.out.println("Registrarse");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        if (UsuarioLogic.buscarUsuario(nombre) != -1) {
            System.out.println("El nombre de usuario ya existe");
            registrarse(scanner);
        } else {
            System.out.print("Ingrese su correo: ");
            String correo = scanner.nextLine();
            System.out.println("Elija su perfil de usuario");
            System.out.println("1. Jugador");
            System.out.println("2. Game Master");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            if (opcion == 1 || opcion == 2) {
                int perfil_id = opcion;
                System.out.print("Ingrese su nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                System.out.print("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                String psswd = PasswordToHash.getSHA256Hash(password);
                if (UsuarioLogic.crearUsuario(nombre, correo, perfil_id, psswd, nacionalidad)) {
                    System.out.println("Usuario creado exitosamente");
                } else {
                    System.out.println("Error al crear el usuario");
                }
            } else {
                System.out.println("Opción inválida");
                registrarse(scanner);
            }
        }
    }
}