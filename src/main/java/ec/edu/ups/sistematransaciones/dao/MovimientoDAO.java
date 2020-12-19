/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.MovimientoEN;
import java.util.Date;
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
public class MovimientoDAO {

    @PersistenceContext
    private EntityManager em;

    /*
	 * metodo que permite crear un movimiento de la cuenta en la base de datos 
     */
    public void insertMovimiento(MovimientoEN movimientoEN) throws Exception {
        em.persist(movimientoEN);
    }

    /*
  	 * metodo que permite retornar un movimiento de la cuenta en la base de datos por medio de su id
     */
    public MovimientoEN readMovimiento(int idMovimiento) throws Exception {
        return em.find(MovimientoEN.class, idMovimiento);
    }

    /*
  	 * metodo que permite actualizar un movimiento de la cuenta en la base de datos 
     */
    public void updateMovimiento(MovimientoEN movimientoEN) throws Exception {
        em.merge(movimientoEN);
    }

    /*
  	 * metodo que permite eliminar un movimiento de la cuenta en la base de datos 
     */
    public void deleteMovimiento(int idMovimiento) throws Exception {
        MovimientoEN m = readMovimiento(idMovimiento);
        em.remove(m);
    }

    public List<MovimientoEN> getMovimientos(String filtro) throws Exception {
        String jpql = "SELECT p FROM CuentaEN p WHERE fechaMovimiento LIKE :filtro"
                + "ORDER BY p.idMovimiento DESC";

        Query q = em.createQuery(jpql, MovimientoEN.class);
        q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }

    /*
 	 * metodo que permite listar los movimiento de la cuenta de la base de datos por medio de su id
     */
    public List<MovimientoEN> listarMovimiento(String idCuenta) {
        String jpql = "Select p FROM MovimientoEN p WHERE p.cuenta like '" + idCuenta + "' ORDER BY p.fechaMovimiento DESC  ";
        Query q = em.createQuery(jpql, MovimientoEN.class);

        return q.getResultList();
    }

    /*
  	 * metodo que permite listar los movimiento de la cuenta de la base de datos por medio de su id, fecha inicial hasta una fecha final y su tipo de cuenta
     */
    public List<MovimientoEN> listarMovimientoFecha(String idCuenta, Date desde, Date hasta, String tipo) {
        if (tipo.equals("Todos")) {
            return listarMovimiento(idCuenta);
        } else {

            String jpql = "Select p FROM MovimientoEN p WHERE p.cuenta like '" + idCuenta + "' AND p.fechaMovimiento BETWEEN '" + desde + "' AND '" + hasta + "'"
                    + "AND p.tipoMovimiento LIKE '" + tipo + "'";
            Query q = em.createQuery(jpql, MovimientoEN.class);

            return q.getResultList();
        }

    }

    /*
   	 * metodo que permite listar los movimiento de la cuenta de la base de datos por medio de su id, fecha inicial hasta una fecha final y su tipo de cuenta
     */
    public List<MovimientoEN> movimientofechas(String idCuenta, Date fecha, Date fecha2, String tipoF) {
        System.out.println(idCuenta + fecha + fecha2 + tipoF);
        String jpql = "SELECT p FROM MovimientoEN p " + "WHERE 	p.cuenta like '" + idCuenta + "' "
                + "and fechaMovimiento BETWEEN  :fecha  AND  :fecha2 and tipoMovimiento "
                + "like :tipoF ";
        Query q = em.createQuery(jpql, MovimientoEN.class);
        q.setParameter("fecha", fecha);
        q.setParameter("fecha2", fecha2);
        q.setParameter("tipoF", tipoF);
        //	q.setParameter("cuenta", idCuenta);
        // List<Telefono>telefono=q.getResultList();
        return q.getResultList();
    }

}
