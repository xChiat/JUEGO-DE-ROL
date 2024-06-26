package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.Personaje;
import org.InfinityCreations.logic.PersonajeLogic;

import java.util.List;
import java.util.Scanner;

public class GestionPersonajesGM {
    public static void gestionPersonajesGM() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        System.out.println("| Bienvenido al menú de gestión de Personajes (GM) |");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Modificar ficha de personaje");
        System.out.println("2. Ver informe de personajes");
        System.out.println("3. Regresar al menú principal");

        int opcion = Main.validateOpcion(3);

        switch (opcion) {
            case 1:
                modificarFichaPersonaje(scanner);
                break;
            case 2:
                verInformePersonajes();
                break;
            case 3:
                Main.mostrarMenuGameMaster();
                break;
            default:
                System.out.println("Opción no válida");
                gestionPersonajesGM();
                break;
        }
    }

    private static void modificarFichaPersonaje(Scanner scanner) {
        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();
        Personaje personaje = PersonajeLogic.getPersonaje(nombre);
        if (personaje != null) {
            System.out.println("Modificando ficha de: " + personaje.getNombre());
            // Implementar la lógica para modificar las propiedades del personaje
            // Ejemplo: personaje.setPuntosVida(nuevosPuntosVida);
            // Llamar a PersonajeLogic.actualizarPersonaje para guardar los cambios
        } else {
            System.out.println("Personaje no encontrado.");
        }
        gestionPersonajesGM();
    }

    private static void verInformePersonajes() {
        List<Personaje> personajes = PersonajeLogic.obtenerPersonajes();
        System.out.println("Informe de personajes:");
        for (Personaje personaje : personajes) {
            System.out.println("Nombre: " + personaje.getNombre());
            System.out.println("Raza: " + personaje.getRazaId());
            System.out.println("Nivel: " + personaje.getNivel());
            System.out.println("Estado: " + personaje.getEstadoId());
            System.out.println("----------------------------");
        }
        Main.mostrarMenuGameMaster();
    }
}
