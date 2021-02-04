/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

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
    
}
