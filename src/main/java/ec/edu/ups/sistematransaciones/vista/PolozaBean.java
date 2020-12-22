/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.dao.CuentaDAO;
import ec.edu.ups.sistematransaciones.dao.DetalleCreditoDAO;
import ec.edu.ups.sistematransaciones.modelo.Poliza;
import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.modelo.PolizaParametrosEN;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author vinicio
 */
@ManagedBean
@ViewScoped
public class PolozaBean {

    @Inject
    private GestionBancariaON on;

    @Inject
    private DetalleCreditoDAO dcreditodao;

    @Inject
    private CuentaDAO cuentadao;

    private String idCuenta;
    private Poliza newCredito;

    private PolizaParametrosEN detalleCredito;

    private CuentaEN cuenta;

    private double monto;

    private UploadedFile fileCedula;
    private UploadedFile fileSRVBasicos;
    private UploadedFile fileSRVRolPagos;

    private byte[] cedulaDigital;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public PolizaParametrosEN getDetalleCredito() {
        return detalleCredito;
    }

    public void setDetalleCredito(PolizaParametrosEN detalleCredito) {
        this.detalleCredito = detalleCredito;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public byte[] getCedulaDigital() {
        return cedulaDigital;
    }

    public void setCedulaDigital(byte[] cedulaDigital) {
        this.cedulaDigital = cedulaDigital;
    }

    public UploadedFile getFile() {
        return fileCedula;
    }

    public void setFile(UploadedFile file) {
        this.fileCedula = file;
    }

    public UploadedFile getFileSRVBasicos() {
        return fileSRVBasicos;
    }

    public void setFileSRVBasicos(UploadedFile fileSRVBasicos) {
        this.fileSRVBasicos = fileSRVBasicos;
    }

    public UploadedFile getFileSRVRolPagos() {
        return fileSRVRolPagos;
    }

    public void setFileSRVRolPagos(UploadedFile fileSRVRolPagos) {
        this.fileSRVRolPagos = fileSRVRolPagos;
    }

    private Date fecha = new Date();

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Poliza getNewCredito() {
        return newCredito;
    }

    public void setNewCredito(Poliza newCredito) {
        this.newCredito = newCredito;
    }

    public List<Poliza> getResumenCredito() {
        return resumenCredito;
    }

    public void setResumenCredito(List<Poliza> resumenCredito) {
        this.resumenCredito = resumenCredito;
    }

    public CuentaEN getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaEN cuenta) {
        this.cuenta = cuenta;
    }

    public List<Poliza> getTablaAmortizacion() {
        return tablaAmortizacion;
    }

    public void setTablaAmortizacion(List<Poliza> tablaAmortizacion) {
        this.tablaAmortizacion = tablaAmortizacion;
    }

    private List<Poliza> tablaAmortizacion;

    private List<Poliza> resumenCredito;

    @PostConstruct
    public void ini() {

        newCredito = new Poliza();
        detalleCredito = new PolizaParametrosEN();

    }

    public void upload() {
        if (fileCedula != null) {
            FacesMessage message = new FacesMessage("Succesful", fileCedula.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void uploadSRVBasicos() {
        if (fileCedula != null) {
            FacesMessage message = new FacesMessage("Succesful", fileSRVBasicos.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void uploadRolPagos() {
        if (fileCedula != null) {
            FacesMessage message = new FacesMessage("Succesful", fileSRVRolPagos.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String guardarCredito() {

        upload();
        uploadSRVBasicos();
        uploadRolPagos();

        try {

            on.generarCredito(newCredito, fileCedula, fileSRVBasicos, fileSRVRolPagos);
            System.out.println("Credito Generado");
        } catch (Exception ex) {
            System.out.println("Error a generar Credito [BEAN] " + ex.getMessage());
        }

        return null;

    }

    public List<Poliza> resumenCredito(String idCuenta) {

        return on.resumenCredito(idCuenta);

    }

    public List<PolizaParametrosEN> tablaAmortizacion(String idCuenta) throws Exception {

        return on.Amortizacion(idCuenta);

    }

    public List<Poliza> fultimoVencimiento(String idCuenta) throws Exception {
        return on.fultimoVencimiento(idCuenta);
    }

    public String abonar(String idCuenta) {

        int idCuota = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCuota");

        System.out.println("idCuotaaaaaaaaaaaaaaaaa " + idCuota);
        System.out.println("Monto a pagar " + monto);

        PolizaParametrosEN detallecuota = new PolizaParametrosEN();

        detallecuota = dcreditodao.buscarDetalle(idCuota);

        System.out.print("DETALLE CUOTAA " + detallecuota);

        detallecuota.abono(monto);

        dcreditodao.guardarDetalle(detallecuota);
        try {
            cuentadao.actualizarPagoCuota(idCuenta, monto);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "amortizacion-credito";
    }

}
