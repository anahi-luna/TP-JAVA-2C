package models;

public class DetalleCompra {
	
	private String id;
	private int cantidad;
	private double subtotal; 
	//deberia de poner articulo(?
	
	public DetalleCompra(String id, int cantidad, double subtotal) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
	
	
	
	
}
