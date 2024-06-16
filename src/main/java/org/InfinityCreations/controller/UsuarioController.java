package org.InfinityCreations.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.InfinityCreations.entities.Perfil;
import org.InfinityCreations.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios;
    private List<Perfil> perfiles;

    public UsuarioController() {
        try(EntityManagerFactory entityManagerFactory =
                    Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = null;
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Session session = em.unwrap(Session.class);
            usuarios = session.createQuery("from Usuario").list();
            perfiles = session.createQuery("from Perfil").list();
            em.getTransaction().commit();
        }
    }
    public int buscarUsuarios(String nombre){
        int n = -1;
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getNombre().equals(nombre)){
                n = i;
                break;
            }
        }
        return n;
    }
    public boolean crearUsuario(Usuario usuario){
        if (buscarUsuarios(usuario.getNombre()) == -1){
            usuarios.add(usuario);
            return true;
        }
        return false;
    }
    public boolean eliminarUsuario(String nombre){
        if (buscarUsuarios(nombre)!= -1){
            usuarios.remove(buscarUsuarios(nombre));
            return true;
        }
        return false;
    }
    public boolean modificarUsuario(Usuario usuario){
        if (buscarUsuarios(usuario.getNombre())!= -1){
            Usuario usuarioaux= getUsuario(Usuario.getNombre());
            usuarioaux.setCorreo(usuario.getCorreo());
            usuarioaux.setNombre(usuario.getNombre());
            usuarioaux.setNacionalidad(usuario.getNacionalidad());
            return true;
        }
        return false;
    }
    public Usuario getUsuario(String nombre){
        if(buscarUsuarios(nombre)!= -1){
            return usuarios.get(buscarUsuarios(nombre));
        }else{
            return null;
        }
    }
}
