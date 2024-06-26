package org.InfinityCreations.controller;

import org.InfinityCreations.entities.Personaje;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonajeController {
    List<Personaje> personajes;
    public PersonajeController() {
        personajes = new ArrayList<>();
        cargarPersonajes();
    }

    private void cargarPersonajes() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Personaje> query = session.createQuery("from Personaje", Personaje.class);
            personajes = query.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int buscarPersonaje(String nombre){
        List<Personaje> p = obtenerTodosLosPersonajes();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }
    public Personaje getPersonaje(String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<Personaje> query = session.createQuery("from Personaje where nombre = :nombre", Personaje.class);
            query.setParameter("nombre", nombre);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Personaje> obtenerTodosLosPersonajes() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<Personaje> query = session.createQuery("from Personaje", Personaje.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int buscarPersonajePorUsuario(int usuario, String nombre) {
        List<Personaje> p = obtenerTodosLosPersonajes();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getNombre().equals(nombre) && p.get(i).getUsuarioId() == usuario) {
                return i;
            }
        }
        return -1;
    }

    public Personaje getPersonajePorUsuario(int usuario, String nombre) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<Personaje> query = session.createQuery("from Personaje where nombre = :nombre and usuarioId = :usuario", Personaje.class);
            query.setParameter("nombre", nombre);
            query.setParameter("usuario", usuario);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean crearPersonaje(String nombre, int i, int dez, int fz, int pv, int niv, int exp, int estadoID, int usuarioID, int razaID){
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            Personaje personaje = new Personaje();
            personaje.setNombre(nombre);
            personaje.setInteligencia(i);
            personaje.setDestreza(dez);
            personaje.setFuerza(fz);
            personaje.setPuntosVida(pv);
            personaje.setNivel(niv);
            personaje.setExperiencia(exp);
            personaje.setEstadoId(estadoID);
            personaje.setUsuarioId(usuarioID);
            personaje.setRazaId(razaID);
            session.save(personaje);
            tx.commit();
            return true;
        }catch (Exception e){
            if (tx!= null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPersonaje(String nombre, int usuario ){
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            Query<Personaje> query = session.createQuery("from Personaje where nombre = :nombre and usuarioId = :usuario", Personaje.class);
            query.setParameter("nombre", nombre);
            query.setParameter("usuario", usuario);
            Personaje personaje = query.uniqueResult();
            session.delete(personaje);
            tx.commit();
            return true;
        }catch (Exception e){
            if (tx!= null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPersonaje(String nombre,int i, int dez, int fz, int pv, int niv, int exp, int estadoID, int usuario){
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            Query<Personaje> query = session.createQuery("from Personaje where nombre = :nombre and usuarioId = :usuario", Personaje.class);
            query.setParameter("nombre", nombre);
            query.setParameter("usuario", usuario);
            Personaje personaje = query.uniqueResult();
            personaje.setPuntosVida(pv);
            personaje.setNivel(niv);
            personaje.setExperiencia(exp);
            personaje.setEstadoId(estadoID);
            session.merge(personaje);
            tx.commit();
            return true;
        }catch (Exception e){
            if (tx!= null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}