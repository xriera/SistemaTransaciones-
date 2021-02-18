package ec.edu.ups.sistematransaciones.service;

import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.modelo.SolicitudPoliza;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;

import ec.edu.ups.sistematransaciones.modelo.UsuarioAdministrativo;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

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
    public Respuesta depositar(@QueryParam("idCuenta") String idCuenta, @QueryParam("cantidad") double cantidad) {
        Respuesta resp = new Respuesta();
        try {
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

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("traferencia")
//    public Respuesta tranferencia(@QueryParam("idCuentaOrigen") String idCuentaOrigen, @QueryParam("idCuentaDestino") String idCuentaDestino, @QueryParam("cantidad") double cantidad) {
//        Respuesta resp = new Respuesta();
//        try {
//            on.transaccion(idCuentaOrigen, idCuentaDestino, cantidad);
//
//            resp.setCodigo(1);
//            resp.setMensaje("Tranferencia satisfactorio");
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.setCodigo(-1);
//            resp.setMensaje("ERROR AL transaccion");
//        }
//        return resp;
//    }
    
    @GET
    @Path("transferencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferirREST(@QueryParam("idCuentaOrigen") String idCuentaOrigen,
            @QueryParam("idCuentaDestino") String idCuentaDestino, @QueryParam("cantidad") double cantidad) throws Exception {
        on.transaccion(idCuentaOrigen, idCuentaDestino, cantidad);
        System.out.println("transfiriendo...");
        //return "transferencia exitosa";
        return Response.ok("transferenciaExitosa").header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("login")
    @Produces("application/json")
    public Response loginsocio(@QueryParam("usuario") String usu, @QueryParam("password") String pws) throws Exception {
        SocioEN p = null;
        Response.ResponseBuilder builder = null;

        p = on.buscarPersona(usu, pws);

        if (p == null) {
            return Response.ok(p).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.ok(p).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("updatecontrase")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarsocio(@QueryParam("email") String email, @QueryParam("clave") String clave)
            throws Exception {
        on.actualizarSocio(email, clave);

        System.out.println("correo y contraseña..." + email + clave);

        return Response.ok("actualizando contraseña").header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("CuentaSocio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CuentaSocio(@QueryParam("cedula") String cedula) throws Exception {

        List<CuentaEN> listaCuenta = on.listarCuentaSocio(cedula);

        return Response.ok(listaCuenta).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @GET
    @Path("polizas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Polizas(@QueryParam("idCuenta") String cuenta) throws Exception {

        List<SolicitudPoliza> listaSolicitudPoliza = on.listaSolicitudPoliza(cuenta);

        return Response.ok(listaSolicitudPoliza).header("Access-Control-Allow-Origin", "*").build();
    }
    
    

}
