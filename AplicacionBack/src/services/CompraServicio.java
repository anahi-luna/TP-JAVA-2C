package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import container.CompraContenedor;
import interfaces.Servicio;
import models.Compra;
import parsers.Solicitud;

public class CompraServicio implements Servicio {

	private CompraContenedor compraContenedor;
	
	public CompraServicio(CompraContenedor compraContenedor) {
		this.compraContenedor = compraContenedor;
	}
	
	@Override
	public Object ejecutar(Solicitud solicitud) {
		String accion = solicitud.getAccion();
		Map<String, String> p = solicitud.getParametros();
		
		switch (accion.toLowerCase()){
			case "listar":
				return listarCompras(p);
			default:
				return "ERROR: acción no reconocida " + accion;
		}
	}
	
	private Object listarCompras(Map<String,String>p) {
		String username = p.get("usuario");
		List<Compra> lista = compraContenedor.getTodas();
		
		if (username == null)
            return lista;
		
		// devuelve sólo las del usuario solicitado
        List<Compra> filtradas = new ArrayList<>();
        for (Compra c : lista) {
            if (c.getUsername().equals(username)) {
                filtradas.add(c);
            }
        }
        return filtradas;
	}
	
}
