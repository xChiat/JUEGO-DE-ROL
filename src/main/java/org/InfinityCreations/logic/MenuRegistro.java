package org.InfinityCreations.logic;

import java.util.Scanner;

public class MenuRegistro {
    public MenuRegistro(){
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
    public void registrarse(){
        System.out.println("Registrarse");

    }
    public void ingresar(){
        System.out.println("Ingresar");
    }
    public void salir(){
        System.out.println("Salir");
    }
}
