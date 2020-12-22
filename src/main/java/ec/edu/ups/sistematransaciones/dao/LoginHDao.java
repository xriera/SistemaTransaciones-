package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.LoginHistoricos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LoginHDao {

    @PersistenceContext
    private EntityManager em;

    /*
  	 * metodo que permite crear los historicos de inicio de sesion en la base de datos
     */
    public void crearAcceso(LoginHistoricos lh) {
        em.persist(lh);

    }

    /*
  	 * metodo que permite listar los historicos de inicio de sesion en la base de datos por medio de su id
     */
    public List<LoginHistoricos> getAcceso(String id) {
        String jpql = "SELECT p FROM LoginHistoricos p "
                + " WHERE id_socio LIKE :id";
        Query q = em.createQuery(jpql, LoginHistoricos.class);
        q.setParameter("id", id + "%");
        return q.getResultList();
    }

}
