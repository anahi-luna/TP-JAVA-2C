package validation;

import exceptions.ValidadorException;
import models.Articulo;

public class ArticuloValidador {
	public static void validar(Articulo art) throws ValidadorException{
		if(art == null) {
			throw new ValidadorException("El artículo no puede ser null");
		}
		
		ValidadorString.from(art.getCodigo())
			.validar(s -> !s.trim().isEmpty(),"El código no puede estar vacío")
			.validarLongitud(1, 20);
		
		ValidadorString.from(art.getDescripcion())
			.validar(s -> !s.trim().isEmpty(), "La descripción no puede estar vacía")
			.validarLongitud(3, 100);
		
		ValidadorNumero.from(art.getPrecio())
			.positivo();
		
		ValidadorNumero.from(art.getStock())
			.noNegativo();
	}
}
