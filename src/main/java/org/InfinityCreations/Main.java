package org.InfinityCreations;
import org.InfinityCreations.entities.Usuario;
import org.InfinityCreations.logic.PasswordToHash;
import org.InfinityCreations.logic.UsuarioLogic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Prototipo juego de rol");
        System.out.println("Bienvenido a Infinity Creations");
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.println("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            if (opcion == 1){
                registrarse();
            } else if (opcion == 2){
                ingresar();
            } else if (opcion == 3){
                salir();
            } else {
                System.out.println("Opción inválida");
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
        System.out.println("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        Usuario usuario = UsuarioLogic.iniciarSesion(nombre, password);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso");
            if (usuario.getPerfil().getId() == 1) {
                mostrarMenuJugador();
            } else if (usuario.getPerfil().getId() == 2) {
                mostrarMenuGameMaster();
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos");
        }
    }

    private static void registrarse() {
        System.out.println("Registrarse");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        if(UsuarioLogic.buscarUsuario(nombre) != -1){
            System.out.println("El nombre de usuario ya existe");
            registrarse();
        } else {
            System.out.println("Ingrese su correo: ");
            String correo = scanner.nextLine();
            System.out.println("Elija su perfil de usuario");
            System.out.println("1. Jugador");
            System.out.println("2. Game Master");
            int opcion = 0;
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            if (opcion == 1 || opcion == 2) {
                int perfil_id = opcion;
                System.out.println("Ingrese su nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                String psswd = PasswordToHash.getSHA256Hash(password);
                if (UsuarioLogic.crearUsuario(nombre, correo, perfil_id, psswd, nacionalidad)) {
                    System.out.println("Usuario creado exitosamente");
                } else {
                    System.out.println("Error al crear el usuario");
                }
            } else {
                System.out.println("Opción inválida");
                registrarse();
            }
        }
    }

    private static void mostrarMenuJugador() {
        System.out.println("Bienvenido al juego");
    }

    private static void mostrarMenuGameMaster() {
        System.out.println("Bienvenido al menú del GameMaster");
    }
}