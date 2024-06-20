package org.InfinityCreations.controller;

import jakarta.persistence.Query;
import org.InfinityCreations.entities.Perfil;
import org.InfinityCreations.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios;

    public UsuarioController() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        usuarios = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Usuario");
            usuarios = query.getResultList();  // Corrige la asignación aquí
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Asegúrate de cerrar la sesión
        }
    }

    public int buscarUsuarios(String nombre) {
        int n = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombre().equals(nombre)) {
                n = i;
                break;
            }
        }
        return n;
    }
    public Usuario getUsuario(String nombre) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombre().equals(nombre)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public Boolean crearUsuario(String nombre, String correo, int id_perfil, String password, String nacionalidad) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        try {
            Perfil pf = session.get(Perfil.class, id_perfil);
            long id = usuarios.size() + 1;
            LocalDateTime fechaRegistro = LocalDateTime.now();
            session.beginTransaction();
            Usuario usuario = new Usuario(id, nombre, correo, pf, fechaRegistro, password, nacionalidad);
            session.save(usuario);
            session.getTransaction().commit();
            usuarios.add(usuario);  // Asegúrate de actualizar la lista en memoria
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();  // Asegúrate de cerrar la sesión
        }
    }

    public Usuario autenticarUsuario(String nombre, String password) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        Usuario usuario = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Usuario where nombre = :nombre and password = :password");
            query.setParameter("nombre", nombre);
            query.setParameter("password", password);
            @SuppressWarnings("unchecked")
            Usuario usuarioResult = (Usuario) query.getSingleResult();
            session.getTransaction().commit();
            return usuarioResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();  // Asegúrate de cerrar la sesión
        }
    }
    public Boolean cambiarPassword(String nombre, String password) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        Usuario user = getUsuario(nombre);
        try{
            session.beginTransaction();
            user.setPassword(password);
            session.update(user);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();  // Asegúrate de cerrar la sesión
        }
    }
}
