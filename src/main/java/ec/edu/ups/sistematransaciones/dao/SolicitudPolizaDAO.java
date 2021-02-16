/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.PolizaEN;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.modelo.SolicitudPoliza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xavier
 */
@Stateless
public class SolicitudPolizaDAO {

    @PersistenceContext
    private EntityManager em;

    /*
	 * metodo que permite crear una poliza
     */
    public void insertSolicitudPoliza(SolicitudPoliza solicitud) throws Exception {
        em.persist(solicitud);
    }

    /*
	 * metodo que permite buscar un socio por medio de su cedula
     */
    public SolicitudPoliza readSolicitudPoliza(int idSolicitud) throws Exception {
        return em.find(SolicitudPoliza.class, idSolicitud);
    }

    /*
	 * metodo que permite ratornar listar los socio por medio de su cedula
     */
    public List<SolicitudPoliza> getSolicitudPoliza() throws Exception {
        String jpql = "SELECT p FROM SolicitudPoliza p";

        Query q = em.createQuery(jpql, SolicitudPoliza.class);
        // q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    public List<SolicitudPoliza> getSolicitudPolizaCliente() throws Exception {
        String jpql = "SELECT p FROM SolicitudPoliza p";

        Query q = em.createQuery(jpql, SolicitudPoliza.class);
        // q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

   
    public List<SolicitudPoliza> listaSolicitudPoliza(String idCuenta) throws Exception {
        String jpql = "SELECT p FROM SolicitudPoliza p WHERE idCuenta LIKE :idCuenta";

        Query q = em.createQuery(jpql, SolicitudPoliza.class);
        q.setParameter("idCuenta", idCuenta);
        System.out.println("ejecuto listasolicitud");
        return q.getResultList();
    }

    public void actualizarSolicitudPoliza(SolicitudPoliza sp) {
        String jpql = "UPDATE SolicitudPoliza p SET p.estado= :estado WHERE p.idSolicitud=:idSolicitud";
        Query q = em.createQuery(jpql);
        q.setParameter("estado", sp.getEstado());
        int d = q.executeUpdate();
        System.out.println("actualizado " + d);
    }
//     public void getSolicitudPoliza(String idCuenta) {
//        String jpql = "SELECT p FROM SolicitudPoliza p WHERE p.idCuenta";
//        Query q = em.createQuery(jpql);
//        q.setParameter("estado", sp.getEstado());
//        int d = q.executeUpdate();
//        System.out.println("actualizado " + d);
//    }

    public List<SolicitudPoliza> getCuenta(String filtro) throws Exception {
        String jpql = "SELECT p FROM SolicitudPoliza p WHERE cedulaSocio LIKE :filtro";

        Query q = em.createQuery(jpql, SolicitudPoliza.class);
        q.setParameter("filtro", filtro);
        return q.getResultList();
    }

}
