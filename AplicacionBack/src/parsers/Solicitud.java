package parsers;

import java.util.Map;

public class Solicitud {
	private String recurso;     // ej: "articulos"
    private String accion;      // ej: "agregar", "editar", "eliminar", "listar", "buscar"
    private Map<String, String> parametros;
	
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
    
    
    
    
}
