package container;

import models.Articulo;

public class ArticuloContenedor extends Contenedor<Articulo>{

	@Override
	protected String extraerClave(Articulo art) {
		
		return art.getCodigo();
	}

}
