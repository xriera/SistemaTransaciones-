package ec.edu.ups.sistematransaciones.service;


import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


import javax.ws.rs.core.MediaType;

import ec.edu.ups.sistematransaciones.modelo.UsuarioAdministrativo;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("clientes")
public class ClienteServiceREST {
    
    @Inject
    private GestionBancariaON on;

    
    @POST
   // @Produces(MediaType.APPLICATION_JSON)
    @Path("retirar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta retiar(Parametros params) {
        Respuesta resp = new Respuesta();
        try {
            on.retirar(params.getIdCuenta(), params.getCantidad());
            System.out.println("retirando...");
             resp.setCodigo(1);
	  resp.setMensaje("retirando satisfactorio");
           
          //return "retirado";
        } catch (Exception e) {
            e.printStackTrace();
             resp.setCodigo(-1);
	  resp.setMensaje("ERROR AL retirandorrrrrrrrr");
            
           // return "Error AL retirar ";
        }
        return resp;
    }

    @POST
    @Path("depositar")
    @Produces(MediaType.APPLICATION_JSON)
     public Respuesta depositar ( @QueryParam("idCuenta") String idCuenta, @QueryParam("cantidad") double cantidad){
         Respuesta resp = new Respuesta();
         try{
          on.depositar(idCuenta, cantidad);
          
          resp.setCodigo(1);
	  resp.setMensaje("Deposistoooooooo satisfactorio");
          System.out.println("depositadooo !!!");
          
         // return "depositado ok!!!";
     } catch (Exception e) {
          e.printStackTrace();
          resp.setCodigo(-1);
	  resp.setMensaje("ERROR AL DEPOSITARrrrrrrrrr");
         // return "ERROR AL DEPOSITAR";
       }
         return resp;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("traferencia")
     public Respuesta tranferencia(@QueryParam("idCuentaOrigen") String idCuentaOrigen, @QueryParam("idCuentaDestino") String idCuentaDestino, @QueryParam("cantidad") double cantidad){
         Respuesta resp = new Respuesta();
         try{
         on.transaccion(idCuentaOrigen, idCuentaDestino, cantidad);
         
          resp.setCodigo(1);
	  resp.setMensaje("Tranferencia satisfactorio");
         } catch (Exception e) {
            e.printStackTrace();
          resp.setCodigo(-1);
	  resp.setMensaje("ERROR AL transaccion");
         }
         return resp;
     }
    
}
