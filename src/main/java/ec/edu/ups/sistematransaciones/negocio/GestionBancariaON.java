/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.negocio;

import ec.edu.ups.sistematransaciones.dao.SocioDao;
import ec.edu.ups.sistematransaciones.dao.TransaccionDAO;
import ec.edu.ups.sistematransaciones.dao.CuentaDAO;
import ec.edu.ups.sistematransaciones.dao.MovimientoDAO;
import ec.edu.ups.sistematransaciones.modelo.SocioEN;
import ec.edu.ups.sistematransaciones.modelo.TransaccionEN;
import ec.edu.ups.sistematransaciones.vista.EmailClient;
import ec.edu.ups.sistematransaciones.modelo.CuentaEN;
import ec.edu.ups.sistematransaciones.modelo.MovimientoEN;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.crypto.Data;

import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author xavier
 */
@Stateless
public class GestionBancariaON {

	@Inject
	private SocioDao socioDao;

	@Inject
	private CuentaDAO cuentaDAO;

	@Inject
	private MovimientoDAO movimientoDAO;
        
        
        @Inject 
        private TransaccionDAO transaccionDAO;
      
        

	/* Socio */

	/*
	 * metodo que permite crear un socio llamando al metodo crearAcceso de su clase
	 * socioDao primero verifica si el socio existe en la base de datos si existe un
	 * socio mostrara un mensaje que el socio no se puede creae caso contrario
	 * verificara si su cedula es correcta y se procedera a creara un nuevo socio
	 */
	public void guardarSocio(SocioEN socio) throws Exception {

		SocioEN aux = socioDao.readSocio(socio.getCedulaSocio());

		if (aux != null) {
			socioDao.updateSocio(socio);
		} else {

			if (validarCedula(socio.getCedulaSocio()) == true) {
				socioDao.insertSocio(socio);
			}

		}

	}

	/*
	 * metodo que permite retornar un socio por medio de su cedula llamando al
	 * metodo readSocio de su clase socioDao
	 */

	public SocioEN buscarSocio(String cedulaSocio) throws Exception {
		return socioDao.readSocio(cedulaSocio);

	}

	/*
	 * metodo que permite actualizar un socio llamando al metodo updateSocio de su
	 * clase socioDao
	 */
	public void actualizarSocio(SocioEN socio) throws Exception {
		socioDao.updateSocio(socio);
	}

	/*
	 * metodo que permite eliminar un socio por medio de su cedula llamando al
	 * metodo deleteSocio de su clase socioDao
	 */
	public void eliminarSocio(String cedula) throws Exception {
		socioDao.deleteSocio(cedula);
	}

	/*
	 * metodo que permite listar a todos los socio llamando al metodo getSocios de
	 * su clase socioDao
	 */
	public List<SocioEN> listarSocios() throws Exception {
		return socioDao.getSocios("%");
	}

        
        public List<CuentaEN> listarCuentas(String filtro) throws Exception {
		return cuentaDAO.getCuenta(filtro);
	}
	
