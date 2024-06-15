package org.InfinityCreations.logic;

import org.InfinityCreations.controller.UsuarioController;
import org.InfinityCreations.entities.Usuario;

public class UsuarioLogic {
    private static UsuarioController usuarioDAO = new UsuarioController();
    public static boolean autentificarUsuarios(String nombre, String password){
        if(obtenerUsuario(nombre)!=null){
            Usuario usuarioConsulta = obtenerUsuario(nombre);
            if(usuarioConsulta.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public static boolean crearUsuario(Usuario usuario){
        return usuarioDAO.crearUsuario(usuario);
    }
    public static boolean modificarUsuario(Usuario usuario){
        return usuarioDAO.modificarUsuario(usuario);
    }
    public static boolean eliminarUsuario(String nombre){
        return usuarioDAO.eliminarUsuario(nombre);
    }
    public static Usuario obtenerUsuario(String nombre){
        return usuarioDAO.getUsuario(nombre);
    }
}
