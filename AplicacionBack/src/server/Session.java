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
	
	private void enviarBloque(String... lineas){
        for (String s : lineas)
            comunicador.enviar(s);
        comunicador.enviar(""); // FIN DE BLOQUE
    }
	
	@Override
	public void run() {
		while(true) {
			
			//FORZAR LOGIN SI NO HAY USUARIO
	        if (usuarioActual == null) {
	            enviarBloque(
	                "=== Bienvenido a la Tienda ===",
	                "Debe iniciar sesión. Formato:",
	                "login username=xxx password=xxx"
	            );
		
	            boolean logueado = false;
	            
		        //LOGIN
		        while(!logueado) {
		        	String linea = comunicador.recibir();
		        	if(linea == null) return;
		        	
		        	Solicitud soli = ComandoParser.parse(linea);
		        	if (soli == null) {
		                enviarBloque("ERROR: comando inválido.");
		                continue;
		            }
		        	
		        	
		        	if(soli.getRecurso().equals("usuario") && soli.getAccion().equals("login")) {
		        		Servicio userServ = locator.get("usuario");
		        		Object respuesta = userServ.ejecutar(soli);
		        		
		        		if(respuesta instanceof Usuario u) {
		        			usuarioActual = u;
		        			
		        			// Crear carrito asociado a la sesión
		        			String idCarrito = "CARR-" + usuarioActual.getNombreUsuario();
		        			carritoSesion = new Carrito(idCarrito, usuarioActual.getNombreUsuario());
		        			enviarBloque("OK: sesión iniciada como " + usuarioActual.getTipo());
		        			logueado = true;
		        		}else {
		        			enviarBloque(respuesta.toString());
		        		}
		        	}else {
		        		enviarBloque("ERROR: debe loguearse primero.");
		        	}
		        }
	        
	        
		        // YA ESTÁ LOGUEADO → ESCUCHAR COMANDOS
		        while(usuarioActual != null) {
		        	
		        	String linea = comunicador.recibir();
		        	if(linea == null)break;
		        	
		        	// SALIR COMPLETAMENTE
		        	if (linea.equalsIgnoreCase("salir")) {
		        		enviarBloque("Sesión finalizada.");
		                break;
		            }
		        	
		        	//COMANDO LOGOUT
		        	if (linea.equalsIgnoreCase("logout")) {
		        		enviarBloque("Sesión cerrada.");
		                usuarioActual = null;
		                carritoSesion = null;
		                break;
		        	}
		              
		        	//  PROCESO NORMAL DE COMANDOS
		        	Solicitud soli = ComandoParser.parse(linea);
		        	
		        	if (soli == null) {
		                enviarBloque("ERROR: comando inválido.");
		                continue;
		            }
		        	
		        	try {
		        		Servicio serv = locator.get(soli.getRecurso());	
		        		
		        		if(serv == null) {
		        			enviarBloque("ERROR: servicio no encontrado");
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
		        		enviarBloque(String.valueOf(result));
		        		
		        	}catch (Exception e) {
		        		enviarBloque("ERROR: " + e.getMessage());
					}
			
		        }
	        }    
	                
	     }
	}
	
}
