package validation;

import exceptions.ValidadorException;
import models.Usuario;

public class UsuarioValidador {

	public static void validarUsuarioParaAlta(Usuario u) throws ValidadorException{
		if(u == null) throw new ValidadorException("Usuario no puede ser null");
		
		ValidadorString.from(u.getNombreUsuario())
			.noVacio()
			.validarLongitud(4, 20);
		
		ValidadorString.from(u.getContrasena())
			.noVacio()
			.validarLongitud(4, 50);
		
		if(u.getTipo() == null) {
			throw new ValidadorException("Tipo de usuario no puede ser null");
		}
		
		ValidadorNumero.from(u.getSaldo())
			.noNegativo();
	}
	
	public static void validarCredenciales(String usuario, String contrasenia) throws ValidadorException{
		ValidadorString.from(usuario).noVacio();
		
		ValidadorString.from(contrasenia).noVacio();
	}
	
}