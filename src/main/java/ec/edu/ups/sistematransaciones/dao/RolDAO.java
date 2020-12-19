/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.Rol;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vinicio
 */
@Stateless
public class RolDAO {
    
    @PersistenceContext
	private EntityManager em;
	
	/*
	 * metodo que permite crear un rol en la base de datos 
	 */
	public void crearRol(Rol rol) {
		em.persist(rol);
	}
	
	/*
	 * metodo que permite listar los rol de la base de datos 
	 */

	public List<Rol> listaRol() {
		String jpql = "SELECT r FROM Rol r";

		Query q = em.createQuery(jpql, Rol.class);

		return q.getResultList();
	}
	
	/*
	 * metodo que permite buscar un rol 
	 */

	public Rol buscar(int id) {
		return em.find(Rol.class, id);
	}
	
//	public Rol buscar2(int idrol) {
//		String jpql="SELECT p FROM Rol p "
//				+ "WHERE p.idrol LIKE :idrol";
//		 TypedQuery<Rol> query = em.createQuery(jpql, Rol.class);
//		    query.setParameter("idrol", idrol);
//		  
//		 Rol c= query.getSingleResult();
//		return c;
//	}
	
	/*
	 * metodo que permite eliminar un rol 
	 */
	public void delete(Rol p) {
		//p = buscar(id);
		em.remove(p);
	}
	
    
}
