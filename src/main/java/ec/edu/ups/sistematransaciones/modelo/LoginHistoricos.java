/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author vinicio
 */
@Entity
public class LoginHistoricos implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_login;
	
	private String descripcion;
	private String fecha;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_socio")
	private SocioEN socio;

	
	
	public SocioEN getSocio() {
		return socio;
	}
	public void setSocio(SocioEN socio) {
		this.socio = socio;
	}



	public int getId_login() {
		return id_login;
	}



	public void setId_login(int id_login) {
		this.id_login = id_login;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	

	
}
