package validation;

import exceptions.ValidadorException;
import models.Carrito;
import models.Compra;

public class CompraValidador {

	public void validar (Compra compra) throws ValidadorException{
		
		//compra que no sea nula
		if (compra == null) {
			throw new ValidadorException ("La compra no puede ser nula");
		}
		
		//compra asociada con carrito
		if (compra.getCarrito() == null) {
			throw new ValidadorException ("La compra debe estar asociado a un carrito");
		}
		Carrito carrito = compra.getCarrito();
		
		//monto final no sea negativo
		if (compra.getMontoFinal() < 0) {
			throw new ValidadorException ("El monto final no debe ser negativo");
		}
		
		//q no este vacio el carrito al final de la compra
		if (carrito.getItems()== null || carrito.getItems().isEmpty()){// si item es null o esta vacio 
			throw new ValidadorException ("No se puede finalizar una compra sin articulos");
		}
		
	}
}
