/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author xavier
 */

@Stateless
public class ClienteDAO {
    @Inject
    private EntityManager em;
    
    @Inject
    private Connection con;
    
    public boolean insert(SocioEN entity){
        em.persist(entity);
        return true;       
    }
    public boolean update(SocioEN entity){
        em.merge(entity);
        return true;
    }
    public SocioEN read(String cedula ) throws SQLException {
        em.find(SocioEN.class, cedula);
        return null;
    }
    public boolean delete(SocioEN entity){
        SocioEN u = em.find(SocioEN.class, entity);
        em.remove(u);
        return true;
    }
	public List<SocioEN> getClientes(){
		String jpql ="SELECT c FROM SocioEN c ";
		Query q = em.createQuery(jpql, SocioEN.class);
		List<SocioEN> listado = q.getResultList();		
		return listado;
	}
}
