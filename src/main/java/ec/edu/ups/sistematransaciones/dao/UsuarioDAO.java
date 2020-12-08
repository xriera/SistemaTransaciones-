/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.Usuario;
import java.sql.Connection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author xavier
 */

@Stateless
public class UsuarioDAO {
    @Inject
    private EntityManager em;
    @Inject
    private Connection con;
    
    public boolean insert(Usuario entity){
        em.persist(entity);
        return true;       
    }
    public boolean update(Usuario entity){
        em.merge(entity);
        return true;
    }
    public boolean read(String id ){
        em.find(Usuario.class, id);
        return true;
    }
    public boolean delete(Usuario entity){
        Usuario u = em.find(Usuario.class, entity);
        em.remove(u);
        return true;
    }
}
