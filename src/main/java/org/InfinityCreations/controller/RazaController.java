package org.InfinityCreations.controller;

import jakarta.persistence.Query;
import org.InfinityCreations.entities.Raza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class RazaController {
    private List<Raza> razas;
    public RazaController() {
        cargarRaza();
    }
    private void cargarRaza() {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        razas = new ArrayList<>();
        try{
            session.beginTransaction();
            Query query = session.createQuery("from Razas");
            razas = query.getResultList();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public int buscarRaza(String nombre){
        for (int i = 0; i < razas.size(); i++) {
            if (razas.get(i).getNombre().equals(nombre)) {
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
    public Boolean crearRaza(String nombre, String descripcion) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        try{
            long id = razas.size()+1;
            session.beginTransaction();
            Raza raza = new Raza(id, nombre, descripcion);
            session.persist(raza);
            session.getTransaction().commit();
            razas.add(raza);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
    public Boolean eliminarRaza(String nombre) {
        SessionFactory sf = SessionFactoryProvider.provideSessionFactory();
        Session session = sf.openSession();
        try{
            session.beginTransaction();
            Query query = session.createQuery("delete from raza where nombre = :nombre");
            query.setParameter("nombre", nombre);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            return result > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
    public String ListarRazas(){
        StringBuilder result = new StringBuilder();
        for (Raza raza : razas) {
            result.append("Nombre: ").append(raza.getNombre()).append(" - Descripcion").append(raza.getDescripcion()).append("\n");
        }
        return result.toString();
    }
}