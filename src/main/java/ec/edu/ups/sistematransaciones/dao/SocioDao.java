/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author xavier
 */
@Stateless
public class SocioDao {

    @PersistenceContext
    private EntityManager em;

    /*
	 * metodo que permite crear un socio 
     */
    public void insertSocio(SocioEN socio) throws Exception {
        em.persist(socio);
    }

    /*
	 * metodo que permite buscar un socio por medio de su cedula
     */
    public SocioEN readSocio(String cedulaSocio) throws Exception {
        return em.find(SocioEN.class, cedulaSocio);
    }

    /*
	 * metodo que permite actualizar un socio 
     */
    public void updateSocio(SocioEN socio) throws Exception {
        em.merge(socio);
    }

    /*
	 * metodo que permite eliminar un socio por medio de su cedula
     */
    public void deleteSocio(String cedula) throws Exception {
        SocioEN s = readSocio(cedula);
        em.remove(s);
    }

    /*
	 * metodo que permite ratornar listar los socio por medio de su cedula
     */
    public List<SocioEN> getSocios(String filtro) throws Exception {
        String jpql = "SELECT p FROM SocioEN p WHERE cedulaSocio LIKE :filtro";

        Query q = em.createQuery(jpql, SocioEN.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    /*
	 * metodo que permite retornar un socio por medio de su correo de usuario
     */
    public SocioEN buscarCorreo(String correo) throws Exception {
        SocioEN c = null;
        try {
            String jpql = "SELECT p FROM SocioEN p " + "WHERE p.correo LIKE :correo";
            TypedQuery<SocioEN> query = em.createQuery(jpql, SocioEN.class);
            query.setParameter("correo", correo);

            c = query.getSingleResult();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return c;
    }

    /*
	 * metodo que permite retornar un socio por medio de su correo de usuario y su clave
     */
    public SocioEN login(String correo, String clave) throws Exception {
        SocioEN p = null;
        String jpql = "SELECT p FROM SocioEN p " + "WHERE p.correo LIKE :correo AND p.clave LIKE :clave";

        TypedQuery<SocioEN> query = em.createQuery(jpql, SocioEN.class);
        query.setParameter("correo", correo);
        query.setParameter("clave", clave);

        try {
            p = query.getSingleResult();
            System.out.println("Encontrado");

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return p;

    }

    /**
     *
     * @param correo
     * @param clave
     * @throws Exception
     */
    public void ActualizarSocio(String correo, String clave) throws Exception {

        String jpql = "UPDATE SocioEN p SET p.clave = :clave WHERE p.correo = :correo";

        Query query = em.createQuery(jpql);
        query.setParameter("correo", correo);
        query.setParameter("clave", clave);
        query.executeUpdate();

    }
}
