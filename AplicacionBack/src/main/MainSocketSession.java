package main;

import java.net.ServerSocket;
import java.net.Socket;

import comunicacion.SocketComunicador;
import interfaces.Comunicador;
import server.ConfiguracionServicios;
import server.Session;
import utils.TiendaServiceLocator;

public class MainSocketSession {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(5000);
        System.out.println("Servidor escuchando en puerto 5000...");
        
        while (true) {

            Socket socket = server.accept();
            System.out.println("Cliente conectado!");

            // Crear servicios NUEVOS para cada sesión
            TiendaServiceLocator locator = ConfiguracionServicios.crearServicios();

            // Crear comunicador por socket
            Comunicador comunicador = new SocketComunicador(socket);

            // Crear y ejecutar la sesión
            Session session = new Session(comunicador, locator);
            new Thread(session).start();
        }
	}

}
