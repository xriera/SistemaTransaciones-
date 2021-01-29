package ec.edu.ups.sistematransaciones.service;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@OpenAPIDefinition(
        servers = {
                @Server(
                        description = "Servidor local",
                        url = "/SistemaTransaciones")
        }
)

@ApplicationPath("rest")
public class JAXActivator extends Application  {

}
