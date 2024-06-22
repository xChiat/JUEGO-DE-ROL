package org.InfinityCreations.controller;

import org.InfinityCreations.entities.Habilidad;
import org.InfinityCreations.entities.Raza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HabilidadController {
    List<Habilidad> habilidades = null;
    public HabilidadController() {
        cargarHabilidades();
    }

    private void cargarHabilidades() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        habilidades = new ArrayList<>();
        try {
            session.beginTransaction();
            jakarta.persistence.Query query = session.createQuery("from Habilidad");
            habilidades = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public int buscarHabilidad(String nombre){
        List<Habilidad> h = obtenerTodasLasHabilidades();
        for (int i = 0; i < h.size(); i++) {
            if (h.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public boolean crearHabilidad(String nombre, String descripcion, int id_raza, int bonoDestreza, int bonoInteligencia) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Raza rz = session.get(Raza.class, id_raza);
            if (rz == null) {
                throw new RuntimeException("Raza con id " + id_raza + " no encontrada");
            }

            Habilidad habilidad = new Habilidad();
            habilidad.setNombre(nombre);
            habilidad.setDescripcion(descripcion);
            habilidad.setRaza(rz);
            habilidad.setBonoDestresa(bonoDestreza);
            habilidad.setBonoInteligencia(bonoInteligencia);

            session.persist(habilidad);
            tx.commit();
            habilidades.add(habilidad); // Asegúrate de que 'habilidades' esté sincronizado correctamente
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


    public boolean eliminarHabilidad(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            jakarta.persistence.Query query = session.createQuery("delete from Habilidad where nombre = :nombre");
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

    public boolean actualizarHabilidad(String nombre, String descripcion, int bonoDestresa, int bonoInteligencia) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Habilidad habilidad = session.get(Habilidad.class, nombre);
            if (habilidad != null) {
                habilidad.setDescripcion(descripcion);
                habilidad.setBonoDestresa(bonoDestresa);
                habilidad.setBonoInteligencia(bonoInteligencia);
                session.merge(habilidad);
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

    public static List<Habilidad> obtenerTodasLasHabilidades() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Habilidad> habilidades = null;
        try {
            tx = session.beginTransaction();
            Query<Habilidad> query = session.createQuery("from Habilidad", Habilidad.class);
            habilidades = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return habilidades;
    }

    public Habilidad getHabilidad(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Habilidad habilidad = null;
        try {
            tx = session.beginTransaction();
            habilidad = session.get(Habilidad.class, nombre);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return habilidad;
    }
}