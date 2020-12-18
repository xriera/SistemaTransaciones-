package ec.edu.ups.sistematransaciones.vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.negocio.GestionCuentasON;
import ec.edu.ups.sistematransaciones.negocio.GestionUsuariosON;

@ManagedBean
@ViewScoped
public class CuentasBean {
	@Inject
	private GestionCuentasON on;
	
	@Inject
	private GestionUsuariosON onUsuario;

	private CuentaEN newCuenta;
	private List<CuentaEN> cuentaENs;
	private SocioEN socioEN;
	
	@PostConstruct
	private void init() {
		socioEN = new SocioEN();
		newCuenta = new CuentaEN();
		cuentaENs = on.getCuentas();
	}
	
	public SocioEN getCliente() {
		return socioEN;
	}

	public void setCliente(SocioEN socioEN) {
		this.socioEN = socioEN;
	}
	public CuentaEN getNewCuenta() {
		return newCuenta;
	}
	public void setNewCuenta(CuentaEN newCuenta) {
		this.newCuenta = newCuenta;
	}
	public List<CuentaEN> getCuentas() {
		return cuentaENs;
	}
	public void setCuentas(List<CuentaEN> cuentaENs) {
		this.cuentaENs = cuentaENs;
	}	
	public String guardarCuenta() {
		//SocioEN cl = new SocioEN();
	
		try {
			onUsuario.registrarUsuario(socioEN);
			
			on.registrarCuenta(newCuenta);
			System.out.println("SocioEN guardado correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Errroro al gudardar " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
}
