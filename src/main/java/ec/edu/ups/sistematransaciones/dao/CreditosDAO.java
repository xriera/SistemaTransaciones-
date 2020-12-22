/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.CreditoEN;
import ec.edu.ups.sistematransaciones.modelo.DetalleCreditoEN;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xavier
 */
@Stateless
public class CreditosDAO {

    @PersistenceContext
    private EntityManager em;

    public void inserCredito(CreditoEN credito) throws Exception {
        em.persist(credito);
    }

    public CreditoEN readCredito(String idCredito) throws Exception {
        return em.find(CreditoEN.class, idCredito);
    }

    public void updateCredito(CreditoEN credito) throws Exception {
        em.merge(credito);
    }

    public void deleteCredito(String idCredito) throws Exception {
        CreditoEN c = readCredito(idCredito);
        em.remove(c);
    }

    public List<CreditoEN> getCreditos(String filtro) throws Exception {
        String jpql = "SELECT p FROM CreditoEN p WHERE idCredito LIKE :filtro";

        Query q = em.createQuery(jpql, CreditoEN.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    public List<CreditoEN> resumenCredito(String idCuenta) {
        System.out.println(idCuenta);
        String jpql = "SELECT d FROM CreditoEN d  " + "WHERE 	d.cuentaen like '" + idCuenta
                + "'  ORDER BY d.cuentaen DESC" + "";
        Query q = em.createQuery(jpql, CreditoEN.class);

        return q.getResultList();
    }

    public List<DetalleCreditoEN> tablaAmortizacion(String idCuenta) throws Exception {

        List<DetalleCreditoEN> detalles = new ArrayList<DetalleCreditoEN>();

        String jpql = "select d FROM CreditoEN d WHERE d.cuentaen like '" + idCuenta + "'";
        Query q = em.createQuery(jpql, CreditoEN.class);
        List<CreditoEN> resultados = q.getResultList();

        for (CreditoEN credito : resultados) {

            for (DetalleCreditoEN detalle : credito.getDetalles()) {

                detalles.add(detalle);
            }

        }

        System.out.print("TAMAÑO TABLAAAAAAAAAAAAAAAAAAA" + detalles.size());
        return detalles;

    }

    public List<CreditoEN> fultimoVencimiento(String idCuenta) {

        String jpql = "select max (d) from CreditoEN d WHERE d.cuentaen like '" + idCuenta + "'";
        Query q = em.createQuery(jpql, CreditoEN.class);

        return q.getResultList();
    }

    /**
     *
     * @param filtro
     * @return
     * @throws Exception
     */
    public List<CreditoEN> listarCredito(String filtro) throws Exception {
        String jpql = "SELECT p FROM CreditoEN p WHERE idCuenta LIKE :filtro";

        Query q = em.createQuery(jpql, CreditoEN.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

}
