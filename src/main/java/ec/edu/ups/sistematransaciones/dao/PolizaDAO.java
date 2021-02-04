/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.PolizaEN;
import ec.edu.ups.sistematransaciones.modelo.PolizaParametrosEN;
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
public class PolizaDAO {
    @PersistenceContext
    private EntityManager em;
    
      /*
	 * metodo que permite crear una poliza
     */
    public void insertPoliza(PolizaEN poliza) throws Exception {
        em.persist(poliza);
    }

    /*
	 * metodo que permite buscar un socio por medio de su cedula
     */
    public PolizaEN readPoliza(int idPoliza) throws Exception {
        return em.find(PolizaEN.class, idPoliza);
    }

    /*
	 * metodo que permite actualizar un socio 
     */
    public void updatePoliza(PolizaEN poliza) throws Exception {
        em.merge(poliza);
    }

    /*
	 * metodo que permite eliminar un socio por medio de su cedula
     */
    public void deleteSocio(int idPoliza) throws Exception {
        PolizaEN s = readPoliza(idPoliza);
        em.remove(s);
    }

    /*
	 * metodo que permite ratornar listar los socio por medio de su cedula
     */
    public List<PolizaEN> getSocios(String filtro) throws Exception {
        String jpql = "SELECT p FROM PolizaEN p WHERE ipPoliza LIKE :filtro";

        Query q = em.createQuery(jpql, PolizaEN.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    
}
