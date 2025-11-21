package models;

public class CarritoItem {

	private int id; 
	private Articulo articulo; //cada carrito, tiene varios articulo==carritoItem
	private int cantidad; 
	
	public CarritoItem(int id, Articulo articulo, int cantidad) {
		this.id = id;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}
	
	public double getSubtotal() {
		return cantidad * articulo.getPrecio();
	}
	
	
	
	
}
