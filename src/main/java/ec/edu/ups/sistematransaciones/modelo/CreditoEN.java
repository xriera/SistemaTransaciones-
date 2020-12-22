/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import java.io.InputStream;
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author xavier
 */
@Entity
public class CreditoEN implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCredito")
    private int idCredito;

    @Column(name = "tipoCredito")
    private String tipoCredito;

    @Column(name = "sistemaAmortizacion")
    private String sistemaAmortizacion;

    @Column(name = "fechaDesenbolso")
    private Date fechaDesenbolso;

    @Column(name = "valorCredito")
    private double valorCredito;

    @Column(name = "periodo")
    private String periodo;

    @Column(name = "interes")
    private double interes;

    @Column(name = "plazoMeses")
    private int plazoMeses;

    @Lob
    @Column(name = "cedula")
    @Typed
    private byte[] cedulaDigital;

    @Lob
    @Column(name = "planillaSRVBasicos")
    @Typed
    private byte[] planillaSRVBasicos;

    @Lob
    @Column(name = "rolPagos")
    @Typed
    private byte[] rolPagos;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "idCuenta")
    private CuentaEN cuentaen;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditoEN", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DetalleCreditoEN> detalles;

    public List<DetalleCreditoEN> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCreditoEN> detalles) {
        this.detalles = detalles;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public String getSistemaAmortizacion() {
        return sistemaAmortizacion;
    }

    public void setSistemaAmortizacion(String sistemaAmortizacion) {
        this.sistemaAmortizacion = sistemaAmortizacion;
    }

    public Date getFechaDesenbolso() {
        return fechaDesenbolso;
    }

    public void setFechaDesenbolso(Date fechaDesenbolso) {
        this.fechaDesenbolso = fechaDesenbolso;
    }

    public double getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public CuentaEN getCuentaen() {
        return cuentaen;
    }

    public void setCuentaen(CuentaEN cuentaen) {
        this.cuentaen = cuentaen;
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

    public byte[] getRolPagos() {
        return rolPagos;
    }

    public void setRolPagos(byte[] rolPagos) {
        this.rolPagos = rolPagos;
    }

}
