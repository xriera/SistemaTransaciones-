/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.ws.rs.ext.ParamConverter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author xavier
 */
@Entity
public class PolizaParametrosEN implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleCredito")
    private int idDetalleCredito;

    private int idPago;
    private double cuota;
    private double capital;
    private double interes;
    private double saldo;
    private String estado;
    private String fecha;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idCredito")
    private Poliza creditoEN;

    public Poliza getCreditoEN() {
        return creditoEN;
    }

    public void setCreditoEN(Poliza creditoEN) {
        this.creditoEN = creditoEN;
    }

    public void abono(double abono) {

        cuota = cuota - abono;
        if (cuota <= 0) {
            estado = "pagado";
        }
    }

    public int getIdDetalleCredito() {
        return idDetalleCredito;
    }

    public void setIdDetalleCredito(int idDetalleCredito) {
        this.idDetalleCredito = idDetalleCredito;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
