package org.InfinityCreations;

import org.InfinityCreations.entities.Usuario;
import org.InfinityCreations.logic.UsuarioLogic;
import org.InfinityCreations.utils.PasswordRecovery;
import org.InfinityCreations.vista.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("|    Prototipo juego de rol   |");
        System.out.println("-------------------------------");
        System.out.println("Bienvenido a Infinity Creations");
        menuPrincipal();
    }

    public static int validateOpcion(int menu) {
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
                        mostrarMenuJugador(usuario);
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
        Usuario usuario = UsuarioLogic.getUsuario(nombre);
        if (usuario == null) {
            System.out.println("El nombre de usuario no existe");
            return;
        }

        int verificationCode = PasswordRecovery.generateVerificationCode();
        PasswordRecovery.sendVerificationCode(usuario.getCorreo(), verificationCode);

        System.out.println("Se ha enviado un código de verificación a su correo.");
        System.out.println("Ingrese el código de verificación:");
        int enteredCode = scanner.nextInt();

        if (enteredCode == verificationCode) {
            System.out.println("Código de verificación correcto. Ingrese su nueva contraseña:");
            scanner.nextLine(); // Consume the newline left-over
            String password = scanner.nextLine();
            if (UsuarioLogic.cambiarPassword(nombre, password)) {
                System.out.println("Contraseña cambiada exitosamente");
                ingresar();
            } else {
                System.out.println("Error al cambiar la contraseña");
            }
        } else {
            System.out.println("Código de verificación incorrecto.");
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

    public static void mostrarMenuJugador(Usuario usuario) {
        System.out.println("Bienvenido al juego");
        System.out.println("----------------------------------");
        System.out.println("Bienvenido al menú del Game Master");
        System.out.println("----------------------------------");
        System.out.println("1. Gestión de personajes");
        System.out.println("2. Tienda");
        System.out.println("3. Comenzar Aventura");
        System.out.println("4. Salir");
        int opcion = validateOpcion(4);
        if (opcion == 1) {
            GestionPersonajesJugador.gestionPersonajesJugador(usuario);
        } else if (opcion == 2) {
            System.out.println("Proximamente...");
            mostrarMenuJugador(usuario);
        } else if (opcion == 3) {
            System.out.println("Proximamente...");
            mostrarMenuJugador(usuario);
        } else if (opcion == 4) {
            salir();
        }
    }

    public static void mostrarMenuGameMaster() {
        System.out.println("----------------------------------");
        System.out.println("Bienvenido al menú del Game Master");
        System.out.println("----------------------------------");
        System.out.println("1. Gestión de personajes");
        System.out.println("2. Gestión de estados");
        System.out.println("3. Gestión de razas");
        System.out.println("4. Gestión de habilidades");
        System.out.println("5. Gestión de poderes");
        System.out.println("6. Gestión de equipo");
        System.out.println("7. Salir");
        int opcion = validateOpcion(7);
        if (opcion == 1) {

        } else if (opcion == 2) {
            GestionEstados.gestionEstados();
        } else if (opcion == 3) {
            GestionRazas.gestionRazas();
        } else if (opcion == 4) {
            GestionHabilidades.gestionHabilidades();
        } else if (opcion == 5) {
            GestionPoderes.gestionPoderes();
        } else if (opcion == 6) {
            GestionEquipo.gestionEquipo();
        } else if (opcion == 7) {
            salir();
        }
    }
}
