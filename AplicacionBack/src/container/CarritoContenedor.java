package container;

import models.Carrito;


public class CarritoContenedor extends Contenedor<Carrito>{

	@Override
	protected String extraerClave(Carrito c) {
		return c.getId();
	}

}
	