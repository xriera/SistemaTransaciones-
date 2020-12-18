/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
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
public class CuentaDAO {
    @Inject
    private EntityManager em;
    @Inject
    private Connection con;
    
    public boolean insert(CuentaEN cuentaEN) throws SQLException {
        em.persist(cuentaEN);
        return true;
    }
    public  boolean update (CuentaEN cuentaEN){
        em.merge(cuentaEN);
        return true;       
    }
    public CuentaEN read (int id){
        em.find(CuentaEN.class, id);
        return null;
    }
    public boolean delete (CuentaEN cuentaEN){
       CuentaEN c = em.find(CuentaEN.class, cuentaEN);
        em.remove(c);
        return true;
    }
	public List<CuentaEN> getCuentas(){
		String jpql ="SELECT c FROM CuentaEN c ";
		Query q = em.createQuery(jpql, CuentaEN.class);
		List<CuentaEN> listado = q.getResultList();		
		return listado;
	}
}
