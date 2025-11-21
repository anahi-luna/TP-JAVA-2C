package container;

import java.util.ArrayList;
import java.util.List;
import models.Compra;

public class CompraContenedor {

	private List<Compra> _listadoCompra;
	
	public CompraContenedor() {
		this._listadoCompra = new ArrayList<Compra>();
	}
	
	//agregamos compra
	public void agregarCompra(Compra compra) {
		_listadoCompra.add(compra);
	}

	//metodo para eliminar compra
	public void eliminarCompra (Compra compra) {
		_listadoCompra.remove(compra); 
	}
	
	//obtenemos copia del listado 
	public ArrayList<Compra> getCompras(){
		ArrayList<Compra> copia = new ArrayList<Compra> (_listadoCompra);
		return copia;
	}
	
	//buscar id la compra de un usuario
	public Compra buscarId (int id) {
		for (Compra compra : _listadoCompra) {
			if (compra.getId() == id) {
				return compra;
			}
		}
		return null; // en caso de no encontrar
	}
	
}
