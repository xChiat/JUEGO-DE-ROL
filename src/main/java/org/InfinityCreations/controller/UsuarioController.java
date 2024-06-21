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
            usuarios = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int buscarUsuarios(String nombre) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public Usuario getUsuario(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
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
            session.persist(usuario);
            session.getTransaction().commit();
            usuarios.add(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public Usuario autenticarUsuario(String nombre, String password) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        Usuario usuarioResult = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Usuario where nombre = :nombre and password = :password");
            query.setParameter("nombre", nombre);
            query.setParameter("password", password);
            usuarioResult = (Usuario) query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuarioResult;
    }

    public Boolean cambiarPassword(String nombre, String password) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("update Usuario set password = :password where nombre = :nombre");
            query.setParameter("password", password);
            query.setParameter("nombre", nombre);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
