package validation;

import exceptions.ValidadorException;

import models.Compra;
import models.CompraItem;
import models.Usuario;

public class CompraValidador {

	public static void validarAntesCompra(Compra compra, Usuario usuario) throws ValidadorException {
        if (compra == null) throw new ValidadorException("Compra nula");
        if (compra.getItems() == null || compra.getItems().isEmpty())
            throw new ValidadorException("No hay items en la compra");
        if (compra.getTotal() <= 0) throw new ValidadorException("Monto total inválido");
        if (usuario == null) throw new ValidadorException("Usuario inválido");

        if (usuario.getSaldo() < compra.getTotal())
            throw new ValidadorException("Saldo insuficiente");

        for (CompraItem it : compra.getItems()) {
            if (it.getCantidad() <= 0) throw new ValidadorException("Cantidad inválida en item");
            if (it.getPrecioUnitario() <= 0) throw new ValidadorException("Precio unitario inválido");
            
        }
    }
}
