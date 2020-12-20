/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.UsuarioAdministrativo;
import ec.edu.ups.sistematransaciones.negocio.UsuarioAdminON;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author vinicio
 */
@ManagedBean
@ViewScoped
public class LoginAdminBean implements Serializable {

    @Inject
    private UsuarioAdminON onadmi;

    private UsuarioAdministrativo p;

    private static String idper;

    private UsuarioAdministrativo usuario = null;

    public UsuarioAdministrativo getP() {
        return p;
    }

    public void setP(UsuarioAdministrativo p) {
        this.p = p;
    }

    @PostConstruct
    public void init() {
        p = new UsuarioAdministrativo();

    }

    public String Login() throws Exception {

        try {

            usuario = onadmi.buscarUsuarioAdmi(p.getUsuario(), p.getContrasena());

            if (usuario != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inicio de Sesion Exitoso"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admi", usuario);

//				idper = onadmi.BuscarUsuario(p.getUsuario());
                return "Socio?faces-redirect=true";

            } else {

            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario o Clave Incorrectas"));
        } catch (Exception e) {

        }
        return null;

    }

    public void cerrarSession() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public void verificarSessionAdmi() throws Exception {
        UsuarioAdministrativo p1 = (UsuarioAdministrativo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admi");
        try {
            if (p1 == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("permisos2.xhtml");
            } else {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
