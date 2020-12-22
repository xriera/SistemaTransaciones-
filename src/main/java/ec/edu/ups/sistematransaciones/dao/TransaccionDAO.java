/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.TransaccionEN;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel Amay
 */
@Stateless
public class TransaccionDAO {

    @PersistenceContext
    private EntityManager em;

    public void insertTransaccion(TransaccionEN transaccion) {
        em.persist(transaccion);

    }

}
