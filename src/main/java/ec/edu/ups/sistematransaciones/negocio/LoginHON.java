package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.LoginHDao;
import ec.edu.ups.sistematransaciones.modelo.LoginHistoricos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginHON {

    @Inject
    private LoginHDao dao;

    /*
  	 * metodo que permite crear historico de sesion llamando al metodo crearAcceso de su clase dao
     */
    public void crearHlogin(LoginHistoricos lh) {
        dao.crearAcceso(lh);
    }

    /*
  	 * metodo que permite listar los historico de sesion llamando al metodo getAcceso de su clase dao
     */
    public List<LoginHistoricos> getHistoricos(String cedula) {
        return dao.getAcceso(cedula);
    }
}
