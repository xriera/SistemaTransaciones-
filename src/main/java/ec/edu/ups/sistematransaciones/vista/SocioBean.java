package ec.edu.ups.sistematransaciones.vista;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.negocio.GestionBancariaON;

@ManagedBean
@ViewScoped
public class SocioBean {

	@Inject
	private GestionBancariaON on;

	private SocioEN newSocio;
	private List<SocioEN> listaSocio;
	private String cedula;

	public SocioEN getNewSocio() {
		return newSocio;
	}

	public void setNewSocio(SocioEN newSocio) {
		this.newSocio = newSocio;
	}

	public List<SocioEN> getListaSocio() {
		return listaSocio;
	}

	public void setListaSocio(List<SocioEN> listaSocio) {
		this.listaSocio = listaSocio;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		System.out.println("Cedula Parametro:" + cedula);
		this.cedula = cedula;
		if (cedula != null) {
			try {
				newSocio = on.buscarSocio(cedula);
			} catch (Exception ex) {
				System.out.println("Error setCedula[BEAN]" + ex);
			}

		}
	}

	@Override
	public String toString() {
		return "SocioBean{" + "newSocio=" + newSocio + '}';
	}

	@PostConstruct
	public void init() {

		try {
			newSocio = new SocioEN();

			cargarSocios();
		} catch (Exception ex) {
			System.out.println("Error al Cargar Socios:" + ex.getMessage());
		}

	}

	public String guardarDatosScocio() {

		try {
			newSocio.setClave(GenerarClave());
			on.guardarSocio(newSocio);
			
			String Asunto = " Cuenta de Usuario";
			String CuerpoMail = "Hola " + newSocio.getNombresSocio() + " Su clave de Inicio de Sesion es " +newSocio.getClave();
			
			on.enviarCorreo(newSocio.getCorreo(), Asunto, CuerpoMail);
			//EmailClient.sendMail(newSocio.getCorreo(), Asunto, CuerpoMail);
			
			System.out.println("Socio Guardado...");
			
		} catch (Exception ex) {
			System.out.println("Error al ingresarSocio[SocioBean]" + ex);
		}
		return "listaSocios";
	}

	public String BuscarDatosScocio(String cedula) {

		ArrayList<SocioEN> lista = new ArrayList<>();
		try {
			SocioEN buscar = on.buscarSocio(cedula);

			System.out.println("Cedula:" + buscar.getCedulaSocio());
			System.out.println("Nombre:" + buscar.getNombresSocio());
			System.out.println("Apellido:" + buscar.getApelidosSocio());
			System.out.println("Edad:" + buscar.getEdadSocio());
			System.out.println("Provincia:" + buscar.getProvinciaSocio());
			System.out.println("Ciudad:" + buscar.getCiudadSocio());
			System.out.println("Direccion:" + buscar.getDireccionSocio());
			System.out.println("Telefono Fijo:" + buscar.getTelefonoFijoSocio());
			System.out.println("Celular:" + buscar.getCedulaSocio());
			// Modificacion Xavier
			System.out.println("Correo:" + buscar.getCorreo());
			System.out.println("Clave:" + buscar.getClave());
			System.out.println("Estado Civil:" + buscar.getEstadoCiviilSocio());

		} catch (Exception ex) {
			System.out.println("Error al ingresarSocio[SocioBean]" + ex);
		}
		return null;
	}

	public String eliminarScocio(String cedula) throws Exception {

		try {
			on.eliminarSocio(cedula);
			System.out.println("Socio Eliminado...");

		} catch (Exception ex) {
			throw new Exception("Error al eliminar Socio[SocioBean]" + ex);

		}
		return null;
	}

	private void cargarSocios() throws Exception {

		// linea 135 importante
		listaSocio = on.listarSocios();

		try {
			List<SocioEN> l2 = on.listarSocios();

			for (SocioEN lista : l2) {

				System.out.println("Cedula:" + lista.getCedulaSocio());
				System.out.println("Nombre:" + lista.getNombresSocio());
				System.out.println("Apellido:" + lista.getApelidosSocio());
				System.out.println("Edad:" + lista.getEdadSocio());
				System.out.println("Provincia:" + lista.getProvinciaSocio());
				System.out.println("Ciudad:" + lista.getCiudadSocio());
				System.out.println("Direccion:" + lista.getDireccionSocio());
				System.out.println("Telefono Fijo:" + lista.getTelefonoFijoSocio());
				System.out.println("Celular:" + lista.getCedulaSocio());
				// Modificacion Xavier
				System.out.println("Correo:" + lista.getCorreo());
				System.out.println("Clave:" + lista.getClave());
				System.out.println("Estado Civil:" + lista.getEstadoCiviilSocio());

			}

		} catch (Exception ex) {
			System.out.println("Error al cargar socios" + ex.getMessage());

		}

	}

	public String editarSocio(String cedula) {

		return "Socio?faces-redirect=true&cedulaSocio=" + cedula;
	}

	public String redirigeCrearCuenta(String cedula) {

		System.out.println("Redirigir:" + cedula);
		return "registro-cuenta?faces-redirect=true&cedulaSocio=" + cedula;
	}

	public String redirigeCrearSocio() {

		System.out.println("Redirigir:" + cedula);
		return "Socio?faces-redirect=true";
	}

	public String redirigeVerSocio() {

		return "listaSocios?faces-redirect=true";
	}

	public String verCuentas() {

		return "listarCuentas?faces-redirect=true";
	}

	public String GenerarClave(){
		String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
		int length = 10;
		String password = null;
		try {
			Random random = SecureRandom.getInstanceStrong();
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
			    int indexRandom = random.nextInt( symbols.length );
			    sb.append( symbols[indexRandom] );
			}
			password = sb.toString();
			//System.out.println("conta"+password);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    // as of JDK 8, this should return the strongest algorithm available to the JVM
		return password;
	}
	
	
	String aleatorio=String.valueOf(GenerarClave());
    
    public String numero(){
    return aleatorio;
    }

    public String getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(String aleatorio) {
        this.aleatorio = aleatorio;
    }
    
}
