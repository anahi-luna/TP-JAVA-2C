package container;

import java.util.ArrayList;
import java.util.List;
import models.Carrito;
import models.CarritoItem;

public class CarritoContenedor {

	private List<CarritoItem> _listadoCarritoItem;
	
	public CarritoContenedor() {
		this._listadoCarritoItem = new ArrayList<CarritoItem>();
	}
	
	//agregamos ITEM
	public void agregarItem(CarritoItem item) {
		_listadoCarritoItem.add(item);
	}
	
	//meotodo eliminar item
	public void eliminarItem (CarritoItem item) {
		_listadoCarritoItem.remove(item);
	}
	
	// mostrar copia del listado
	public ArrayList<CarritoItem> getItems(){
		ArrayList<CarritoItem> copia = new ArrayList<CarritoItem> (_listadoCarritoItem);
		return copia; 
	}
	
	//buscar por id
	public CarritoItem buscarId(int id) {
		for (CarritoItem item : _listadoCarritoItem) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
}
	