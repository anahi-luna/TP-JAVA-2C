package services;

import java.util.List;
import java.util.Map;

import container.ArticuloContenedor;
import enums.UsuarioTipo;
import exceptions.ValidadorException;
import interfaces.Servicio;
import models.Articulo;
import models.Usuario;
import parsers.Solicitud;
import validation.ArticuloValidador;

public class ArticuloServicio implements Servicio{

	private final ArticuloContenedor contenedor;
	
	public ArticuloServicio(ArticuloContenedor contenedor) {
		this.contenedor= contenedor;
	}
	
	@Override
	public Object ejecutar(Solicitud solicitud) {
		Usuario usuarioActivo = solicitud.getUsuarioActivo();
		String accion = solicitud.getAccion() == null ?"": solicitud.getAccion().toLowerCase();
		Map<String, String> param = solicitud.getParametros();
		
		// Validación global para todo el módulo
        if (usuarioActivo == null || usuarioActivo.getTipo() != UsuarioTipo.EMPLEADO)
            return "ERROR: solo EMPLEADO puede usar el módulo de artículos"; 
		try {
			switch (accion) {
			case "crear":	
				Articulo aNuevo = crearArticuloDesdeParams(param);
				ArticuloValidador.validar(aNuevo);
				contenedor.crear(aNuevo);
				return "Ok: articulo agregado "+ aNuevo.getCodigo();
			case "editar":
				Articulo aEdit = crearArticuloDesdeParams(param);
                ArticuloValidador.validar(aEdit);
                boolean ed = contenedor.editar(aEdit);
                return ed ? "OK: artículo editado" : "ERROR: artículo no encontrado";
			case "eliminar":
				String codigoEliminar = param.get("codigo");
				boolean delete = contenedor.eliminar(codigoEliminar);
				return delete? "OK: artículo eliminado" : "ERROR: artículo no encontrado";
			case "buscar":
                String codigoBuscar = param.get("codigo");
                Articulo found = contenedor.buscar(codigoBuscar);
                return found == null ? ("ERROR: no encontrado " + codigoBuscar) : found;
			case "listar":
                List<Articulo> todos = contenedor.getTodos();
                return todos;
			case "actualizarstock":
                String cod = param.get("codigo"); //CORREGIR STOCK 
                String stockStr = param.get("stock");
                Articulo art = contenedor.buscar(cod);
                if (art == null) return "ERROR: artículo no encontrado";
                try {
                    int stock = Integer.parseInt(stockStr);
                    art.setStock(stock);
                    return "OK: stock actualizado";
                } catch (NumberFormatException nfe) {
                    return "ERROR: stock inválido";
                }
			default:
				return "ERROR: acción desconocida: " + accion;
			}
		} catch (ValidadorException ve) {
            return "ERROR_VALIDACION: " + ve.getMessage();
        } catch (RuntimeException re) {
            return "ERROR_RUNTIME: " + re.getMessage();
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
	}
	
	private Articulo crearArticuloDesdeParams(Map<String, String> p ) {
		String codigo = p.get("codigo");
		String descripcion = p.getOrDefault("descripcion", "");
		double precio = 0.0;
		int stock=0;
		
		try {
			if(p.get("precio") != null) precio = Double.parseDouble(p.get("precio"));
		} catch (NumberFormatException e) {/* TODO: handle exception */}
		
		try {
			if (p.get("stock") != null) stock = Integer.parseInt(p.get("stock"));
		} catch (NumberFormatException e) { /* queda 0 */ }
		
		return new Articulo(codigo, descripcion, precio, stock);
	}
	
}
