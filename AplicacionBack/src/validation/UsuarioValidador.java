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
	
	
    /*public static boolean esNombreUsuarioValido(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return false;
        }
        //4 a 20 caracteres alfanum�ricos
        return nombreUsuario.matches("^[a-zA-Z0-9]{4,20}$");
    }

    
    public static boolean esContrasenaValida(String contrasena) {
        if (contrasena == null || contrasena.length() < 4) {
            return false; // M�nimo de 4 caracteres
        }
        return true;
    }

     // Valida que una cantidad de dinero sea positiva

    public static boolean esMontoPositivo(double monto) {
        return monto > 0.0;
    }*/
}