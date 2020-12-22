/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.MovimientoEN;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class MovimientoBean {

    @Inject
    private GestionBancariaON on;

    private MovimientoEN newMovimiento;
    private List<MovimientoEN> listaMovimiento;

    private Date fecha = new Date();

    private String tipomovimiento;

    public MovimientoEN getNewMovimiento() {
        return newMovimiento;
    }

    public void setNewMovimiento(MovimientoEN newMovimiento) {
        this.newMovimiento = newMovimiento;
    }

    public List<MovimientoEN> getListaMovimiento() {
        return listaMovimiento;
    }

    public void setListaMovimiento(List<MovimientoEN> listaMovimiento) {
        this.listaMovimiento = listaMovimiento;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @PostConstruct
    public void init() {
        newMovimiento = new MovimientoEN();
        Date fecha1 = new Date();
        fechadesde = fecha1;
        fechahasta = fecha1;
        tipo_movi = "Todos";
    }

    public String guardarMovimiento() {
        try {
            on.guardarMovimiento(newMovimiento);

        } catch (Exception ex) {
            System.out.println("error guardarMovimeinto [Bean]" + ex);
        }
        return null; 
    }

    public String depositar(String idCuenta, double cantidad) {

        on.depositar(idCuenta, cantidad);
        System.out.println("depositando...");
        return null;
    }

    public void retiar(String idCuenta, double cantidad) {

        on.retirar(idCuenta, cantidad);
        System.out.println("retirando...");
    }

    public String redirigeListarCuentas() {
        return "lista-cuentas";
    }

    public List<MovimientoEN> listarMovimiento(String idCuenta) {
        return on.listarMovimiento(idCuenta);
    }
    public List<MovimientoEN> listarMovimiento2(String idCuenta) {
        return on.listarMovimiento(idCuenta);
    }
    private String idCuenta;

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    private Date fechadesde;
    private Date fechahasta;
    private String tipo_movi;

    public Date getFechadesde() {
        return fechadesde;
    }

    public void setFechadesde(Date fechadesde) {
        this.fechadesde = fechadesde;
    }

    public Date getFechahasta() {
        return fechahasta;
    }

    public void setFechahasta(Date fechahasta) {
        this.fechahasta = fechahasta;
    }

    public String getTipo_movi() {
        return tipo_movi;
    }

    public void setTipo_movi(String tipo_movi) {
        this.tipo_movi = tipo_movi;
    }

    public Date convertirFechas(String fecha2) {

        String dateStr = fecha2;
        DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            String formattedDate = writeFormat.format(date);
        }

        return date;
    }

    /*
	  * Movimiento fechas corregidos
	  * 
     */
    public void loadFechas(String idCuenta) {

        //String tipo = String.valueOf(tipomovimiento);
        System.out.println(idCuenta + "-" + fechadesde + "---" + fechahasta + "--" + tipomovimiento);
        listaMovimiento = on.listarPorFecha(idCuenta, fechadesde, fechahasta, tipomovimiento);
        System.out.println(listaMovimiento);
    }
}
