package main;

import comunicacion.ConsoleComunicador;
import interfaces.Comunicador;
import server.ConfiguracionServicios;
import server.Session;
import utils.TiendaServiceLocator;

public class MainConsolaSession {
	
	public static void main(String[] args) {
		// Crear todos los servicios
        TiendaServiceLocator locator = ConfiguracionServicios.crearServicios();

        // Comunicador de consola
        Comunicador comunicador = new ConsoleComunicador();

        // Crear la sesión
        Session session = new Session(comunicador, locator);

        // Ejecutar la sesión en un hilo
        Thread hilo = new Thread(session);
        hilo.start();
	}
}
