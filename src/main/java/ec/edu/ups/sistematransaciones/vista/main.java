/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.Cliente;
import ec.edu.ups.sistematransaciones.modelo.Cuenta;
import ec.edu.ups.sistematransaciones.negocio.GestionCuentasON;
import ec.edu.ups.sistematransaciones.negocio.GestionLoginON;
import ec.edu.ups.sistematransaciones.negocio.GestionUsuariosON;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xavier
 */
@WebServlet("/persona")
public class main extends HttpServlet{
    
    @Inject
    private GestionUsuariosON on;
    @Inject
    private GestionCuentasON onCuentas;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("<h1>SISTEMA TRANSACCIONAL</h1>");
                
            
                
                               
            Cliente c = new Cliente();
            c.setCedula("03082905591");
            c.setNombres("xavier riera");
            c.setApelidos("taza");
            c.setEdad(20);
            c.setCelular("013910");
            c.setCorreo("xavier@gmail.com");
            c.setDireccion("Paute");
            c.setEstadoCiviil("casado");
            c.setProvincia("azuay");
            c.setCiudad("Cuenca");
            c.setTelefonoFijo("0284949");
            c.setClave("patito.123");
            
            Cuenta cu = new Cuenta();
            //cu.setId(1);
            cu.setSaldo(32);
            cu.setTipoCuenta("corriente");
            cu.setCliente(c);
            
           // c.setCuenta(cu);
           
            
        try {
            onCuentas.registrarCuenta(cu);
           
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
