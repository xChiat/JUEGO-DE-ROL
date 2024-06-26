package org.InfinityCreations.controller;

import org.InfinityCreations.entities.Armas;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ArmasController {
    List<Armas> armas;

    public ArmasController() {
        armas = new ArrayList<>();
        cargarArmas();
    }

    private void cargarArmas() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Armas> query = session.createQuery("from Armas", Armas.class);
            armas = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int buscarArmas(String nombre) {
        List<Armas> a = obtenerTodasLasArmas();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public boolean crearArmas(String nombre, String descripcion, int bonoFuerza, int precio) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Armas arma = new Armas();
            arma.setNombre(nombre);
            arma.setDescripcion(descripcion);
            arma.setBonoFuerza(bonoFuerza);
            arma.setPrecio(precio);
            session.persist(arma);
            tx.commit();
            armas.add(arma);
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarArmas(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Armas where nombre = :nombre");
            query.setParameter("nombre", nombre);
            int result = query.executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar el Arma: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarArmas(String nombre, String newNombre, String descripcion, int bonoFuerza, int precio) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query<Armas> query = session.createQuery("from Armas where nombre = :nombre", Armas.class);
            query.setParameter("nombre", nombre);
            Armas arma = query.uniqueResult();
            if (arma != null) {
                arma.setNombre(newNombre);
                arma.setDescripcion(descripcion);
                arma.setBonoFuerza(bonoFuerza);
                arma.setPrecio(precio);
                session.merge(arma);
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

    public static List<Armas> obtenerTodasLasArmas() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        List<Armas> armas = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Armas> query = session.createQuery("from Armas", Armas.class);
            armas = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return armas;
    }

    public Armas getArmas(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Armas arma = null;
        try {
            tx = session.beginTransaction();
            Query<Armas> query = session.createQuery("from Armas where nombre = :nombre", Armas.class);
            query.setParameter("nombre", nombre);
            arma = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return arma;
    }
}
