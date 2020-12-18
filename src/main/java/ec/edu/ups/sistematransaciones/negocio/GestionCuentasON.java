/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.ClienteDAO;
import ec.edu.ups.sistematransaciones.dao.CuentaDAO;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
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
    
    public boolean registrarCuenta(CuentaEN cuentaEN) throws Exception {
        try {
           
            cuentaEN.setFechaRegistroCuenta(new Date());
            daoCuenta.insert(cuentaEN);
            return true;
        } catch (Exception e) {
            throw new Exception("Error al registar cuenta !!! " +e.getMessage());
        }       
    }
    
	public List<CuentaEN> getCuentas(){
		return daoCuenta.getCuentas();
	}
	

}
