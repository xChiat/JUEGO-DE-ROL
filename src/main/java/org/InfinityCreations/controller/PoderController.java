package org.InfinityCreations.controller;

import org.InfinityCreations.entities.Poder;
import org.InfinityCreations.entities.Raza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PoderController {
    List<Poder> poderes = null;
    public PoderController() {
        cargarPoderes();
    }

    private void cargarPoderes() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        poderes = new ArrayList<>();
        try {
            session.beginTransaction();
            jakarta.persistence.Query query = session.createQuery("from Poder");
            poderes = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public int buscarPoder(String nombre){
        List<Poder> p = obtenerTodosLosPoderes();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public boolean crearPoder(String nombre, String descripcion, int id_raza, int bonoFuerza) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Raza rz = session.get(Raza.class, id_raza);
            if (rz == null) {
                throw new RuntimeException("Raza con id " + id_raza + " no encontrada");
            }

            Poder poder = new Poder();
            poder.setNombre(nombre);
            poder.setDescripcion(descripcion);
            poder.setRaza(rz);
            poder.setBonoFuerza(bonoFuerza);


            session.persist(poder);
            tx.commit();
            poderes.add(poder); // Asegúrate de que 'habilidades' esté sincronizado correctamente
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    public boolean eliminarPoder(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Poder> query = session.createQuery("delete from Poder where nombre = :nombre",Poder.class);
            query.setParameter("nombre", nombre);
            int result = query.executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar el poder: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean actualizarPoder(String nombre, String descripcion, int bonoFuerza) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Poder> query = session.createQuery("from Poder where nombre = :nombre", Poder.class);
            query.setParameter("nombre", nombre);
            Poder poder = query.uniqueResult();
            if (poder != null) {
                poder.setDescripcion(descripcion);
                poder.setBonoFuerza(bonoFuerza);
                session.merge(poder);
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
        } finally {
            session.close();
        }
    }

    public static List<Poder> obtenerTodosLosPoderes() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Poder> poderes = null;
        try {
            tx = session.beginTransaction();
            Query<Poder> query = session.createQuery("from Poder", Poder.class);
            poderes = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return poderes;
    }
    public static List<Poder> obtenerPoderesxRaza(int idRaza) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Poder> pXraza = null;
        try {
            tx = session.beginTransaction();
            Query<Poder> query = session.createQuery("from Poder where raza.id = :idRaza", Poder.class);
            query.setParameter("idRaza", idRaza);
            pXraza = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pXraza;
    }

    public Poder getPoder(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Poder poder = null;
        try {
            tx = session.beginTransaction();
            Query<Poder> query = session.createQuery("from Poder where nombre = :nombre", Poder.class);
            query.setParameter("nombre", nombre);
            poder = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return poder;
    }
}
