package org.InfinityCreations.controller;

import org.InfinityCreations.entities.Armaduras;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ArmadurasController {
    List<Armaduras> armaduras;

    public ArmadurasController() {
        armaduras = new ArrayList<>();
        cargarArmaduras();
    }

    private void cargarArmaduras() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Armaduras> query = session.createQuery("from Armaduras", Armaduras.class);
            armaduras = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int buscarArmaduras(String nombre) {
        List<Armaduras> a = obtenerTodasLasArmaduras();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public boolean crearArmaduras(String nombre, String descripcion, int bonoVida, int precio) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Armaduras armadura = new Armaduras();
            armadura.setNombre(nombre);
            armadura.setDescripcion(descripcion);
            armadura.setBonoVida(bonoVida);
            armadura.setPrecio(precio);
            session.persist(armadura);
            tx.commit();
            armaduras.add(armadura);
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarArmaduras(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Armaduras where nombre = :nombre");
            query.setParameter("nombre", nombre);
            int result = query.executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar la Armadura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarArmaduras(String nombre, String newNombre, String descripcion, int bonoVida, int precio) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query<Armaduras> query = session.createQuery("from Armaduras where nombre = :nombre", Armaduras.class);
            query.setParameter("nombre", nombre);
            Armaduras armadura = query.uniqueResult();
            if (armadura != null) {
                armadura.setNombre(newNombre);
                armadura.setDescripcion(descripcion);
                armadura.setBonoVida(bonoVida);
                armadura.setPrecio(precio);
                session.merge(armadura);
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

    public static List<Armaduras> obtenerTodasLasArmaduras() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        List<Armaduras> armaduras = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Armaduras> query = session.createQuery("from Armaduras", Armaduras.class);
            armaduras = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return armaduras;
    }

    public Armaduras getArmadura(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Armaduras armadura = null;
        try {
            tx = session.beginTransaction();
            Query<Armaduras> query = session.createQuery("from Armaduras where nombre = :nombre", Armaduras.class);
            query.setParameter("nombre", nombre);
            armadura = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return armadura;
    }
}
