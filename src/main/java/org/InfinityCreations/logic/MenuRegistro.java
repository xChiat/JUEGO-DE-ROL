package org.InfinityCreations.logic;

import java.util.Scanner;

public class MenuRegistro {
    public MenuRegistro(){
        menu();
    }
    public static void menu(){
        System.out.println("Bienvenido a Infinity Creations");
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Registrarse");
        System.out.println("2. Ingresar");
        System.out.println("3. Salir");
        System.out.println("Ingrese una opción: ");
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        opcion = scanner.nextInt();
        if (opcion == 1){
            registrarse();
        }else if (opcion == 2){
            ingresar();
        } else if (opcion == 3){
            salir();
        }else{
            System.out.println("Opción inválida");
            new MenuRegistro();
        }
    }
    public static void registrarse(){
        System.out.println("Registrarse");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        if(UsuarioLogic.buscarUsuario(nombre) != -1){
            System.out.println("El nombre de usuario ya existe");
            registrarse();
        }else{
            System.out.println("Ingrese su correo: ");
            String correo = scanner.nextLine();
            System.out.println("Elija su perfil de usuario");
            System.out.println("1. Jugador");
            System.out.println("2. Game Master");
            int opcion = 0;
            opcion = scanner.nextInt();
            if (opcion == 1){
                int perfil_id= opcion;
                System.out.println("Ingrese su nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                String psswd = PasswordToHash.getSHA256Hash(password);
                UsuarioLogic.crearUsuario(nombre, correo, perfil_id, nacionalidad, psswd);
                System.out.println("Usuario creado exitosamente");
            }else if(opcion == 2){
                int perfil_id= opcion;
                System.out.println("Ingrese su nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String password = scanner.nextLine();
                String psswd = PasswordToHash.getSHA256Hash(password);
                UsuarioLogic.crearUsuario(nombre, correo, perfil_id, nacionalidad, psswd);
                System.out.println("Usuario creado exitosamente");
            }else{
                System.out.println("Opción inválida");
                registrarse();
            }
        };

    }
    public static void ingresar(){
        System.out.println("Ingresar");
    }
    public static void salir(){
        System.out.println("Salir");
    }
}