	/*
	 * metodo que permite validar la cedula del socio
	 */
	public boolean validarCedula(String cedula) throws Exception {
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10) {
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {

					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;

					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {

			cedulaCorrecta = false;
			throw new Exception("Una excepcion ocurrio en el proceso de validadcion" + err);
		}

		if (!cedulaCorrecta) {
			throw new Exception("Cedula Incorrecta- !! Verifique la cedula para ingresar los contactos");

		}

		return cedulaCorrecta;

	}

	/* Cuenta */

	/*
	 * metodo que permite crear una cuenta para el socio llamando al metodo
	 * readSocio de su clase cuentaDAO primero verifica si la cuenta no existe, si
	 * no existe se procedera a crear una cuenta para el socio
	 */
        
        String cuenta1;
	public void guardarCuenta(CuentaEN cuenta) throws Exception {

		CuentaEN aux = cuentaDAO.readCuenta(cuenta.getIdCuenta());
                
               // cuenta1=aux.getIdCuenta();

		if (aux != null) {
			cuentaDAO.updateCuenta(cuenta);
		} else {

			cuentaDAO.insertCuenta(cuenta);
		}
	}

	
	/*
	 * metodo que permite retornar una cuenta por medio de su id llamando al metodo read cuenta de su clase cuentaDAO
	 */
        
        
        
	public CuentaEN buscarCuenta(String id) throws Exception {
            
                 
		return cuentaDAO.readCuenta(id);
	}

	/*
	 * metodo que permite actualizar una cuenta llamando al metodo  updatecuenta de su clase cuentaDAO
	 */
	public void actualizarCuenta(CuentaEN cuenta) throws Exception {
		cuentaDAO.updateCuenta(cuenta);
	}

	/*
	 * metodo que permite eliminar una cuenta por medio de su id llamando al metodo  deletecuenta de su clase cuentaDAO
	 */
	public void eliminarCuenta(String idCuenta) throws Exception {
		cuentaDAO.deleteCuenta(idCuenta);
	}
	
	/*
	 * metodo que permite listar las cuenta llamando al metodo  getCuenta de su clase cuentaDAO
	 */

        

//	public List<CuentaEN> listarCuentas(String idCuenta) throws Exception {
//            
// 
//              System.out.println("Encontre:"+idCuenta);
//		return cuentaDAO.getCuenta(idCuenta);
//	}
public List<CuentaEN> listarCuentas() throws Exception {
            
 
              System.out.println("Encontre:");
		return cuentaDAO.getCuenta("%");
	}


	/* Movimiento */

	/*
	 * metodo que permite crear una Movimiento de la cuenta llamando al metodo  insertMovimiento de su clase movimientoDAO
	 */
	public void guardarMovimiento(MovimientoEN movimiento) throws Exception {
		movimientoDAO.insertMovimiento(movimiento);
	}

	/*
	 * metodo que permite retornar una Movimiento de cuenta por medio de su id llamando al metodo  readMovimiento de su clase movimientoDAO
	 */
	public MovimientoEN buscarMovimiento(int id) throws Exception {
		return movimientoDAO.readMovimiento(id);
	}

	/*
	 * metodo que permite actualizar una Movimiento de cuenta llamando al metodo  updateMovimiento de su clase movimientoDAO
	 */

	public void actualizarMovimiento(MovimientoEN movimientoEN) throws Exception {
		movimientoDAO.updateMovimiento(movimientoEN);
	}

	/* Depositar Saldo */
	
	/*
	 * metodo que crear un actualizar el saldo de la cuenta para realizar un deposito llamando al metodo  actualizarSaldoCuenta de su clase cuentaDAO
	 */

	public void depositar(String idCuenta, double cantidad) {

		try {
			cuentaDAO.actualizarSaldoCuenta(idCuenta, cantidad);
		} catch (Exception ex) {
			System.out.println("Error SALDO[ON]" + ex.getLocalizedMessage());

		}

	}

	/* Retirar Saldo */
	
	/*
	 * metodo que crear un actualizar el saldo de la cuenta para realizar un retiro llamando al metodo  actualizarRetiroCuenta de su clase cuentaDAO
	 * primero verifica si el saldo de la cuenta no sea menor a la cantidad retirada
	 */
	public void retirar(String idCuenta, double cantidad) {

		try {

			CuentaEN aux = cuentaDAO.readCuenta(idCuenta);

			if (cantidad > aux.getSaldo()) {
				System.out.println("Solo Puede Retirar:" + aux.getSaldo());
			} else {

				cuentaDAO.actualizarRetiroCuenta(idCuenta, cantidad);
			}
		} catch (Exception ex) {
			System.out.println("Error SALDO[ON]" + ex.getLocalizedMessage());

		}

	}

	/*
	 * miki
	 */
	
	/*
	 * metodo que retornar un socio llamando al metodo  login por medio de si correo y clave de su clase socioDao
	 */
	public SocioEN buscarPersona(String correo, String clave) throws Exception {
		return socioDao.login(correo, clave);

	}

	/*
	 * metodo que retornar un socio llamando al metodo  buscarCorreo por medio de si correo de su clase socioDao
	 */
	public SocioEN BuscarCorreo(String correo) throws Exception {
		return socioDao.buscarCorreo(correo);
	}

	
	/*
	 * metodo que listar los movimiento de las cuentas llamando al metodo  listarMovimiento por medio de su idCuenta de su clase movimientoDAO
	 */
	public List<MovimientoEN> listarMovimiento(String idCuenta) {
		return movimientoDAO.listarMovimiento(idCuenta);
	}

	/*
	 * metodo que listar los movimiento de las cuentas llamando al metodo  listarMovimientoFecha por medio de su idCuenta, desde, hasta y tipo  de su clase movimientoDAO
	 */
	public List<MovimientoEN> listarMovimientoFecha(String idCuenta, Date desde, Date hasta, String tipo) {

		System.out.println(desde + "---" + hasta);
		return movimientoDAO.listarMovimientoFecha(idCuenta, desde, hasta, tipo);
	}

	/*
	 * metodo que listar los movimiento de las cuentas llamando al metodo  movimientofechas por medio de su idCuenta, desde, hasta y tipo  de su clase movimientoDAO
	 */
	public List<MovimientoEN> listarPorFecha(String idCuenta, Date fecha, Date fecha2, String tipoF) {
		return movimientoDAO.movimientofechas(idCuenta, fecha, fecha2, tipoF);
	}
        
        
        public void transaccion(String idCuentaOrigen, String idCuentaDestino,double cantidad){
            
            try {
                CuentaEN aux =cuentaDAO.readCuenta(idCuentaOrigen);
                
                if(cantidad>aux.getSaldo()){
                    System.out.println("La cuenta no tiene fondos no se puede hacer la transaccion");
                    
                }else {
                    
                     cuentaDAO.actualizarRetiroCuenta(idCuentaOrigen, cantidad);
                    
                     cuentaDAO.actualizarSaldoCuenta(idCuentaDestino, cantidad);
                     
                     TransaccionEN t=new TransaccionEN();
                     t.setCuentaOrigen(idCuentaOrigen);
                     t.setCuentaDestino(idCuentaDestino);
                     t.setCantidad(cantidad);
                     t.setFechaTransaccion(new Date());
                     
                     transaccionDAO.insertTransaccion(t);
                    
                }
                
                
                
            } catch (Exception ex) {
                System.out.println("error transaccion...[ON] "+ex.getMessage());
                
                
                
            }
        
        }
        
        public void restarAbono (String idCuenta, double abono) throws Exception {
        
				cuentaDAO.actualizarPagoCuota(idCuenta, abono);
		
        }

        //Vinicio
        public void actualizarSocio(String correo,String clave) throws Exception {
       	socioDao.ActualizarSocio(correo, clave);
       	 String Asunto = " Cambio de Contrasena";
    	String CuerpoMail = "Su Cambio de Contrasena fue exitoso"+ " "+clave ;
    	
 		enviarCorreo(correo, Asunto, CuerpoMail);
    		//EmailClient.sendMail(correo, Asunto, CuerpoMail);
            
       }
        
        public List<CuentaEN> listarCuentaSocio(String cedula) throws Exception {
     		return cuentaDAO.CuentaSocio(cedula);
     	}
         
        
        public void enviarCorreo(String destino,String Asunto,String CuerpoMail) {
    		Thread hilo= new Thread(new EmailClient(destino, Asunto, CuerpoMail));
    		hilo.start();
	
    	}
        
        
      
        
  

}
