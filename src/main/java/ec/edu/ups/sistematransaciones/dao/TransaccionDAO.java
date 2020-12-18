package ec.edu.ups.sistematransaciones.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.sistematransaciones.modelo.TransaccionEN;

@Stateless
public class TransaccionDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    
    
    public void insertTransaccion(TransaccionEN transaccion){
        em.persist(transaccion);

}
    
    
    
}