package main;

import builders.ServerBuilder;
import server.ConfiguracionServicios;
import server.Server;

public class MainServer {

	public static void main(String[] args) throws Exception{
		Server server = new ServerBuilder()
				.setPuerto(5000)
				.setServiceLocator(ConfiguracionServicios.crearServicios())
				.build();
		
		server.start();
	}

}
