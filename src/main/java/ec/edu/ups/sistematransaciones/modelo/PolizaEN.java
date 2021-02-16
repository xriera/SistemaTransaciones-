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
import javax.enterprise.inject.Typed;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.primefaces.model.UploadedFile;

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
    
    private double monto;
    private int plazo;
    private double interezGanado;
    private Date fechaPoliza;
    private Date fechaVencimiento;
    @Lob
    @Column(name="cedula") 
    @Typed
    private byte[] cedulaDigital;
     
    @Lob
    @Column(name="planillaSRVBasicos") 
    @Typed
    private byte[] planillaSRVBasicos;
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

    public byte[] getCedulaDigital() {
        return cedulaDigital;
    }

    public void setCedulaDigital(byte[] cedulaDigital) {
        this.cedulaDigital = cedulaDigital;
    }

    public byte[] getPlanillaSRVBasicos() {
        return planillaSRVBasicos;
    }

    public void setPlanillaSRVBasicos(byte[] planillaSRVBasicos) {
        this.planillaSRVBasicos = planillaSRVBasicos;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }




    
    
}
