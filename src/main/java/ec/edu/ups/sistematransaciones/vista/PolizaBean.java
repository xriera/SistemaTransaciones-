/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.modelo.PolizaEN;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
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
 * @author xavier
 */
@ManagedBean
@ViewScoped
public class PolizaBean {
    @Inject
    private GestionBancariaON on;
    
    private String idCuenta;
    private String idSocio;
    private PolizaEN newPoliza;
    private CuentaEN newCuenta;
    private SocioEN newSocio;
    private String cedula;
    private List<CuentaEN> listaCuenta;
    private double monto;
    private int plazo;
    private double interes=0;
    private double tasaInteres;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) throws Exception {
        System.out.println("cedula parametro"+cedula);
        this.cedula = cedula;
       if(cedula!=null){
         newSocio = on.buscarSocio(cedula);
       }
    }
    public String redirigirApoliza(String cedula){
        System.out.print("redirigirA poliza "+cedula+" fin");
        return "crear-poliza?faces-redirect=true"+cedula;
    }

    public SocioEN getNewSocio() {
        return newSocio;
    }

    public void setNewSocio(SocioEN newSocio) {
        this.newSocio = newSocio;
    }
    
    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
    
    
    
    public String getIdCuenta() {
        return idCuenta;
    }

    public PolizaEN getNewPoliza() {
        return newPoliza;
    }

    public void setNewPoliza(PolizaEN newPoliza) {
        this.newPoliza = newPoliza;
    }

    public CuentaEN getNewCuenta() {
        return newCuenta;
    }

    public void setNewCuenta(CuentaEN newCuenta) {
        this.newCuenta = newCuenta;
    }
    
    
    @PostConstruct
    public void init(){
        //generarPoliza();
        newPoliza = new PolizaEN();
        
        try {
            newCuenta = new CuentaEN();
            newSocio = new  SocioEN();
            cargarCuentas();

        } catch (Exception ex) {
            System.out.println("Error init [CuentaBean]");
        }
    }

    public List<CuentaEN> getListaCuenta() {
        return listaCuenta;
    }

    public void setListaCuenta(List<CuentaEN> listaCuenta) {
        this.listaCuenta = listaCuenta;
    }
    
    
    
    public String generarPoliza() throws Exception{
       // PolizaEN p= new PolizaEN();
 
        if (plazo==1) {
           interes = monto*5.50*plazo/1200;
        }
        if (plazo==2) {
           interes = monto*5.75*plazo/1200;
        }
        if (plazo==3) {
           interes = monto*6.25*plazo/1200;
        }
        if (plazo==4) {
           interes = monto*7*plazo/1200;
        }
        if (plazo==5) {
           interes = monto*7.50*plazo/1200;
        }
        if (plazo>=6) {
           interes = monto*8.50*plazo/1200;
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("interes"+interes);
        newPoliza.setManoto(monto);
        newPoliza.setPlazo(plazo);
        newPoliza.setInterezGanado(interes);
        newPoliza.setFechaPoliza(new Date());
         String recuperaCedula=SocioBean.cedula;
         System.out.println("ec.edu.ups.sistematransaciones.vista.PolizaBean.ingresarSolicitud()"+recuperaCedula);
        on.generarPoliza(newPoliza);

        
        ingresarSolicitud(newPoliza);
        
        System.out.println("cuenta33- ");
        System.out.println("monto1"+monto);
        System.out.println("plazo"+plazo);
        System.out.println("interes"+interes);
        
        return null;
    }
    
    public String ingresarSolicitud(PolizaEN poliza){
        
        try {
            System.out.println("idCuentass "+getIdCuenta());
            CuentaEN cuenta = on.buscarCuenta(idCuenta);
            SolicitudPoliza solicitud = new SolicitudPoliza();
            
            solicitud.setEstado(0);
            solicitud.setCuenta(cuenta);
            solicitud.setPoliza(poliza);
            on.insertarSolicitudPoliza(solicitud);
            
            
            System.out.println("socio1"+idCuenta);
            System.out.println("Insetado solicitud Poliza");
            
            
        } catch (Exception ex) {
            Logger.getLogger(PolizaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public void setIdCuenta(String idCuenta) {
        System.out.println("ID Cuenta: " + idCuenta);
        this.idCuenta = idCuenta;
        
//        if (idCuenta != null) {
//            try {
//                newCuenta = on.buscarCuenta(idCuenta);
//                System.out.println("IdSocio"+idSocio);
//            } catch (Exception ex) {
//                System.out.println("Error setIDCuenta[BEAN]" + ex);
//            }
//        }

    }
        
        public void cargarCuentas() throws Exception {
        listaCuenta = on.listarCuentas();
    }
        
        
        
}
