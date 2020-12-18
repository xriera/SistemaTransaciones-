/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author xavier
 */
@Entity
public class CuentaEN implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column (name="idCuenta")
    private String idCuenta;
  
    @Column (name="tipoCuenta")
    private String tipoCuenta;
    
    @Column (name="fechaRegistroCuenta")
    private Date fechaRegistroCuenta;
    
     
     @OneToOne 
     @JoinColumn(name="cedulaSocio")
    private SocioEN socioen;
     
     @Column(name="saldo")
     private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Date getFechaRegistroCuenta() {
        return fechaRegistroCuenta;
    }

    public void setFechaRegistroCuenta(Date fechaRegistroCuenta) {
        this.fechaRegistroCuenta = fechaRegistroCuenta;
    }

    public SocioEN getSocio() {
        return socioen;
    }

    public void setSocio(SocioEN socio) {
        this.socioen = socio;
    }

    @Override
    public String toString() {
        return "CuentaEN{" + "idCuenta=" + idCuenta + ", tipoCuenta=" + tipoCuenta + ", fechaRegistroCuenta=" + fechaRegistroCuenta + ", socioen=" + socioen + ", saldo=" + saldo + '}';
    }
}
