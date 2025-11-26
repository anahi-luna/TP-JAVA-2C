package parsers;

import java.util.Map;

import models.Carrito;
import models.Usuario;

public class Solicitud {
	private String recurso;     // ej: "articulos"
    private String accion;      // ej: "agregar", "editar", "eliminar", "listar", "buscar"
    private Map<String, String> parametros;
    
    private Usuario usuarioActivo;
    private Carrito carritoActivo;

	public Solicitud() {}
    
    public Solicitud(String recurso, String accion, Map<String, String> parametros) {
		super();
		this.recurso = recurso;
		this.accion = accion;
		this.parametros = parametros;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Map<String, String> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, String> parametros) {
		this.parametros = parametros;
	}
	public Usuario getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(Usuario usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	public Carrito getCarritoActivo() {
		return carritoActivo;
	}

	public void setCarritoActivo(Carrito carritoActivo) {
		this.carritoActivo = carritoActivo;
	}
    
    
    
}
