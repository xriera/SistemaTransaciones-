package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.modelo.LoginHistoricos;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;
import ec.edu.ups.sistematransaciones.negocio.LoginHON;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4537479482646908992L;

    // @Inject
//private PersonaON on;
    @Inject
    private GestionBancariaON on;

    @Inject
    private LoginHON onlogin;

    // private EmailClient emial;
//private Persona p;
    private SocioEN p;
    private LoginHistoricos login;

    private int numeroCuota;

    private List<LoginHistoricos> listalogin;
    private List<CuentaEN> listaCuenta;
    private List<CuentaEN> listaCuenta2;

    private static String idper;
//private Persona pp = null;
    private SocioEN pp = null;

    public List<CuentaEN> getListaCuenta2() {
        return listaCuenta2;
    }

    public void setListaCuenta2(List<CuentaEN> listaCuenta2) {
        this.listaCuenta2 = listaCuenta2;
    }

    public List<LoginHistoricos> getListalogin() {
        return listalogin;
    }

    public SocioEN getP() {
        return p;
    }

    public void setP(SocioEN p) {
        this.p = p;
    }

    public void setListalogin(List<LoginHistoricos> listalogin) {
        this.listalogin = listalogin;
    }

    public List<CuentaEN> getListaCuenta() {
        return listaCuenta;
    }

    public void setListaCuenta(List<CuentaEN> listaCuenta) {
        this.listaCuenta = listaCuenta;
    }

    @PostConstruct
    public void init() {
        p = new SocioEN();
        login = new LoginHistoricos();
        
        // try {
        listaLogins();
        // } catch (Exception e) {
        // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        try {
            cargarCuentas();
            //cargarCuentas2();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {

        System.out.println("id la cuota a pagar: " + numeroCuota);

        this.numeroCuota = numeroCuota;

    }

    public String Login() throws Exception {
        FacesMessage message;
        SimpleDateFormat date = new SimpleDateFormat();
        String fecha = date.format(new Date());
        try {

            pp = on.buscarPersona(p.getCorreo(), p.getClave());
            if (pp != null) {
                // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inicio
                // de Sesion Exitoso"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", pp);
                String Asunto = " Inicio de Sesion Exitoso";
                String CuerpoMail = "Hola " + pp.getNombresSocio() + " Su inicio de sesion fu exitoso" + " " + fecha;

                login.setDescripcion(Asunto);
                login.setFecha(fecha);
                login.setSocio(on.BuscarCorreo(p.getCorreo()));
                onlogin.crearHlogin(login);
                idper = on.BuscarCorreo(p.getCorreo()).getCedulaSocio();

                enviarCorreo(p.getCorreo(), Asunto, CuerpoMail);
                //EmailClient.sendMail(p.getCorreo(), Asunto, CuerpoMail);
                //return "ultimo-movimiento?faces-redirect=true";
                return "lista-cuenta-cliente?faces-redirect=true";
            } else {
                String Asuntofail = " Inicio de Sesion Fallido";
                String CuerpoMailfail = "Querido Usuario su intento de Sesion a sido Fallido en la fecha:" + fecha
                        + " Observamos que tienes problemas para iniciar sesion en tu cuenta";

                if (on.BuscarCorreo(p.getCorreo()) != null) {

                    login.setDescripcion(Asuntofail);
                    login.setFecha(fecha);
                    login.setSocio(on.BuscarCorreo(p.getCorreo()));
                    onlogin.crearHlogin(login);
                    //EmailClient.sendMail(p.getCorreo(), Asuntofail, CuerpoMailfail);
                    enviarCorreo(p.getCorreo(), Asuntofail, CuerpoMailfail);

                }

            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario o Clave Incorrectas"));
        } catch (Exception e) {

        }
        return null;

    }

    public void listaLogins() {
        try {
            System.out.println("Lista del Usuario: " + idper);

            listalogin = onlogin.getHistoricos(idper);
            listalogin.size();
            
            System.out.println("lilista de logins "+listalogin.get(0).getSocio().getCuentaen().getIdCuenta());
        } catch (Exception e) {
            System.out.println("Error al Listar" + e.getMessage());
        }

    }

//	public void saveMessage() {
//		FacesContext context = FacesContext.getCurrentInstance();
//
//		context.addMessage(null, new FacesMessage("Inicio de Sesion\",\"Usuario Correcto"));
//	}
    public void cerrarSession() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public void verificarSession() throws Exception {
//		SocioEN p1 = (SocioEN) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//		try {
//			if (p1 == null) {
//				FacesContext.getCurrentInstance().getExternalContext().redirect("permisos.xhtml");
//			} else {
//				
//				
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
    }

    public void verificarSessionSocio() throws Exception {
        SocioEN p1 = (SocioEN) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            if (p1 == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("permisos.xhtml");
            } else {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public String redirigirCuota() {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idCuota", getNumeroCuota());
        return "cuota-pagar";
    }

    public void cargarCuentas() throws Exception {
        listaCuenta = on.listarCuentas(idper);
    }
    
        public void cargarCuentas2() throws Exception {
         if(listalogin.size() > 0){
            
            CuentaEN cuenta = on.buscarCuenta(listalogin.get(1).getSocio().getCuentaen().getIdCuenta());
            listaCuenta2.add(cuenta);
             System.out.println("lista 2 corriente"+cuenta.getIdCuenta());
         }
       // listaCuenta = on.listarCuentas(idper);
       
    }

    public void enviarCorreo(String destino, String Asunto, String CuerpoMail) {
        Thread hilo = new Thread(new EmailClient(destino, Asunto, CuerpoMail));
        hilo.start();

    }

}
