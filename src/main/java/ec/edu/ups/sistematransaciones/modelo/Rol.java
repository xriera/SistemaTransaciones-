package ec.edu.ups.sistematransaciones.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int idrol;

    @Column(name = "nombre_rol")
    private String nombre;

    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<UsuarioAdministrativo> usuario;

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<UsuarioAdministrativo> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<UsuarioAdministrativo> usuario) {
        this.usuario = usuario;
    }

}
