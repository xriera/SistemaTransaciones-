/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.MovimientoEN;
import ec.edu.ups.sistematransaciones.modelo.SolicitudPoliza;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author vinicio
 */
@ManagedBean
@ViewScoped
public class SolicitudPolizaBean {

    @Inject
    private GestionBancariaON on;
    public static String idCuenta;
    private String idSolicitud;
    public List<SolicitudPoliza> listasolicitud;
    public List<SolicitudPoliza> listSolicitudTodas;
    public static String getIdCuenta() {
        return idCuenta;
    }

    public static void setIdCuenta(String idCuenta) {
         String idCuenta1 = CuentaBean.idCuentaPoliza;
        SolicitudPolizaBean.idCuenta = idCuenta1;
    }

    public List<SolicitudPoliza> getListSolicitudTodas() {
        return listSolicitudTodas;
    }

    public void setListSolicitudTodas(List<SolicitudPoliza> listSolicitudTodas) {
        this.listSolicitudTodas = listSolicitudTodas;
    }



 
    
    public List<SolicitudPoliza> getListasolicitud() {
        return listasolicitud;
    }

    public void setListasolicitud(List<SolicitudPoliza> listasolicitud) {
        this.listasolicitud = listasolicitud;
    }


    @PostConstruct
    public void init() {
        try {
            listarSolicitud();
            listarPolizas(idCuenta);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudPolizaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarSolicitud() throws Exception {
        listSolicitudTodas= on.listaSolicitudPolizas();
    }
    public String listarPolizas(String idCuenta) throws Exception{
        System.out.println("cuentaPoliza"+idCuenta);
        listasolicitud= on.listaSolicitudPoliza(idCuenta);
        for (SolicitudPoliza s:listasolicitud) {
            System.out.println("s."+s.getCuenta().getIdCuenta());
            System.out.println("sp"+s.getPoliza().getIdPoliza());
        }
        return "";
    } 
    public String actullizarEstado(String idCuenta, String idPoliza, double monto){
        try {
            on.retenerDineroPoliza(idCuenta, monto);
            System.out.println("retencion "+idCuenta+"monto"+monto);
            
            on.actualizarEstadoAprobado(idPoliza);
            System.out.println("aprobado [bean])");
        } catch (Exception ex) {
            Logger.getLogger(SolicitudPolizaBean.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("error aprobado [bean])");
        }
        return "lista-solicitud-polizas.xhtml";
        
    }
        public String actullizarEstadoRechazado(String idPoliza){
        try {
            on.actualizarEstadoRechazada(idPoliza);
            System.out.println("Rechazado [bean])");
        } catch (Exception ex) {
            Logger.getLogger(SolicitudPolizaBean.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("error rechazado[bean])");
        }
        return "lista-solicitud-polizas.xhtml";
        
    }

}
