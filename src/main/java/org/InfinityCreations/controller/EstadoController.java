package org.InfinityCreations.controller;

import jakarta.persistence.Query;
import org.InfinityCreations.entities.Estado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EstadoController {
    private List<Estado> estados;
    public EstadoController(){
        cargarEstado();
    }
    public static List<Estado> obtenerTodosLosEstados() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Estado> estados = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Estado",Estado.class);
            estados = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return estados;
    }

    private void cargarEstado() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            estados = new ArrayList<>();
            session.beginTransaction();
            org.hibernate.query.Query<Estado> query = session.createQuery("from Estado", Estado.class);
            estados = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int buscarEstado(String nombre) {
        List<Estado> e = obtenerTodosLosEstados();
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }
    public Estado getEstado(String nombre) {
        for (Estado estado : estados) {
            if (estado.getNombre().equals(nombre)) {
                return estado;
            }
        }
        return null;
    }
    public static boolean crearEstado(String nombre, String descripcion) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Estado estado = new Estado();
            estado.setNombre(nombre);
            estado.setDescripcion(descripcion);
            session.persist(estado);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public static boolean eliminarEstado(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Estado where nombre = :nombre", Estado.class);
            query.setParameter("nombre", nombre);
            int result = query.executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar la raza: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static boolean actualizarEstado(String nombre, String descripcion) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            org.hibernate.query.Query<Estado> query = session.createQuery("from Estado where nombre = :nombre", Estado.class);
            query.setParameter("nombre", nombre);
            Estado estado = query.uniqueResult();
            if (estado != null) {
                estado.setDescripcion(descripcion);
                session.merge(estado);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
