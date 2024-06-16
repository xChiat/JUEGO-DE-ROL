package org.InfinityCreations.controller;

import org.InfinityCreations.entities.Perfil;
import org.InfinityCreations.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios;
    public UsuarioController() {
        SessionFactory sf=SessionFactoryProvider.provideSessionFactory();
        Session session=sf.openSession();
        try{
            session.beginTransaction();
            usuarios=session.createQuery("from Usuario").list();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int buscarUsuarios(String nombre){
        int n = -1;
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getNombre().equals(nombre)){
                n = i;
                break;
            }
        }
        return n;
    }

    public Boolean crearUsuario(String nombre, String correo, int id_perfil, String password, String nacionalidad){
        SessionFactory sf=SessionFactoryProvider.provideSessionFactory();
        Session session=sf.openSession();
        try{
            Perfil pf = session.get(Perfil.class,id_perfil);
            long id = usuarios.size()+1;
            LocalDateTime fechaRegistro = LocalDateTime.now();
            session.beginTransaction();
            Usuario usuario = new Usuario(id,nombre,correo,pf,fechaRegistro,password,nacionalidad);
            session.save(usuario);
            session.getTransaction().commit();
            sf.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sf.close();
            return false;
        }
    }
}
