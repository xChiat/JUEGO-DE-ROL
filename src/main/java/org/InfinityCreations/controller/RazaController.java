package org.InfinityCreations.controller;

import jakarta.persistence.Query;
import org.InfinityCreations.entities.Raza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RazaController {
    private List<Raza> razas;

    public RazaController() {
        cargarRaza();
    }

    public static List<Raza> obtenerTodasLasRazas() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Raza> razas = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Raza");
            razas = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return razas;
    }

    private void cargarRaza() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        razas = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Raza");
            razas = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int buscarRaza(String nombre) {
        List<Raza> r = obtenerTodasLasRazas();
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public Raza getRaza(String nombre) {
        for (Raza raza : razas) {
            if (raza.getNombre().equals(nombre)) {
                return raza;
            }
        }
        return null;
    }

    public static boolean crearRaza(String nombre, String descripcion) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Raza raza = new Raza();
            raza.setNombre(nombre);
            raza.setDescripcion(descripcion);
            session.persist(raza);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al persistir la raza: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean eliminarRaza(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Utilizar la entidad Raza en la consulta HQL
            Query query = session.createQuery("delete from Raza where nombre = :nombre");
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
        } finally {
            session.close();
        }
    }
}