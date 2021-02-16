/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author xavier
 */
@Entity
public class SolicitudPoliza implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  idSolicitud;
    private String estado;
    
    @ManyToOne
     @JsonIgnore
     @JoinColumn(name = "idCuenta")
    private CuentaEN cuenta ;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idPoliza")
    private PolizaEN poliza;

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CuentaEN getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaEN cuenta) {
        this.cuenta = cuenta;
    }

    public PolizaEN getPoliza() {
        return poliza;
    }

    public void setPoliza(PolizaEN poliza) {
        this.poliza = poliza;
    }
    
    
}
