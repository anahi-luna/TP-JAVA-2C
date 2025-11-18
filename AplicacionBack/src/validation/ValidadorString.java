package validation;

import java.util.function.Predicate;

import exceptions.ValidadorException;

public class ValidadorString {
	
	public static ValidadorString from(String valor) {
		return new ValidadorString(valor);
	}
	
	private String valor;
	
	public ValidadorString(String valor) {
		this.valor = valor;
	}
	
	public ValidadorString validar(Predicate<String> predicado, String msjError)
		throws ValidadorException {
			if(!predicado.test(this.valor)) {
				throw new ValidadorException(msjError);
			}
		return this;
	}
	
	public ValidadorString validarLongitud(int min, int max)
			throws ValidadorException  {
		if(this.valor.length() < min) {
			throw new ValidadorException(
				"La longitud debe ser mayor a " + min +" caracteres");
		}
		if(this.valor.length() > max) {
			throw new ValidadorException(
					"La longitud no debe superar los " + max + " caracteres");
		}
		return this;
	}
	
	public ValidadorString noVacio() throws ValidadorException{
		if(this.valor == null || this.valor.trim().isEmpty()) {
			throw new ValidadorException("El valor no puede estar vacío");
		}
		return this;
	}
}
