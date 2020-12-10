/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.ClienteDAO;
import ec.edu.ups.sistematransaciones.modelo.Cliente;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author xavier
 */
@Stateless
public class GestionUsuariosON {
    @Inject
    private ClienteDAO daoCliente;
    
    public boolean registrarUsuario(Cliente cliente) throws Exception{
    
        try {
        Cliente aux = daoCliente.read(cliente.getCedula());
       
        if(aux!=null){
            daoCliente.update(cliente);
        }else{
            daoCliente.insert(cliente);
        }
        
        return true;
        }catch(Exception e){
            throw new Exception("Erroro al registar"+e.getMessage());
        }
         
      
    }
}
