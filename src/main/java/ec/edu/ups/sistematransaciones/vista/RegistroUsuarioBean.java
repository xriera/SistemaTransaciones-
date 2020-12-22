package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.Rol;
import ec.edu.ups.sistematransaciones.modelo.UsuarioAdministrativo;
import ec.edu.ups.sistematransaciones.negocio.RolON;
import ec.edu.ups.sistematransaciones.negocio.UsuarioAdminON;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class RegistroUsuarioBean {

    @Inject
    private RolON ron;

    @Inject
    private UsuarioAdminON onadmi;

    private UsuarioAdministrativo uadmi;

    private Rol rol;

    private List<Rol> rols;

    private int id;

    private static int iduser;

    private List<UsuarioAdministrativo> listaUAdmi;

    public List<UsuarioAdministrativo> getListaUAdmi() {
        return listaUAdmi;
    }

    public void setListaUAdmi(List<UsuarioAdministrativo> listaUAdmi) {
        this.listaUAdmi = listaUAdmi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioAdministrativo getUadmi() {
        return uadmi;
    }

    public void setUadmi(UsuarioAdministrativo uadmi) {
        this.uadmi = uadmi;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public void init() {
        rols = ron.listaRol();

        uadmi = new UsuarioAdministrativo();
        recuperarUAdmi();
    }

    public String guardarDatos() {
        try {
            rol = new Rol();

            uadmi.setRol(ron.buscar(id));
            onadmi.crearUsuarioAdmi(uadmi);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return "listar";
    }

    public void recuperarUAdmi() {
        try {
            listaUAdmi = onadmi.listarUAdmi();

            List<UsuarioAdministrativo> listaua = onadmi.listarUAdmi();
            for (UsuarioAdministrativo ua : listaua) {
                System.out.println("Nombre:" + ua.getNombre());
                System.out.println("Apellido:" + ua.getApellido());
                System.out.println("Usuario:" + ua.getUsuario());
                System.out.println("Clave:" + ua.getContrasena());
                System.out.println("Rol:" + ua.getRol().getNombre());

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al Listar" + e.getMessage());
        }

    }

    public String eliminarUadmi(int idu) throws Exception {

        try {
            onadmi.eliminarUAdmi(idu);
            System.out.println("Administrador Eliminado..." + idu);

        } catch (Exception ex) {
            throw new Exception("Error al eliminar" + ex);

        }
        return null;
    }

}
