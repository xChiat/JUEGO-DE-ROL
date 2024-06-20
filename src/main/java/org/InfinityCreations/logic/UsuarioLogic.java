package org.InfinityCreations.logic;

import org.InfinityCreations.controller.UsuarioController;
import org.InfinityCreations.entities.Usuario;

public class UsuarioLogic {
    private static UsuarioController usuarioController = new UsuarioController();

    public static int buscarUsuario(String nombre) {
        return usuarioController.buscarUsuarios(nombre);
    }

    public static Usuario getUsuario(String nombre) {
        return usuarioController.getUsuario(nombre);
    }

    public static Boolean crearUsuario(String nombre, String correo, int id_perfil, String password, String nacionalidad) {
        return usuarioController.crearUsuario(nombre, correo, id_perfil, password, nacionalidad);
    }

    public static boolean iniciarSesion(String nombre, String password) {
        return usuarioController.autenticarUsuario(nombre, PasswordToHash.getSHA256Hash(password)) != null;
    }

    public static Boolean cambiarPassword(String nombre, String password) {
        return usuarioController.cambiarPassword(nombre, PasswordToHash.getSHA256Hash(password));
    }
}
