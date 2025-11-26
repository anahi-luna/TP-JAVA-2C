package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import container.ArticuloContenedor;
import container.CompraContenedor;
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

	private ArticuloContenedor articuloContenedor;
	private CompraContenedor compraContenedor;
	
	//sesion
	private Usuario usuarioActivo;
	private Carrito carritoActivo;
	
	public CarritoServicio(ArticuloContenedor a, CompraContenedor c) {
		this.articuloContenedor = a;
		this.compraContenedor = c;
	}
	
	// Inyección desde Session
	public void setSesion(Usuario usuario, Carrito carrito) {
		this.usuarioActivo= usuario;
		this.carritoActivo = carrito;
	}
	
	@Override
	public Object ejecutar(Solicitud solicitud) {
		
		String accion = solicitud.getAccion().toLowerCase();
		Map<String, String> p = solicitud.getParametros();
		
		try {
			return switch (accion) {
			case "agregar" -> agregarItem(p);
			case "quitar" ->quitarItem(p);
			case "ver" ->verCarrito();
			case "vaciar" ->vaciarCarrito();
			case "finalizar" ->finalizarCompra();
			case "listarcompras" -> listarCompras();
			default -> "ERROR: acción desconocida " + accion;
			};
		} catch (ValidadorException ve) {
            return "ERROR_VALIDACION: " + ve.getMessage();
        } catch (RuntimeException re) {
            return "ERROR: " + re.getMessage();
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
	}

	
	//  MÉTODOS PRIVADOS DE ACCIÓN
	private Object agregarItem(Map<String,String> p)throws Exception{
		
        String codigo = p.get("codigo");
        String cantStr = p.get("cantidad");
        
        if (codigo == null || cantStr == null)
        return "ERROR: faltan parámetros";
        
        int cantidad = Integer.parseInt(cantStr);

        CarritoValidador.validarParaOperacion(carritoActivo);
        
        Articulo art = articuloContenedor.buscar(codigo);
        if (art == null) return "ERROR: artículo no existe";
        
        if (art.getStock() < cantidad)
            return "ERROR: stock insuficiente";
        
        // Buscar si ya existe el item en el carrito
        Optional<CarritoItem> existente = carritoActivo.getItems().stream()
        			.filter(i -> i.getArticuloCodigo().equals(codigo))
        			.findFirst();
        
        if(existente.isPresent()) {
        	existente.get().setCantidad(existente.get().getCantidad() + cantidad);
        }else {
        	carritoActivo.getItems().add(new CarritoItem(codigo, art, cantidad));
        }
        
        return "OK: item agregado. Total = " + carritoActivo.getMontoFinal();
        
	}
	
	private Object quitarItem(Map<String,String> p) throws Exception{
		
        String codigo = p.get("codigo");
        if (codigo == null)
            return "ERROR: falta código";
        
        boolean removed = carritoActivo.getItems()
        		.removeIf(i -> i.getArticuloCodigo().equals(codigo));
        
        return removed ? "OK: item quitado" : "ERROR: no estaba en el carrito";
	}
	
	private Object vaciarCarrito(){
        carritoActivo.getItems().clear();
        
        return "OK: carrito vaciado";
	}
	
	private Object verCarrito(){
		return carritoActivo;
	}
	
	// FINALIZAR COMPRA
	private Object finalizarCompra()throws Exception{

		if (carritoActivo.getItems().isEmpty())
            return "ERROR: carrito vacío";
		
		List<CompraItem> lista = new ArrayList<>();
        
        for(CarritoItem ci: carritoActivo.getItems()) {
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
        Compra compra = new Compra(compraId,
        		usuarioActivo.getNombreUsuario(),
        		LocalDateTime.now(), lista);
        
        CompraValidador.validarAntesCompra(compra, usuarioActivo);
        
        // Descontar stock
        for (CarritoItem ci : carritoActivo.getItems()) {
            Articulo art = ci.getArticulo();
            art.setStock(art.getStock() - ci.getCantidad());
        }
        
        // Descontar saldo del usuario
        usuarioActivo.setSaldo(usuarioActivo.getSaldo() - compra.getTotal());
        
        // Guardar compra
        compraContenedor.crear(compra);
        
        // Cerrar carrito
        carritoActivo.setEstado(EstadoDelCarrito.FINALIZADO);
        
        carritoActivo = new Carrito("CARR-" + usuarioActivo.getNombreUsuario(),
                usuarioActivo.getNombreUsuario());
        
        return "OK: compra finalizada. ID = " + compra.getId() +
                " | total = " + compra.getTotal();
	}
	
	private Object listarCompras() {
		return compraContenedor.getTodas().stream()
				.filter(c -> c.getUsername().equals(usuarioActivo.getNombreUsuario()))
				.toList();
	}

}
