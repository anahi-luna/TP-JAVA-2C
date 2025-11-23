package validation;

import exceptions.ValidadorException;
import models.Carrito;
import models.CarritoItem;

public class CarritoValidador {
	
	public void validar (Carrito carrito) throws ValidadorException {
		
		//carrito que no sea nulo
		if (carrito == null) {
			throw new ValidadorException ("El carrito no puede ser nulo"); }
		
		//debe estar asociado a un usuario
		if (carrito.getUsuario() == null) {
			throw new ValidadorException ("El carrito debe asociarse a un usuario");
		}
		
		//que la cantidad de items no sea menos 
		for (CarritoItem item : carrito.getItems()) {
			if (item.getCantidad() <= 0) {
				throw new ValidadorException ("Hay un item con cantidad invalido: "+ item.getCantidad());
			}
		}
		
	}
}
