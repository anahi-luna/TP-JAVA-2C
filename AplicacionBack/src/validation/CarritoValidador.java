package validation;

import exceptions.ValidadorException;
import models.Articulo;
import models.Carrito;
import models.CarritoItem;

public class CarritoValidador {
	
	// Validar carrito para operaciones (agregar/quitar/finalizar)
    public static void validarParaOperacion(Carrito carrito) throws ValidadorException {
        if (carrito == null) throw new ValidadorException("Carrito nulo");
        if (carrito.getId() == null || carrito.getId().isBlank())
            throw new ValidadorException("Carrito sin id");
        if (carrito.getUsername() == null || carrito.getUsername().isBlank())
            throw new ValidadorException("Carrito sin usuario asociado");
        if (carrito.getEstado() == null)
            throw new ValidadorException("Carrito sin estado");
        if (carrito.getEstado() != enums.EstadoDelCarrito.PENDIENTE)
            throw new ValidadorException("Carrito no en estado PENDIENTE");
    }

    // Validar item antes de agregarse (stock y cantidad se chequean en servicio también)
    public static void validarItem(CarritoItem item) throws ValidadorException {
        if (item == null) throw new ValidadorException("Item nulo");
        if (item.getArticuloCodigo() == null || item.getArticuloCodigo().isBlank())
            throw new ValidadorException("Item sin codigo de articulo");
        if (item.getCantidad() <= 0) throw new ValidadorException("Cantidad debe ser > 0");
        Articulo a = item.getArticulo();
        if (a != null && a.getStock() < item.getCantidad())
            throw new ValidadorException("Stock insuficiente para " + a.getCodigo());
    }
}
