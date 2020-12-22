/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import java.util.Date;
import java.util.List;
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
public class CuentaBean {

    @Inject
    private GestionBancariaON on;

    private CuentaEN newCuenta;
    private List<CuentaEN> listaCuenta;

    private Date fecha = new Date();

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CuentaEN getNewCuenta() {
        return newCuenta;
    }

    public void setNewCuenta(CuentaEN newCuenta) {
        this.newCuenta = newCuenta;
    }

    public List<CuentaEN> getListaCuenta() {
        return listaCuenta;
    }

    public void setListaCuenta(List<CuentaEN> listaCuenta) {
        this.listaCuenta = listaCuenta;
    }

    private String idCuenta;

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        System.out.println("ID Cuenta: " + idCuenta);
        this.idCuenta = idCuenta;

        if (idCuenta != null) {
            try {
                newCuenta = on.buscarCuenta(idCuenta);

            } catch (Exception ex) {
                System.out.println("Error setIDCuenta[BEAN]" + ex);
            }
        }

    }

    @PostConstruct
    public void init() {

        try {
            newCuenta = new CuentaEN();

            cargarCuentas();

        } catch (Exception ex) {
            System.out.println("Error init [CuentaBean]");
        }

    }

    /*    Cuenta             */
    public String guardarDatosCuenta() {

        try {
            SocioEN buscar = on.buscarSocio(newCuenta.getSocio().getCedulaSocio());
            System.out.println("busacar"+buscar.getCedulaSocio());
            
             String Asunto = " Cuenta de Usuario";
            String CuerpoMail = "Banca en linea. Estimado Sr(a) " + buscar.getNombresSocio()+" "+buscar.getApelidosSocio()
                    +" con numero de cedula "+buscar.getCedulaSocio()
                    +" su numero de cuenta es: "+newCuenta.getIdCuenta()
                    +" fue creada exitosamente a las "+newCuenta.getFechaRegistroCuenta()
                    + " bienvenido, su clave de inicio de sesion es: " + buscar.getClave()+"\n"
                    +"Gracias por su confianza";

            on.enviarCorreo(buscar.getCorreo(), Asunto, CuerpoMail);
            
            on.guardarCuenta(newCuenta);

            System.out.println("Cuenta Guardada");

        } catch (Exception ex) {
            System.out.println("Guardar Cuenta..." + ex.getMessage());
        }
        return "lista-cuentas";
    }

    public void cargarCuentas() throws Exception {
        listaCuenta = on.listarCuentas();
    }

    public String eliminarCuenta(String idCuenta) {

        try {
            on.eliminarCuenta(idCuenta);
        } catch (Exception ex) {
            System.out.println("Error al Eliminar Cuenta [Bean]" + ex);
        }
        return null;
    }

    public String conpruebaCuentaExistente(String idCuenta) {

        try {

            CuentaEN busqueda = on.buscarCuenta(idCuenta);

            System.out.println("Encontrado" + busqueda.getIdCuenta());
            System.out.println("Encontrado Pertenece" + busqueda.getSocio().getNombresSocio());

        } catch (Exception ex) {
            System.out.println("Error al Buscar:" + ex.getMessage());

        }
        return null;
    }

    public String redirigeCrearMovimiento(String idCuenta) {

        System.out.println("Redirigir:" + idCuenta);
        return "Movimiento?faces-redirect=true&idCuenta=" + idCuenta;
    }

    public String redirigeCrearRetiro(String idCuenta) {

        System.out.println("Redirigir:" + idCuenta);
        return "Retiro?faces-redirect=true&idCuenta=" + idCuenta;
    }

    public String redirigeCrearCredito(String idCuenta) {

        System.out.println("Redirigir:" + idCuenta);
        return "GenerarCredito?faces-redirect=true&idCuenta=" + idCuenta;
    }

    /**
     * Numero de Cuenta
     */
    public static long numbGen() {
        while (true) {
            long numb = (long) (Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 12) {
                return numb;
            }
        }
    }

    String aleatorio = String.valueOf(numbGen());

    public String numero() {
        return aleatorio;
    }

    public String getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(String aleatorio) {
        this.aleatorio = aleatorio;
    }

}
