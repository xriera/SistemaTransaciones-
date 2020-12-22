/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.Poliza;
import ec.edu.ups.sistematransaciones.modelo.PolizaParametrosEN;
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

    public void inserCredito(Poliza credito) throws Exception {
        em.persist(credito);
    }

    public Poliza readCredito(String idCredito) throws Exception {
        return em.find(Poliza.class, idCredito);
    }

    public void updateCredito(Poliza credito) throws Exception {
        em.merge(credito);
    }

    public void deleteCredito(String idCredito) throws Exception {
        Poliza c = readCredito(idCredito);
        em.remove(c);
    }

    public List<Poliza> getCreditos(String filtro) throws Exception {
        String jpql = "SELECT p FROM CreditoEN p WHERE idCredito LIKE :filtro";

        Query q = em.createQuery(jpql, Poliza.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    public List<Poliza> resumenCredito(String idCuenta) {
        System.out.println(idCuenta);
        String jpql = "SELECT d FROM CreditoEN d  " + "WHERE 	d.cuentaen like '" + idCuenta
                + "'  ORDER BY d.cuentaen DESC" + "";
        Query q = em.createQuery(jpql, Poliza.class);

        return q.getResultList();
    }

    public List<PolizaParametrosEN> tablaAmortizacion(String idCuenta) throws Exception {

        List<PolizaParametrosEN> detalles = new ArrayList<PolizaParametrosEN>();

        String jpql = "select d FROM CreditoEN d WHERE d.cuentaen like '" + idCuenta + "'";
        Query q = em.createQuery(jpql, Poliza.class);
        List<Poliza> resultados = q.getResultList();

        for (Poliza credito : resultados) {

            for (PolizaParametrosEN detalle : credito.getDetalles()) {

                detalles.add(detalle);
            }

        }

        System.out.print("TAMAÑO TABLAAAAAAAAAAAAAAAAAAA" + detalles.size());
        return detalles;

    }

    public List<Poliza> fultimoVencimiento(String idCuenta) {

        String jpql = "select max (d) from CreditoEN d WHERE d.cuentaen like '" + idCuenta + "'";
        Query q = em.createQuery(jpql, Poliza.class);

        return q.getResultList();
    }

    /**
     *
     * @param filtro
     * @return
     * @throws Exception
     */
    public List<Poliza> listarCredito(String filtro) throws Exception {
        String jpql = "SELECT p FROM CreditoEN p WHERE idCuenta LIKE :filtro";

        Query q = em.createQuery(jpql, Poliza.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

}
