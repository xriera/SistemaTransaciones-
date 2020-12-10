/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
    
    public boolean insert(Cliente entity){
        em.persist(entity);
        return true;       
    }
    public boolean update(Cliente entity){
        em.merge(entity);
        return true;
    }
    public Cliente read(String cedula ) throws SQLException {
        em.find(Cliente.class, cedula);
        return null;
    }
    public boolean delete(Cliente entity){
        Cliente u = em.find(Cliente.class, entity);
        em.remove(u);
        return true;
    }
}
