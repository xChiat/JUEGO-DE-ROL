package org.InfinityCreations.vista;

import org.InfinityCreations.Main;
import org.InfinityCreations.entities.*;
import org.InfinityCreations.logic.*;
import org.InfinityCreations.utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GestionPersonajesJugador {
    public static void gestionPersonajesJugador(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("| Bienvenido al menú de gestión de Personajes (Jugador) |");
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Crear personaje");
        System.out.println("2. Ver personajes");
        System.out.println("3. Regresar al menú principal");

        int opcion = Main.validateOpcion(3);

        switch (opcion) {
            case 1:
                crearPersonaje(scanner, usuario);
                break;
            case 2:
                verPersonajes(scanner, usuario);
                break;
            case 3:
                Main.mostrarMenuJugador(usuario);
                break;
            default:
                System.out.println("Opción no válida");
                gestionPersonajesJugador(usuario);
                break;
        }
    }

    private static void crearPersonaje(Scanner scanner, Usuario usuario) {
        int maxNombreLength = DatabaseUtils.getColumnMaxLength("Personaje", "nombre");
        System.out.println("---------------------");
        System.out.println("|  Crear Personaje  |");
        System.out.println("---------------------");
        System.out.println("Ingrese el nombre del nuevo personaje: ");
        String nombre = readInputWithMaxLength(scanner, maxNombreLength);
        int usuarioId = (int) usuario.getId();
        if (PersonajeLogic.buscarPersonajePorUsuario(nombre, usuarioId) != -1) {
            System.out.println("El nombre del personaje ya existe");
            gestionPersonajesJugador(usuario);
        } else {
            maxNombreLength = DatabaseUtils.getColumnMaxLength("raza", "nombre");
            System.out.print("Ingrese la raza del personaje: ");
            String raza = readInputWithMaxLength(scanner, maxNombreLength);
            if (RazaLogic.buscarRaza(raza) == -1) {
                System.out.println("La raza no existe");
                gestionPersonajesJugador(usuario);
            } else {
                int razaId = RazaLogic.getRaza(raza).getId();
                int estadoId = EstadoLogic.getEstado("vivo").getId();
                boolean creado = PersonajeLogic.crearPersonaje(nombre, 3, 3, 3, 10, 1, 0, estadoId, usuarioId, razaId);
                if (creado) {
                    System.out.println("Personaje creado exitosamente.");
                    Personaje p = PersonajeLogic.getPersonajePorUsuario(usuarioId, nombre);
                    asignarHyPiniciales(scanner, raza, p);
                } else {
                    System.out.println("Error al crear el personaje.");
                }
                gestionPersonajesJugador(usuario);
            }
        }
    }

    private static void asignarHyPiniciales(Scanner scanner, String raza, Personaje personaje) {
        int bonoInteligencia = 0;
        int bonoDestreza = 0;
        int bonoFuerza = 0;
        int bonoVida = 0;
        System.out.println("-------------------------------------");
        System.out.println("|  Habilidades y Poderes iniciales  |");
        System.out.println("-------------------------------------");
        System.out.println("- Asignar habilidades -");
        int idRaza = RazaLogic.getRaza(raza).getId();
        List<Habilidad> hXraza = HabilidadLogic.obtenerHabilidadesxRaza(idRaza);
        System.out.println("- Habilidades disponibles -");
        for (Habilidad habilidad : hXraza) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Nombre : " + habilidad.getNombre());
            System.out.println("Descripción : " + habilidad.getDescripcion());
            System.out.println("Bono de destreza : " + habilidad.getBonoDestresa());
            System.out.println("Bono de inteligencia : " + habilidad.getBonoInteligencia());
            System.out.println("----------------------------------------------------------------");
        }
        int h = 0;
        while (h < 2) {
            System.out.println("Seleccione una habilidad - Ingrese el nombre- :");
            String nombreHabilidad = readInputWithMaxLength(scanner, DatabaseUtils.getColumnMaxLength("Habilidad", "nombre"));
            for (Habilidad habilidad : hXraza) {
                if (Objects.equals(nombreHabilidad, habilidad.getNombre())) {
                    bonoDestreza += habilidad.getBonoDestresa();
                    bonoInteligencia += habilidad.getBonoInteligencia();
                    List<Habilidad> selectedPowers = personaje.getHabilidades();
                    if (selectedPowers == null) {
                        selectedPowers = new ArrayList<>();
                    }
                    selectedPowers.add(habilidad);
                    personaje.setHabilidades(selectedPowers);
                    h++;
                } else {
                    System.out.println("Habilidad no encontrada");
                }
                break;
            }
        }
        System.out.println("- Asignar poderes -");
        List<Poder> pXraza = PoderLogic.obtenerPoderesxRaza(idRaza);
        System.out.println("- Poderes disponibles -");
        for (Poder poder : pXraza) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Nombre : " + poder.getNombre());
            System.out.println("Descripción : " + poder.getDescripcion());
            System.out.println("Bono de Fuerza : " + poder.getBonoFuerza());
            System.out.println("----------------------------------------------------------------");
        }
        System.out.println("Seleccione un poder - Ingrese el nombre- :");
        String nombrePoder = readInputWithMaxLength(scanner, DatabaseUtils.getColumnMaxLength("Poder", "nombre"));
        for (Poder poder : pXraza) {
            if (Objects.equals(nombrePoder, poder.getNombre())) {
                bonoFuerza += poder.getBonoFuerza();
                List<Poder> selectedPowers = personaje.getPoderes();
                if (selectedPowers == null) {
                    selectedPowers = new ArrayList<>();
                }
                selectedPowers.add(poder);
                personaje.setPoderes(selectedPowers);
            } else {
                System.out.println("Poder no encontrado");
            }
            break;
        }
        System.out.println("- Asignar equipamiento -");
        List<Armaduras> armaduras = ArmadurasLogic.obtenerArmaduras();
        System.out.println("- Equipos disponibles -");
        for (Armaduras armadura : armaduras) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Nombre: " + armadura.getNombre());
            System.out.println("Descripción: " + armadura.getDescripcion());
            System.out.println("Tipo: Armadura");
            System.out.println("Protección: " + armadura.getBonoVida() + " puntos");
            System.out.println("Precio: $" + armadura.getPrecio());
            System.out.println("----------------------------------------------------------------");
        }
        List<Armas> armas = ArmasLogic.obtenerArmas();
        for (Armas arma : armas) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Nombre: " + arma.getNombre());
            System.out.println("Descripción: " + arma.getDescripcion());
            System.out.println("Tipo: Arma");
            System.out.println("Daño: " + arma.getBonoFuerza() + " puntos");
            System.out.println("Precio: $" + arma.getPrecio());
            System.out.println("----------------------------------------------------------------");
        }
        System.out.println("Seleccione el tipo de equipo :");
        System.out.println("1. Armadura");
        System.out.println("2. Armas");
        int opcion = Main.validateOpcion(2);
        switch (opcion) {
            case 1:
                System.out.println("Seleccione un equipo - Ingrese el nombre- :");
                String nombreArmadura = readInputWithMaxLength(scanner, DatabaseUtils.getColumnMaxLength("Armaduras", "nombre"));
                for (Armaduras armadura : armaduras) {
                    if (Objects.equals(nombreArmadura, armadura.getNombre())) {
                        bonoVida += armadura.getBonoVida();
                        List<Equipo> selectedPowers = personaje.getEquipamiento();
                        if (selectedPowers == null) {
                            selectedPowers = new ArrayList<>();
                        }
                        selectedPowers.add(armadura);
                        personaje.setEquipamiento(selectedPowers);
                    } else {
                        System.out.println("Armadura no encontrada");
                    }
                    break;
                }
                break;
            case 2:
                System.out.println("Seleccione un equipo - Ingrese el nombre- :");
                String nombreArma = readInputWithMaxLength(scanner, DatabaseUtils.getColumnMaxLength("Armas", "nombre"));
                for (Armas arma : armas) {
                    if (Objects.equals(nombreArma, arma.getNombre())) {
                        bonoFuerza += arma.getBonoFuerza();
                        List<Equipo> selectedPowers = personaje.getEquipamiento();
                        if (selectedPowers == null) {
                            selectedPowers = new ArrayList<>();
                        }
                        selectedPowers.add(arma);
                        personaje.setEquipamiento(selectedPowers);
                    } else {
                        System.out.println("Arma no encontrada");
                    }
                    break;
                }
                break;
            default:
                System.out.println("Tipo de equipo no válido");
                break;
        }
        int i = personaje.getInteligencia() + bonoInteligencia;
        int d = personaje.getDestreza() + bonoDestreza;
        int f = personaje.getFuerza() + bonoFuerza;
        int v = personaje.getPuntosVida() + bonoVida;
        if (PersonajeLogic.actualizarPersonaje(personaje.getNombre(), i, d, f, v, personaje.getNivel(), personaje.getExperiencia(), personaje.getEstadoId(), personaje.getUsuarioId())) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Creación de personaje Finalizado :)");
            System.out.println("----------------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Creación de personaje Fallida :(");
            System.out.println("----------------------------------------------------------------");
        }
    }

    private static void verPersonajes(Scanner scanner, Usuario usuario) {
        int usuarioId = (int) usuario.getId();
        List<Personaje> personajes = PersonajeLogic.obtenerPersonajes();
        for (Personaje personaje : personajes) {
            if (personaje.getUsuarioId() == usuarioId) {
                System.out.println("-----------------------------------------");
                System.out.println("Nombre: " + personaje.getNombre());
                System.out.println("Raza: " + personaje.getRazaId());
                System.out.println("Nivel: " + personaje.getNivel());
                System.out.println("Estado: " + personaje.getEstadoId());
                System.out.println("-----------------------------------------");
            }
        }
        Main.mostrarMenuJugador(usuario);
    }

    private static String readInputWithMaxLength(Scanner scanner, int maxLength) {
        String input = scanner.nextLine();
        while (input.length() > maxLength) {
            System.out.println("La entrada es demasiado larga. El máximo permitido es " + maxLength + " caracteres. Inténtelo de nuevo:");
            input = scanner.nextLine();
        }
        return input;
    }
}
