/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.service;

import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author xavier
 */
@WebService
public class ClienteServiceSOAPLocal {
    
    @Inject
    private GestionBancariaON on;
    
    @WebMethod
    public String retiar(String idCuenta, double cantidad) {
        try {
            on.retirar(idCuenta, cantidad);
            System.out.println("retirando...");
            return "retirado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error AL retirar soap";
        }
    }

    
    @WebMethod
     public String depositar(String idCuenta, double cantidad){
     try{
          on.depositar(idCuenta, cantidad);
          System.out.println("depositado !!!");
          return "depositado ok!!!";
     } catch (Exception e) {
          e.printStackTrace();
          return "ERROR AL DEPOSITAR";
       }

    }
     public String tranferencia(String idCuentaOrigen,String idCuentaDestino, double cantidad){
         try{
         on.transaccion(idCuentaOrigen, idCuentaDestino, cantidad);
         return "Tranferencia exitosa";
         } catch (Exception e) {
            e.printStackTrace();
          return "ERROR AL Transferiri [soap]"; 
         }
     }
}
