/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.UsuarioAdministrativo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vinicio
 */
@Stateless
public class UsuarioAdmiDAO {

    @PersistenceContext
    private EntityManager em;

    /*
	 * metodo que permite crear un usuario administrador en la base de datos
     */
    public void crearUsuarioAdmin(UsuarioAdministrativo admi) {
        em.persist(admi);
    }

    /*
	 * metodo que permite listar un usuario administrador por medio de un nombre de usuario
     */
    public List<UsuarioAdministrativo> listaUAdmi(String nombre) {

        String jpql = "SELECT p FROM UsuarioAdministrativo p WHERE nombre LIKE :nombre";
        // System.out.println(jpql);

        Query q = em.createQuery(jpql, UsuarioAdministrativo.class);

        q.setParameter("nombre", nombre + "%");
        return q.getResultList();

    }

    /*
	 * metodo que permite retornar usuario administrador por medio del clave primaria o id
     */
    public UsuarioAdministrativo buscarUAdmin(int id) throws Exception {
        return em.find(UsuarioAdministrativo.class, id);
    }

    /*
	 * metodo que permite eliminar  un usuario administrador por medio de su clave primaria
     */
    public void deleteUAdmi(int id) throws Exception {
        // UsuarioAdministrativo s = em.find(UsuarioAdministrativo.class, id);
        String jpql = "DELETE FROM UsuarioAdministrativo p WHERE p.idusuario = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        int deletedCount = query.executeUpdate();
        // em.remove(s);
        System.out.println("entities deleted: " + deletedCount);
    }

    /*
	 * metodo que permite retornar un usuario administrador por medio de su nombre de usuario
     */
    public UsuarioAdministrativo buscarUsuarioAdmi(String usuario) throws Exception {
        UsuarioAdministrativo c = null;
        try {
            String jpql = "SELECT p FROM UsuarioAdministrativo p " + "WHERE p.usuario LIKE :usuario";
            TypedQuery<UsuarioAdministrativo> query = em.createQuery(jpql, UsuarioAdministrativo.class);
            query.setParameter("usuario", usuario);

            c = query.getSingleResult();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return c;
    }

    /*
	 * metodo que permite retornar un usuario administrador por medio de su nombre de usuario y su clave
     */
    public UsuarioAdministrativo login(String usuario, String contrasena) throws Exception {
        UsuarioAdministrativo p = null;
        String jpql = "SELECT p FROM UsuarioAdministrativo p "
                + "WHERE p.usuario LIKE :usuario AND p.contrasena LIKE :contrasena";

        TypedQuery<UsuarioAdministrativo> query = em.createQuery(jpql, UsuarioAdministrativo.class);
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);

        try {
            p = query.getSingleResult();
            System.out.println("Encontrado");

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return p;

    }

}
