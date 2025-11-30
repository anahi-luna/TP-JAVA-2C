package builders;

import server.ConfiguracionServicios;
import server.Server;
import utils.TiendaServiceLocator;

public class ServerBuilder {
	private int puerto = 5000;
	private TiendaServiceLocator locator;
	
	public ServerBuilder setPuerto(int puerto) {
        this.puerto = puerto;
        return this;
    }
	
	public ServerBuilder setServiceLocator(TiendaServiceLocator locator) {
        this.locator = locator;
        return this;
    }
	
	public Server build() {
		if(locator == null) 
			locator = ConfiguracionServicios.crearServicios();
		return new Server(puerto);
	}
}
