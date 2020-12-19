/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.LoginDAO;
import ec.edu.ups.sistematransaciones.modelo.LoginHistoricos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author xavier
 */
@Stateless
public class GestionLoginON {
    
    	@Inject
	private LoginDAO dao;
	
	/*
  	 * metodo que permite crear historico de sesion llamando al metodo crearAcceso de su clase dao
  	 */
	public void crearHlogin(LoginHistoricos lh) {
		dao.crearAcceso(lh);
	}
	
	/*
  	 * metodo que permite listar los historico de sesion llamando al metodo getAcceso de su clase dao
  	 */
	public List<LoginHistoricos> getHistoricos(String cedula){
		return dao.getAcceso(cedula);
	}
    
}
