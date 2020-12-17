/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.Cliente;
import ec.edu.ups.sistematransaciones.modelo.Cuenta;
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
    
    public boolean insert(Cuenta cuenta) throws SQLException {
        em.persist(cuenta);
        return true;
    }
    public  boolean update (Cuenta cuenta){
        em.merge(cuenta);
        return true;       
    }
    public Cuenta read (int id){
        em.find(Cuenta.class, id);
        return null;
    }
    public boolean delete (Cuenta cuenta){
       Cuenta c = em.find(Cuenta.class, cuenta);
        em.remove(c);
        return true;
    }
	public List<Cuenta> getCuentas(){
		String jpql ="SELECT c FROM Cuenta c ";
		Query q = em.createQuery(jpql, Cuenta.class);
		List<Cuenta> listado = q.getResultList();		
		return listado;
	}
}
