package ec.edu.ups.sistematransaciones.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Poliza implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private double momto;
	private int plazo;
	private int tasaInteres;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getMomto() {
		return momto;
	}
	public void setMomto(double momto) {
		this.momto = momto;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	public int getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(int tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	
	
}
