package server;

import container.ArticuloContenedor;
import container.CompraContenedor;
import container.UsuarioContenedor;
import enums.UsuarioTipo;
import models.Usuario;
import services.ArticuloServicio;
import services.CarritoServicio;
import services.CompraServicio;
import services.UsuarioServicio;
import utils.TiendaServiceLocator;

public class ConfiguracionServicios {
	public static TiendaServiceLocator crearServicios() {
		// CONTENEDORES
		ArticuloContenedor artCont = new ArticuloContenedor();
        UsuarioContenedor userCont = new UsuarioContenedor();
        CompraContenedor compraCont = new CompraContenedor();
        
     // === PRECARGAR ADMIN ===
        Usuario admin = new Usuario("admin", "1234", UsuarioTipo.EMPLEADO);
        userCont.crear(admin);

        System.out.println(">>> Usuario admin precargado (admin / 1234)");
        
        // SERVICIOS
        ArticuloServicio artServ = new ArticuloServicio(artCont);
        UsuarioServicio userServ = new UsuarioServicio(userCont);
        CarritoServicio carritoServ = new CarritoServicio(artCont, userCont, compraCont);
        CompraServicio compraServ = new CompraServicio(compraCont);
        
        // LOCATOR
        TiendaServiceLocator locator = new TiendaServiceLocator();
        locator.registrar("articulo", artServ);
        locator.registrar("usuario", userServ);
        locator.registrar("carrito", carritoServ);
        locator.registrar("compra", compraServ);
        
        return locator;
	}
}
