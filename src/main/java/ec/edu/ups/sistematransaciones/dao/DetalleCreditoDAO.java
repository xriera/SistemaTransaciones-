/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.DetalleCreditoEN;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DetalleCreditoDAO {

    @PersistenceContext
    private EntityManager em;

    public void guardarDetalle(DetalleCreditoEN detalle) {

        if (buscarDetalle(detalle.getIdDetalleCredito()) != null) {

            em.merge(detalle);
        } else {
            em.persist(detalle);
        }

    }

    public List<DetalleCreditoEN> DetalleCredito(String filtro) throws Exception {
        //SELECT * FROM banco.creditoen c,banco.detallecreditoen d where c.idCuenta=669544807323 and c.idCredito=d.idCredito;
        //String jpql = "SELECT p FROM DetalleCreditoEN p, CreditoEN d WHERE d.idCredito=p.idCredito and d.idCuenta LIKE :filtro";
        String jpql = "SELECT e FROM DetalleCreditoEN e JOIN e.CreditoEN p ON  e.idCredito=p.idCredito and p.idCuenta LIKE :filtro";

        // String jpql = "SELECT p FROM CreditoEN p WHERE idCuenta LIKE :filtro";
        Query q = em.createQuery(jpql, DetalleCreditoEN.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    public DetalleCreditoEN buscarDetalle(int codigoD) {
        return em.find(DetalleCreditoEN.class, codigoD);

    }

}
