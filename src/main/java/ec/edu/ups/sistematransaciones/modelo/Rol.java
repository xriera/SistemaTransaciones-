/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author xavier
 */
@Entity
public class Rol {
    
    @Id
    private int id;
    private int rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", rol=" + rol + '}';
    }
      
}
