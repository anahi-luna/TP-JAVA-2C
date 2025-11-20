package validation;

public class UsuarioValidator {

    public static boolean esNombreUsuarioValido(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return false;
        }
        //4 a 20 caracteres alfanuméricos
        return nombreUsuario.matches("^[a-zA-Z0-9]{4,20}$");
    }

    
    public static boolean esContrasenaValida(String contrasena) {
        if (contrasena == null || contrasena.length() < 4) {
            return false; // Mínimo de 4 caracteres
        }
        return true;
    }

    /**
     * Valida que una cantidad de dinero sea positiva
     */
    public static boolean esMontoPositivo(double monto) {
        return monto > 0.0;
    }
}