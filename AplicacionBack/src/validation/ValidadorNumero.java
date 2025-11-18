package validation;

import java.util.function.Predicate;

import exceptions.ValidadorException;

public class ValidadorNumero {
	private double valor;
	
	public static ValidadorNumero from(double valor) {
		return new ValidadorNumero(valor);
	}
	
	private ValidadorNumero(double valor) {
		this.valor=valor;
	}
	
	public ValidadorNumero validar(Predicate<Double> predicado, 
			String msjError) throws ValidadorException{
		if(!predicado.test(this.valor)) {
			throw new ValidadorException(msjError);
		}
		return this;
	}
	
	public ValidadorNumero positivo() throws ValidadorException{
		if(valor <=0) {
			throw new ValidadorException("El número debe ser mayor a 0");
		}
		return this;
	}
	
	public ValidadorNumero noNegativo() throws ValidadorException{
		if(valor < 0) {
			throw new ValidadorException("El número no puede ser negativo");
		}
		return this;
	}
}
