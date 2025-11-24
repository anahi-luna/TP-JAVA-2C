package container;

import models.Usuario;

public class UsuarioContenedor extends Contenedor<Usuario>{

	public UsuarioContenedor() {
		super();
	}
	
	@Override
	protected String extraerClave(Usuario usuario) {
		if(usuario == null) return null;
		return usuario.getNombreUsuario();
	}
	
	
    /*private static UsuarioContenedor instancia;
    private List<Usuario> usuarios;

    private UsuarioContenedor() {
        this.usuarios = new ArrayList<>();
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

    public boolean agregarUsuario(Usuario usuario) {
        if (getUsuario(usuario.getNombreUsuario()) != null) {
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    public Usuario getUsuario(String nombreUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }*/
}