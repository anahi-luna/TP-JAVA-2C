package services;

import container.UsuarioContenedor;
import models.Usuario;
import enums.UsuarioTipo;
import validation.UsuarioValidator;
import java.util.Map;

public class UsuarioService {
    private UsuarioContenedor contenedor;

    public UsuarioService() {
        this.contenedor = UsuarioContenedor.getInstancia();
    }

   
    /**
      Crea y agrega un nuevo usuario si es válido y no existe.
     */
    public Usuario crearUsuario(String nombreUsuario, String contrasena, UsuarioTipo tipo) {
        if (!UsuarioValidator.esNombreUsuarioValido(nombreUsuario) || 
            !UsuarioValidator.esContrasenaValida(contrasena)) 
        {
            
            System.err.println("Error: Nombre de usuario o contraseña no válidos.");
            return null;
        }

        if (contenedor.getUsuario(nombreUsuario) != null) {
            System.err.println("Error: El nombre de usuario ya existe.");
            return null;
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, tipo);
        contenedor.agregarUsuario(nuevoUsuario);
        return nuevoUsuario;
    }

 
    /**
      Valida si las credenciales coinciden con un usuario existente.
    */
    public Usuario validarCredenciales(String nombreUsuario, String contrasena) {
        Usuario usuario = contenedor.getUsuario(nombreUsuario);

        if (usuario == null) {
            return null;
        }

        
        if (usuario.getContrasena().equals(contrasena)) {
            return usuario;
        } else {
           
            return null;
        }
    }

   
    /**
     Agrega dinero al saldo de un usuario.
    
     */
    public boolean agregarSaldo(String nombreUsuario, double monto) {
        if (!UsuarioValidator.esMontoPositivo(monto)) {
            return false;
        }

        Usuario usuario = contenedor.getUsuario(nombreUsuario);
        if (usuario != null) {
            usuario.setSaldo(usuario.getSaldo() + monto);
            return true;
        }
        return false;
    }

    /**
     Transfiere dinero de un usuario a otro.
     */
    public int transferirSaldo(String origenNombre, String destinoNombre, double monto) {
        if (!UsuarioValidator.esMontoPositivo(monto)) {
            return 4;
        }

        Usuario origen = contenedor.getUsuario(origenNombre);
        Usuario destino = contenedor.getUsuario(destinoNombre);

        if (origen == null) {
            return 1; // Origen no encontrado
        }
        if (destino == null) {
            return 2; // Destino no encontrado
        }
        if (origen.getSaldo() < monto) {
            return 3; // Saldo insuficiente
        }

        // Realizar la transferencia
        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        return 0; // Éxito
    }

    public Map<String, Usuario> listarTodos() {
        return contenedor.listarTodos();
    }
}