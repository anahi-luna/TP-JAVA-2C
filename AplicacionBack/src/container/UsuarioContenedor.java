package container;
import modelo.Usuario;
import modelo.UsuarioTipo;
import java.util.HashMap;
import java.util.Map;

public class UsuarioContenedor {

	public class UsuarioContenedor {
	    private static UsuarioContenedor instancia;
	    private Map<String, Usuario> usuarios; 
	    private UsuarioContenedor() {
	        this.usuarios = new HashMap<>();
	        // Agregar algunos datos iniciales para prueba
	        agregarUsuario(new Usuario("admin", "1234", UsuarioTipo.EMPLEADO));
	        agregarUsuario(new Usuario("clienteA", "passA", UsuarioTipo.CLIENTE));
	        agregarUsuario(new Usuario("clienteB", "passB", UsuarioTipo.CLIENTE));
	    }

	    public static UsuarioContenedor getInstancia() {
	        if (instancia == null) {
	            instancia = new UsuarioContenedor();
	        }
	        return instancia;
	    }

	    // Métodos del Contenedor
	   
	    public boolean agregarUsuario(Usuario usuario) {
	        if (usuarios.containsKey(usuario.getNombreUsuario())) {
	            return false;
	        }
	        usuarios.put(usuario.getNombreUsuario(), usuario);
	        return true;
	    }

	    
	    public Usuario getUsuario(String nombreUsuario) {
	        return usuarios.get(nombreUsuario);
	    }

	   
	    public Map<String, Usuario> listarTodos() {
	        return usuarios;
	    }

	}
}

