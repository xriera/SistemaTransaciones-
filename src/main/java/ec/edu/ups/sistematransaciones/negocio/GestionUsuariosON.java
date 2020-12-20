/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.SocioDao;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author xavier
 */
@Stateless
public class GestionUsuariosON {
//    @Inject
//    private SocioDao daoCliente;
//    
//    public boolean registrarUsuario(SocioEN socioEN) throws Exception{
//    
//        try {
//        SocioEN aux = daoCliente.read(socioEN.getCedulaSocio());
//       
//        if(aux!=null){
//            daoCliente.update(socioEN);
//        }else{
//            daoCliente.insert(socioEN);
//        }
//        
//        return true;
//        }catch(Exception e){
//            throw new Exception("Error al registar"+e.getMessage());
//        }          
//    }
//    
//	public List<SocioEN> getClientes(){
//		
//		return daoCliente.getClientes();
//	}
	
}
