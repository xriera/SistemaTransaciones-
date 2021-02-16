/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.dao;

import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import java.util.List;
//import static javafx.scene.input.KeyCode.T;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vinicio
 */
@Stateless
public class CuentaDAO {

    @PersistenceContext
    private EntityManager em;

    /*
	 * metodo que permite crear cuentas de los socios en la base de datos
     */
    public void insertCuenta(CuentaEN cuenta) throws Exception {

        em.persist(cuenta);

    }

    /*
  	 * metodo que permite retornar una cuenta en la base de datos por medio de su id
     */
    public CuentaEN readCuenta(String id) throws Exception {
        return em.find(CuentaEN.class, id);
    }

    /*
  	 * metodo que permite actualizar una cuenta en la base de datos
     */
    public void updateCuenta(CuentaEN cuenta) throws Exception {
        em.merge(cuenta);
    }

    /*
  	 * metodo que permite eliminar una cuenta en la base de datos
     */
    public void deleteCuenta(String idCuenta) throws Exception {
        CuentaEN c = readCuenta(idCuenta);
        em.remove(c);
    }

    public List<CuentaEN> getCuenta(String filtro) throws Exception {
        String jpql = "SELECT p FROM CuentaEN p WHERE cedulaSocio LIKE :filtro";

        Query q = em.createQuery(jpql, CuentaEN.class);
        q.setParameter("filtro", filtro);
        return q.getResultList();
    }


    /*
  	 * metodo que permite hacer un deposito de saldo de la cuenta de un cliente  en la base de datos
     */
    public void actualizarSaldoCuenta(String idCuenta, double cantidad) throws Exception {

        String jpql = "UPDATE CuentaEN p SET p.saldo = p.saldo+" + cantidad + " WHERE idcuenta='" + idCuenta + "'";

        Query query = em.createQuery(jpql);
        query.executeUpdate();

    }

    /*
  	 * metodo que permite hacer el retiro de saldo de la cuenta de un cliente  en la base de datos
     */
    public void actualizarRetiroCuenta(String idCuenta, double cantidad) throws Exception {

        String jpql = "UPDATE CuentaEN p SET p.saldo = p.saldo-" + cantidad + " WHERE idcuenta='" + idCuenta + "'";

        Query query = em.createQuery(jpql);
        query.executeUpdate();

    }

    /**
     *
     * @param filtro
     * @return
     * @throws Exception
     */
    public List<CuentaEN> CuentaSocio(String filtro) throws Exception {
        String jpql = "SELECT p FROM CuentaEN p WHERE cedulaSocio LIKE :filtro";
        //String jpql = "SELECT p FROM UsuarioAdministrativo p WHERE p.usuario LIKE :usuario";
        Query q = em.createQuery(jpql, CuentaEN.class);
        q.setParameter("filtro", filtro);
        return q.getResultList();
    }

    public void actualizarPagoCuota(String idCuenta, double abono) throws Exception {

        String jpql = "UPDATE CuentaEN p SET p.saldo = p.saldo-" + abono + " WHERE idcuenta='" + idCuenta + "'";

        Query query = em.createQuery(jpql);
        query.executeUpdate();

    }

    public CuentaEN CuentaxCedula(String cedulaSocio) {
       CuentaEN cuenta=null;
       String jpql = "SELECT p FROM CuentaEN p WHERE cedulaSocio LIKE :cedulaSocio";
     Query query = em.createQuery(jpql,CuentaEN.class);
     query.setParameter("cedulaSocio", cedulaSocio);
    return cuenta=(CuentaEN) query.getResultList();
     
     
    }

}
