package ec.edu.ups.sistematransaciones.vista;

import ec.edu.ups.sistematransaciones.modelo.Rol;
import ec.edu.ups.sistematransaciones.modelo.UsuarioAdministrativo;
import ec.edu.ups.sistematransaciones.negocio.RolON;
import ec.edu.ups.sistematransaciones.negocio.UsuarioAdminON;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/persona")
public class RegistroPersona extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -3785458131672848839L;

    @Inject
    private UsuarioAdminON onadmi;

    @Inject
    private RolON onrol;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("<h1>SISTEMA TRANSACCIONAL</h1>");

        UsuarioAdministrativo admi = new UsuarioAdministrativo();

        admi.setNombre("Gestion");
        admi.setApellido("Usuarios");
        admi.setUsuario("admin");
        admi.setContrasena("cuenca");

        UsuarioAdministrativo admi2 = new UsuarioAdministrativo();

        admi2.setNombre("Juan");
        admi2.setApellido("Perez");
        admi2.setUsuario("jperez");
        admi2.setContrasena("cuenca");

        Rol rol1 = new Rol();
        rol1.setNombre("Administrador");

        Rol rol2 = new Rol();
        rol2.setNombre("Cajero");

        Rol rol3 = new Rol();
        rol3.setNombre("Asist. capacitaciones");

        onrol.crearRol(rol1);
        onrol.crearRol(rol2);
        onrol.crearRol(rol3);

        admi.setRol(rol1);
        admi2.setRol(onrol.buscar(2));

        onadmi.crearUsuarioAdmi(admi);
        onadmi.crearUsuarioAdmi(admi2);

    }

}
