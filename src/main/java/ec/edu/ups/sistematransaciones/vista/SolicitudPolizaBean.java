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

    private String idSolicitud;
    private List<SolicitudPoliza> listasolicitud;

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
        } catch (Exception ex) {
            Logger.getLogger(SolicitudPolizaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarSolicitud() throws Exception {
        listasolicitud = on.listaSolicitudPolizas();
    }

}
