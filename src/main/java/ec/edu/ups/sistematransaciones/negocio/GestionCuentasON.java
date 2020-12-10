/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.ClienteDAO;
import ec.edu.ups.sistematransaciones.dao.CuentaDAO;
import ec.edu.ups.sistematransaciones.modelo.Cliente;
import ec.edu.ups.sistematransaciones.modelo.Cuenta;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.crypto.Data;

/**
 *
 * @author xavier
 */
@Stateless
public class GestionCuentasON {
    @Inject
    private CuentaDAO daoCuenta;
    @Inject
    private ClienteDAO daoCliente;
    
    public boolean registrarCuenta(Cuenta cuenta) throws Exception {
        try {
            Cliente aux = daoCliente.read(cuenta.getCliente().getCedula());
            if(aux!=null){
                daoCliente.update(cuenta.getCliente());
            }else{
                daoCliente.insert(cuenta.getCliente());
            }
            cuenta.setFechaRegistroCuenta(new Date());
            daoCuenta.insert(cuenta);
            return true;
        } catch (Exception e) {
            throw new Exception("Error al registar cuenta !!! " +e.getMessage());
        }       
    }

}
