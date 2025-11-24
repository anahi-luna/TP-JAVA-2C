package container;

import java.util.List;

import models.Compra;

public class CompraContenedor extends Contenedor<Compra>{

	public CompraContenedor() {
        super();
    }
	
	@Override
	protected String extraerClave(Compra compra) {
		return compra.getId();
	}
	
	// helper: devolver copia de todas las compras
    public List<Compra> getTodas() {
        return getTodos();
    }
	
	
}
