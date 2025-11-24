package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import container.ArticuloContenedor;
import container.CompraContenedor;
import container.UsuarioContenedor;
import enums.EstadoDelCarrito;
import exceptions.ValidadorException;
import interfaces.Servicio;
import models.Articulo;
import models.Carrito;
import models.CarritoItem;
import models.Compra;
import models.CompraItem;
import models.Usuario;
import parsers.Solicitud;
import validation.CarritoValidador;
import validation.CompraValidador;

public class CarritoServicio implements Servicio {

	//Carritos activos por username
	private Map<String, Carrito> carritosActivos = new HashMap<>();
	
	private ArticuloContenedor articuloContenedor;
	private UsuarioContenedor usuarioContenedor;
	private CompraContenedor compraContenedor;
	
	public CarritoServicio(ArticuloContenedor a, UsuarioContenedor u, CompraContenedor c) {
		this.articuloContenedor = a;
		this.usuarioContenedor =u;
		this.compraContenedor = c;
	}
	
	@Override
	public Object ejecutar(Solicitud solicitud) {
		String accion = solicitud.getAccion();
		Map<String, String> p = solicitud.getParametros();
		
		return ejecutarInterno(accion, p);
	}
	
	public Object ejecutarInterno(String accion,Map<String,String> p) {
		try {
			switch (accion.toLowerCase()){
				case "crear":
					return crearCarrito(p);
				
				case "agregar":
					return agregarItem(p);
				
				case "quitar":
					return quitarItem(p);
					
				case "vaciar":
					return vaciarCarrito(p);
					
				case "ver":
					return verCarrito(p);
				
				case "finalizar":
					return finalizarCompra(p);
				
				case "listarcompras":
					return listarCompras(p);
				default:
					return "ERROR: acción desconocida " + accion;
			}
		} catch (ValidadorException ve) {
            return "ERROR_VALIDACION: " + ve.getMessage();
        } catch (RuntimeException re) {
            return "ERROR: " + re.getMessage();
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
	}
	
	//  MÉTODOS PRIVADOS DE ACCIÓN
	private Object crearCarrito(Map<String,String> p) throws Exception{
		String username = p.get("username");
		if (username == null) return "ERROR: falta username";
		
		Usuario u = usuarioContenedor.buscar(username);
        if (u == null) return "ERROR: usuario no existe";
        
        // Si ya tiene carrito activo, devolverlo
        if (carritosActivos.containsKey(username))
            return "Carrito ya existente: " + carritosActivos.get(username);
        
        Carrito c = new Carrito("CARR-" + username, username);
        carritosActivos.put(username, c);
        
        return "OK: carrito creado -> " + c;
	}
	
	private Object agregarItem(Map<String,String> p)throws Exception{
		String username = p.get("username");
        String codigo = p.get("codigo");
        String cantStr = p.get("cantidad");
        
        if (username == null || codigo == null || cantStr == null)
            return "ERROR: faltan parámetros";
        
        int cantidad = Integer.parseInt(cantStr);

        Usuario u = usuarioContenedor.buscar(username);
        if (u == null) return "ERROR: usuario no existe";
        
        Carrito carrito = carritosActivos.get(username);
        if (carrito == null) return "ERROR: primero debe crear el carrito";
        
        CarritoValidador.validarParaOperacion(carrito);
        
        Articulo art = articuloContenedor.buscar(codigo);
        if (art == null) return "ERROR: artículo no existe";

        if (art.getStock() < cantidad)
            return "ERROR: stock insuficiente";
        
        // Buscar si ya existe el item en el carrito
        Optional<CarritoItem> existente = carrito.getItems().stream()
        			.filter(i -> i.getArticuloCodigo().equals(codigo))
        			.findFirst();
        
        if(existente.isPresent()) {
        	existente.get().setCantidad(existente.get().getCantidad() + cantidad);
        }else {
        	CarritoItem nuevo = new CarritoItem(codigo, art, cantidad);
        	carrito.getItems().add(nuevo);
        }
        
        return "OK: item agregado. Total carrito = " + carrito.getMontoFinal();
        
	}
	
	private Object quitarItem(Map<String,String> p) throws Exception{
		String username = p.get("username");
        String codigo = p.get("codigo");

        if (username == null || codigo == null)
            return "ERROR: faltan parámetros";
        
        Carrito carrito = carritosActivos.get(username);
        if (carrito == null) return "ERROR: el usuario no tiene carrito";
        
        boolean removed = carrito.getItems().removeIf(i-> i.getArticuloCodigo().equals(codigo));
        if (!removed) return "ERROR: ese artículo no está en el carrito";

        return "OK: item quitado. Total = " + carrito.getMontoFinal();
	}
	
	private Object vaciarCarrito(Map<String, String>p) throws Exception{
		String username = p.get("username");
        if (username == null) return "ERROR: falta username";
        
        Carrito carrito = carritosActivos.get(username);
        if (carrito == null) return "ERROR: carrito no existe";
        
        carrito.getItems().clear();
        
        return "OK: carrito vaciado";
	}
	
	private Object verCarrito(Map<String,String>p)throws Exception{
		String username = p.get("username");
        if (username == null) return "ERROR: falta username";

        Carrito carrito = carritosActivos.get(username);
        if (carrito == null) return "ERROR: no hay carrito";

        return carrito;
	}
	
	// FINALIZAR COMPRA
	private Object finalizarCompra(Map<String,String>p)throws Exception{
		String username = p.get("username");
        if (username == null) return "ERROR: falta username";

        Usuario u = usuarioContenedor.buscar(username);
        if (u == null) return "ERROR: usuario no existe";

        Carrito carrito = carritosActivos.get(username);
        if (carrito == null) return "ERROR: no tiene carrito";
        
        CarritoValidador.validarParaOperacion(carrito);
        
        if (carrito.getItems().isEmpty())
            return "ERROR: carrito vacío";
        
        // Crear CompraItem desde los CarritoItem
        List<CompraItem> lista = new ArrayList<>();
        for(CarritoItem ci: carrito.getItems()) {
        	Articulo art = ci.getArticulo();
            if (art.getStock() < ci.getCantidad())
                return "ERROR: stock insuficiente en " + art.getCodigo();
            
            CompraItem item = new CompraItem(
                    art.getCodigo(),
                    art.getDescripcion(),
                    ci.getCantidad(),
                    art.getPrecio()
                );
            lista.add(item);
        }
        
        // Crear compra
        String compraId = "CMP-" + System.currentTimeMillis();
        Compra compra = new Compra(compraId, username, LocalDateTime.now(), lista);
        
        CompraValidador.validarAntesCompra(compra, u);
        
        // Descontar stock
        for (CarritoItem ci : carrito.getItems()) {
            Articulo art = ci.getArticulo();
            art.setStock(art.getStock() - ci.getCantidad());
        }
        
        // Descontar saldo del usuario
        u.setSaldo(u.getSaldo() - compra.getTotal());
        
        // Guardar compra
        compraContenedor.crear(compra);
        
        // Cerrar carrito
        carrito.setEstado(EstadoDelCarrito.FINALIZADO);
        carritosActivos.remove(username);
        
        return "OK: compra finalizada. ID = " + compra.getId() +
                " | total = " + compra.getTotal();
	}
	
	private Object listarCompras(Map<String,String>p) {
		String username = p.get("username");
        if (username == null)
            return "ERROR: falta username";
        
        List<Compra> todas = compraContenedor.getTodas();
        
        List<Compra> filtradas = new ArrayList<>();
        for(Compra c : todas) {
        	if(c.getUsername().equals(username))
        		filtradas.add(c);
        }
        
        return filtradas;
	}

}
