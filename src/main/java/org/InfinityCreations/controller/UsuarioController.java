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

    public Boolean crearUsuario(String nombre, String correo, int id_perfil, String password, String nacionalidad) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        try {
            Perfil pf = session.get(Perfil.class, id_perfil);
            LocalDateTime fechaRegistro = LocalDateTime.now();
            session.beginTransaction();
            Usuario usuario = new Usuario(0, nombre, correo, pf, fechaRegistro, password, nacionalidad);  // Deja que la BD genere el ID
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
}
