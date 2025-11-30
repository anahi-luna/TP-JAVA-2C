package server;

import java.net.ServerSocket;
import java.net.Socket;

import comunicacion.SocketComunicador;
import interfaces.Comunicador;
import utils.TiendaServiceLocator;

public class Server {
	private int puerto;
	private boolean ejecutando;
	private TiendaServiceLocator locator;
	
	public Server(int puerto) {
		this.puerto=puerto;
		this.locator=locator=ConfiguracionServicios.crearServicios();
	}
	
	public void start() throws Exception{
		ejecutando = true;
		System.out.println("Servidor iniciando en puerto " + puerto + "...");
		
		ServerSocket server = new ServerSocket(puerto);
		System.out.println("Servidor escuchando...");
		
		while(ejecutando) {
			Socket socket = server.accept();
			System.out.println("Cliente conectado!");
			
			// Un comunicador por cliente
			Comunicador comunicador = new SocketComunicador(socket);
			
			// Un locator NUEVO por cliente
			//TiendaServiceLocator nuevoLocator = ConfiguracionServicios.crearServicios();
			
			Session session = new Session(comunicador, locator);
			new Thread(session).start();
		}
	}
	
	public void stop() {
		ejecutando = false;
	}
}
