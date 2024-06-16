package org.InfinityCreations.logic;

import org.InfinityCreations.controller.UsuarioController;

public class UsuarioLogic {
    private static UsuarioController usuarioController = new UsuarioController();
    public static int buscarUsuario(String nombre){
        return usuarioController.buscarUsuarios(nombre);
    }
    public static Boolean crearUsuario(String nombre, String correo, int id_perfil, String password, String nacionalidad){
        return usuarioController.crearUsuario(nombre, correo, id_perfil, password, nacionalidad);
    }
}
