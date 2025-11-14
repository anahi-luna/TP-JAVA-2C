package models;
import java.time.LocalDate;

public class CompraVenta {
	
	private String id;
	private String usuarioCliente;
	private LocalDate fecha; 
	private double total;
	private DetalleCompra detalles; 
	
	public CompraVenta(String id, String usuarioCliente, LocalDate fecha, double total, DetalleCompra detalles) {
		super();
		this.id = id;
		this.usuarioCliente = usuarioCliente;
		this.fecha = fecha;
		this.total = total;
		this.detalles = detalles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(String usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public DetalleCompra getDetalles() {
		return detalles;
	}

	public void setDetalles(DetalleCompra detalles) {
		this.detalles = detalles;
	} 
	
	
	
	
}
