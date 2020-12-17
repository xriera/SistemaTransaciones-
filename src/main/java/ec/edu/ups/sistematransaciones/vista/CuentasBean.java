package ec.edu.ups.sistematransaciones.vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.sistematransaciones.modelo.Cliente;
import ec.edu.ups.sistematransaciones.modelo.Cuenta;
import ec.edu.ups.sistematransaciones.negocio.GestionCuentasON;

@ManagedBean
@ViewScoped
public class CuentasBean {
	@Inject
	private GestionCuentasON on;
	
	private Cliente newCliente;
	private List<Cuenta> cuentas;
	
	@PostConstruct
	private void init() {
		cuentas= on.getCuentas();
	}
	
	public Cliente getNewCliente() {
		return newCliente;
	}
	public void setNewCliente(Cliente newCliente) {
		this.newCliente = newCliente;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
		
	
	
}
