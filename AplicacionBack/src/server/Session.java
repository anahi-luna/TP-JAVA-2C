package server;

import interfaces.Comunicador;
import interfaces.Servicio;
import models.Carrito;
import models.Usuario;
import parsers.ComandoParser;
import parsers.Solicitud;
import utils.TiendaServiceLocator;

public class Session implements Runnable{

	private Comunicador comunicador;
	private TiendaServiceLocator locator;
	
	private Usuario usuarioActual;
	private Carrito carritoSesion;
	
	public Session(Comunicador comunicador, TiendaServiceLocator locator) {
		this.comunicador = comunicador;
		this.locator = locator;
	}
	
	@Override
	public void run() {
		comunicador.enviar("=== Bienvenido a la Tienda ===");
		comunicador.enviar("Debe iniciar sesión. Formato:");
		comunicador.enviar("login username=xxx password=xxx");
	
        //LOGIN
        while(usuarioActual == null) {
        	String linea = comunicador.recibir();
        	Solicitud soli = ComandoParser.parse(linea);
        	
        	if(soli.getRecurso().equals("usuario") && soli.getAccion().equals("login")) {
        		Servicio userServ = locator.get("usuario");
        		Object respuesta = userServ.ejecutar(soli);
        		
        		if(respuesta instanceof Usuario u) {
        			usuarioActual = u;
        			
        			// Crear carrito asociado a la sesión
        			String idCarrito = "CARR-" + usuarioActual.getNombreUsuario();
        			carritoSesion = new Carrito(idCarrito, usuarioActual.getNombreUsuario());
        			comunicador.enviar("OK: sesión iniciada como " + usuarioActual.getTipo());
        		}else {
        			comunicador.enviar(respuesta.toString());
        		}
        	}else {
        		comunicador.enviar("ERROR: debe loguearse primero.");
        	}
        }
        
        comunicador.enviar("Listo. Puede enviar comandos. (salir para terminar)");
        
        // Bucle principal
        while(true) {
        	String linea = comunicador.recibir();
        	if(linea == null)break;
        	
        	// SALIR COMPLETAMENTE
        	if (linea.equalsIgnoreCase("salir")) {
                comunicador.enviar("Sesión finalizada.");
                break;
            }
        	
        	//COMANDO LOGOUT
        	if (linea.equalsIgnoreCase("logout")) {
                comunicador.enviar("Sesión cerrada.");
                usuarioActual = null;
                carritoSesion = null;
                run(); // volver a iniciar sesión
                return;
        	}
              
        	//  PROCESO NORMAL DE COMANDOS
        	Solicitud soli = ComandoParser.parse(linea);
        	try {
        		Servicio serv = locator.get(soli.getRecurso());
        		if(serv == null) {
        			comunicador.enviar("ERROR: servicio no encontrado");
                    continue;
        		}
        		// INYECTAR USUARIO ACTIVO EN SOLICITUD
        		soli.setUsuarioActivo(usuarioActual);
        		
        		// Si es CarritoServicio → inyectar sesión
                if (soli.getRecurso().equals("carrito")) {
                    services.CarritoServicio cs = (services.CarritoServicio) serv;
                    cs.setSesion(usuarioActual, carritoSesion);
                }
        		
        		Object result = serv.ejecutar(soli);
        		comunicador.enviar(String.valueOf(result));
        		
        	}catch (Exception e) {
				comunicador.enviar("ERROR: " + e.getMessage());
			}
        }
		
	}
	
}
