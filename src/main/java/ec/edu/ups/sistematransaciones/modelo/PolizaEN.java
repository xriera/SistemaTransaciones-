/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author xavier
 */
@Entity
public class PolizaEN implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "idCredito")
    private int idPoliza;
    
    private double manoto;
    private int plazo;
    private double interezGanado;
    private Date fechaPoliza;
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "poliza", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SolicitudPoliza> solicitudPolizas;

    public List<SolicitudPoliza> getSolicitudPolizas() {
        return solicitudPolizas;
    }

    public void setSolicitudPolizas(List<SolicitudPoliza> solicitudPolizas) {
        this.solicitudPolizas = solicitudPolizas;
    }
     
    public int getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(int idPoliza) {
        this.idPoliza = idPoliza;
    }

    public double getManoto() {
        return manoto;
    }

    public void setManoto(double manoto) {
        this.manoto = manoto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public double getInterezGanado() {
        return interezGanado;
    }

    public void setInterezGanado(double interezGanado) {
        this.interezGanado = interezGanado;
    }

    public Date getFechaPoliza() {
        return fechaPoliza;
    }

    public void setFechaPoliza(Date fechaPoliza) {
        this.fechaPoliza = fechaPoliza;
    }


    
    
}
